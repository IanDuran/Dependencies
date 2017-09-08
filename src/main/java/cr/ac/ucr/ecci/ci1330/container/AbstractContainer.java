package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.bean.Bean;

import java.util.Map;

public abstract class AbstractContainer implements Container{
    protected Map<String, Bean> beansById;
    protected Map<String, Bean> beansByType;
    protected String initMethod;
    protected String destroyMethod;

    public AbstractContainer(String path){

    }

    protected abstract void startInjection();
    protected abstract void insertDependencies();
}
