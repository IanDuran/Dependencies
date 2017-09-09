package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import nu.xom.Element;
import nu.xom.Elements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AnnotationParser extends AbstractParser {

    public AnnotationParser(String path){
        super(path);
    }

    @Override
    public void parseFile(Map<String, Bean> beanMap) {
        try{
            Element rootElement = super.configurationFile.getRootElement();
            Elements elements = rootElement.getChildElements();
            for (int i = 0; i < elements.size(); i++) {
                Element currElement = elements.get(i);
                if (currElement.getLocalName().equals("annotation-config")) {
                    File packageFolder = new File(System.getProperty("user.dir") + "/src/main/java/" + currElement.getAttributeValue("package").replace('.', '/'));
                    String[] classNames = packageFolder.list();
                    if (classNames != null) {
                        for (int j = 0; j < classNames.length; j++) {
                            classNames[j] = classNames[j].replace(System.getProperty("user.dir"), "").replace(".java", "");
                            classNames[j] = currElement.getAttribute("package").getValue() + '.' + classNames[j];

                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void createBean(Map<String, Bean> beanMap,Element bean) {

    }

    public static void main(String... args){
        AnnotationParser an = new AnnotationParser("src\\main\\resources\\configuration.xml");
        an.parseFile(new HashMap<String, Bean>());
    }
}
