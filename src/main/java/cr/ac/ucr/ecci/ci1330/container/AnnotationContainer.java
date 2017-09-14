package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.annotation.*;
import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;
import cr.ac.ucr.ecci.ci1330.parser.AnnotationParser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

public class AnnotationContainer extends AbstractContainer{

    private AnnotationParser parser;

    public AnnotationContainer(String path){
        super();
        this.parser = new AnnotationParser(path);
    }

    private void fillBeanInfo(Bean bean){
        try {
            Class beanClass = Class.forName(bean.getClassName());
            String scopeValue = "SINGLETON";
            //Revisa si hay Id, si lo hay, lo pone en el bean y lo agrega al mapa en caso de que no exista
            if(beanClass.isAnnotationPresent(Id.class)){
                Id beanId = (Id) beanClass.getAnnotation(Id.class);
                if(super.beansById.get(beanId.value()) == null) {
                    bean.setId(beanId.value());
                    super.beansById.put(beanId.value(), bean);
                }else{
                    throw new IllegalArgumentException();
                }
            }
            //Lo mismo para el Scope
            if(beanClass.isAnnotationPresent(Scope.class)){
                Scope beanScope = (Scope) beanClass.getAnnotation(Scope.class);
                scopeValue = beanScope.value().toUpperCase();
            }
            bean.setScope(cr.ac.ucr.ecci.ci1330.enums.Scope.valueOf(scopeValue));

            this.setMethodName(bean, beanClass, PostConstruct.class, true);
            this.setMethodName(bean, beanClass, PreDestroy.class, false);
            this.setDependenciesInfo(bean, beanClass);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setMethodName(Bean bean, Class beanClass, Class<? extends Annotation> annotationClass, boolean isInitMethod){
        Method[] classMethods = beanClass.getDeclaredMethods();
        for(int i = 0; i < classMethods.length; i++){
            if(classMethods[i].isAnnotationPresent(annotationClass)){
                if(isInitMethod) {
                    bean.setInitMethod(classMethods[i].getName());
                }else {
                    bean.setDestroyMethod(classMethods[i].getName());
                }
            }
        }
    }

    public Object getBeanById(String id) {
        return null;
    }

    public Object getBeanByType(String className) {
        return null;
    }

    @Override
    public void startInjection() {
        this.parser.parseFile(super.beansById, super.beansByType);
        Iterator<Map.Entry<String, Bean>> iterator = super.beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Bean> currEntry = iterator.next();
            this.fillBeanInfo(currEntry.getValue());
        }
    }

    private void setDependenciesInfo(Bean bean, Class beanClass){
        Constructor[] constructors = beanClass.getConstructors();
        for(int i = 0; i < constructors.length; i++){
            if(constructors[i].isAnnotationPresent(Autowired.class)){
                Class[] paramClasses = constructors[i].getParameterTypes();
                Dependency newDependency;
                for(int j = 0; j < paramClasses.length; j++){
                    newDependency = new Dependency();
                    newDependency.setDependencyClassName(paramClasses[j].getName());
                    bean.addDependency(newDependency);
                }
            }
        }

        Method[] methods = beanClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if(methods[i].isAnnotationPresent(Autowired.class) && methods[i].getParameterTypes().length == 1){
                Class[] paramClasses = methods[i].getParameterTypes();
                Dependency newDependency;
                for(int j = 0; j < paramClasses.length; j++){
                    newDependency = new Dependency();
                    newDependency.setMethodName(methods[i].getName());
                    newDependency.setDependencyClassName(paramClasses[j].getName());
                    bean.addDependency(newDependency);
                }
            }
        }
    }

    @Override
    protected void insertDependencies(Bean bean) {
    }

    @Override
    protected Object createBean(Bean bean) {
        return null;
    }

    @Override
    protected void initializeBeans() {
    }
}
