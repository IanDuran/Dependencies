package cr.ac.ucr.ecci.ci1330.bean;

import cr.ac.ucr.ecci.ci1330.enums.Autowired;
import cr.ac.ucr.ecci.ci1330.enums.Scope;

import java.util.LinkedList;
import java.util.List;

public class Bean {
    private String name;
    private Scope scope;
    private Autowired autowired;
    private String className;
    private List<Dependency> dependencies;

    public Bean(){
        dependencies = new LinkedList<Dependency>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        return autowired;
    }

    public void setAutowired(Autowired autowired) {
        this.autowired = autowired;
    }

    public List<Dependency> getDependencies() {
        return this.dependencies;
    }

    public void addDependency(Dependency dependency){
        this.dependencies.add(dependency);
    }
}
