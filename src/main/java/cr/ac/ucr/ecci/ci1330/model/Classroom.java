package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Id;

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
public class Classroom {
    private Whiteboard whiteboard;
    private Teacher teacher;
    private Assistant assistant;
    private Desk desk;

    @Autowired
    public void setWhiteboard(Whiteboard whiteboard) {
        this.whiteboard = whiteboard;
        System.out.println("Whiteboard set in Classroom");
    }

    @Autowired
    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
        System.out.println("Assistant set in Classroom");
    }

    @Autowired
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        System.out.println("Teacher set in Classroom");
    }

    @Autowired
    public void setDesk(Desk desk) {
        this.desk = desk;
        System.out.println("Teacher Desk set in Classroom");
    }
}
