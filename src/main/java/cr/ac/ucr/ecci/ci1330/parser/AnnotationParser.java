package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import nu.xom.Element;

import java.util.Map;

public class AnnotationParser extends AbstractParser {

    public AnnotationParser(String path){
        super(path);
    }



    @Override
    public void parseFile(Map<String, Bean> beanMap) {
        try{
            Element element = super.configurationFile.getRootElement().getFirstChildElement("annotation-config");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void createBean(Map<String, Bean> beanMap,Element bean) {

    }
}
