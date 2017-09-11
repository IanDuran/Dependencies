package cr.ac.ucr.ecci.ci1330.container;

import cr.ac.ucr.ecci.ci1330.bean.Bean;
import cr.ac.ucr.ecci.ci1330.parser.AnnotationParser;

public class AnnotationContainer extends AbstractContainer{

    private AnnotationParser parser;

    public AnnotationContainer(String path){
        super();
        this.parser = new AnnotationParser(path);
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
    protected void insertDependencies(Bean bean) {

    }

    @Override
    protected Object createBean(Bean bean) {
        return null;
    }

    @Override
    protected void initializeBeans() {

    }
}
