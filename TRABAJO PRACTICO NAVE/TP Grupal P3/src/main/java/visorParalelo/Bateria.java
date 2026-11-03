package visorParalelo;

public class Bateria {
    private static int contadorID = 0;
    private int ID;
    private int duracion;

    Bateria (int duracion){
        this.ID = aumentarID();
        this.duracion = duracion;
    }

    Bateria (){
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
