package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Scope
public class Auditory {
    private Seat s1;
    private Seat s2;

    public Auditory(Seat s1, Seat s2){
        this.s1 = s2;
        this.s2 = s2;
        if(s1 != null && s2 != null){
            System.out.println("Auditory seat are not null");
        } else {
            System.out.println("Something went wrong with auditory seats");
        }
    }

    public void setS1(Seat s1) {
        this.s1 = s1;
    }

    public void setS2(Seat s2) {
        this.s2 = s2;
    }
}
