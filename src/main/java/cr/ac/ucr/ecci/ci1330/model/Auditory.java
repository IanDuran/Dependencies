package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Scope
public class Auditory {
    private Seat s1;

    @Autowired
    public Auditory(Seat s1){
        this.s1 = s1;
        if(this.s1 != null){
            System.out.println("Auditory seat is not null");
        } else {
            System.out.println("Something went wrong with auditory seats");
        }
    }


    public void setS1(Seat s1) {
        this.s1 = s1;
    }

}
