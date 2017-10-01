package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Scope
public class Listeners {
    private Student alonso;
    private Student pizza;
    private Student milton;
    private Student oscar;
    private Student margarito;
    private Student elbrayan;

    @Autowired
    public Listeners(Student alonso, Student pizza, Student milton, Student oscar,
                     Student margarito, Student elbrayan){
        this.alonso = alonso;
        this.pizza = pizza;
        this.milton = milton;
        this.oscar = oscar;
        this.margarito = margarito;
        this.elbrayan = elbrayan;
    }
}
