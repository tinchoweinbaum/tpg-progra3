package CapaNegocio;

import CapaPresentacion.*;
import asistentes.*;
import bitacora.RegistroBitacora;
import exceptions.*;
import nave.*;
import tripulantes.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//ESTE VA A CONECTARSE CON EL ENLAZADOR
public class Controlador implements DocumentListener, ActionListener {
    private VistaInicioSesion vistaInicioSesion;
    private VistaRegistro vistaRegistro;
    private BotonesMain vistaMain;
    private VistaBitacora vistaBitacora;
    private VistaCrearNave vistaCrearNave;
    private VistaGeneradores vistaGeneradores;
    private VistaTripulacion vistaTripulacion;
    private VistaMisiones vistaMisiones;
    private VistaEstadoNave vistaRecursos;
    private Asistente asistente;
    //private final TIPOBASEDATOS basedatos;
    private Tripulante capitanAct;

    public Controlador(VistaInicioSesion vista) {
        this.vistaInicioSesion = vista;
        this.iniciaVistas();
    }

    private void iniciaVistas(){
        this.vistaRecursos = new VistaEstadoNave();
        this.vistaRecursos.setControlador(this);
        this.vistaMisiones = new VistaMisiones();
        this.vistaMisiones.setControlador(this);
        this.vistaRegistro = new VistaRegistro();
        this.vistaRegistro.setControlador(this);
        this.vistaMain = new BotonesMain();
        this.vistaMain.setControlador(this);
        this.vistaBitacora = new VistaBitacora();
        this.vistaBitacora.setControlador(this);
        this.vistaCrearNave = new VistaCrearNave();
        this.vistaCrearNave.setControlador(this);
        this.vistaGeneradores = new VistaGeneradores();
        this.vistaGeneradores.setControlador(this);
        this.vistaTripulacion = new VistaTripulacion();
        this.vistaTripulacion.setControlador(this);
    }

