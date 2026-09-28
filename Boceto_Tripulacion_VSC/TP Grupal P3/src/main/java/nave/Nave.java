
package nave;
import asistentes.*;
import java.util.ArrayList;
import tripulantes.*;

abstract public class Nave{

    protected int combustible,energia,desgaste;
    protected double nivelDesgaste;
    protected final Asistente asistenteCabina;
    protected final Warp motor;
    protected ArrayList<Tripulante> tripulantes = new ArrayList<>();

    public Nave(Asistente ac,Warp motor){
        super();
        this.asistenteCabina = ac;
        this.motor = motor;
    }

    public double getNivelDesgaste() {
        return nivelDesgaste;
    }

    public void setNivelDesgaste(double nivelDesgaste) {
        this.nivelDesgaste = nivelDesgaste;
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

    public Asistente getAsistenteCabina() {
        return asistenteCabina;
    }

    public Warp getMotor() {
        return motor;
    }

    public void agregaTripulante(Tripulante tripulantes){
        this.tripulantes.add(tripulantes);
    }
}