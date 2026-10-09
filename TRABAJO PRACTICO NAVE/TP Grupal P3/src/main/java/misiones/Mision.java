package misiones;

import asistentes.Asistente;
import bitacora.Bitacora;
import bitacora.RegistroError;
import bitacora.RegistroMision;
import bitacora.RegistroMotor;
import exceptions.*;
import motorwarp.MotorWarp;
import nave.Nave;

// Superclase del patrón template de las misiones.
public abstract class Mision{
    private final String nombre;
    private final String descripcion;
    private final float combustibleRequerido;
    private final float energiaAportada; // Valor de energía que la misión SUMA a la nave.
    private final float desgasteRequerido; // Desgaste mínimo que tiene que tener la nave para aceptar la misión.

    public Mision(String nombre, String descripcion, float combustibleRequerido, float energiaAportada, float desgasteRequerido) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.combustibleRequerido = combustibleRequerido;
        this.energiaAportada = energiaAportada;
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

    public float getEnergiaAportada() {
        return this.energiaAportada;
    }

    public float getDesgasteRequerido() {
        return desgasteRequerido;
    }

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
     * @throws ExcesoEnergiaException si la energía actual más el aporte de la misión supera el máximo permitido (100).
     * @throws MisionImposibleException si ocurre cualquier otro error general que impida realizar la misión.
     */
    protected void preparar(Asistente ac) throws MisionImposibleException{
        Nave nave = ac.getNave();

        if ((nave.getCapitan()==null ) || (nave.getTripulantes()<4))
            throw new ErrorTripulantesException("La tripulacion no cumple los minimos requisitos para realizar la mision");

        if (this.getCombustibleRequerido() > nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        //Desgaste maximo = 100
        if (this.getDesgasteRequerido() + nave.getDesgaste() > 100)
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        //Energia maxima = 100
        if (this.getEnergiaAportada()>0 && nave.getEnergia() + this.getEnergiaAportada() > 100)
            throw new ExcesoEnergiaException("Se supera la cantidad maxima de energia soportada por la nave");

        //Verifico que se pueda usar el motor
        if (ac.getMotor().getEstadoActual().getIdEstado() != MotorWarp.DISPONIBLE)
            throw new MotorNoDisponibleException("El motor no se encuentra disponible");

        System.out.println("Mision " + this.getNombre() + " aceptada por asistente");
    }

    protected void saltar(Asistente ac){
        ac.getMotor().prepararSalto();
    }

    abstract protected void ejecutar();

    abstract protected void evaluar();
    
    /**
     * Metodo que finaliza la mision, se contacta con el asistente de la nave, 
     * actualiza los registros de la nave y los recursos de la misma
     * 
     * @param ac Asistente de la nave, validado y != NULL
     */

    protected void cerrar(Asistente ac){
        System.out.println("MISION FINALIZADA - ACTUALIZANDO RECURSOS");

        Nave nave = ac.getNave();
        Bitacora bitacora = ac.getBitacorasNave();

        // Actualiza los recursos de la nave y escribe en la bitacora que lo hizo.
        bitacora.agregarRegistro(nave.setCombustible(nave.getCombustible() - this.combustibleRequerido));
        bitacora.agregarRegistro(nave.setDesgaste(nave.getDesgaste() + this.desgasteRequerido));
        bitacora.agregarRegistro(nave.setEnergia(nave.getEnergia() + this.energiaAportada));

        bitacora.agregarRegistro(new RegistroMision("Mision completa.",this));

        ac.actualizaBitacoraMotor();
    }

    public void ejecutarMision(Asistente ac){
        try {
            this.preparar(ac);
            this.ejecutar();
            this.saltar(ac);
            this.evaluar();
            this.cerrar(ac);
        }
        catch (MisionImposibleException e){
            System.out.println("No se pudo aceptar la mision " + this.getNombre() + ": " + e.getMessage());
            Bitacora bitacora = ac.getBitacorasNave();
            bitacora.agregarRegistro(new RegistroError("No se pudo aceptar la mision", e));
        }
    }

    @Override
    public String toString(){
        return "REPORTE MISION : " + this.getNombre() + "\n" + this.getDescripcion() + "\nCombustible consumido:" + this.combustibleRequerido + " litros\nLa nave sufrio un desgaste de " + this.desgasteRequerido + " unidades" + (this.energiaAportada >0 ? ("\nSe incremento la energia de la nave en " + this.energiaAportada + " unidades") : "");
    }

}
