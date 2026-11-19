package CapaNegocio;

import CapaPresentacion.*;
import asistentes.*;
import bitacora.RegistroBitacora;
import destinoytablero.*;
import elementosUniverso.*;
import exceptions.*;
import misiones.Mision;
import misiones.Mision1;
import misiones.Mision2;
import misiones.Mision3;
import motorwarp.MotorWarp;
import nave.*;
import persistencia.Usuario;
import persistencia.UsuariosDAO;
import tripulantes.*;
import visorParalelo.Visor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    private VistaMapa vistaMapa;
    private Tablero tablero;
    private ArrayList<ElementoDelUniverso> elementosUni = new ArrayList<>();

    private Enlazador enlazador;

    private UsuariosDAO dao;

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
            // No se tiene que encargar el modelo de esto???? va  acá en el controller???
            String username = vistaInicioSesion.getTextUsuario().getText();
            String pass = vistaInicioSesion.getPasswordField().getText();

            Usuario usuarioRecuperado = dao.buscarPorUsername(username);

            if(usuarioRecuperado == null){
                ventanaEmergente("No se encontró el usuario ingresado, intente de nuevo.", "ERROR");
                return;
            }

            // --- Sesión iniciada ---
            vistaInicioSesion.dispose();
            ventanaEmergente("INICIO DE SESION EXITOSO");
            vistaCrearNave.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaInicioSesion.ACCESO_REGISTRO)) {
            // Checkeo en dao si ya existe un usuario con el username ingresado.
            vistaInicioSesion.dispose();
            vistaRegistro.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaRegistro.REGISTRO_USER)) {
            //metodo para registrar usuario
            //metodo para rescatar el usuario ( que sera el capitan de la nave) y usarlo

            vistaRegistro.dispose();
            ventanaEmergente("REGISTRO EXITOSO");
            vistaCrearNave.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.CARGAR_COMBUSTIBLE))
            try{
                enlazador.cargaCombustibleNave(Float.parseFloat(vistaRecursos.getTxtCantidadCombustible().getText()));
                ventanaEmergente("CARGA DE COMBUSTIBLE EXITOSA");
            } catch (CantCombustibleInvalidaException e) {
                ventanaEmergente(e.getMessage(),"ERROR");
            } catch (NumberFormatException e) {
                ventanaEmergente("Formato Invalido","ERROR");
            }finally{
                vistaRecursos.getTxtCantidadCombustible().setText("");
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.CARGAR_ENERGIA))
            try {
                enlazador.cargaEnergiaNave(Float.parseFloat(vistaRecursos.getTxtCantidadEnergia().getText()));
                ventanaEmergente("CARGA DE ENERGIA EXITOSA");
            }catch (CantEnergiaInvalidaException e){
                ventanaEmergente(e.getMessage(),"ERROR");
            } catch (NumberFormatException e) {
                ventanaEmergente("Formato Invalido","ERROR");
            }
            finally {
                vistaRecursos.getTxtCantidadEnergia().setText("");
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.LLENAR_COMBUSTIBLE))
            try{
                enlazador.cargaCombustibleNaveFull();
                vistaRecursos.getTxtCantidadCombustible().setText("");
                ventanaEmergente("CARGA DE COMBUSTIBLE EXITOSA");
            } catch (CantCombustibleInvalidaException e) {
                ventanaEmergente(e.getMessage(),"ERROR");
            }finally {
                vistaRecursos.getTxtCantidadCombustible().setText("");
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.LLENAR_ENERGIA))
            try {
                enlazador.cargaEnergiaNaveFull();
                vistaRecursos.getTxtCantidadEnergia().setText("");
                ventanaEmergente("CARGA DE ENERGIA EXITOSA");
            }catch (CantEnergiaInvalidaException e){
                ventanaEmergente(e.getMessage(),"ERROR");
            }finally {
                vistaRecursos.getTxtCantidadEnergia().setText("");
            }

        if (actionEvent.getActionCommand().equals(VistaEstadoNave.MANTENIMIENTO))
            try {
                enlazador.reparaDesgasteNaveFull();
                ventanaEmergente("MANTENIMIENTO EXITOSO");
            }catch (CantDesgasteInvalidaException e) {
                ventanaEmergente(e.getMessage(),"ERROR");
            }

        if (actionEvent.getActionCommand().equals(VistaCrearNave.CREAR_NAVE)){
            try{
                comienzaPartida();
                actualizaVisorMotor(0);
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
                ventanaEmergente("SE AGREGO CORRECTAMENTE EL TRIPULANTE");
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
                    ventanaEmergente("TRIPULANTE DESPEDIDO");
                    actualizaVisorTripulantes();
                }
            }else{
                ventanaEmergente("Debe seleccionar un tripulante","ERROR");
            }
        }

        if (actionEvent.getActionCommand().equals(VistaGeneradores.PRODUCE_BATERIA)){
            vistaGeneradores.getAreaConsolaMini().append("\n \n-INGRESO UNA NUEVA NAVE PRODUCTORA-");
            enlazador.nuevaGeneradora();
        }

        if (actionEvent.getActionCommand().equals(VistaGeneradores.PRODUCE_NAVE)){
            vistaGeneradores.getAreaConsolaMini().append("\n \n-INGRESO UNA NUEVA NAVE CONSUMIDORA-");
            enlazador.nuevaConsumidora();
        }

        if (actionEvent.getActionCommand().equals(VistaMisiones.SELECCIONA_MISION)){
            Mision mAct = null;
            if (vistaMisiones.getRadioOne().isSelected()){
                mAct = new Mision1(enlazador.getTablero().obtenerObjetivo());
                enlazador.getBitacorasNave().agregarRegistro(new RegistroBitacora("LOCALIZANDO SEÑAL DE AUXILIO..."));
            }
            if (vistaMisiones.getRadioTwo().isSelected()){
                mAct = new Mision2(enlazador.getTablero().obtenerObjetivo());
                enlazador.getBitacorasNave().agregarRegistro(new RegistroBitacora("BUSCANDO COORDENADAS DEL TESORO..."));
            }
            if (vistaMisiones.getRadioThree().isSelected()){
                if(enlazador.getTablero().getPosicionNave().getY()!=enlazador.getTablero().getY()/2 && enlazador.getTablero().getPosicionNave().getX()!=enlazador.getTablero().getX()/2){
                    int xOrigen = enlazador.getTablero().getX()/2;
                    int yOrigen = enlazador.getTablero().getY()/2;
                    Posicion objetivoCentro = enlazador.getTablero().obtenerObjetivo();
                    enlazador.getBitacorasNave().agregarRegistro(new RegistroBitacora("LOCALIZANDO ESTACION ESPACIAL..."));
                    while (objetivoCentro.getX() != xOrigen || objetivoCentro.getY() != yOrigen)
                        objetivoCentro = enlazador.getTablero().obtenerObjetivo();
                    mAct = new Mision3(objetivoCentro);
                }else{
                    ventanaEmergente("TU NAVE SE VE BASTANTE BIEN - REALIZA UNA MISION Y VUELVE MAS TARDE");
                }
            }
            if (mAct != null) {
                agregaTripulantePrueba();  //despues borrar
                ejecucionMisionVista(mAct);
                mAct.agregarObservador(this);
                misionBackGround(mAct);
            }
        }

        String auxVentana = actionEvent.getActionCommand();
        switch (auxVentana) {
            case BotonesMain.ACCESO_BITACORA -> vistaBitacora.arranca();
            case BotonesMain.ACCESO_MISIONES -> vistaMisiones.arranca();
            case BotonesMain.ACCESO_TRIPULACION -> vistaTripulacion.arranca();
            case BotonesMain.ACCESO_VISOR_PARALELO -> vistaGeneradores.arranca();
            case BotonesMain.ACCESO_RECURSOS -> vistaRecursos.arranca();
            case BotonesMain.ACCESO_MAPA -> {
                vistaMapa.arranca();
                actualizacionVistaMapa();
            }
            case BotonesMain.RESET_GAME -> resetGame();
        }
    }

    private void misionBackGround(Mision mAct) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
            mAct.ejecutarMision(enlazador.getAsistente());
            return null;
            }
        };
        worker.execute();
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
            tablero.agregarObservador(this);
            vistaMapa = new VistaMapa(tablero.getX(),tablero.getY());
            vistaMapa.setControlador(this);
        } catch (CantidadDeElementosSuperaCasillaException | DimensionesDeTableroInconsistentesException e) {
            ventanaEmergente(e.getMessage());
        }
    }

    private void iniciaElementosUni() {
        Random aleatorio = new Random();
        elementosUni.clear();
        ElementoDelUniverso aux = null;
        for (int i=0; i<70; i++){
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

    private void ejecucionMisionVista(Mision mAct){
        vistaMain.setTitle("PROCESANDO... " + mAct.getNombre() + " " + mAct.getDescripcion());
        vistaGeneradores.dispose();
        vistaTripulacion.dispose();
        vistaCrearTripulante.dispose();
        vistaMisiones.dispose();
        if (!vistaBitacora.isShowing())
            vistaBitacora.arranca();
        if (!vistaRecursos.isShowing())
            vistaRecursos.arranca();
        vistaMapa.arranca();
        vistaMain.getBotonBorraCrearNave().setEnabled(false);
        vistaMain.getBotonMisiones().setEnabled(false);
        vistaMain.getBotonTripulacion().setEnabled(false);
        vistaMain.getBotonVisorParalelo().setEnabled(false);
        switchRecursosNave(false);
    }

    private void finVistaMision(){
        vistaMain.getBotonBorraCrearNave().setEnabled(true);
        vistaMain.getBotonMisiones().setEnabled(true);
        vistaMain.getBotonTripulacion().setEnabled(true);
        vistaMain.getBotonVisorParalelo().setEnabled(true);
        vistaMain.setTitle("PANEL PRINCIPAL");
        actualizacionVistaMapa();
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
        //CREACION NAVE
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
        nave.setName(vistaCrearNave.getTxtNombreNave().getText());
        nave.agregaTripulante(asignaCapitan());
        vistaCrearNave.dispose();
        ventanaEmergente("NAVE ASIGNADA CORRECTAMENTE");

        //TABLERO;ASISTENTE;ENLAZADOR;VISOR PARALELO
        creaTablero();
        setEnlazador(new Enlazador(new Asistente(nave,tablero),iniciaUniversoParalelo()));
        enlazador.getBitacorasNave().agregarObservador(this);
        enlazador.getMotor().agregarObservador(this);
        enlazador.getAsistente().getDestino().turnoDestino();

        //ARREGLO VISTAS
        switchRecursosNave(false);
        actualizacionVistaMapa();
        actualizaVistaRecursosNave();
        actualizaVistaBitacora();
        actualizaVisorTripulantes();
        vistaMainReactivada();
    }

    private void vistaMainReactivada() {
        vistaMain.getBotonMisiones().setEnabled(true);
        vistaMain.getBotonBitacora().setEnabled(true);
        vistaMain.getBotonTripulacion().setEnabled(true);
        vistaMain.getBotonVisorParalelo().setEnabled(true);
        vistaMain.getBotonRecursos().setEnabled(true);
        vistaMain.getBotonMapa().setEnabled(true);
        vistaMain.getLblCapitan().setText("Capitan "+ enlazador.getNave().getCapitan().getNombre());
        vistaMain.getLblNombreNave().setText((enlazador.getNave().getName()).toUpperCase());
        vistaMain.arranca();
    }

    public void actualizaVisorParalelo(String text){
        vistaGeneradores.getAreaConsolaMini().append("\n#"+text+"\n@------------");
        vistaGeneradores.getAreaConsolaMini().setCaretPosition(vistaGeneradores.getAreaConsolaMini().getDocument().getLength());
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

    private void actualizaVisorMotor(int i){
        String estado = "";
        Color color = null;
        switch(i){
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
            default:
                estado = "MOTOR DESTRUIDO";
                color = new Color(122, 7, 0);
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

    private void actualizaVistaRecursosNave(){
        vistaRecursos.getBarraCombustible().setValue((int)enlazador.getNave().getCombustible());
        vistaRecursos.getBarraDesgaste().setValue((int)enlazador.getNave().getDesgaste());
        vistaRecursos.getBarraEnergia().setValue((int)enlazador.getNave().getEnergia());
    }

    private void actualizaVistaBitacora() {
        // Creamos una copia instantánea para evitar ConcurrentModificationException
        List<RegistroBitacora> bitacoras = new ArrayList<>(enlazador.getBitacorasNave().getRegistros());

        // Usamos StringBuilder para armar el texto rápido y en memoria
        StringBuilder sb = new StringBuilder();
        sb.append("Registro de bitacoras de ")
                .append(enlazador.getNave().getName().toUpperCase())
                .append("\nCapitaneada por ")
                .append(enlazador.getNave().getCapitan().getNombre().toUpperCase());

        for (RegistroBitacora bitacora : bitacoras) {
            sb.append("\n").append(bitacora.toString());
        }

        // Actualizamos el componente visual una sola vez
        vistaBitacora.getAreaConsola().setText(sb.toString());
        vistaBitacora.getAreaConsola().setCaretPosition(vistaBitacora.getAreaConsola().getDocument().getLength());
    }

    private void ventanaEmergente(String mensaje){
        this.ventanaEmergente(mensaje,"");
    }

    private void ventanaEmergente(String mensaje,String icono) {
        VistaExcepcion ventana = new VistaExcepcion();
        if (icono.equals("ERROR"))
            ventana.getLblIcono().setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        else
            ventana.getLblIcono().setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        ventana.getLblMensaje().setText("<html><body style='width: 220px'>" + mensaje + "</body></html>");
        ventana.arranca();
    }

    private void puedeBitacora(){
        if (SwingUtilities.isEventDispatchThread()) {
            actualizaVistaBitacora();
            return;
        }

        // Si venimos del hilo secundario, frenamos el sistema hasta que la UI termine de pintar
        try {
            SwingUtilities.invokeAndWait(() -> {
                actualizaVistaBitacora();
            });
        } catch (Exception e) {
            ventanaEmergente("ERROR DESCONOCIDO");
        }
    }


    @Override
    public void actualizar(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            switch (mensaje) {
                case "FIN_MISION":
                    finVistaMision();
                    break;
                case "FIN_MISION_BASE":
                    ventanaEmergente("PUEDES REALIZAR EL MANTENIMIENTO DE LA NAVE");
                    switchRecursosNave(true);
                    finVistaMision();
                    break;
                case "ACTUALIZACION_VISTA_TABLERO":
                    actualizacionVistaMapa();
                    break;
                case "MOTOR_DISPONIBLE":
                    actualizaVisorMotor(MotorWarp.DISPONIBLE);
                    break;
                case "MOTOR_PREPARANDO":
                    actualizaVisorMotor(MotorWarp.PREPARANDO_SALTO);
                    break;
                case "MOTOR_SALTANDO":
                    actualizaVisorMotor(MotorWarp.SALTO_WARP);
                    break;
                case "MOTOR_ENFRIANDO":
                    actualizaVisorMotor(MotorWarp.ENFRIAMIENTO);
                    break;
                case "BITACORA":
                    puedeBitacora();
                    actualizaVistaRecursosNave();
                    break;
                case "GAME_OVER":
                    ventanaEmergente("GAME OVER", "ERROR");
                    vistasJuegoPerdido();
                    break;
            }
        });
    }

    private void switchRecursosNave(boolean estado) {
        vistaRecursos.getBtnMantenimiento().setEnabled(estado);
        vistaRecursos.getTxtCantidadCombustible().setEnabled(estado);
        vistaRecursos.getBtnCargarCombustible().setEnabled(estado);
        vistaRecursos.getBtnLlenarCombustible().setEnabled(estado);
        vistaRecursos.getTxtCantidadEnergia().setEnabled(estado);
        vistaRecursos.getBtnCargarEnergia().setEnabled(estado);
        vistaRecursos.getBtnLlenarEnergia().setEnabled(estado);
    }

    /**
     * Actualiza el mapa con imágenes de los elementos y colores que los representan para verlos facilmente.
     */
    private void actualizacionVistaMapa() {
        for (int i = 0; i < enlazador.getTablero().getX(); i++) {
            for (int j = 0; j < enlazador.getTablero().getY(); j++) {
                vistaMapa.getBotonCelda(i, j).actualizarIcono(null);
            }
        }

        // Definición de colores personalizados (R, G, B)
        Color colorRojizoEnemigo = new Color(235, 105, 105);
        Color colorVerdosoEstacion = new Color(144, 238, 144);
        Color colorVerdeObjetivo = new Color(46, 184, 92);

        ElementoDelUniverso[][] tableroAux = enlazador.getTablero().verTablero();
        ElementoDelUniverso actual;

        for (int i = 0; i < enlazador.getTablero().getX(); i++) {
            for (int j = 0; j < enlazador.getTablero().getY(); j++) {
                String rutaActual = "";
                actual = tableroAux[i][j];

                if (actual != null) {
                    vistaMapa.getBotonCelda(i, j).setBackground(null);
                    if (actual.equals(new Asteroide())) {
                        rutaActual = "src/main/java/CapaPresentacion/iconosFolder/asteroide.png";
                        vistaMapa.getBotonCelda(i, j).setBackground(Color.ORANGE);
                        vistaMapa.getBotonCelda(i, j).setToolTipText("ASTEROIDE");
                    }
                    if (actual.equals(new EstacionCarga())) {
                        rutaActual = "src/main/java/CapaPresentacion/iconosFolder/estacionEspacial.png";
                        vistaMapa.getBotonCelda(i, j).setBackground(colorVerdosoEstacion);
                        vistaMapa.getBotonCelda(i, j).setToolTipText("ESTACION DE CARGA");
                    }
                    if (actual.equals(new ElementoNaveExploradora())) {
                        rutaActual = "src/main/java/CapaPresentacion/iconosFolder/naveExploradora.png";
                        vistaMapa.getBotonCelda(i, j).setBackground(colorRojizoEnemigo);
                        vistaMapa.getBotonCelda(i, j).setToolTipText("NAVE EXPLORADORA");
                    }
                    if (actual.equals(new ElementoNaveCombate())) {
                        rutaActual = "src/main/java/CapaPresentacion/iconosFolder/naveCombate.png";
                        vistaMapa.getBotonCelda(i, j).setBackground(colorRojizoEnemigo);
                        vistaMapa.getBotonCelda(i, j).setToolTipText("NAVE DE COMBATE");
                    }
                    if (actual.equals(new ElementoNaveCarguera())) {
                        rutaActual = "src/main/java/CapaPresentacion/iconosFolder/naveCarga.png";
                        vistaMapa.getBotonCelda(i, j).setBackground(colorRojizoEnemigo);
                        vistaMapa.getBotonCelda(i, j).setToolTipText("NAVE DE CARGA");
                    }
                } else {
                    vistaMapa.getBotonCelda(i, j).setBackground(null);
                }

                if (enlazador.getTablero().getObjetivo() != null
                        && i == enlazador.getTablero().getObjetivo().getX()
                        && j == enlazador.getTablero().getObjetivo().getY()) {
                    rutaActual = "src/main/java/CapaPresentacion/iconosFolder/objetivo.png";
                    vistaMapa.getBotonCelda(i, j).setBackground(colorVerdeObjetivo);
                }

                if (i == enlazador.getTablero().getPosicionNave().getX()
                        && j == enlazador.getTablero().getPosicionNave().getY()) {
                    rutaActual = "src/main/java/CapaPresentacion/iconosFolder/naveUser.png";
                    vistaMapa.getBotonCelda(i, j).setBackground(Color.CYAN);
                }

                vistaMapa.getBotonCelda(i, j).actualizarIcono(rutaActual);
            }
        }
    }

    private void vistasJuegoPerdido() {
        enlazador.getNave().setCombustible(0);
        enlazador.getNave().setEnergia(0);
        enlazador.getNave().setDesgaste(Nave.MAX_DESGASTE);
        actualizaVistaRecursosNave();
        actualizaVisorMotor(5);
        actualizacionVistaMapa();
        vistaMain.getBotonBorraCrearNave().setEnabled(true);
        vistaMain.getBotonVisorParalelo().setEnabled(false);
        vistaMain.getBotonTripulacion().setEnabled(false);
        vistaMain.getBotonRecursos().setEnabled(false);
        vistaMain.getBotonBitacora().setEnabled(false);
        vistaMain.getBotonMisiones().setEnabled(false);
        vistaMain.getBotonMapa().setEnabled(false);

        int xNave = enlazador.getTablero().getPosicionNave().getX();
        int yNave = enlazador.getTablero().getPosicionNave().getY();

        vistaMapa.getBotonCelda(xNave,yNave).setBackground(new Color(0,0,0));
        vistaMapa.getBotonCelda(xNave,yNave).actualizarIcono("src/main/java/CapaPresentacion/iconosFolder/calavera.png");

    }

}
