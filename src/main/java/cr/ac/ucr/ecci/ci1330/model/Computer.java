package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

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
    }
}
