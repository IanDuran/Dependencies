package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.Autowired;
import cr.ac.ucr.ecci.ci1330.annotation.Id;
import cr.ac.ucr.ecci.ci1330.annotation.Scope;

@Id("123")
@Scope
public class VideoBeam {


    @Autowired
    public VideoBeam(){
        System.out.println("VideoBeam done");
    }


}
