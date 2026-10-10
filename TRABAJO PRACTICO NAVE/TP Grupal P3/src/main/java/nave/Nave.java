package nave;
import java.util.ArrayList;

import bitacora.RegistroRecursos;
import exceptions.*;
import tripulantes.*;

public abstract class Nave{
    public static final float MAX_COMBUSTIBLE = 100;
    public static final float MAX_ENERGIA = 100;
    public static final float MAX_DESGASTE = 100;

    protected float combustible, energia, desgaste = 0;
    protected ArrayList<Tripulante> tripulantes = new ArrayList<>();
    protected Tripulante capitan = null;
    float estadoPrevio;

    public Nave(){
        super();
    }

    public float getCombustible() {
        return this.combustible;
    }

    /**
     * Metodo para establecer una cantidad de combustible a la nave, realmente este método no se va a usar nunca.
     * 
     * <b>pre:</b> valorNuevo >= 0 && valorNuevo <= 100
     * <b>post:</b> Se establece la cantidad de combustible y se registra en la bitacora.
     * 
     * @param valorNuevo Es la cantidad de combustible que voy a agregar
     * @return Registra en bitacora la cantidad de combustible agregada y la cantidad final
     */
    protected void setCombustible(float valorNuevo){
        assert valorNuevo >= 0 && valorNuevo <= MAX_COMBUSTIBLE: "Cantidad de combustible inválida.";
        this.combustible = valorNuevo;
    }

    /**
     * Método para cargar combustible a la nave.<br>
     * <b> Pre: </b>cantCarga > 0.<br>
     * <b>Post: </b>Se carga la cantidad de combustible indicada a la nave.
     *
     * @param cantCarga Cantidad de combustible a cargar.
     * @return Objeto de tipo {@code RegistroRecursos} para la bitácora del asistente.
     * @throws CantCombustibleInvalidaException
     */
    public RegistroRecursos cargaCombustible(float cantCarga) throws CantCombustibleInvalidaException{
        assert cantCarga > 0: "Se debe cargar un número positivo de combustible";

        if (this.getCombustible() + cantCarga > MAX_COMBUSTIBLE)
            throw new CantCombustibleInvalidaException("La carga supera la capacidad máxima del tanque.");

        this.combustible += cantCarga;
        return new RegistroRecursos("COMBUSTIBLE",cantCarga,this.combustible);
    }

    /**
     * Depende de quien llame a esta función en la 2da entrega la excepción no tiene senitdo, se checkearía antes si hay combustible o no.
     * Método para consumir combustible de la nave.
     * @param cantConsumida
     * @return
     * @throws CantCombustibleInvalidaException
     */
    public RegistroRecursos consumeCombustible(float cantConsumida) throws CantCombustibleInvalidaException{
        assert cantConsumida > 0: "Se debe consumir un número positivo de combustible";

        if (this.combustible - cantConsumida < 0)
            throw new CantCombustibleInvalidaException("No se puede consumir " + cantConsumida + " de combustible, no hay suficiente.");

        this.combustible -= cantConsumida;
        return new RegistroRecursos("COMBUSTIBLE", -cantConsumida, this.combustible);
    }

    public float getEnergia() {
        return energia;
    }

    /**
     * Metodo para establecer una cantidad de energia(carga) a la nave
     *
     * <b>pre:</b> energia es un numero positivo
     * <b>post:</b> Se establece la cantidad de energia y se registra en la bitacora
     *
     * @param valorNuevo Es la cantidad de carga que voy a agregar
     * @return Registra en bitacora la cantidad de energia agregada y la cantidad final
     */
    protected void setEnergia(float valorNuevo) {
        this.energia = valorNuevo;
    }

    public RegistroRecursos cargaEnergia(float cantCarga) throws CantEnergiaInvalidaException {
        assert cantCarga > 0: "Se debe cargar un número positivo de combustible";

        if (this.energia + cantCarga > MAX_ENERGIA)
            throw new CantEnergiaInvalidaException("La carga supera la capacidad máxima de energia.");

        this.energia += cantCarga;
        return new RegistroRecursos("ENERGIA", cantCarga, this.energia);
    }

