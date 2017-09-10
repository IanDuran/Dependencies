package cr.ac.ucr.ecci.ci1330.bean;

import cr.ac.ucr.ecci.ci1330.enums.Autowired;

public class Dependency {
    private String attributeName;
    private String beanId;
    private String className;
    private Autowired autowired;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getBeanId() {
        return this.beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public Autowired getAutowired() {
        return this.autowired;
    }

    public void setAutowired(Autowired autowired) {
        this.autowired = autowired;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
