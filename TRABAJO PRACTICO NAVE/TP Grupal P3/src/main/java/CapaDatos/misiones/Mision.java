package CapaDatos.misiones;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.*;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.*;
import CapaDatos.nave.Nave;

/**
 * Clase abstracta base que implementa el patrón Template Method para definir el ciclo de vida
 * de una misión espacial (preparar, ejecutar, evaluar y cerrar).
 * <p><b>Invariante de clase:</b> nombre != null && descripcion != null && posicionObjetivo != null && estadoActual != null && combustibleRequerido >= 0 && energiaRequerida >= 0 && desgasteRequerido >= 0</p>
 */
public abstract class Mision extends Observable{
    protected final static String ESTADO_ACTIVA = "ESTADO_ACTIVA";
    protected final static String ESTADO_FALLIDO = "ESTADO_FALLIDO";
    protected final static String ESTADO_NAVE_DESTRUIDA = "ESTADO_NAVE_DESTRUIDA";

    protected String estadoActual = ESTADO_ACTIVA;
    protected final String nombre;
    protected final String descripcion;
    protected final float combustibleRequerido;
    protected final float energiaRequerida;
    protected final float desgasteRequerido;
    protected float acumuladoPerdidaEnergia = 0;
    protected float acumuladoPerdidaCombustible = 0;
    protected float acumuladoIncrementoDesgaste = 0;
    protected Posicion posicionObjetivo;
    protected String causaError;

