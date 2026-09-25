package asistentes;
import misiones.*;

public class Asistente {
    private String nombre;
    Mision misionAct = null; // Cuando el asistente nace no tiene misión asignada.
    // Maquina de estados de warpeo
    // Motor warp


    public Asistente(String nombre) {
        this.nombre = nombre;
    }

    public void setMisionAct(Mision mision){
        this.misionAct = mision;
    }
}
