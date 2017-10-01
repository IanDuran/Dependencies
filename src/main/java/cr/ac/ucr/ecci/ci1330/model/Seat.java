package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Id;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Id({"firstSeat", "secondSeat", "thirdSeat"})
@Scope("PROTOTYPE")
public class Seat {
    //private Student student;

    @Autowired
    public Seat(){

    }

    public void setStudent(/*Student student*/) {
        //this.student = student;
    }
}
