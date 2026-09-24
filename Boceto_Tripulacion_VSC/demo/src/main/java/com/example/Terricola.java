package com.example;

public class Terricola extends Tripulante{
    
    public Terricola(String nom,int ant){
        super(nom, ant);
        this.sueldo = 20;
    }


    public String getOrigen(){
        return "Terricola";
    }


}
