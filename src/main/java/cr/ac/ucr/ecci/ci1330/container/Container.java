package cr.ac.ucr.ecci.ci1330.container;

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
public interface Container {
    /**
     * Gets a bean from an id.
     * @param id the bean's id.
     * @return the bean with that id.
     */
    Object getBeanById(String id);

    /**
     * Gets a bean from a type.
     * @param className the bean's type.
     * @return the bean with that type.
     */
    Object getBeanByType(String className);
}
