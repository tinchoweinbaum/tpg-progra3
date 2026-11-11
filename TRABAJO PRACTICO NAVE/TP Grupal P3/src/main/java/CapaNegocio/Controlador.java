package CapaNegocio;

import CapaPresentacion.*;
import asistentes.*;
import bitacora.RegistroBitacora;
import exceptions.*;
import motorwarp.MotorWarp;
import nave.*;
import persistencia.Usuario;
import tripulantes.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.IllegalFormatException;
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
    private VistaCrearTripulante vistaCrearTripulante;
    private Asistente asistente;
    //private final TIPOBASEDATOS basedatos;
    private Tripulante capitanAct;

    public Controlador(VistaInicioSesion vista) {
        this.vistaInicioSesion = vista;
        this.iniciaVistas();
    }

    private void iniciaVistas(){
        this.vistaCrearTripulante = new VistaCrearTripulante();
        this.vistaCrearTripulante.setControlador(this);
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
            if (!vistaCrearNave.getTxtNombreNave().getText().isEmpty() && !vistaCrearNave.getTxtAntiguedadCapitan().getText().isEmpty() && !vistaCrearNave.getTxtNombreCapitan().getText().isEmpty()) {
                vistaCrearNave.getBtnCrear().setEnabled(true);
            } else
                vistaCrearNave.getBtnCrear().setEnabled(false);

        if (!vistaCrearTripulante.getTxtAntiguedadTripulante().getText().isEmpty() && !vistaCrearTripulante.getTxtNombreTripulante().getText().isEmpty())
            vistaCrearTripulante.getBtnCrear().setEnabled(true);
        else
            vistaCrearTripulante.getBtnCrear().setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getActionCommand().equals(VistaInicioSesion.INICIO_SESION)) {
            //metodo para chequear si existen datos en db
            //metodo para rescatar el usuario ( que sera el capitan de la nave) y usarlo
            //capitanAct = usuarioRecuperado;
            //podria ponerse en un try catch si se recupera el objeto y sino tira una vista emergente
            vistaInicioSesion.dispose();
            ventanaEmergente("INICIO DE SESION EXITOSO","");
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
            ventanaEmergente("REGISTRO EXITOSO","");
            vistaCrearNave.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.CARGAR_COMBUSTIBLE))
            try{
                asistente.cargaCombustibleNave(Float.parseFloat(vistaRecursos.getTxtCantidadCombustible().getText()));
                actualizaRecursosNave();
                ventanaEmergente("CARGA DE COMBUSTIBLE EXITOSA","");
            } catch (CantCombustibleInvalidaException e) {
                ventanaEmergente(e.getMessage(),"ERROR");
            } catch (NumberFormatException e) {
                ventanaEmergente("Formato Invalido","ERROR");
            }finally{
                vistaRecursos.getTxtCantidadCombustible().setText("");
                actualizaBitacora();
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.CARGAR_ENERGIA))
            try {
                asistente.cargaEnergiaNave(Float.parseFloat(vistaRecursos.getTxtCantidadEnergia().getText()));
                actualizaRecursosNave();
                ventanaEmergente("CARGA DE ENERGIA EXITOSA","");
            }catch (CantEnergiaInvalidaException e){
                ventanaEmergente(e.getMessage(),"ERROR");
            } catch (NumberFormatException e) {
                ventanaEmergente("Formato Invalido","ERROR");
            }
            finally {
                vistaRecursos.getTxtCantidadEnergia().setText("");
                actualizaBitacora();
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.LLENAR_COMBUSTIBLE))
            try{
                asistente.cargaComustibleNaveFull();
                actualizaRecursosNave();
                vistaRecursos.getTxtCantidadCombustible().setText("");
                ventanaEmergente("CARGA DE COMBUSTIBLE EXITOSA","");
            } catch (CantCombustibleInvalidaException e) {
                ventanaEmergente(e.getMessage(),"ERROR");
            }finally {
                vistaRecursos.getTxtCantidadCombustible().setText("");
                actualizaBitacora();
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.LLENAR_ENERGIA))
            try {
                asistente.cargaEnergiaNaveFull();
                actualizaRecursosNave();
                vistaRecursos.getTxtCantidadEnergia().setText("");
                ventanaEmergente("CARGA DE ENERGIA EXITOSA","");
            }catch (CantEnergiaInvalidaException e){
                ventanaEmergente(e.getMessage(),"ERROR");
            }finally {
                vistaRecursos.getTxtCantidadEnergia().setText("");
                actualizaBitacora();
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.MANTENIMIENTO))
            try {
                asistente.reparaDesgasteNaveFull();
                actualizaBitacora();
                actualizaRecursosNave();
                ventanaEmergente("MANTENIMIENTO EXITOSO","");
            }catch (CantDesgasteInvalidaException e) {
                ventanaEmergente(e.getMessage(),"ERROR");
            }

        if (actionEvent.getActionCommand().equals(VistaCrearNave.CREAR_NAVE)){
            try{
                comienzaPartida();
                actualizaVisorMotor();
            }catch (TipoNaveInvalidoException | ErrorAgregarTripulacionException | ErrorCreacionPersonajeException e){
                ventanaEmergente(e.getMessage(),"ERROR");
                resetGame();
            }catch(NumberFormatException e){
                ventanaEmergente("Formato invalido - Antigüedad","ERROR");
                resetGame();
            }

        }

        if (actionEvent.getActionCommand().equals(VistaTripulacion.AGREGA_TRIPULANTE)){
            vistaCrearTripulante.getTxtNombreTripulante().setText("");
            vistaCrearTripulante.getTxtAntiguedadTripulante().setText("");
            vistaCrearTripulante.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaCrearTripulante.CREAR_TRIPULANTE)){
            try {
                asistente.getNave().agregaTripulante(agregaTripulante());
                ventanaEmergente("SE AGREGO CORRECTAMENTE EL TRIPULANTE","");
            }catch(ErrorCreacionPersonajeException | ErrorAgregarTripulacionException e){
                ventanaEmergente(e.getMessage(),"ERROR");
            }
            finally {
                vistaCrearTripulante.dispose();
                actualizaVisorTripulantes();
            }
        }

        if (actionEvent.getActionCommand().equals(VistaTripulacion.BORRA_TRIPULANTE)){
            Tripulante auxTrip = vistaTripulacion.getListaTripulantes().getSelectedValue();
            if (auxTrip!=null) {
                asistente.getNave().eliminaTripulante(auxTrip);
                if(auxTrip.esCapitan()){
                    ventanaEmergente("FIN DEL JUEGO - LA NAVE NO TIENE CAPITAN","ERROR");
                    resetGame();
                }else{
                    ventanaEmergente("TRIPULANTE DESPEDIDO", "");
                    actualizaVisorTripulantes();
                }
            }else{
                ventanaEmergente("Debe seleccionar un tripulante","ERROR");
            }
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

    private void actualizaVisorTripulantes(){
        vistaTripulacion.getModeloLista().clear();
        for(Tripulante t:asistente.getNave().getTripulacion())
            vistaTripulacion.getModeloLista().addElement(t);
    }

    private Tripulante agregaTripulante() throws ErrorCreacionPersonajeException {
        String nombre = vistaCrearTripulante.getTxtNombreTripulante().getText();
        String origen = (String) vistaCrearTripulante.getComboOrigenTripulante().getSelectedItem();
        String cargo = (String) vistaCrearTripulante.getComboCargoTripulante().getSelectedItem();
        int antiguedad = Integer.parseInt(vistaCrearTripulante.getTxtAntiguedadTripulante().getText());
        return FactoryPeople.creaPersonaje(cargo,origen,nombre,antiguedad);
    }

    private void comienzaPartida() throws NumberFormatException, TipoNaveInvalidoException, ErrorAgregarTripulacionException, ErrorCreacionPersonajeException {
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
        asignaCapitan();
        nave.agregaTripulante(capitanAct);
        asistente.getBitacorasNave().agregarRegistro(new RegistroBitacora("NAVE CREADA"));
        ventanaEmergente("NAVE ASIGNADA CORRECTAMENTE","");
        actualizaRecursosNave();
        actualizaBitacora();
        vistaCrearNave.dispose();
        vistaMain.getLblCapitan().setText("Capitan "+ capitanAct.getNombre());
        vistaMain.getLblNombreNave().setText((asistente.getNave().getName()).toUpperCase());
        actualizaVisorTripulantes();
        vistaMain.arranca();
    }

    private void resetGame(){
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
        vistaCrearNave.getTxtNombreNave().setText("");
        vistaCrearNave.getTxtAntiguedadCapitan().setText("");
        vistaCrearNave.getTxtNombreCapitan().setText("");
        vistaCrearNave.getButtonGroupNaves().clearSelection();
        vistaCrearNave.arranca();
    }

    //TIENE QUE SER OBSERVER - OBSERVABLE XDXD
    //el visro es observer de hilo del motor , ver motor como runnable , lo que observa es justamente el motor que el motor llama a notifcar visor
    private void actualizaVisorMotor(){
        String estado = "";
        Color color = null;
        switch(asistente.getMotor().getEstadoActual().getIdEstado()){
            case MotorWarp.DISPONIBLE:
                estado = "DISPONIBLE";
                color = new Color(46, 139, 87);
                break;
            case MotorWarp.PREPARANDO_SALTO:
                estado = "PREPARANDO SALTO";
                color = new Color(230, 145, 56);
                break;
            case MotorWarp.SALTO_WARP:
                estado = "EN SALTO WARP";
                color = new Color(30, 144, 255);
                break;
            case MotorWarp.ENFRIAMIENTO:
                estado = "ENFRIANDO MOTOR";
                color = new Color(95, 158, 160);
                break;
        }
        vistaRecursos.getBarraEstado().setString(estado);
        vistaRecursos.getBarraEstado().setForeground(color);
    }

    private void asignaCapitan() throws ErrorCreacionPersonajeException , NumberFormatException {
        String origen = (String) vistaCrearNave.getComboOrigenCapitan().getSelectedItem();
        String nombre = vistaCrearNave.getTxtNombreCapitan().getText();
        int antiguedad = Integer.parseInt(vistaCrearNave.getTxtAntiguedadCapitan().getText());
        this.capitanAct = FactoryPeople.creaPersonaje(FactoryPeople.CAPITAN, origen, nombre, antiguedad);
    }

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

    public void ventanaEmergente(String mensaje,String icono) {
        VistaExcepcion ventana = new VistaExcepcion();
        if (icono.equals("ERROR"))
            ventana.getLblIcono().setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        else
            ventana.getLblIcono().setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        ventana.getLblMensaje().setText("<html><body style='width: 220px'>" + mensaje + "</body></html>");
        ventana.arranca();
    }



}