    /**
     * Constructor base de una misión espacial.
     *
     * @param nombre Identificador o código de la misión.
     * @param descripcion Detalle breve del objetivo de la misión.
     * @param combustibleRequerido Cantidad mínima de combustible necesaria para iniciar la misión.
     * @param energiaRequerida Cantidad mínima de energía necesaria para iniciar la misión.
     * @param desgasteRequerido Incremento de desgaste base que produce la misión.
     * @param posicionObjetivo Coordenadas destino de la misión en el tablero.
     * @pre nombre != null && descripcion != null && posicionObjetivo != null && combustibleRequerido >= 0 && energiaRequerida >= 0 && desgasteRequerido >= 0
     * @post Se inicializan los atributos de la misión con los valores recibidos y estadoActual == ESTADO_ACTIVA.
     */
    public Mision(String nombre, String descripcion, float combustibleRequerido, float energiaRequerida, float desgasteRequerido,Posicion posicionObjetivo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.combustibleRequerido = combustibleRequerido;
        this.energiaRequerida = energiaRequerida;
        this.desgasteRequerido = desgasteRequerido;
        this.posicionObjetivo = posicionObjetivo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public float getCombustibleRequerido() {
        return this.combustibleRequerido;
    }

    public Posicion getPosicionObjetivo() {
        return this.posicionObjetivo;
    }

    public float getEnergiaRequerida() {
        return this.energiaRequerida;
    }

    public float getDesgasteRequerido() {
        return desgasteRequerido;
    }

    public String getEstadoActual(){return this.estadoActual;}

    /**
     * Valida si la CapaDatos.nave cuenta con tripulación, capitán y recursos suficientes para afrontar la misión.
     * Si alguna condición falla, captura la excepción internamente, registra el motivo en bitácora y marca la misión como fallida.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y su bitácora.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null && posicionObjetivo != null
     * @post Si la CapaDatos.nave tiene capitán, más de 4 CapaDatos.tripulantes y recursos suficientes, estadoActual permanece en ESTADO_ACTIVA y se registra la ubicación del objetivo; en caso contrario, estadoActual pasa a ESTADO_FALLIDO y se registra el motivo del rechazo.
     */
    protected void preparar(Asistente ac){
        Nave nave = ac.getNave();
        try{
            if ((nave.getCapitan()==null ) || (nave.getTripulacion().size()<=4))
                throw new ErrorTripulantesException("La tripulacion no cumple los minimos requisitos para realizar la mision");

            if (this.getCombustibleRequerido() >= nave.getCombustible())
                throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

            if (this.getDesgasteRequerido() + nave.getDesgaste() >= Nave.MAX_DESGASTE)
                throw new ExcesoDesgasteException("Demasiado desgaste en la CapaDatos.nave para realizar la mision");

            if (this.energiaRequerida >= nave.getEnergia())
                throw new EnergiaInsuficienteException("Reserva de energia insuficiente para realizar la mision");

            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("OBJETIVO UBICADO EN ["+posicionObjetivo.getX() + "][" +  posicionObjetivo.getY() + "]"));
        }catch (MisionImposibleException e){
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("No puede realizarse la mision " + getNombre() + "(" + getDescripcion() + ") - " + e.getMessage()));
            this.estadoActual = ESTADO_FALLIDO;
        }

    }

    /**
     * Ejecuta el desplazamiento de la CapaDatos.nave hacia el objetivo turno a turno mientras la misión esté activa,
     * descontando recursos base y gestionando colisiones, encuentros o destrucción de la CapaDatos.nave.
     *
     * @param ac Asistente encargado de controlar la CapaDatos.nave y el tablero.
     * @pre ac != null && ac.getNave() != null && ac.getTablero() != null && ac.getBitacorasNave() != null
     * @post Si estadoActual == ESTADO_ACTIVA, consume los recursos iniciales y avanza hasta alcanzar posicionObjetivo (calculando los acumulados de variación de recursos) o hasta que ocurra una excepción que cambie estadoActual a ESTADO_NAVE_DESTRUIDA o ESTADO_FALLIDO notificando "GAME_OVER".
     */
    protected void ejecutar(Asistente ac) {
        if (!estadoActual.equals(ESTADO_ACTIVA))
            return;

        float estadoCombustibleInicial = ac.getNave().getCombustible();
        float estadoEnergiaInicial = ac.getNave().getEnergia();
        float estadoDesgasteInicial = ac.getNave().getDesgaste();
        boolean encontro = false;
        try {
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("REQUERIMIENTOS MINIMOS DE MISION CONSUMIDOS"));
            ac.consumeCombustibleNave(this.combustibleRequerido);
            ac.consumeEnergiaNave(this.energiaRequerida);
            ac.aumentaDesgasteNave(this.desgasteRequerido);

            while (estadoActual.equals(ESTADO_ACTIVA) && !encontro) {
                Thread.sleep(2000);
                ac.chequeaPosicionActual();
                ac.mueveNave();
                if (ac.getNave().getCombustible()==0 || ac.getNave().getEnergia()==0 || ac.getNave().getDesgaste()==100){
                    throw new NaveDestruidaException("LA NAVE NO LOGRO SOBREVIVIR AL UNIVERSO");
                }
                if (ac.getTablero().getPosicionNave().getX()== posicionObjetivo.getX() && ac.getTablero().getPosicionNave().getY()==posicionObjetivo.getY()){
                    encontro = true;
                    notificarObservadores("ACTUALIZACION_VISTA_TABLERO");
                }else
                    ac.darTurnosTablero(1);
            }
            this.acumuladoPerdidaCombustible = ac.getNave().getCombustible() - estadoCombustibleInicial;
            this.acumuladoIncrementoDesgaste = ac.getNave().getDesgaste() - estadoDesgasteInicial;
            this.acumuladoPerdidaEnergia = ac.getNave().getEnergia() - estadoEnergiaInicial;
        }catch (NaveDestruidaException e) {
            estadoActual=ESTADO_NAVE_DESTRUIDA;
            causaError = e.getMessage();
            notificarObservadores("GAME_OVER");
        } catch (Exception e) {
            estadoActual=ESTADO_FALLIDO;
            causaError = e.getMessage();
            notificarObservadores("GAME_OVER");
        }
    }

    /**
     * Evalúa el desenlace de la misión y registra en la bitácora el resultado final junto con las variaciones acumuladas de recursos.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y su bitácora.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Si estadoActual != ESTADO_NAVE_DESTRUIDA, agrega un RegistroMision con el resultado y los RegistroRecursos correspondientes; si la CapaDatos.nave fue destruida, agrega un RegistroBitacora emitido desde el centro de mando terrestre.
     */
    protected void evaluar(Asistente ac) {
        if (!this.estadoActual.equals(ESTADO_NAVE_DESTRUIDA)) {
            if (this.estadoActual.equals(ESTADO_ACTIVA))
                ac.getBitacorasNave().agregarRegistro(new RegistroMision("MISION REALIZADA CON EXITO - VARIACION RECURSOS TOTALES", this));
            else
                ac.getBitacorasNave().agregarRegistro(new RegistroMision("MISION FALLIDA - MOTIVO: " + causaError + " - VARIACION RECURSOS TOTALES",this));
            if(acumuladoPerdidaCombustible>0)
                ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("COMBUSTIBLE", this.acumuladoPerdidaCombustible, ac.getNave().getCombustible()));
            if(acumuladoPerdidaEnergia>0)
                ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("ENERGIA", this.acumuladoPerdidaEnergia, ac.getNave().getEnergia()));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("DESGASTE", this.acumuladoIncrementoDesgaste, ac.getNave().getDesgaste()));
        }else{
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("BITÁCORA EMITIDA DESDE CENTRO DE MANDO TERRESTRE - La CapaDatos.nave conocida como '" + ac.getNave().getName() + "' ha sido destruída en acción.\nMOTIVO: " + causaError));
        }
    }

    /**
     * Ejecuta las acciones específicas de cierre de cada tipo de misión concreta y notifica su finalización a los observadores.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y su bitácora.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Se aplican los efectos finales propios de la subclase de misión si estadoActual == ESTADO_ACTIVA.
     */
    abstract protected void cerrar(Asistente ac);

    /**
     * Método plantilla (Template Method) que orquesta secuencialmente las etapas de preparación, ejecución, evaluación y cierre de la misión.
     *
     * @param ac Asistente encargado de ejecutar la misión.
     * @pre ac != null && ac.getNave() != null && ac.getTablero() != null && ac.getBitacorasNave() != null
     * @post Se ejecutan en orden preparar(ac), ejecutar(ac), evaluar(ac) y cerrar(ac).
     */
    public final void ejecutarMision(Asistente ac){
        this.preparar(ac);
        this.ejecutar(ac);
        this.evaluar(ac);
        this.cerrar(ac);
    }

    /**
     * Devuelve una representación en texto de la misión con su nombre y descripción.
     *
     * @return Cadena formateada como "nombre--descripcion".
     * @pre true
     * @post Retorna la cadena formateada sin modificar el estado de la misión.
     */
    @Override
    public String toString(){
        return this.getNombre() + "--" + this.getDescripcion();
    }

}
