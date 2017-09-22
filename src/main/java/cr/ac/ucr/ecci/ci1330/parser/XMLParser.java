package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.bean.Dependency;
import cr.ac.ucr.ecci.ci1330.enums.Autowired;
import cr.ac.ucr.ecci.ci1330.enums.Injection;
import cr.ac.ucr.ecci.ci1330.enums.Scope;
import nu.xom.Element;
import nu.xom.Elements;

import java.util.Map;

/*
    Universidad de Costa Rica
     Facultad de ingeniería
     Escuela de Ciencias de la Computación e Informática
     Ingeniería de Software 1
     Autores:
     Brenes Solano Silvia B41133
     Cubero Sánchez Josué B42190
     Durán Gregory Ian B42322
 */

public class XMLParser extends AbstractParser {

    private String initMethod;
    private String destroyMethod;

    public XMLParser(String path){
        super(path);
    }

    @Override
    public void parseFile(Map<String,Bean> idBeanMap,Map<String,Bean> classBeanMap){
        Elements rootChildren = this.configurationFile.getRootElement().getChildElements();
        int rootChildNum = rootChildren.size();
        int i = 0;
        boolean continuee = true;
        Elements beans = null;
        while((i<rootChildNum)&&(continuee)){
            if(rootChildren.get(i).getLocalName().equals("init-method")){
                this.initMethod = rootChildren.get(i).getAttributeValue("method");
            } else if(rootChildren.get(i).getLocalName().equals("destroy-method")){
                this.destroyMethod = rootChildren.get(i).getAttributeValue("method");
            } else if(rootChildren.get(i).getLocalName().equals("beans")){
                continuee = false;
                beans = rootChildren.get(i).getChildElements();
            }
            i++;
        }
        Element bean;
        int childNum = beans.size();
        for(int j=0;j<childNum;j++){
            bean = beans.get(j);
            this.createBean(idBeanMap,classBeanMap,bean);
        }
    }

    @Override
    public void createBean(Map<String, Bean> idBeanMap,Map<String,Bean> classBeanMap, Element bean) {
        Bean newBean = new Bean();
        newBean.setId(bean.getAttributeValue("id"));
        newBean.setClassName(bean.getAttributeValue("class"));
        if(bean.getAttributeValue("scope") != null) {
            newBean.setScope(Scope.valueOf(bean.getAttributeValue("scope")));
        }else{
            newBean.setScope(Scope.SINGLETON);
        }
        if(bean.getAttributeValue("injection") != null){
            newBean.setInjection(Injection.valueOf(bean.getAttributeValue("injection")));
        }else{
            newBean.setInjection(Injection.SETTER);
        }
        if(bean.getAttributeValue("init-method") != null) {
            newBean.setInitMethod(bean.getAttributeValue("init-method"));
        }else{
            newBean.setInitMethod(this.initMethod);
        }
        if(bean.getAttributeValue("destroy-method") != null){
            newBean.setDestroyMethod(bean.getAttributeValue("destroy-method"));
        }else{
            newBean.setDestroyMethod(this.destroyMethod);
        }

        this.setAllDependencies(bean,newBean);
        if(idBeanMap.get(bean.getAttributeValue("id")) == null) {
            idBeanMap.put(bean.getAttributeValue("id"), newBean);
        }else{
            throw new IllegalArgumentException();
        }
        classBeanMap.put(bean.getAttributeValue("class"),newBean);
    }

    /**
     * Iterate through all dependencies from XML and add them to dependecies list of bean.
     * @param bean the bean element from XML.
     * @param newBean the bean being created.
     */
    private void setAllDependencies(Element bean, Bean newBean){
        if(bean.getChildElements().size()>0) {
            Elements dependencies = bean.getChildElements().get(0).getChildElements();
            Element dependency;
            int dependenciesNum = dependencies.size();
            for (int i = 0; i < dependenciesNum; i++) {
                dependency = dependencies.get(i);
                newBean.getDependencies().add(this.setDependency(dependency));
            }
        }
    }

    /**
     * Set one dependency.
     * @param dependency the dependency element from XML.
     * @return the new dependency.
     */
    private Dependency setDependency(Element dependency){
        Dependency newDependency = new Dependency();
        if(dependency.getAttributeValue("dependencyId") != null){
            newDependency.setDependencyId(dependency.getAttributeValue("dependencyId"));
        }
        if(dependency.getAttributeValue("dependencyClassName") != null){
            newDependency.setDependencyClassName(dependency.getAttributeValue("dependencyClassName"));
        }
        newDependency.setAttributeName(dependency.getAttributeValue("attributeName"));
        newDependency.setAutowired(Autowired.valueOf(dependency.getAttributeValue("autowired")));
        return newDependency;
    }

    /*public static void main(String[] args) {
        XMLParser p = new XMLParser("src\\main\\resources\\configuration.xml");
        Map<String,Bean> m = new HashMap<String, Bean>();
        Map<String,Bean> m1 = new HashMap<String, Bean>();
        p.parseFile(m,m1);
        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Bean b = (Bean) pair.getValue();
            System.out.println(pair.getKey());
            System.out.println("\n"+pair.getKey()+ " " + b.getClassName()+ " " + b.getAutowired()+ " " + b.getInjection() + " " + b.getDestroyMethod()
                    + " " + b.getInitMethod()+ " " + b.getScope()+ " ");
            System.out.println("Dependencies:");
            for(Dependency dep:b.getDependencies()){
                System.out.println(dep.getAttributeName()+" "+dep.getAutowired()+" "+dep.getDependencyId());
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        Iterator it2 = m1.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pair = (Map.Entry)it2.next();
            Bean b1 = (Bean) pair.getValue();
            System.out.println(pair.getKey());
            System.out.println("\n"+pair.getKey()+ " " + b1.getClassName()+ " " + b1.getAutowired()+ " " + b1.getInjection() + " " + b1.getDestroyMethod()
                    + " " + b1.getInitMethod()+ " " + b1.getScope()+ " ");
            System.out.println("Dependencies:");
            for(Dependency dep:b1.getDependencies()){
                System.out.println(dep.getAttributeName()+" "+dep.getAutowired()+" "+dep.getDependencyId());
            }
            it2.remove(); // avoids a ConcurrentModificationException
        }
    }*/
}
