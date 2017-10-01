package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;
import cr.ac.ucr.ecci.ci1330.graph.AdjacencyList;
import cr.ac.ucr.ecci.ci1330.graph.GraphAlgorithms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public abstract class AbstractContainer implements Container{

    protected Map<String, Bean> beansById;
    protected Map<String, Bean> beansByType;
    protected String initMethod;
    protected String destroyMethod;

    public AbstractContainer(){
        this.beansById = new HashMap<String, Bean>();
        this.beansByType = new HashMap<String, Bean>();
    }

    /**
     * Starts dependency injection.
     */
    protected abstract void startInjection();

    /**
     * Initializes beans which are singletons and setter injection.
     */
    protected abstract void initializeBeans();

    /**
     * Starts dependency injection for a given bean.
     * @param bean the bean whose dependencies will be injected.
     */
    protected abstract void insertDependencies(Bean bean);

    /**
     * Call an appropiate method to inject setter dependencies, depending on dependency scope and dependency existance.
     * @param bean the bean to inject dependendies.
     * @param beanMap the bean map.
     * @param key the dependency id.
     * @param dependency the dependency.
     */
    protected abstract void insertSetterDependencies(Bean bean, Map<String, Bean> beanMap, String key, Dependency dependency);

    /**
     * Iterate through all bean constructors to get the correct one.
     * @param bean the bean whom we iterate its constructors.
     */
    protected abstract void matchConstructorDependencies(Bean bean);

    /**
     * Create a bean with all dependencies injected.
     * @param bean the bean to be injected.
     * @return the injected bean.
     */
    protected abstract Object createBean(Bean bean);

    /**
     * Inject the dependency looking for its setter method.
     * @param parent the parent bean.
     * @param child the child bean.
     * @param childDependency the dependency.
     */
    protected abstract void setterInjection(Bean parent, Bean child, Dependency childDependency);

    /**
     * Inject the dependency if it already exists.
     * @param parent the parent bean.
     * @param instance the instance of child bean.
     * @param childDependency the dependency.
     */
    protected abstract void setterInjection(Bean parent, Object instance, Dependency childDependency);

    /**
     * Check if constructor has correct the arguments for constructor injection.
     * @param classes the argument types array.
     * @param dependencies the dependencies array.
     * @return true if its correct constructor, false otherwise.
     */
    protected abstract boolean areArgumentsCorrect(Class[] classes, List<Dependency> dependencies);

    /**
     * Insert constructor dependencies to bean.
     * @param bean the bean which will be injected.
     * @param constructor the correct constructor.
     * @param classes the argument array for injections.
     */
    protected abstract void insertConstructorDependencies(Bean bean, Constructor constructor, Class[] classes);

    /**
     * Calls the destroy methods in all beans.
     */
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

    /**
     * Checks the dependencies for cycles that will
     * @return true if there are cycles in the beans graph, false otherwise.
     */
    protected boolean hasCycles(){
        AdjacencyList<Bean> graph = new AdjacencyList<Bean>(true, false);
        Iterator<Map.Entry<String, Bean>> iterator = beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            Bean currBean = iterator.next().getValue();
            if(currBean.getDependencies().size() > 0)
                graph.addNode(currBean);
        }

        iterator = beansByType.entrySet().iterator();
        while(iterator.hasNext()){
            Bean currBean = iterator.next().getValue();
            List<Dependency> dependencies = currBean.getDependencies();
            for (int i = 0; i < dependencies.size(); i++) {
                Bean dependencyBean = beansByType.get(dependencies.get(i).getDependencyClassName());
                if(dependencyBean.getDependencies().size() > 0)
                    graph.addEdge(currBean, dependencyBean);
            }
        }
        return !GraphAlgorithms.isGraphAcyclic(graph);
    }
}
