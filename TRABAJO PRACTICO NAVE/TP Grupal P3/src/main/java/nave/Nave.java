
package nave;
import java.util.ArrayList;

import bitacora.RegistroRecursos;
import tripulantes.*;

abstract public class Nave{

    protected float combustible,energia,desgaste = 0;
    protected ArrayList<Tripulante> tripulantes = new ArrayList<>();

    public Nave(){
        super();
    }

    public float getCombustible() {
        return combustible;
    }

    public RegistroRecursos setCombustible(float carga){
        this.combustible = carga;
        return new RegistroRecursos("COMBUSTIBLE",carga,this.getCombustible());
    }

    public float getEnergia() {
        return energia;
    }

    public RegistroRecursos setEnergia(float carga) {
        this.energia = carga;
        return new RegistroRecursos("ENERGIA",carga,this.getEnergia());
    }

    public float getDesgaste() {
        return desgaste;
    }

    public RegistroRecursos setDesgaste(float desgaste) {
        float estadoPrevio = this.getDesgaste();
        this.desgaste = desgaste;
        return new RegistroRecursos("DESGASTE",-estadoPrevio,0);
    }

    /** contrato mejorado con gemini <br>
     * Agrega una lista completa de tripulantes iterando sobre ellos.
     * <b>Pre:</b>
     * - tripulantes != null
     * <b>Post:</b>
     * - Se añade cada tripulante de la lista a la tripulación.
     * @param tripulantes La lista de tripulantes que se desea agregar.
     */
    public void agregaTripulante(ArrayList<Tripulante> tripulantes) {
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
    public void agregaTripulante(Tripulante tripulante) {
        this.tripulantes.add(tripulante);
    }
}