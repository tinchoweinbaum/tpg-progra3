package main;

import CapaNegocio.Controlador;
import CapaPresentacion.VistaInicioSesion;
import asistentes.Asistente;
import misiones.Mision;
import motorwarp.MotorWarp;
import persistencia.Usuario;
import persistencia.UsuariosDAO;
import sistema.*;
import tripulantes.*;

import persistencia.UsuariosSqlDao; // import temporal para testear DAO de SQL

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
        Controlador controlador = new Controlador(vistaInicioSesion);
        vistaInicioSesion.setControlador(controlador);
        vistaInicioSesion.arranca();

//        UsuariosDAO sqlDao = new UsuariosSqlDao();
//        sqlDao.registrarUsuario(new Usuario("usuario", "12345"));
//        System.out.print(sqlDao.getUsuarios());
    }

}

