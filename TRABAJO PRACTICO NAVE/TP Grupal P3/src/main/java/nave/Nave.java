package nave;
import java.util.ArrayList;

import bitacora.RegistroRecursos;
import exceptions.*;
import tripulantes.*;

public abstract class Nave{

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
     * Metodo para establecer una cantidad de combustible a la nave
     * 
     * <b>pre:</b> carga es un numero positivo
     * <b>post:</b> Se establece la cantidad de combustible y se registra en la bitacora
     * 
     * @param valorNuevo Es la cantidad de combustible que voy a agregar
     * @return Registra en bitacora la cantidad de combustible agregada y la cantidad final
     */
    
    public RegistroRecursos setCombustible(float valorNuevo){
        assert valorNuevo > 0: "TE AMO MARTIN";
        float estadoPrevio = this.getCombustible();
        this.combustible = valorNuevo;
        return new RegistroRecursos("COMBUSTIBLE",valorNuevo-estadoPrevio,this.getCombustible());
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
    
    public RegistroRecursos setEnergia(float valorNuevo) {
        float estadoPrevio = this.getEnergia();
        this.energia = valorNuevo;
        return new RegistroRecursos("ENERGIA",valorNuevo - estadoPrevio,this.getEnergia());
    }

    public float getDesgaste() {
        return desgaste;
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
    
    public RegistroRecursos setDesgaste(float desgaste) {
        this.desgaste += desgaste;
        return new RegistroRecursos("DESGASTE",desgaste,this.getDesgaste());
    }

    /** 
     *
     * Agrega una lista completa de tripulantes iterando sobre ellos.
     * 
     * <b>Pre:</b>  tripulantes != null
     * <b>Post:</b> Se añade cada tripulante de la lista a la tripulación.
     * 
     * @param tripulantes La lista de tripulantes que se desea agregar.
     */
    public void agregaTripulante(ArrayList<Tripulante> tripulantes) {
        try {
            for (Tripulante t : tripulantes) {
                this.agregaTripulante(t);
            }
        }catch(ErrorAgregarTripulacionException e){
            System.out.println(e.getMessage());
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
        if (tripulante==null)
            throw new TripulanteInvalidoException("Tripulante nulo");
        if (this.capitan!=null && tripulante.esCapitan())
            throw new CapitanExistenteExceptionAgregar("No puede agregarse a " + tripulante.getNombre() + ". Ya existe un Capitan en la nave");
        if (tripulante.esCapitan())
            this.capitan = tripulante;
        this.tripulantes.add(tripulante);
    }

    public void eliminaTripulante(Tripulante tripulante) {
        this.tripulantes.remove(tripulante);
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