package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Id;
import cr.ac.ucr.ecci.ci1330.annotation.PostConstruct;
import cr.ac.ucr.ecci.ci1330.annotation.PreDestroy;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Id("student")
@Scope
public class Student {

    public Student(){

    }


    @PostConstruct
    public void init(){

    }

    @PreDestroy
    public void destroy(){

    }
}
