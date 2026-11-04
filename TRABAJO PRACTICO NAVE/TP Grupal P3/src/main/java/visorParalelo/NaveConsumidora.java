package visorParalelo;

public class NaveConsumidora implements Runnable{
    private static int contadorID = 1;
    private final int ID;
    private final PlataformaDeCarga plataforma;

    public NaveConsumidora(PlataformaDeCarga plataforma){
        this.ID = aumentarID();
        this.plataforma = plataforma;
    }

    private static synchronized int aumentarID(){
        return contadorID++;
    }

    public int getID() {
        return ID;
    }

    @Override
    public void run() {
        Bateria b;
        while (true){
            try {
                b = plataforma.sacarBateria();
                System.out.println("La nave consumidora " + this.ID + " saco la bateria " + b.getID() + " de la plataforma");
                Thread.sleep(b.getDuracion());
                System.out.println("La nave consumidora " + this.ID + " agoto la bateria " + b.getID());
            } catch (InterruptedException e){
                //Si hay un problema de hilos
                System.out.println("Nave consumidora " + this.ID + " interrumpida. Finalizando hilo.");
                break;
            } catch (Exception e){
                //Cualquier otro error
                System.out.println("La nave consumidora " + this.ID + " sufrio un error " + e.toString());
                break;
            }
        }
    }
}
