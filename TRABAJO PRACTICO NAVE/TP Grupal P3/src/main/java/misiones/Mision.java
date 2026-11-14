package misiones;

import asistentes.Asistente;
import bitacora.*;
import exceptions.*;
import nave.Nave;


public abstract class Mision{
    protected final static String ESTADO_ACTIVA = "ESTADO_ACTIVA";
    protected final static String ESTADO_FALLIDO = "ESTADO_FALLIDO";

    private String estadoActual = ESTADO_ACTIVA;
    private final String nombre;
    private final String descripcion;
    private final float combustibleRequerido;
    private final float energiaRequerida;
    private final float desgasteRequerido;
    private float acumuladoPerdidaEnergia = 0;
    private float acumuladoPerdidaCombustible = 0;
    private float acumuladoIncrementoDesgaste = 0;

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
    protected void preparar(Asistente ac) throws MisionImposibleException{
        Nave nave = ac.getNave();

        if ((nave.getCapitan()==null ) || (nave.getCantTripulantes()<=4))
            throw new ErrorTripulantesException("La tripulacion no cumple los minimos requisitos para realizar la mision");

        if (this.getCombustibleRequerido() >= nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        if (this.getDesgasteRequerido() + nave.getDesgaste() >= 100)
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        if (this.energiaRequerida >= nave.getEnergia())
            throw new EnergiaInsuficienteException("Reserva de energia insuficiente para realizar la mision");

    }

    protected void ejecutar(Asistente ac) throws MisionImposibleException , NaveDestruidaException{
        ac.consumeCombustibleNave(this.combustibleRequerido);
        ac.consumeEnergiaNave(this.energiaRequerida);
        ac.aumentaDesgasteNave(this.desgasteRequerido);

        //while para el viaje, puede lanzar una excepcion si no alcanzan recursos
        while(this.estadoActual.equals(ESTADO_ACTIVA)){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new CombustibleInsuficienteException("aaa");
            }
            finally {
                estadoActual=ESTADO_FALLIDO;
            }
            //realizo viaje
            //acumulo desgastes y gastos
            //si llegase a 0 de energia o combustible o 100 de desgaste deberia señalar una excepcion para terminar el juego
            ac.actualizaBitacoraMotor();
        }
    }

    /**
     * Metodo que finaliza la mision, se contacta con el asistente de la nave,
     * actualiza los registros de la nave y los recursos de la misma
     *
     * @param ac Asistente de la nave, validado y != NULL
     */
    protected void evaluar(Asistente ac){
        if (this.estadoActual.equals(ESTADO_ACTIVA)){
            ac.getBitacorasNave().agregarRegistro(new RegistroMision("MISION REALIZADA CON EXITO",this));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("COMBUSTIBLE",this.acumuladoPerdidaCombustible,ac.getNave().getCombustible()));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("ENERGIA",this.acumuladoPerdidaEnergia,ac.getNave().getEnergia()));
            ac.getBitacorasNave().agregarRegistro(new RegistroRecursos("DESGASTE",this.acumuladoIncrementoDesgaste,ac.getNave().getDesgaste()));
        }else{
            ac.getBitacorasNave().agregarRegistro(new RegistroMision("MISION FALLIDA",this));
        }
    }

    abstract protected void cerrar(Asistente ac);

    public void ejecutarMision(Asistente ac) throws MisionImposibleException , NaveDestruidaException{
        try {
            this.preparar(ac);
            this.ejecutar(ac);
        }
        catch (MisionImposibleException | NaveDestruidaException e){
            ac.getBitacorasNave().agregarRegistro(new RegistroError("No se pudo aceptar la mision", e));
            this.estadoActual = ESTADO_FALLIDO;
            throw e;
        }
        finally {
            this.evaluar(ac);
            this.cerrar(ac);
        }
    }

    @Override
    public String toString(){
        return this.getNombre() + "\t" + this.getDescripcion();
    }

}
