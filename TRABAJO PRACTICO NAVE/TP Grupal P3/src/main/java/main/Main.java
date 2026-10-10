package main;

import asistentes.Asistente;
import misiones.Mision;
import sistema.*;
import tripulantes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Sistema system = Sistema.getInstance();

        Asistente asist1 = system.inicio("COMBATE");

        asist1.cargaCombustibleNave(-90);

        //asist1.muestraBitacora();

        Mision m1 = system.creaMision(1);
        System.out.println(m1.getDescripcion());

        Mision m2 = system.creaMision(4);

        Tripulante trip1 = system.creaTripulante("CAPITAN","TERRICOLA","MARTIN",24);
        Tripulante trip2 = system.creaTripulante("Alferez","TERRICOLA","Gonzalo",24);
        Tripulante trip3 = system.creaTripulante("Teniente","TERRICOLA","LISANDRO",21);
        Tripulante trip4 = system.creaTripulante("Consejero","TERRICOLA","Juampi",25);

        Tripulante trip5 = system.creaTripulante("CAPiTAN","VULcANO","LEONEL",67);

        ArrayList<Tripulante> tripu = new ArrayList<>();
        tripu.add(trip1);
        tripu.add(trip2);
        tripu.add(trip3);
        tripu.add(trip4);
        tripu.add(trip5);

//        for (Tripulante t : tripu){
//            System.out.println(t);
//        }

        asist1.getNave().agregaTripulante(tripu);

//        for (Tripulante t : asist1.getNave().getTripulacion()){
//            System.out.println(t);
//        }

        Mision m3 = system.creaMision(3);

        //m3.ejecutarMision(asist1);

        //asist1.muestraBitacora();

        asist1.setEstadoMotor(3);

        asist1.muestraNBitacoras(1);


    }
}