    public Asistente getAsistente() {
        return asistente;
    }

//    public TIPOBASEDATOS getBasedatos() {
//        return basedatos;
//    }


    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        analizaCambio();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        analizaCambio();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        analizaCambio();
    }

    private void analizaCambio() {
        if (!vistaInicioSesion.getTextUsuario().getText().isEmpty() && vistaInicioSesion.getPasswordField().getPassword().length > 0)
            vistaInicioSesion.getBtnInicioSesion().setEnabled(true);
        else
            vistaInicioSesion.getBtnInicioSesion().setEnabled(false);

        if (!vistaRegistro.getTextUsuario().getText().isEmpty() && vistaRegistro.getPasswordField().getPassword().length > 0)
            vistaRegistro.getBtnRegistro().setEnabled(true);
        else
            vistaRegistro.getBtnRegistro().setEnabled(false);

        if (vistaCrearNave.getButtonGroupNaves().getSelection()!=null)
            if (!vistaCrearNave.getTxtNombreNave().getText().isEmpty()){
                vistaCrearNave.getBtnCrear().setEnabled(true);
            }else
                vistaCrearNave.getBtnCrear().setEnabled(false);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(VistaInicioSesion.INICIO_SESION)) {
            //metodo para chequear si existen datos en db
            //metodo para rescatar el usuario ( que sera el capitan de la nave) y usarlo
            //capitanAct = usuarioRecuperado;
            vistaInicioSesion.dispose();
            vistaCrearNave.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaInicioSesion.ACCESO_REGISTRO)) {
            vistaInicioSesion.dispose();
            vistaRegistro.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaRegistro.REGISTRO_USER)) {
            //metodo para registrar usuario
            //metodo para rescatar el usuario ( que sera el capitan de la nave) y usarlo
            asignaCapitan();
            vistaRegistro.dispose();
            vistaCrearNave.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.CARGAR_COMBUSTIBLE))
            try{
                asistente.cargaCombustibleNave(Float.parseFloat(vistaRecursos.getTxtCantidadCombustible().getText()));
                actualizaBitacora();
                actualizaRecursosNave();
            } catch (CantCombustibleInvalidaException e) {
                //new error window
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.CARGAR_ENERGIA))
            try {
                asistente.cargaEnergiaNave(Float.parseFloat(vistaRecursos.getTxtCantidadEnergia().getText()));
                actualizaBitacora();
                actualizaRecursosNave();
            }catch (CantEnergiaInvalidaException e){
                //new error window
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.LLENAR_COMBUSTIBLE))
            try{
                asistente.cargaComustibleNaveFull();
                actualizaBitacora();
                actualizaRecursosNave();
            } catch (CantCombustibleInvalidaException e) {
                //new error window
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.LLENAR_ENERGIA))
            try {
                asistente.cargaEnergiaNaveFull();
                actualizaBitacora();
                actualizaRecursosNave();
            }catch (CantEnergiaInvalidaException e){
                //new error window
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.MANTENIMIENTO))
            try {
                asistente.reparaDesgasteNaveFull();
                actualizaBitacora();
                actualizaRecursosNave();
            }catch (CantDesgasteInvalidaException e) {
                //new erro windows
            }


        if (actionEvent.getActionCommand().equals(VistaCrearNave.CREAR_NAVE)){
            try{
                Nave nave = null;
                if (vistaCrearNave.getRadioCarguera().isSelected()){
                    nave = FactoryNaves.getTipo(FactoryNaves.TIPO_CARGUERO);
                }
                if (vistaCrearNave.getRadioCombate().isSelected()){
                    nave = FactoryNaves.getTipo(FactoryNaves.TIPO_COMBATE);
                }
                if (vistaCrearNave.getRadioExploradora().isSelected()){
                    nave = FactoryNaves.getTipo(FactoryNaves.TIPO_EXPLORADORA);
                }
                asistente = new Asistente(nave);
                nave.setName(vistaCrearNave.getTxtNombreNave().getText());
                nave.agregaTripulante(capitanAct);
            }catch (TipoNaveInvalidoException e){
                //new vistaError?
            }catch (ErrorAgregarTripulacionException e){
                //new  vistaError?
            }
            asistente.getBitacorasNave().agregarRegistro(new RegistroBitacora("NAVE CREADA"));
            actualizaRecursosNave();
            actualizaBitacora();
            vistaCrearNave.dispose();
            vistaMain.getLblCapitan().setText("Capitan "+ capitanAct.getNombre());
            vistaMain.getLblNombreNave().setText((asistente.getNave().getName()).toUpperCase());
            vistaMain.arranca();
        }
        String auxVentana = actionEvent.getActionCommand();
        switch (auxVentana) {
            case BotonesMain.ACCESO_BITACORA -> vistaBitacora.arranca();
            case BotonesMain.ACCESO_MISIONES -> vistaMisiones.arranca();
            case BotonesMain.ACCESO_TRIPULACION -> vistaTripulacion.arranca();
            case BotonesMain.ACCESO_VISOR_PARALELO -> vistaGeneradores.arranca();
            case BotonesMain.ACCESO_RECURSOS -> vistaRecursos.arranca();
            case BotonesMain.RESET_GAME -> resetGame();
        }
    }

    private void resetGame(){
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
        vistaCrearNave.getTxtNombreNave().setText("");
        vistaCrearNave.getButtonGroupNaves().clearSelection();
        vistaCrearNave.arranca();
    }

    //este es temproral, luego se usaria el de abajo para pasarlo el dto
    private void asignaCapitan() {
        try{
            String origen = (String) vistaRegistro.getComboOrigen().getSelectedItem();
            String nombre = vistaRegistro.getTextUsuario().getText();
            this.capitanAct = FactoryPeople.creaPersonaje(FactoryPeople.CAPITAN, origen, nombre, 0);
        }catch (ErrorCreacionPersonajeException e) {
            //new errorwindows
        }
    }

//    private void asignaCapitan(Tripulante cap ){
//
//    }

    private void actualizaRecursosNave(){
        vistaRecursos.getBarraCombustible().setValue((int)asistente.getNave().getCombustible());
        vistaRecursos.getBarraDesgaste().setValue((int)asistente.getNave().getDesgaste());
        vistaRecursos.getBarraEnergia().setValue((int)asistente.getNave().getEnergia());
    }

    private void actualizaBitacora(){
        List<RegistroBitacora> bitacoras = asistente.getBitacorasNave().getRegistros();
        vistaBitacora.getAreaConsola().setText("Registro de bitacoras de " + (asistente.getNave().getName()).toUpperCase() + "\nCapitaneada por " + (capitanAct.getNombre()).toUpperCase());
        for (RegistroBitacora bitacora : bitacoras)
            vistaBitacora.getAreaConsola().setText(vistaBitacora.getAreaConsola().getText() + "\n" + bitacora.toString());

    }

}
