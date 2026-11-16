package nave;
import java.util.ArrayList;

import bitacora.*;
import exceptions.*;
import tripulantes.*;

public abstract class Nave{
    public static final float MAX_COMBUSTIBLE = 100;
    public static final float MAX_ENERGIA = 100;
    public static final float MAX_DESGASTE = 100;

    public static final int COMBATE_DERROTA = -1;
    public static final int COMBATE_PAZ = 0;
    public static final int COMBATE_VICTORIA = 1;

    protected float combustible, energia, desgaste = 0;
    protected ArrayList<Tripulante> tripulantes = new ArrayList<>();
    protected Tripulante capitan = null;
    protected String name;

    public Nave(){
        super();
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

    public abstract RegistroCombate atacar(Nave nave) throws NaveDestruidaException;

    public abstract RegistroCombate atacadoPorCombate() throws NaveDestruidaException;

    public abstract RegistroCombate atacadoPorCarguero()throws NaveDestruidaException;

    public abstract RegistroCombate atacadoPorExplorador()throws NaveDestruidaException;


    public float getCombustible() {
        return this.combustible;
    }

    /**
     * Metodo para establecer una cantidad de combustible a la nave, usado únicamente en los constructores de las subclases concretas de nave.<br>
     * <b>pre:</b> valorNuevo >= 0 && valorNuevo <= 100
     *
     * @param valorNuevo Es la cantidad de combustible que voy a agregar
     */
    public void setCombustible(float valorNuevo){
        assert valorNuevo >= 0 && valorNuevo <= MAX_COMBUSTIBLE: "Cantidad de combustible inválida para setCombustible.";
        this.combustible = valorNuevo;
    }

    public float getEnergia() {
        return energia;
    }

    /**
     * Metodo para establecer una cantidad de energia a la nave, usado únicamente en los constructores de las subclases concretas de nave<br>
     * <b>Pre:</b> valorNuevo >= 0 && valorNuevo <= 10<br>
     * @param valorNuevo Valor de energía a establecer
     */
    public void setEnergia(float valorNuevo) {
        assert valorNuevo >= 0 && valorNuevo <= MAX_ENERGIA: "Valor inválido de energía para setEnergía";
        this.energia = valorNuevo;
    }

    /**
     * Metodo para establecer una cantidad de desgaste a la nave, usado únicamente en los constructores de las subclases concretas de nave<br>
     * <b>pre:</b> valorNuevo >= 0 && valorNuevo <= 100 <br>
     * @param valorNuevo Cantidad de desgaste a establecer.
     */
    public void setDesgaste(float valorNuevo) {
        assert valorNuevo >= 0 && valorNuevo <= MAX_DESGASTE: "Valor inválido para setDesgaste.";
        this.desgaste = valorNuevo;
    }


    public float getDesgaste() {
        return this.desgaste;
    }

    /**
     * Agrega un nuevo integrante a la tripulación.
     * <b>Pre:</b>
     * - tripulante != null
     * <b>Post:</b>
     * - Se añade el tripulante a la lista de tripulantes.
     * @param tripulante El tripulante que se desea agregar.
     * @throws exceptions.ErrorAgregarTripulacionException devuelve una excepcion debido a que no se puedo agregar un tripulante
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

    public ArrayList<Tripulante> getTripulacion(){
        return this.tripulantes;
    }


    public void eliminaTripulante(Tripulante auxTrip) {
        this.tripulantes.remove(auxTrip);
    }
}