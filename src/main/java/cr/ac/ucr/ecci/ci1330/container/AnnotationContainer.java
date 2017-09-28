package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.annotation.*;
import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;
import cr.ac.ucr.ecci.ci1330.enums.Injection;
import cr.ac.ucr.ecci.ci1330.parser.AnnotationParser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/*
    Universidad de Costa Rica
     Facultad de ingeniería
     Escuela de Ciencias de la Computación e Informática
     Ingeniería de Software 1
     Autores:
     Brenes Solano Silvia B41133
     Cubero Sánchez Josué B42190
     Durán Gregory Ian B42322
 */

public class AnnotationContainer extends XMLContainer{

    private AnnotationParser parser;

    public AnnotationContainer(String path){
        super(path);
        this.parser = new AnnotationParser(path);
        this.parser.parseFile(super.beansById, super.beansByType);
        this.startInjection();
    }

    @Override
    public void startInjection() {
        Iterator<Map.Entry<String, Bean>> iterator = super.beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Bean> currEntry = iterator.next();
            this.fillBeanInfo(currEntry.getValue());
        }
        super.startInjection();
    }

    /**
     * Fill bean attributes.
     * @param bean the bean to be filled.
     */
    private void fillBeanInfo(Bean bean){
        try {
            Class beanClass = Class.forName(bean.getClassName());
            if(beanClass.getName().equals("cr.ac.ucr.ecci.ci1330.model.Classroom")){
                System.out.println();
            }
            cr.ac.ucr.ecci.ci1330.enums.Scope scopeValue = cr.ac.ucr.ecci.ci1330.enums.Scope.SINGLETON; //aca no puede hacer de una vez la vara de tipo enum?
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
                scopeValue = cr.ac.ucr.ecci.ci1330.enums.Scope.valueOf(beanScope.value().toUpperCase());
            }
            bean.setScope(scopeValue);
            bean.setInjection(Injection.SETTER);
            this.setMethodName(bean, beanClass, PostConstruct.class, true);
            this.setMethodName(bean, beanClass, PreDestroy.class, false);
            this.setDependenciesInfo(bean, beanClass);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Set init or destroy method.
     * @param bean the bean to be setted.
     * @param beanClass the bean class.
     * @param annotationClass the annotation class.
     * @param isInitMethod a boolean to identify if its init or destroy method.
     */
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

    /**
     * Fill dependencies information.
     * @param bean bean which contains the dependencies.
     * @param beanClass the class of the bean.
     */
    private void setDependenciesInfo(Bean bean, Class beanClass){
        Constructor[] constructors = beanClass.getConstructors();
        for(int i = 0; i < constructors.length; i++){
            if(constructors[i].isAnnotationPresent(Autowired.class)){
                bean.setInjection(Injection.CONSTRUCTOR);
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
                Autowired autowired = methods[i].getAnnotation(Autowired.class);
                Class[] paramClasses = methods[i].getParameterTypes();
                Dependency newDependency;
                for(int j = 0; j < paramClasses.length; j++){
                    newDependency = new Dependency();
                    if(!autowired.id().equals("")){
                        newDependency.setDependencyId(autowired.id());
                        newDependency.setAutowired(cr.ac.ucr.ecci.ci1330.enums.Autowired.NAME);
                    }else{
                        newDependency.setAutowired(cr.ac.ucr.ecci.ci1330.enums.Autowired.TYPE);
                    }
                    newDependency.setMethodName(methods[i].getName());
                    newDependency.setDependencyClassName(paramClasses[j].getName());
                    bean.addDependency(newDependency);
                }
            }
        }
    }

}
