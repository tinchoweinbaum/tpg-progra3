
package nave;
import java.util.ArrayList;
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

    public void setCombustible(float combustible) {
        this.combustible = combustible;
    }

    public float getEnergia() {
        return energia;
    }

    public void setEnergia(float energia) {
        this.energia = energia;
    }

    public float getDesgaste() {
        return desgaste;
    }

    public void setDesgaste(float desgaste) {
        this.desgaste = desgaste;
    }

    public void agregaTripulante(Tripulante tripulantes){
        this.tripulantes.add(tripulantes);
    }
}