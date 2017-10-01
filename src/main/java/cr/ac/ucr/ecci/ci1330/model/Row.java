package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;

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
