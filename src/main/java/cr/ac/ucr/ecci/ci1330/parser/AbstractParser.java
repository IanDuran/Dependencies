package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import nu.xom.Builder;
import nu.xom.Document;

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
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public abstract void parseFile(Map<String, Bean> beanMap);
    public abstract void createBean(Map<String, Bean> beanMap);
}
