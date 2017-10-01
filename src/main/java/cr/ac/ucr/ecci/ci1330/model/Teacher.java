package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.*;

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
@Id("teacher")
@Scope
public class Teacher {
    private Assistant assistant;
    private Student student;

    @Autowired
    public Teacher(Student student, Assistant assistant){
        this.student = student;
        this.assistant = assistant;
        if(this.assistant != null){
            System.out.println("Assistant is set in Teacher");
        }
        if(this.student != null){
            System.out.println("Student is set in Teacher");
        }
    }

    public Teacher(){

    }

    public Assistant getAssistant() {
        return this.assistant;
    }

    public void setAssistant(Assistant assistant) {
        System.out.println("Assistant");
        this.assistant = assistant;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        System.out.println("Student");
        this.student = student;
    }

    @PostConstruct
    public void init(){
    }

    @PreDestroy
    public void destroy(){
    }
}
