
package nave;
import java.util.ArrayList;
import tripulantes.*;

abstract public class Nave{

    protected int combustible,energia,desgaste = 0;
    protected ArrayList<Tripulante> tripulantes = new ArrayList<>();

    public Nave(){
        super();
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getDesgaste() {
        return desgaste;
    }

    public void setDesgaste(int desgaste) {
        this.desgaste = desgaste;
    }

    public void agregaTripulante(Tripulante tripulantes){
        this.tripulantes.add(tripulantes);
    }
}