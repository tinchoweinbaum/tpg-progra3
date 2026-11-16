package CapaNegocio;

import CapaPresentacion.*;
import asistentes.*;
import bitacora.RegistroBitacora;
import destinoytablero.*;
import elementosUniverso.*;
import exceptions.*;
import misiones.Mision;
import misiones.Mision1;
import motorwarp.MotorWarp;
import nave.*;
import persistencia.Usuario;
import tripulantes.*;
import visorParalelo.Visor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Random;

public class Controlador implements DocumentListener, ActionListener , Observador {
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
    private Tablero tablero;
    private ArrayList<ElementoDelUniverso> elementosUni = new ArrayList<>();

    private Enlazador enlazador;

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
        this.vistaTripulacion = new VistaTripulacion();
        this.vistaTripulacion.setControlador(this);
    }

    private void setEnlazador(Enlazador e){
        this.enlazador = e;
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
                enlazador.cargaCombustibleNave(Float.parseFloat(vistaRecursos.getTxtCantidadCombustible().getText()));
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
                enlazador.cargaEnergiaNave(Float.parseFloat(vistaRecursos.getTxtCantidadEnergia().getText()));
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
                enlazador.cargaCombustibleNaveFull();
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
                enlazador.cargaEnergiaNaveFull();
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
                enlazador.reparaDesgasteNaveFull();
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
                enlazador.getNave().agregaTripulante(agregaTripulante());
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
                enlazador.getNave().eliminaTripulante(auxTrip);
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

        if (actionEvent.getActionCommand().equals(VistaGeneradores.PRODUCE_BATERIA)){
            vistaGeneradores.getAreaConsolaMini().append("\n-INGRESO UNA NUEVA NAVE PRODUCTORA-");
            enlazador.nuevaGeneradora();
        }

        if (actionEvent.getActionCommand().equals(VistaGeneradores.PRODUCE_NAVE)){
            vistaGeneradores.getAreaConsolaMini().append("\n-INGRESO UNA NUEVA NAVE CONSUMIDORA-");
            enlazador.nuevaConsumidora();
        }

        if (actionEvent.getActionCommand().equals(VistaMisiones.SELECCIONA_MISION)){
            Mision mAct = null;
            if (vistaMisiones.getRadioOne().isSelected()){
                mAct = new Mision1();
            }
            if (vistaMisiones.getRadioTwo().isSelected()){

            }
            if (vistaMisiones.getRadioThree().isSelected()){

            }
            if (mAct != null) {
                agregaTripulantePrueba();  //despues borrar
                ejecucionMision();
                mAct.agregarObservador(this);
                vistaMain.setTitle("PROCESANDO... " + mAct.getNombre() + " " + mAct.getDescripcion());
                mAct.ejecutarMision(enlazador.getAsistente());
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

    private void agregaTripulantePrueba() {
        try {
            enlazador.agregaTripulante(FactoryPeople.creaPersonaje(FactoryPeople.TENIENTE,FactoryPeople.MARCIANO,"pedro",1));
            enlazador.agregaTripulante(FactoryPeople.creaPersonaje(FactoryPeople.TENIENTE,FactoryPeople.MARCIANO,"pedro",1));
            enlazador.agregaTripulante(FactoryPeople.creaPersonaje(FactoryPeople.TENIENTE,FactoryPeople.MARCIANO,"pedro",1));
            enlazador.agregaTripulante(FactoryPeople.creaPersonaje(FactoryPeople.TENIENTE,FactoryPeople.MARCIANO,"pedro",1));
        } catch (ErrorCreacionPersonajeException e) {
            //
        } catch (ErrorAgregarTripulacionException e) {
            //
        }
    }

    private void creaTablero(){
        try{
            iniciaElementosUni();
            tablero = new Tablero(20,20,elementosUni);
        } catch (CantidadDeElementosSuperaCasillaException | DimensionesDeTableroInconsistentesException e) {
            ventanaEmergente(e.getMessage(),"");
        }
    }

    private void iniciaElementosUni() {
        Random aleatorio = new Random();
        elementosUni.clear();
        ElementoDelUniverso aux = null;
        for (int i=0; i<300; i++){
            int opcion = aleatorio.nextInt(5)+1;//cant de elementos
            switch (opcion){
                case 1 -> aux = new Asteroide();
                case 2 -> aux = new ElementoNaveCarguera();
                case 3 -> aux = new ElementoNaveCombate();
                case 4 -> aux = new ElementoNaveExploradora();
                case 5 -> aux = new EstacionCarga();
            }
            elementosUni.add(aux);
        }
    }

    private void ejecucionMision(){
        vistaGeneradores.dispose();
        vistaTripulacion.dispose();
        vistaCrearTripulante.dispose();
        vistaMisiones.dispose();
        vistaMain.getBotonBorraCrearNave().setEnabled(false);
        vistaMain.getBotonMisiones().setEnabled(false);
        vistaMain.getBotonTripulacion().setEnabled(false);
        vistaMain.getBotonVisorParalelo().setEnabled(false);
    }

    private void finMision(){
        vistaMain.getBotonBorraCrearNave().setEnabled(true);
        vistaMain.getBotonMisiones().setEnabled(true);
        vistaMain.getBotonTripulacion().setEnabled(true);
        vistaMain.getBotonVisorParalelo().setEnabled(true);
        vistaMain.setTitle("PANEL PRINCIPAL");
    }

    private void actualizaVisorTripulantes(){
        vistaTripulacion.getModeloLista().clear();
        for(Tripulante t:enlazador.getNave().getTripulacion())
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
        creaTablero();
        nave.setName(vistaCrearNave.getTxtNombreNave().getText());
        nave.agregaTripulante(asignaCapitan());
        vistaCrearNave.dispose();
        ventanaEmergente("NAVE ASIGNADA CORRECTAMENTE","");
        setEnlazador(new Enlazador(new Asistente(nave,tablero),iniciaUniversoParalelo()));
        actualizaRecursosNave();
        actualizaBitacora();
        actualizaVisorTripulantes();
        vistaMain.getLblCapitan().setText("Capitan "+ enlazador.getNave().getCapitan().getNombre());
        vistaMain.getLblNombreNave().setText((enlazador.getNave().getName()).toUpperCase());
        vistaMain.arranca();
    }

    public void actualizaVisorParalelo(String text){
        vistaGeneradores.getAreaConsolaMini().append("\n#"+text+"\n@-@-@-@-@-@-@");
    }

    private Visor iniciaUniversoParalelo(){
        this.vistaGeneradores = new VistaGeneradores();
        this.vistaGeneradores.setControlador(this);
        return new Visor(this);
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
        switch(enlazador.getMotor().getEstadoActual().getIdEstado()){
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

    private Tripulante asignaCapitan() throws ErrorCreacionPersonajeException , NumberFormatException {
        String origen = (String) vistaCrearNave.getComboOrigenCapitan().getSelectedItem();
        String nombre = vistaCrearNave.getTxtNombreCapitan().getText();
        int antiguedad = Integer.parseInt(vistaCrearNave.getTxtAntiguedadCapitan().getText());
        return FactoryPeople.creaPersonaje(FactoryPeople.CAPITAN, origen, nombre, antiguedad);
    }

    private void actualizaRecursosNave(){
        vistaRecursos.getBarraCombustible().setValue((int)enlazador.getNave().getCombustible());
        vistaRecursos.getBarraDesgaste().setValue((int)enlazador.getNave().getDesgaste());
        vistaRecursos.getBarraEnergia().setValue((int)enlazador.getNave().getEnergia());
    }

    private void actualizaBitacora(){
        List<RegistroBitacora> bitacoras = enlazador.getBitacorasNave().getRegistros();
        vistaBitacora.getAreaConsola().setText("Registro de bitacoras de " + (enlazador.getNave().getName()).toUpperCase() + "\nCapitaneada por " + (enlazador.getNave().getCapitan().getNombre()).toUpperCase());
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


    @Override
    public void actualizar(String mensaje) {
        if (mensaje.equals("FIN_MISION")) {
            actualizaBitacora();
            finMision();
        }else if(mensaje.equals("HOLA")){
            ventanaEmergente("FFF","");
        }else if (mensaje.equals("GAME_OVER")){
            ventanaEmergente("GAME OVER","ERROR");
            vistaMain.getBotonBorraCrearNave().setEnabled(true);
        }

    }

}
