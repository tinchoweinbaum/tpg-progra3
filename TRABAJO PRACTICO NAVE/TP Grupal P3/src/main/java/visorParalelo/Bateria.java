package visorParalelo;

import java.util.concurrent.ThreadLocalRandom;

public class Bateria {
    private static int contadorID = 1;
    private final int ID;
    private final int duracion;
    private final String creador;

    public Bateria (String creador,int duracion){
        this.ID = aumentarID();
        this.duracion = duracion;
        this.creador = creador;
    }

    public Bateria (String creador){
        this.ID = aumentarID();
        this.duracion = ThreadLocalRandom.current().nextInt(500, 1501);
        this.creador = creador;
    }

    private static synchronized int aumentarID(){
        return contadorID++;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "bateria " + this.ID + " [Creada por " + this.creador + "]";
    }
}
