package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.container.AnnotationContainer;
import cr.ac.ucr.ecci.ci1330.enums.Autowired;
import nu.xom.Element;
import nu.xom.Elements;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
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

public class AnnotationParser extends AbstractParser {

    public AnnotationParser(String path){
        super(path);
    }

    @Override
    public void parseFile(Map<String, Bean> idBeanMap, Map<String,Bean> classBeanMap) {
        try{
            Element rootElement = super.configurationFile.getRootElement();
            Elements elements = rootElement.getChildElements();
            this.fillClassMap(classBeanMap, elements);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Fill the bean class map using the xom elements list tag.
     * @param classBeanMap the bean class map.
     * @param elements the xom elements list tag.
     */
    private void fillClassMap(Map<String, Bean> classBeanMap, Elements elements){
        for (int i = 0; i < elements.size(); i++) {
            Element currElement = elements.get(i);
            if (currElement.getLocalName().equals("annotation-config")) {
                File packageFolder = new File(System.getProperty("user.dir") + "/src/main/java/" + currElement.getAttributeValue("package").replace('.', '/'));
                String[] classNames = packageFolder.list();
                if (classNames != null) {
                    for (int j = 0; j < classNames.length; j++) {
                        classNames[j] = classNames[j].replace(System.getProperty("user.dir"), "").replace(".java", "");
                        classNames[j] = currElement.getAttribute("package").getValue() + '.' + classNames[j];
                        Bean newBean = new Bean();
                        if(classBeanMap.get(classNames[j]) == null) {
                            newBean.setClassName(classNames[j]);
                            classBeanMap.put(classNames[j], newBean);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void createBean(Map<String, Bean> idBeanMap,Map<String,Bean> classBeanMap,Element bean) {

    }

    public static void main(String... args){
        //AnnotationContainer annotationContainer = new AnnotationContainer("src\\main\\resources\\configuration.xml");
        //annotationContainer.startInjection();
        try{
            Class testClass = Class.forName("cr.ac.ucr.ecci.ci1330.model.Teacher");
            Constructor[] constructors = testClass.getConstructors();
            Class[] classes = constructors[0].getParameterTypes();
            for (int i = 0; i < classes.length; i++) {
                System.out.println(classes[i].toString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
