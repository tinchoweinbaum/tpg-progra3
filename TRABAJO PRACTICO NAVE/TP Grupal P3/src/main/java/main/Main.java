package main;

import asistentes.Asistente;
import misiones.Mision;
import motorwarp.MotorWarp;
import sistema.*;
import tripulantes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        escenario1();
        escenario2();
        escenario3();
        escenario4();
    }

    public static void escenario1(){
        //Las 3 misiones ejecutadas sin errores

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
        System.out.println("-------------------------------");
    }

    public static void escenario2(){
        //Mision invalida por falta de recursos

        Sistema sistema = Sistema.getInstance();
        Asistente ac1 = sistema.inicio("Carguero");

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

        ac1.consumeCombustibleNave(ac1.getNave().getCombustible());
        Mision m1 = sistema.creaMision(1);
        m1.ejecutarMision(ac1);
        ac1.muestraBitacora();

        System.out.println("-------------------------------");
    }

    public static void escenario3(){
        //Ciclo de salto warp, sin y con fallos

        Sistema sistema = Sistema.getInstance();
        Asistente ac1 = sistema.inicio("Carguero");

        ac1.saltar();
        ac1.muestraBitacora();
        System.out.println(" ");

        ac1.setEstadoMotor(MotorWarp.SALTO_WARP);
        ac1.muestraNBitacoras(1);

        System.out.println("-------------------------------");
    }

    public static void escenario4(){
        //Carga invalida de recursos

        Sistema sistema = Sistema.getInstance();
        Asistente ac1 = sistema.inicio("Carguero");

        ac1.cargaCombustibleNave(1000);
        ac1.muestraBitacora();

        System.out.println("-------------------------------");
    }
}

