package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.parser.XMLParser;

public class XMLContainer extends AbstractContainer {
    private XMLParser parser;

    public XMLContainer(String path){
        super();
        this.parser = new XMLParser(path);
    }

    public Object getBeanById(String id) {
        return null;
    }

    public Object getBeanByType(String className) {
        return null;
    }

    @Override
    protected void startInjection() {

    }

    @Override
    protected void insertDependencies() {

    }
}
