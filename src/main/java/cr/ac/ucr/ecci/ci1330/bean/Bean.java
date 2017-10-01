package cr.ac.ucr.ecci.ci1330.bean;

import cr.ac.ucr.ecci.ci1330.enums.Injection;
import cr.ac.ucr.ecci.ci1330.enums.Scope;

import java.util.LinkedList;
import java.util.List;

/**
 * Universidad de Costa Rica
 * Facultad de ingeniería
 * Escuela de Ciencias de la Computación e Informática
 * Ingeniería de Software 1
 * Autores:
 *
 * @author Brenes Solano Silvia B41133
 * @author Cubero Sánchez Josué B42190
 * @author Durán Gregory Ian B42322
 */
public class Bean{
    private String id;
    private String className;
    private String initMethod;
    private String destroyMethod;
    private Scope scope;
    private Injection injection;
    private Object instance;
    private List<Dependency> dependencies;

    public Bean(){
        this.dependencies = new LinkedList<Dependency>();
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
        return this.initMethod;
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
        return this.injection;
    }

    public void setInjection(Injection injection) {
        this.injection = injection;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id != null && this.className != null && this.id.equals(((Bean) obj).getId()) && this.className.equals(((Bean) obj).getClassName());
    }
}
