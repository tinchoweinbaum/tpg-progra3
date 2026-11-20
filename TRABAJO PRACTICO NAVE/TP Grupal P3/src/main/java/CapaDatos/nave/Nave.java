package CapaDatos.nave;
import java.util.ArrayList;
import CapaDatos.bitacora.*;
import CapaDatos.exceptions.*;
import CapaDatos.tripulantes.*;

/**
 * Clase abstracta base que modela una CapaDatos.nave espacial, sus recursos (combustible, energía, desgaste),
 * su tripulación y el protocolo de doble despacho (Double Dispatch) para resolver combates.
 * <p><b>Invariante de clase:</b> CapaDatos.tripulantes != null && combustible >= 0 && combustible <= MAX_COMBUSTIBLE && energia >= 0 && energia <= MAX_ENERGIA && desgaste >= 0 && desgaste <= MAX_DESGASTE</p>
 */
public abstract class Nave{
    public static final float MAX_COMBUSTIBLE = 100;
    public static final float MAX_ENERGIA = 100;
    public static final float MAX_DESGASTE = 100;

    protected float combustible, energia, desgaste = 0;
    protected ArrayList<Tripulante> tripulantes = new ArrayList<>();
    protected Tripulante capitan = null;
    protected String name;

    /**
     * Constructor por defecto de la clase base Nave.
     *
     * @pre true
     * @post Inicializa la lista de CapaDatos.tripulantes vacía, desgaste == 0 y capitan == null.
     */
    public Nave(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tripulante getCapitan() {
        return capitan;
    }

    /**
     * Inicia un enfrentamiento contra otra CapaDatos.nave aplicando doble despacho (Double Dispatch).
     *
     * @param nave Nave oponente contra la cual se interactúa.
     * @return RegistroCombate con el resultado del encuentro.
     * @throws NaveDestruidaException Si el encuentro provoca la destrucción de la CapaDatos.nave.
     * @pre CapaDatos.nave != null
     * @post Delega la resolución del combate a la CapaDatos.nave oponente y retorna el registro correspondiente.
     */
    public abstract RegistroCombate atacar(Nave nave) throws NaveDestruidaException;

    /**
     * Resuelve el resultado de recibir el ataque de una CapaDatos.nave de combate.
     *
     * @return RegistroCombate describiendo el desenlace del ataque.
     * @throws NaveDestruidaException Si esta CapaDatos.nave no resiste el ataque de una CapaDatos.nave de combate.
     * @pre true
     * @post Retorna un RegistroCombate si resiste el ataque o lanza NaveDestruidaException si es destruida.
     */
    public abstract RegistroCombate atacadoPorCombate() throws NaveDestruidaException;

    /**
     * Resuelve el resultado de recibir el ataque de una CapaDatos.nave carguera.
     *
     * @return RegistroCombate describiendo el desenlace del ataque.
     * @throws NaveDestruidaException Si esta CapaDatos.nave no resiste el ataque de una CapaDatos.nave carguera.
     * @pre true
     * @post Retorna un RegistroCombate si resiste el ataque o lanza NaveDestruidaException si es destruida.
     */
    public abstract RegistroCombate atacadoPorCarguero()throws NaveDestruidaException;

    /**
     * Resuelve el resultado de recibir el ataque de una CapaDatos.nave exploradora.
     *
     * @return RegistroCombate describiendo el desenlace del ataque.
     * @throws NaveDestruidaException Si esta CapaDatos.nave no resiste el ataque de una CapaDatos.nave exploradora.
     * @pre true
     * @post Retorna un RegistroCombate si resiste el ataque o lanza NaveDestruidaException si es destruida.
     */
    public abstract RegistroCombate atacadoPorExplorador()throws NaveDestruidaException;


    public float getCombustible() {
        return this.combustible;
    }

    public float getEnergia() {
        return energia;
    }

    public float getDesgaste() {
        return this.desgaste;
    }

    public void setCombustible(float valorNuevo){
        assert valorNuevo >= 0 && valorNuevo <= MAX_COMBUSTIBLE: "Cantidad de combustible inválida para setCombustible.";
        this.combustible = valorNuevo;
    }

    public void setEnergia(float valorNuevo) {
        assert valorNuevo >= 0 && valorNuevo <= MAX_ENERGIA: "Valor inválido de energía para setEnergía";
        this.energia = valorNuevo;
    }

    public void setDesgaste(float valorNuevo) {
        assert valorNuevo >= 0 && valorNuevo <= MAX_DESGASTE: "Valor inválido para setDesgaste.";
        this.desgaste = valorNuevo;
    }

    /**
     * Incorpora un nuevo tripulante a la CapaDatos.nave validando que no sea nulo, no esté duplicado
     * y no exista ya un capitán asignado si el tripulante tiene cargo de capitán.
     *
     * @param tripulante El tripulante que se desea incorporar.
     * @throws TripulanteInvalidoException Si tripulante es null o ya forma parte de la tripulación.
     * @throws CapitanExistenteExceptionAgregar Si tripulante.esCapitan() es true y ya existe un capitán en la CapaDatos.nave.
     * @throws ErrorAgregarTripulacionException Si ocurre cualquier error de validación al agregar el tripulante.
     * @pre tripulante != null && !CapaDatos.tripulantes.contains(tripulante) && !(tripulante.esCapitan() && capitan != null)
     * @post CapaDatos.tripulantes.contains(tripulante), la cantidad de CapaDatos.tripulantes aumenta en 1 y, si tripulante.esCapitan(), this.capitan == tripulante.
     */
    public void agregaTripulante(Tripulante tripulante)throws ErrorAgregarTripulacionException {
        if (tripulante == null)
            throw new TripulanteInvalidoException("Tripulante nulo.");
        if (this.capitan != null && tripulante.esCapitan())
            throw new CapitanExistenteExceptionAgregar("No puede agregarse a " + tripulante.getNombre() + ". Ya existe un Capitan en la CapaDatos.nave.");
        if(this.tripulantes.contains(tripulante))
            throw new TripulanteInvalidoException("Ya se cargó al tripulante " + tripulante.getNombre() + " a la tripulación de la CapaDatos.nave.");

        if (tripulante.esCapitan())
            this.capitan = tripulante;
        this.tripulantes.add(tripulante);
    }

    public ArrayList<Tripulante> getTripulacion(){
        return this.tripulantes;
    }

    /**
     * Remueve un tripulante específico de la lista de tripulación de la CapaDatos.nave.
     *
     * @param auxTrip Tripulante que se desea eliminar.
     * @pre auxTrip != null
     * @post !CapaDatos.tripulantes.contains(auxTrip)
     */
    public void eliminaTripulante(Tripulante auxTrip) {
        this.tripulantes.remove(auxTrip);
    }
}