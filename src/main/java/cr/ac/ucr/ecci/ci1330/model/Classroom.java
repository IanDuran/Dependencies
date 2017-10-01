package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Id;

public class Classroom {
    private Whiteboard whiteboard;
    private Teacher teacher;
    private Assistant assistant;
    private Desk desk;

    @Autowired
    public void setWhiteboard(Whiteboard whiteboard) {
        this.whiteboard = whiteboard;
    }

    @Autowired
    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    @Autowired
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Autowired
    public void setDesk(Desk desk) {
        this.desk = desk;
    }
}
