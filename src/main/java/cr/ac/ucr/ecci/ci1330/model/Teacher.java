package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.*;

@Id("teacher")
@Scope("PROTOTYPE")
public class Teacher {
    private Assistant assistant;
    private Student student;

    @Autowired
    public Teacher(Student student, Assistant assistant){

    }

    public Assistant getAssistant() {
        return this.assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @PostConstruct
    public void init(){

    }

    @PreDestroy
    public void destroy(){

    }
}
