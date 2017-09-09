package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;
import cr.ac.ucr.ecci.ci1330.enums.Autowired;
import cr.ac.ucr.ecci.ci1330.enums.Scope;
import cr.ac.ucr.ecci.ci1330.parser.XMLParser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class XMLContainer extends AbstractContainer {

    private XMLParser parser;

    public XMLContainer(String path) {
        super();
        this.parser = new XMLParser(path);
    }

    public Object createBean(Bean bean) {
        Class currClass;
        Object instance = null;
        try {
            currClass = Class.forName(bean.getClassName());
            Constructor constructor = currClass.getConstructor();
            instance = constructor.newInstance();
            this.insertDependencies(bean);       //se envia el bean con la nueva instancia creada para que le inserte las dependencias.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Object getBeanById(String id) {
        Bean bean = this.beansById.get(id);
        Object instance = null;
        if (bean != null) {
            if (bean.getScope().equals(Scope.SINGLETON)) {
                instance = bean.getInstance();
            } else {
                instance = createBean(bean);
            }
        }
        return instance;
    }

    public Object getBeanByType(String className) {
        Bean bean = this.beansByType.get(className);
        Object instance = null;
        if (bean != null) {
            if (bean.getScope().equals(Scope.SINGLETON)) {
                instance = bean.getInstance();
            } else {
                instance = createBean(bean);
            }
        }
        return instance;
    }

    @Override
    public void startInjection() {
        Iterator<Map.Entry<String, Bean>> iterator = super.beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            this.insertDependencies(iterator.next().getValue());
        }
    }

    @Override
    public void insertDependencies(Bean bean) {
        for (Dependency dependency : bean.getDependencies()) {
            if (dependency.getAutowired().equals(Autowired.ID)) {
                this.insertDependencies(bean, super.beansById, dependency.getBeanId(), dependency);
            } else {
                this.insertDependencies(bean, super.beansByType, dependency.getClassName(), dependency);
            }
        }
    }

    private void insertDependencies(Bean bean, Map<String, Bean> beanMap, String key, Dependency dependency){
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
                        && methods[i].getParameterTypes()[0].equals(Class.forName(childDependency.getClassName()))) {//Revisa que el parametro del setter sea del mismo tipo que la dependencia
                    methods[i].invoke(parent.getInstance(), instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
