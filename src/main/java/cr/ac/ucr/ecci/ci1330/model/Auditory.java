package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

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
