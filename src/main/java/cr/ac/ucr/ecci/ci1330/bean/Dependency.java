package cr.ac.ucr.ecci.ci1330.bean;

import cr.ac.ucr.ecci.ci1330.enums.Autowired;

public class Dependency {
    private String attributeName;
    private String beanId;
    private Autowired autowired;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public Autowired getAutowired() {
        return autowired;
    }

    public void setAutowired(Autowired autowired) {
        this.autowired = autowired;
    }
}
