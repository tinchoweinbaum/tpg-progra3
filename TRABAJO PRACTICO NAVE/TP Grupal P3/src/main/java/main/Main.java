package main;

import CapaNegocio.Controlador;
import CapaPresentacion.VistaInicioSesion;
import asistentes.Asistente;
import misiones.Mision;
import misiones.Mision1;
import motorwarp.MotorWarp;
import persistencia.Usuario;
import persistencia.UsuariosDAO;
import tripulantes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
        Controlador controlador = new Controlador(vistaInicioSesion);
        vistaInicioSesion.setControlador(controlador);
        vistaInicioSesion.arranca();
    }

}

