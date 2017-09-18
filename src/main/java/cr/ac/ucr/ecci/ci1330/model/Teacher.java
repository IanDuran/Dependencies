package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.*;

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
            System.out.println("Assistant is not null");
        }
        if(this.student != null){
            System.out.println("Student is not null");
        }
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
