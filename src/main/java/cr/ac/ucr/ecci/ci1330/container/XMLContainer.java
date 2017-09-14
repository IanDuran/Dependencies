package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;
import cr.ac.ucr.ecci.ci1330.enums.Autowired;
import cr.ac.ucr.ecci.ci1330.enums.Injection;
import cr.ac.ucr.ecci.ci1330.enums.Scope;
import cr.ac.ucr.ecci.ci1330.parser.XMLParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;


public class XMLContainer extends AbstractContainer {

    private XMLParser parser;

    public XMLContainer(String path) {
        super();
        this.parser = new XMLParser(path);
        this.parser.parseFile(this.beansById,this.beansByType);
    }

    /**
     * Gets a bean from an id.
     * @param id the bean's id.
     * @return the bean with that id.
     */
    public Object getBeanById(String id) {
        Bean bean = this.beansById.get(id);
        Object instance = null;
        if (bean != null) { //si el bean existe
            if (bean.getScope().equals(Scope.SINGLETON)) {
                instance = bean.getInstance();
            } else { //si es prototype
                instance = this.createBean(bean);
            }
        } //sino devuelve la instancia como null
        return instance;
    }

    /**
     * Gets a bean from a type.
     * @param className the bean's type.
     * @return the bean with that type.
     */
    public Object getBeanByType(String className) {
        Bean bean = this.beansByType.get(className);
        Object instance = null;
        if (bean != null) {
            if (bean.getScope().equals(Scope.SINGLETON)) {
                instance = bean.getInstance();
            } else {
                instance = this.createBean(bean);
            }
        }
        return instance;
    }

    /**
     * Create a bean with all dependencies injected.
     * @param bean the bean to be injected.
     * @return the injected bean.
     */
    @SuppressWarnings("unchecked")
    public Object createBean(Bean bean) {
        Class currClass;
        Object instance = null;

        try {
            currClass = Class.forName(bean.getClassName());
            Constructor constructor = currClass.getConstructor();
            instance = constructor.newInstance();
            this.insertDependencies(bean); //se envia el bean con la nueva instancia creada para que le inserte las dependencias.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public void startInjection(){
        this.initializeBeans();
        Iterator<Map.Entry<String, Bean>> iterator = super.beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            this.insertDependencies(iterator.next().getValue());
        }
    }

    @Override
    public void insertDependencies(Bean bean) {
        if(bean.getInjection().equals(Injection.SETTER)) {
            for (Dependency dependency : bean.getDependencies()) {
                if (dependency.getAutowired().equals(Autowired.NAME)) {  //aca lo cambie, es autowired by name
                    this.insertSetterDependencies(bean, super.beansById, dependency.getDependencyId(), dependency);
                } else {
                    this.insertSetterDependencies(bean, super.beansByType, dependency.getDependencyClassName(), dependency);
                }
            }
        }else{
            this.matchConstructorDependencies(bean);
        }
    }

    private void insertSetterDependencies(Bean bean, Map<String, Bean> beanMap, String key, Dependency dependency){
        Bean newBean = beanMap.get(key);
        if (newBean != null) {
            if (newBean.getScope().equals(Scope.SINGLETON)) {
                //Con setters
                this.setterInjection(bean, newBean, dependency);
            } else {
                //llamar a create bean
                this.setterInjection(bean, this.createBean(newBean), dependency);
            }
        }
    }


    private void matchConstructorDependencies(Bean bean){
        try {
            Class beanClass = Class.forName(bean.getClassName());
            List<Dependency> dependencies = bean.getDependencies();
            Constructor[] constructors = beanClass.getConstructors();
            Class[] classes = null;
            boolean isConstructorFound = false;
            int correctConstructorIndex = -1;
            for (int i = 0; i < constructors.length && !isConstructorFound; i++) {
                classes = constructors[i].getParameterTypes();
                if(classes.length == dependencies.size()){
                    isConstructorFound = this.areArgumentsCorrect(classes, dependencies);
                    if(isConstructorFound) {
                        correctConstructorIndex = i;
                    }
                }
            }
            if(correctConstructorIndex != -1 && classes != null){
                this.insertConstructorDependencies(bean, constructors[correctConstructorIndex], classes);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertConstructorDependencies(Bean bean, Constructor constructor, Class[] classes){
        Object[] dependencies = new Object[classes.length];
        for (int i = 0; i < dependencies.length; i++) {
            dependencies[i] = super.beansByType.get(classes[i].getName().split(" ")[2]).getInstance();
        }
        try {
            Object beanInstance = constructor.newInstance(dependencies);
            bean.setInstance(beanInstance);
        }catch (Exception e){
            e.printStackTrace();
        }
        //Object instance = constructor.newInstance();

    }

    private boolean areArgumentsCorrect(Class[] classes, List<Dependency> dependencies){
        boolean areCorrect = false;
        Set<Integer> foundArguments = new TreeSet<Integer>();
        int argumentNumber = 0;
        for (int i = 0; i < classes.length; i++) {
            for (int j = 0; j < dependencies.size(); j++) {
                if(classes[i].toString().contains(dependencies.get(j).getDependencyClassName()) && !foundArguments.contains(j)){
                    foundArguments.add(j);
                    argumentNumber++;
                }
            }
        }
        if(argumentNumber == classes.length){
            areCorrect = true;
        }
        return areCorrect;
    }


    private void setterInjection(Bean parent, Bean child, Dependency childDependency) {
        this.setterInjection(parent, child.getInstance(), childDependency);
    }


    private void setterInjection(Bean parent, Object instance, Dependency childDependency) {
        try {
            Class beanClass = Class.forName(parent.getClassName());
            Method[] methods = beanClass.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().toLowerCase().contains(childDependency.getAttributeName().toLowerCase()) //Dice que el metodo es un set
                        && methods[i].getParameterTypes().length == 1 //Revisa que solo tenga un parametro, por ser un setter
                        && methods[i].getParameterTypes()[0].equals(Class.forName(childDependency.getDependencyClassName()))) {//Revisa que el parametro del setter sea del mismo tipo que la dependencia
                    methods[i].invoke(parent.getInstance(), instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initializeBeans() {
        Iterator<Map.Entry<String, Bean>> iterator = super.beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            Bean currBean = iterator.next().getValue();
            if(currBean.getScope().equals(Scope.SINGLETON) && currBean.getInjection().equals(Injection.SETTER)){
                try {
                    Class currClass = Class.forName(currBean.getClassName());
                    Constructor constructor = currClass.getConstructor();
                    Object instance = constructor.newInstance();
                    currBean.setInstance(instance);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
