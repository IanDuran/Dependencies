package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
     * @param idBeanMap the bean map with ids.
     * @param classBeanMap the bean map with classes.
     */
    public abstract void parseFile(Map<String, Bean> idBeanMap,Map<String,Bean> classBeanMap);

    /**
     * Create a bean from XML element.
     * @param idBeanMap the bean map that will contain beans by id.
     * @param classBeanMap the bean map that will contain beans by class.
     * @param bean the bean element from XML.
     */
    public abstract void createBean(Map<String, Bean> idBeanMap,Map<String,Bean> classBeanMap, Element bean);
}
