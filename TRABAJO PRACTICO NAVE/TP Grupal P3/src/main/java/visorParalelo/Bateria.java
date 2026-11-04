package visorParalelo;

public class Bateria {
    private static int contadorID = 1;
    private int ID;
    private int duracion;

    public Bateria (int duracion){
        this.ID = aumentarID();
        this.duracion = duracion;
    }

    public Bateria (){
        this(500);
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
}
