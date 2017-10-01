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
public class Row {
    private Seat firstSeat;
    private Seat secondSeat;
    private Seat thirdSeat;

    @Autowired(value = "NAME", id = "firstSeat")
    public void setFirstSeat(Seat firstSeat) {
        this.firstSeat = firstSeat;
    }

    @Autowired(value = "NAME", id = "secondSeat")
    public void setSecondSeat(Seat secondSeat) {
        this.secondSeat = secondSeat;
    }

    @Autowired(value = "NAME", id = "thirdSeat")
    public void setThirdSeat(Seat thirdSeat) {
        this.thirdSeat = thirdSeat;
    }
}
