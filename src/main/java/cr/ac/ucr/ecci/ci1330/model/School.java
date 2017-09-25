package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;

public class School {
    private Classroom classroom;

    @Autowired
    public School(Classroom classroom){
        this.classroom = classroom;
    }
}
