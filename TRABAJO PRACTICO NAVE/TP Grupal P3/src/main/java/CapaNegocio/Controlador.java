package CapaNegocio;

import CapaPresentacion.*;
import CapaDatos.asistentes.*;
import CapaDatos.bitacora.RegistroBitacora;
import CapaDatos.destinoytablero.*;
import CapaDatos.elementosUniverso.*;
import CapaDatos.exceptions.*;
import CapaDatos.misiones.*;
import CapaDatos.motorwarp.MotorWarp;
import CapaDatos.nave.*;
import CapaDatos.persistencia.*;
import CapaDatos.tripulantes.*;
import CapaDatos.visorParalelo.Visor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Controlador principal de la aplicación encargado de gestionar la interacción entre las vistas y la lógica de negocio.
 * <p><b>Invariante de clase:</b> enlazador != null && elementosUni != null</p>
 */
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


    /**
     * Construye una instancia del Controlador asociando la vista inicial, configurando las vistas secundarias
     * e inicializando el enlazador con la persistencia SQL. Si falla la inicialización de la base de datos,
     * muestra una ventana emergente de error.
     *
     * @param vista Vista inicial de inicio de sesión.
     * @pre vista != null
     * @post Se inicializan todas las vistas secundarias y, si no ocurre una DAOAuxiliarException, se instancia el enlazador con un UsuariosSqlDao; en caso de error, se despliega una ventana emergente informando el fallo.
     */
    public Controlador(VistaInicioSesion vista) {
        try{
            this.vistaInicioSesion = vista;
            this.iniciaVistas();
            this.enlazador = new Enlazador(new UsuariosSqlDao());
        } catch (DAOAuxiliarException e) {
            ventanaEmergente(e.getMessage(),"ERROR");
        }
    }

    /**
     * Instancia todas las vistas del sistema y les asigna este controlador.
     *
     * @pre true
     * @post Todas las vistas (excepto vistaInicioSesion, vistaGeneradores y vistaMapa) quedan instanciadas y enlazadas al controlador.
     */
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

    public Enlazador getEnlazador() {
        return enlazador;
    }

    /**
     * Detecta la inserción de texto en los documentos escuchados y evalúa el estado de los botones.
     *
     * @param documentEvent Evento de modificación del documento.
     * @pre documentEvent != null
     * @post Se actualiza la habilitación de los botones de confirmación en las vistas de formulario.
     */
    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        analizaCambio();
    }

    /**
     * Detecta la eliminación de texto en los documentos escuchados y evalúa el estado de los botones.
     *
     * @param documentEvent Evento de modificación del documento.
     * @pre documentEvent != null
     * @post Se actualiza la habilitación de los botones de confirmación en las vistas de formulario.
     */
    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        analizaCambio();
    }

    /**
     * Detecta cambios de atributos en los documentos escuchados y evalúa el estado de los botones.
     *
     * @param documentEvent Evento de modificación del documento.
     * @pre documentEvent != null
     * @post Se actualiza la habilitación de los botones de confirmación en las vistas de formulario.
     */
    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        analizaCambio();
    }

    /**
     * Valida si los campos obligatorios de los formularios activos tienen contenido para habilitar o deshabilitar sus botones de acción.
     *
     * @pre vistaInicioSesion != null && vistaRegistro != null && vistaCrearNave != null && vistaCrearTripulante != null
     * @post Los botones de envío de cada vista quedan habilitados únicamente si sus respectivos campos requeridos no están vacíos.
     */
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

    /**
     * Gestiona todos los eventos de acción disparados por los botones y controles de las distintas vistas.
     *
     * @param actionEvent Evento de acción capturado.
     * @pre actionEvent != null && actionEvent.getActionCommand() != null
     * @post Se ejecuta la lógica de negocio y actualización de interfaz correspondiente al comando recibido.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getActionCommand().equals(VistaInicioSesion.INICIO_SESION)) {
            String usuarioIngresado = vistaInicioSesion.getTextUsuario().getText();
            String passIngresada = vistaInicioSesion.getPasswordField().getText();

            try {
                enlazador.validaLoginUsuario(usuarioIngresado, passIngresada);

                vistaInicioSesion.dispose();
                ventanaEmergente("INICIO DE SESION EXITOSO");
                vistaCrearNave.arranca();
            }
            catch (RegistroException | UsuarioLoginException e){
                ventanaEmergente(e.getMessage(),"ERROR");
            }
        }

        if (actionEvent.getActionCommand().equals(VistaInicioSesion.ACCESO_REGISTRO)) {
            vistaInicioSesion.dispose();
            vistaRegistro.arranca();
        }

        if (actionEvent.getActionCommand().equals(VistaRegistro.REGISTRO_USER)) {
            try {
                String usernameIngresado = vistaRegistro.getTextUsuario().getText();
                String contraIngresada = vistaRegistro.getPasswordField().getText();

                enlazador.validaRegistroUsuario(usernameIngresado, contraIngresada);

                vistaRegistro.dispose();
                ventanaEmergente("REGISTRO EXITOSO");
                vistaCrearNave.arranca();
            }
            catch (DAOAuxiliarException | RegistroException e){
                ventanaEmergente(e.getMessage(), "ERROR");
            }
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
            }catch(NumberFormatException e){
                ventanaEmergente("Error de formato - Antigüedad","ERROR");
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

    /**
     * Ejecuta una misión en un hilo secundario (SwingWorker) para evitar el bloqueo de la interfaz gráfica.
     *
     * @param mAct Misión a ejecutar.
     * @pre mAct != null && enlazador.getAsistente() != null
     * @post La misión comienza su ejecución en segundo plano.
     */
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

    /**
     * Inicializa los elementos del universo, crea el tablero de juego de 20x20 y configura su vista asociada.
     *
     * @pre true
     * @post tablero != null && vistaMapa != null (si no ocurren excepciones de dimensiones o capacidad).
     */
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

    /**
     * Genera aleatoriamente 70 elementos espaciales y los almacena en la lista del universo.
     *
     * @pre elementosUni != null
     * @post elementosUni.size() == 70, conteniendo instancias aleatorias de subclases de ElementoDelUniverso.
     */
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

    /**
     * Prepara las ventanas y deshabilita controles de acción mientras una misión está en curso.
     *
     * @param mAct Misión que entra en ejecución.
     * @pre mAct != null
     * @post Se cierran vistas secundarias de gestión, se muestran bitácora, recursos y mapa, y se bloquean botones en vistaMain.
     */
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

    /**
     * Restaura la interfaz principal y rehabilita los botones de navegación al finalizar una misión.
     *
     * @pre vistaMain != null
     * @post Los botones principales quedan habilitados, el título se restablece y el mapa se actualiza.
     */
    private void finVistaMision(){
        vistaMain.getBotonBorraCrearNave().setEnabled(true);
        vistaMain.getBotonMisiones().setEnabled(true);
        vistaMain.getBotonTripulacion().setEnabled(true);
        vistaMain.getBotonVisorParalelo().setEnabled(true);
        vistaMain.setTitle("PANEL PRINCIPAL");
        actualizacionVistaMapa();
    }

    /**
     * Sincroniza la lista visual de CapaDatos.tripulantes con la tripulación actual de la CapaDatos.nave.
     *
     * @pre enlazador.getNave() != null && vistaTripulacion != null
     * @post El modelo de la lista visual contiene exactamente los mismos CapaDatos.tripulantes que la CapaDatos.nave.
     */
    private void actualizaVisorTripulantes(){
        vistaTripulacion.getModeloLista().clear();
        for(Tripulante t:enlazador.getNave().getTripulacion())
            vistaTripulacion.getModeloLista().addElement(t);
    }

    /**
     * Crea un nuevo tripulante a partir de los datos ingresados en el formulario correspondiente.
     *
     * @return Nueva instancia de Tripulante.
     * @throws ErrorCreacionPersonajeException Si los datos no cumplen con las reglas de creación del personaje.
     * @throws NumberFormatException Si el campo de antigüedad no contiene un número entero válido.
     * @pre vistaCrearTripulante != null
     * @post Retorna un Tripulante válido sin modificar aún la tripulación de la CapaDatos.nave.
     */
    private Tripulante agregaTripulante() throws ErrorCreacionPersonajeException {
        String nombre = vistaCrearTripulante.getTxtNombreTripulante().getText();
        String origen = (String) vistaCrearTripulante.getComboOrigenTripulante().getSelectedItem();
        String cargo = (String) vistaCrearTripulante.getComboCargoTripulante().getSelectedItem();
        int antiguedad = Integer.parseInt(vistaCrearTripulante.getTxtAntiguedadTripulante().getText());
        return FactoryPeople.creaPersonaje(cargo,origen,nombre,antiguedad);
    }

    /**
     * Inicializa una nueva partida creando la CapaDatos.nave, asignando su capitán, generando el tablero y configurando observadores y vistas.
     *
     * @throws NumberFormatException Si la antigüedad del capitán no es un entero válido.
     * @throws TipoNaveInvalidoException Si el tipo de CapaDatos.nave seleccionado no es válido.
     * @throws ErrorAgregarTripulacionException Si no es posible agregar el capitán a la CapaDatos.nave.
     * @throws ErrorCreacionPersonajeException Si los datos del capitán son inválidos.
     * @pre vistaCrearNave != null && enlazador != null
     * @post La partida queda iniciada con tablero, asistente, visor paralelo y vistas principales activas.
     */
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
        this.enlazador.setAsistente(new Asistente(nave, tablero));
        this.enlazador.setVisor(iniciaUniversoParalelo());

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

    /**
     * Habilita los botones del panel principal, establece las etiquetas de CapaDatos.nave y capitán, y muestra la ventana.
     *
     * @pre vistaMain != null && enlazador.getNave() != null && enlazador.getNave().getCapitan() != null
     * @post La ventana principal queda visible y operativa para el jugador.
     */
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

    /**
     * Añade un mensaje de registro en la consola del visor del universo paralelo y desplaza el scroll al final.
     *
     * @param text Texto a mostrar en la consola.
     * @pre vistaGeneradores != null && text != null
     * @post El texto se concatena al área de consola y el cursor se posiciona al final del documento.
     */
    public void actualizaVisorParalelo(String text){
        vistaGeneradores.getAreaConsolaMini().append("\n#"+text+"\n@------------");
        vistaGeneradores.getAreaConsolaMini().setCaretPosition(vistaGeneradores.getAreaConsolaMini().getDocument().getLength());
    }

    /**
     * Instancia la vista de generadores y crea un nuevo visor paralelo vinculado a este controlador.
     *
     * @return Nueva instancia de Visor.
     * @pre true
     * @post vistaGeneradores != null && retorna un Visor enlazado a this.
     */
    private Visor iniciaUniversoParalelo(){
        this.vistaGeneradores = new VistaGeneradores();
        this.vistaGeneradores.setControlador(this);
        return new Visor(this);
    }

    /**
     * Cierra todas las ventanas abiertas, limpia los campos de creación de CapaDatos.nave y reinicia el flujo a la pantalla de creación.
     *
     * @pre vistaCrearNave != null
     * @post Todas las ventanas previas se cierran y vistaCrearNave se muestra con sus campos vacíos.
     */
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

    /**
     * Actualiza el texto y color de la barra de estado del motor warp según su código de estado.
     *
     * @param i Código numérico que representa el estado actual del motor warp.
     * @pre vistaRecursos != null
     * @post La barra de estado muestra la descripción y color correspondientes al estado i.
     */
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

    /**
     * Crea un tripulante con rol de Capitán a partir de los datos ingresados en la vista de creación de CapaDatos.nave.
     *
     * @return Instancia de Tripulante con cargo de Capitán.
     * @throws ErrorCreacionPersonajeException Si los datos del capitán son inválidos.
     * @throws NumberFormatException Si la antigüedad ingresada no es un número entero.
     * @pre vistaCrearNave != null
     * @post Retorna un Capitán válido listo para ser asignado a una CapaDatos.nave.
     */
    private Tripulante asignaCapitan() throws ErrorCreacionPersonajeException , NumberFormatException {
        String origen = (String) vistaCrearNave.getComboOrigenCapitan().getSelectedItem();
        String nombre = vistaCrearNave.getTxtNombreCapitan().getText();
        int antiguedad = Integer.parseInt(vistaCrearNave.getTxtAntiguedadCapitan().getText());
        return FactoryPeople.creaPersonaje(FactoryPeople.CAPITAN, origen, nombre, antiguedad);
    }

    /**
     * Actualiza las barras de progreso de combustible, desgaste y energía en la vista de estado de la CapaDatos.nave.
     *
     * @pre vistaRecursos != null && enlazador.getNave() != null
     * @post Los valores de las barras visuales coinciden con los valores actuales de la CapaDatos.nave.
     */
    private void actualizaVistaRecursosNave(){
        vistaRecursos.getBarraCombustible().setValue((int)enlazador.getNave().getCombustible());
        vistaRecursos.getBarraDesgaste().setValue((int)enlazador.getNave().getDesgaste());
        vistaRecursos.getBarraEnergia().setValue((int)enlazador.getNave().getEnergia());
    }

    /**
     * Reconstruye y muestra el historial completo de registros de bitácora en el área de texto correspondiente.
     *
     * @pre vistaBitacora != null && enlazador.getBitacorasNave() != null && enlazador.getNave() != null
     * @post El área de consola muestra todos los registros actuales y desplaza el scroll al final.
     */
    private void actualizaVistaBitacora() {
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

    /**
     * Muestra una ventana emergente con ícono informativo por defecto.
     *
     * @param mensaje Texto a mostrar al usuario.
     * @pre mensaje != null
     * @post Se despliega una ventana modal/emergente con el mensaje indicado.
     */
    private void ventanaEmergente(String mensaje){
        this.ventanaEmergente(mensaje,"");
    }

    /**
     * Muestra una ventana emergente con un mensaje formateado y un ícono de advertencia o información.
     *
     * @param mensaje Texto a mostrar en la ventana.
     * @param icono Tipo de ícono ("ERROR" para advertencia, cualquier otro valor para información).
     * @pre mensaje != null && icono != null
     * @post Se instancia y hace visible una VistaExcepcion con el mensaje e ícono configurados.
     */
    private void ventanaEmergente(String mensaje,String icono) {
        VistaExcepcion ventana = new VistaExcepcion();
        if (icono.equals("ERROR"))
            ventana.getLblIcono().setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        else
            ventana.getLblIcono().setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        ventana.getLblMensaje().setText("<html><body style='width: 220px'>" + mensaje + "</body></html>");
        ventana.arranca();
    }

    /**
     * Garantiza que la actualización de la vista de bitácora se ejecute de forma segura sobre el hilo de despacho de eventos (EDT).
     *
     * @pre true
     * @post La vista de bitácora queda actualizada sin condiciones de carrera en la interfaz gráfica.
     */
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

    /**
     * Recibe notificaciones de los objetos observados (misiones, tablero, bitácora, motor) y actualiza la UI en el EDT.
     *
     * @param mensaje Identificador del evento notificado.
     * @pre mensaje != null
     * @post Se programa en el EDT la actualización visual correspondiente al evento recibido.
     */
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

    /**
     * Habilita o deshabilita los controles de carga de recursos y mantenimiento en la vista de estado de la CapaDatos.nave.
     *
     * @param estado true para habilitar los controles, false para deshabilitarlos.
     * @pre vistaRecursos != null
     * @post Todos los campos de texto y botones de recarga/reparación tienen su estado enabled igual al parámetro estado.
     */
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
     * Actualiza la representación gráfica del mapa asignando íconos, colores de fondo y tooltips según los elementos presentes en el tablero.
     *
     * @pre vistaMapa != null && enlazador.getTablero() != null && enlazador.getTablero().verTablero() != null
     * @post Cada celda de vistaMapa refleja visualmente el elemento del universo, el objetivo o la posición actual de la CapaDatos.nave.
     */
    private void actualizacionVistaMapa() {
        for (int i = 0; i < enlazador.getTablero().getX(); i++) {
            for (int j = 0; j < enlazador.getTablero().getY(); j++) {
                vistaMapa.getBotonCelda(i, j).actualizarIcono(null);
                vistaMapa.getBotonCelda(i, j).setToolTipText(null);
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

    /**
     * Configura el estado final de derrota estableciendo los recursos de la CapaDatos.nave en crítico, bloqueando accesos y marcando la destrucción en el mapa.
     *
     * @pre enlazador.getNave() != null && enlazador.getTablero() != null && vistaMain != null && vistaMapa != null
     * @post La CapaDatos.nave queda con combustible=0, energía=0, desgaste máximo, los botones de juego deshabilitados y la celda de la CapaDatos.nave con ícono de calavera.
     */
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

        vistaMapa.getBotonCelda(xNave,yNave).setBackground(new Color(149, 0, 0));
        vistaMapa.getBotonCelda(xNave,yNave).actualizarIcono("src/main/java/CapaPresentacion/iconosFolder/calavera.png");

    }

}
