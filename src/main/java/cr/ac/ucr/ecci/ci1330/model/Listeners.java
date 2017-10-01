package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Scope
public class Listeners {
    private Student alonso;

    @Autowired
    public Listeners(Student alonso){
        this.alonso = alonso;
        if(this.alonso != null){
            System.out.println("Alonso de oyente");
        } else {
            System.out.println("Logro matricular alonso");
        }
    }

    /*public void setAlonso(Student alonso) {
        this.alonso = alonso;
    }*/
}
