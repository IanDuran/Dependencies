package cr.ac.ucr.ecci.ci1330.model;

public class Teacher {
    private Assistant assistant;
    private Student student;

    public Teacher(){

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
}
