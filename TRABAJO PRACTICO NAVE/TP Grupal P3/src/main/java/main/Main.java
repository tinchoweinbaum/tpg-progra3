package main;

import CapaNegocio.Controlador;
import CapaPresentacion.VistaInicioSesion;

public class Main {
    public static void main(String[] args){
        VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
        Controlador controlador = new Controlador(vistaInicioSesion);
        vistaInicioSesion.setControlador(controlador);
        vistaInicioSesion.arranca();
    }

}

