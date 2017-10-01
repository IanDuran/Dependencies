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
@Scope("PROTOTYPE")
public class Computer {
    private Keyboard keyboard;
    private Mouse mouse;
    private Monitor monitor;


    @Autowired
    public Computer(Keyboard keyboard, Mouse mouse, Monitor monitor){
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.monitor = monitor;
        if(this.keyboard != null && this.mouse != null && this.monitor != null){
            System.out.println("Computer haves all of its components");
        } else {
            System.out.println("Computer dead");
        }
    }
}
