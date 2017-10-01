package cr.ac.ucr.ecci.ci1330.model;

import cr.ac.ucr.ecci.ci1330.annotation.*;

/*
    Universidad de Costa Rica
     Facultad de ingeniería
     Escuela de Ciencias de la Computación e Informática
     Ingeniería de Software 1
     Autores:
     Brenes Solano Silvia B41133
     Cubero Sánchez Josué B42190
     Durán Gregory Ian B42322
 */

@Id("student")
@Scope
public class Student {
   // private Listeners lis;


    @Autowired
    public Student(/*Listeners lis*/){
        //this.lis = lis;
    }


    /*@PostConstruct
    public void init(){

    }

    @PreDestroy
    public void destroy(){

    }*/
}
