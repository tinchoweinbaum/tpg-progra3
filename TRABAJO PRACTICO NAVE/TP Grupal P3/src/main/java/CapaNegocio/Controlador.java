package CapaNegocio;

import CapaPresentacion.*;
import asistentes.Asistente;
import exceptions.TipoNaveInvalidoException;
import nave.FactoryNaves;
import nave.Nave;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        if (!vistaCrearNave.getTxtNombreNave().getText().isEmpty() && vistaCrearNave.getButtonGroupNaves().getSelection().isSelected()){
            vistaCrearNave.getBtnCrear().setEnabled(true);
        }else
            vistaCrearNave.getBtnCrear().setEnabled(false);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(VistaInicioSesion.INICIO_SESION)) {
            //metodo para chequear si existen datos en db
            //metodo para rescatar el usuario ( que sera el capitan de la nave) y usarlo
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
            vistaRegistro.dispose();
            vistaCrearNave.arranca();
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
            }catch (TipoNaveInvalidoException e){
                //new vistaError?
            }
            vistaCrearNave.dispose();
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
        vistaMain.dispose();
        vistaCrearNave.getTxtNombreNave().setText("");
        vistaCrearNave.getButtonGroupNaves().clearSelection();
        vistaCrearNave.arranca();
    }

}
