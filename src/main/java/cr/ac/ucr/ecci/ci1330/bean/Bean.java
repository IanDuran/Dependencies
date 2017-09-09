package cr.ac.ucr.ecci.ci1330.bean;

import cr.ac.ucr.ecci.ci1330.enums.Autowired;
import cr.ac.ucr.ecci.ci1330.enums.Scope;

import java.util.LinkedList;
import java.util.List;

public class Bean {
    private String id;
    private Scope scope;
    private Autowired autowired;
    private Injection injection;
    private String className;
    private String initMethod;
    private String destroyMethod;
    private Object instance;
    private List<Dependency> dependencies;

    public Bean(){
        dependencies = new LinkedList<Dependency>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public Scope getScope() {
        return this.scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Autowired getAutowired() {
        return this.autowired;
    }

    public void setAutowired(Autowired autowired) {
        this.autowired = autowired;
    }

    public Object getInstance() {
        return this.instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public List<Dependency> getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public void addDependency(Dependency dependency){
        this.dependencies.add(dependency);
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getDestroyMethod() {
        return destroyMethod;
    }

    public void setDestroyMethod(String destroyMethod) {
        this.destroyMethod = destroyMethod;
    }

    public Injection getInjection() {
        return injection;
    }

    public void setInjection(Injection injection) {
        this.injection = injection;
    }
}
