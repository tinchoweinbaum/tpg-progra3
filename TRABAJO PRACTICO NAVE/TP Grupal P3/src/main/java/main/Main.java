package main;

import asistentes.Asistente;
import misiones.Mision;
import sistema.*;
import tripulantes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Sistema sistema = Sistema.getInstance();
        Asistente ac1 = sistema.inicio("Carguero");
        Mision m3 = sistema.creaMision(3);
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
        m3.ejecutarMision(ac1);
        ac1.muestraBitacora();

        System.out.println(ac1);
    }
}