    public RegistroRecursos consumeEnergia(float cantConsumida) throws CantEnergiaInvalidaException{
        assert cantConsumida > 0: "Se debe consumir una cantidad positiva de energía";

        if(this.energia - cantConsumida < 0)
            throw new CantEnergiaInvalidaException("No se puede consumir " + cantConsumida + " de energía, la nave no tiene suficiente.");

        this.energia -= cantConsumida;
        return new RegistroRecursos("ENERGIA", -cantConsumida, this.energia);
    }

    /**
     * Metodo para establecer una cantidad de desgaste a la nave
     *
     * <b>pre:</b> desgaste es un numero positivo
     * <b>post:</b> Se establece la cantidad de desgaste y se registra en la bitacora
     *
     * @param desgaste Es la cantidad de desgaste que voy a agregar
     * @return Registra en bitacora la cantidad de energia agregada y la cantidad final
     */
    protected void setDesgaste(float desgaste) {
        this.desgaste = desgaste;
    }

    public RegistroRecursos aumentaDesgaste(float cantAumentada) throws CantDesgasteInvalidaException{
        assert cantAumentada > 0: "Se debe sumar una cantidad positiva de desgaste";

        if (this.desgaste + cantAumentada > MAX_DESGASTE)
            throw new CantDesgasteInvalidaException("No se puede aumentar el desgaste, superaría el máximo de 100.");

        this.desgaste += cantAumentada;
        return new RegistroRecursos("DESGASTE", cantAumentada, this.desgaste);
    }

    public RegistroRecursos reparaDesgaste(float cantReparada) throws CantDesgasteInvalidaException{
        assert cantReparada > 0: "Se debe reparar una cantidad positiva de desgaste";

        if (this.desgaste - cantReparada < 0)
            throw new CantDesgasteInvalidaException("No se puede tener menos de 0 de desgaste.");

        this.desgaste -= cantReparada;
        return new RegistroRecursos("DESGASTE", -cantReparada, this.desgaste);
    }

    public float getDesgaste() {
        return desgaste;
    }

    /**
     *
     * Agrega una lista completa de tripulantes iterando sobre ellos.<br>
     * <b>Pre:</b>  tripulantes != null
     * <b>Post:</b> Se añade cada tripulante de la lista a la tripulación.
     * 
     * @param tripulantes La lista de tripulantes que se desea agregar.
     */
    public void agregaTripulante(ArrayList<Tripulante> tripulantes) throws ErrorAgregarTripulacionException{
        for (Tripulante t : tripulantes) {
            this.agregaTripulante(t);
        }
    }

    /**
     * Agrega un nuevo integrante a la tripulación.
     * <b>Pre:</b>
     * - tripulante != null
     * <b>Post:</b>
     * - Se añade el tripulante a la lista de tripulantes.
     * @param tripulante El tripulante que se desea agregar.
     */
    public void agregaTripulante(Tripulante tripulante)throws ErrorAgregarTripulacionException {
        if (tripulante == null)
            throw new TripulanteInvalidoException("Tripulante nulo.");
        if (this.capitan != null && tripulante.esCapitan())
            throw new CapitanExistenteExceptionAgregar("No puede agregarse a " + tripulante.getNombre() + ". Ya existe un Capitan en la nave.");
        if(this.tripulantes.contains(tripulante))
            throw new TripulanteInvalidoException("Ya se cargó al tripulante " + tripulante.getNombre() + " a la tripulación de la nave.");

        if (tripulante.esCapitan())
            this.capitan = tripulante;
        this.tripulantes.add(tripulante);
    }

    /**
     * Devuelve true si se pudo eliminar el tripulante, false si no.<br>
     * <b>Post: </b>Se elimina el tripulante de la tripulación si este existe, si es el capitán se deja en null la referencia al capitán de la nave.
     * @param tripulante
     */
    public boolean eliminaTripulante(Tripulante tripulante) {
        boolean eliminado = this.tripulantes.remove(tripulante);
        if (eliminado && tripulante.equals(this.capitan)) {
            this.capitan = null;
        }
        return eliminado;
    }

    public ArrayList<Tripulante> getTripulacion(){
        return this.tripulantes;
    }

    public int getCantTripulantes() {
        return tripulantes.size();
    }

    public Tripulante getCapitan() {
        return capitan;
    }
}