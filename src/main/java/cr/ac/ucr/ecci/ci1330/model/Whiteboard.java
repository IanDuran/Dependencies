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
public class Whiteboard {
    private Pilot pilot;
    private Eraser eraser;

    public Pilot getPilot() {
        return pilot;
    }

    @Autowired
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
        System.out.println("Pilot set in Whiteboard");
    }


    public Eraser getEraser() {
        return eraser;
    }

    @Autowired
    public void setEraser(Eraser eraser) {
        this.eraser = eraser;
        System.out.println("Eraser set in Whiteboard");
    }
}
