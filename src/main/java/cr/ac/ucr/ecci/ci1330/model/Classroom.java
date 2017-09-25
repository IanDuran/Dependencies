package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Id;

public class Classroom {
    private Teacher teacher;
    private Assistant assistant;


    @Autowired
    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    @Autowired
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
