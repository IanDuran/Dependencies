package cr.ac.ucr.ecci.ci1330.container;

public interface Container {
    Object getBeanById(String id);
    Object getBeanByType(String className);
}
