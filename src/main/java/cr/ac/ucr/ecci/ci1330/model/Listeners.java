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
