package cr.ac.ucr.ecci.ci1330.parser;

import cr.ac.ucr.ecci.ci1330.bean.Bean;

import java.util.Map;

public abstract class AbstractParser {
    protected String path;

    public AbstractParser(String path){
        this.path = path;
    }
    public abstract void parseFile(Map<String, Bean> beanMap);
    public abstract void createBean(Map<String, Bean> beanMap);
}
