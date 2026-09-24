package com.example;

public class Marciano extends Tripulante{
    
    public Marciano(String nom,int ant){
        super(nom, ant);
        this.sueldo = 18;
    }

    public String getOrigen(){
        return "Marciano";
    }
}
