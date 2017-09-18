package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractContainer implements Container{

    protected Map<String, Bean> beansById;
    protected Map<String, Bean> beansByType;
    protected String initMethod;
    protected String destroyMethod;

    public AbstractContainer(){
        this.beansById = new HashMap<String, Bean>();
        this.beansByType = new HashMap<String, Bean>();
    }

    protected abstract void startInjection();
    protected abstract void insertDependencies(Bean bean);
    protected abstract Object createBean(Bean bean);
    protected abstract void initializeBeans();
    public abstract Object getBeanById(String id);
    public abstract Object getBeanByType(String className);
    protected abstract void insertSetterDependencies(Bean bean, Map<String, Bean> beanMap, String key, Dependency dependency);
    protected abstract void matchConstructorDependencies(Bean bean);
    protected abstract void insertConstructorDependencies(Bean bean, Constructor constructor, Class[] classes);
    protected abstract boolean areArgumentsCorrect(Class[] classes, List<Dependency> dependencies);
    protected abstract void setterInjection(Bean parent, Bean child, Dependency childDependency);
    protected abstract void setterInjection(Bean parent, Object instance, Dependency childDependency);




    public void destroy(){
        Iterator<Map.Entry<String, Bean>> iterator = beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            try {
                Bean currBean = iterator.next().getValue();
                Class beanClass = Class.forName(currBean.getClassName());
                Method destroyMethod = beanClass.getMethod(currBean.getDestroyMethod());
                destroyMethod.invoke(currBean.getInstance());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
