package cr.ac.ucr.ecci.ci1330.bean;

import cr.ac.ucr.ecci.ci1330.enums.Autowired;

public class Dependency {
    private String attributeName;
    private String methodName;
    private String dependencyId;
    private String dependencyClassName;
    private Autowired autowired;


    public String getDependencyId() {
        return this.dependencyId;
    }

    public void setDependencyId(String dependencyId) {
        this.dependencyId = dependencyId;
    }

    public Autowired getAutowired() {
        return this.autowired;
    }

    public void setAutowired(Autowired autowired) {
        this.autowired = autowired;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getDependencyClassName() {
        return this.dependencyClassName;
    }

    public void setDependencyClassName(String beanClassName) {
        this.dependencyClassName = beanClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
