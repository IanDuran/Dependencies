package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public abstract class AbstractParser {
    protected Document configurationFile;

    public AbstractParser(String path){
        try {
            InputStream inputStream = new FileInputStream(new File(path));
            Builder builder = new Builder();
            this.configurationFile = builder.build(inputStream);
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Parse XML file to fill the bean maps.
     * @param beanMap the bean map.
     */
    public abstract void parseFile(Map<String, Bean> beanMap);

    /**
     * Create a bean from XML element.
     * @param beanMap the bean map that will contain it.
     * @param bean the bean element from XML.
     */
    public abstract void createBean(Map<String, Bean> beanMap, Element bean);


}
