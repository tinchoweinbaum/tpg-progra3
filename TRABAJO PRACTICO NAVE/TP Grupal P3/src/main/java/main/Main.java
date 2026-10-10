package main;

import asistentes.Asistente;
import misiones.Mision;
import sistema.*;
import tripulantes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        escenario1();
    }

    public static void escenario1(){
        Sistema sistema = Sistema.getInstance();
        Asistente ac1 = sistema.inicio("Carguero");
        Asistente ac2 = sistema.inicio("Combate");
        Asistente ac3 = sistema.inicio("Explorador");

        ArrayList<Tripulante> tripulantes = new ArrayList<>();
        Tripulante t1 = sistema.creaTripulante("CAPITAN","Vulcano","palermo",5);
        Tripulante t2 = sistema.creaTripulante("Alferez","Marciano","riquelme",10);
        Tripulante t3 = sistema.creaTripulante("Consejero","Marciano","bianchi",35);
        Tripulante t4 = sistema.creaTripulante("Teniente","Marciano","dua lipa",10);
        tripulantes.add(t1);
        tripulantes.add(t2);
        tripulantes.add(t3);
        tripulantes.add(t4);
        ac1.agregaTripulante(tripulantes);

        Mision m1 = sistema.creaMision(1);
        m1.ejecutarMision(ac1);
        ac1.muestraBitacora();
        System.out.println("");

        Mision m2 = sistema.creaMision(2);
        m2.ejecutarMision(ac1);
        ac1.muestraNBitacoras(7);
        System.out.println("");

        Mision m3 = sistema.creaMision(3);
        m3.ejecutarMision(ac1);
        ac1.muestraNBitacoras(6);
        System.out.println("");

        System.out.println(ac1);
    }

    public static void escenario2(){

    }

    public static void escenario3(){

    }

    public static void escenario4(){

    }
}

