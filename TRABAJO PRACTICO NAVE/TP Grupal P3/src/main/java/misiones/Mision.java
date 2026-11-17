package misiones;

import asistentes.Asistente;
import bitacora.*;
import destinoytablero.Destino;
import destinoytablero.Observable;
import destinoytablero.Posicion;
import exceptions.*;
import nave.Nave;


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

    public Mision(String nombre, String descripcion, float combustibleRequerido, float energiaRequerida, float desgasteRequerido) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.combustibleRequerido = combustibleRequerido;
        this.energiaRequerida = energiaRequerida;
        this.desgasteRequerido = desgasteRequerido;
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

    public float getEnergiaRequerida() {
        return this.energiaRequerida;
    }

    public float getDesgasteRequerido() {
        return desgasteRequerido;
    }

    public String getEstadoActual(){return this.estadoActual;}

    /** Contrato mejorado con llm de navegador.<br>
     * Acepta una misión para la nave, validando los recursos necesarios y el estado actual.
     *
     * <p><b>Pre:</b></p>
     * <ul>
     *   <li>Se asume que el asistente existe y es válido (no es nulo y sus datos son consistentes).</li>
     *   <li>La nave del asistente se encuentra inicializada con un estado válido.</li>
     * </ul>
     *
     * <p><b>Post:</b></p>
     * <ul>
     *   <li>Se ejecuta la misión.</li>
     *   <li>Se agrega un {@code RegistroMision} a la bitácora de la nave.</li>
     *   <li>Si se ejecuta correctamente, los recursos de la nave se actualizan acorde al resultado.</li>
     * </ul>
     *
     * @param ac El asistente que va a ejecutar la misión
     * @throws CombustibleInsuficienteException si el combustible requerido supera al disponible en la nave.
     * @throws ExcesoDesgasteException si el desgaste actual más el requerido supera el máximo permitido (100).
     * @throws EnergiaInsuficienteException si la energia requerida supera al disponible en la nave.
     * @throws MisionImposibleException si ocurre cualquier otro error general que impida realizar la misión.
     */
    protected void preparar(Asistente ac){
        Nave nave = ac.getNave();
        try{
            if ((nave.getCapitan()==null ) || (nave.getTripulacion().size()<=4))
                throw new ErrorTripulantesException("La tripulacion no cumple los minimos requisitos para realizar la mision");

            if (this.getCombustibleRequerido() >= nave.getCombustible())
                throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

            if (this.getDesgasteRequerido() + nave.getDesgaste() >= 100)
                throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

            if (this.energiaRequerida >= nave.getEnergia())
                throw new EnergiaInsuficienteException("Reserva de energia insuficiente para realizar la mision");

            posicionObjetivo = ac.getTablero().obtenerObjetivo();
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("OBJETIVO UBICADO EN ["+posicionObjetivo.getX() + "][" +  posicionObjetivo.getY() + "]"));
        }catch (MisionImposibleException e){
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("No puede realizarse la mision " + getNombre() + "(" + getDescripcion() + ") - " + e.getMessage()));
            this.estadoActual = ESTADO_FALLIDO;
        }

    }

    protected void ejecutar(Asistente ac) {
        if(estadoActual.equals(ESTADO_ACTIVA)){
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
                    if (ac.getTablero().getPosicionNave().getX()== posicionObjetivo.getX() && ac.getTablero().getPosicionNave().getY()==posicionObjetivo.getY())
                        encontro = true;
                    else
                        ac.darTurnosTablero(1);
                }
                this.acumuladoPerdidaCombustible = ac.getNave().getCombustible()-estadoCombustibleInicial;
                this.acumuladoIncrementoDesgaste = ac.getNave().getDesgaste()-estadoDesgasteInicial;
                this.acumuladoPerdidaEnergia = ac.getNave().getEnergia()-estadoEnergiaInicial;
            }catch (NaveDestruidaException e) {
                estadoActual=ESTADO_NAVE_DESTRUIDA;
                causaError = e.getMessage();
                notificarObservadores("GAME_OVER");
            } catch (Exception e) {
                ac.getBitacorasNave().agregarRegistro(new RegistroMision((e.getMessage() + " - ESTADO INOPERANTE"),this));
                estadoActual=ESTADO_FALLIDO;
            }
        }
    }

    /**
     * Metodo que finaliza la mision, se contacta con el asistente de la nave,
     * actualiza los registros de la nave y los recursos de la misma
     *
     * @param ac Asistente de la nave, validado y != NULL
     */
    protected void evaluar(Asistente ac) {
        if (this.estadoActual.equals(ESTADO_ACTIVA)) {
            ac.getBitacorasNave().agregarRegistro(new RegistroMision("MISION REALIZADA CON EXITO - DESGLOSE RECURSOS TOTALES", this));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("COMBUSTIBLE", this.acumuladoPerdidaCombustible, ac.getNave().getCombustible()));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("ENERGIA", this.acumuladoPerdidaEnergia, ac.getNave().getEnergia()));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("DESGASTE", this.acumuladoIncrementoDesgaste, ac.getNave().getDesgaste()));
        }else if(this.estadoActual.equals(ESTADO_FALLIDO)){
            ac.getBitacorasNave().agregarRegistro(new RegistroMision("MISION FALLIDA",this));
        }else{
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("BITACORA REALIZADA DESDE CENTRO DE MANDO TERRESTRE - Nave conocida como '" + ac.getNave().getName() + "' ha sido destruida en accion.\nMOTIVO: " + causaError));
        }
    }

    abstract protected void cerrar(Asistente ac);

    public final void ejecutarMision(Asistente ac){
        this.preparar(ac);
        this.ejecutar(ac);
        this.evaluar(ac);
        this.cerrar(ac);
    }

    @Override
    public String toString(){
        return this.getNombre() + "--" + this.getDescripcion();
    }


}
