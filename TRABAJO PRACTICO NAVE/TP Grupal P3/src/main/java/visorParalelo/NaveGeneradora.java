package visorParalelo;

public class NaveGeneradora implements Runnable{
    private static int contadorID = 1;
    private final int ID;
    private final PlataformaDeCarga plataforma;

    public NaveGeneradora(PlataformaDeCarga plataforma){
        this.ID = aumentarID();
        this.plataforma = plataforma;
    }

    private static synchronized int aumentarID(){
        return contadorID++;
    }

    public int getID() {
        return ID;
    }

    private Bateria crearBateria(){
        Bateria b;
        b = new Bateria(nombreHilo());
        System.out.println(nombreHilo() + " creo una nueva bateria " + b.getID());
        return b;
    }

    @Override
    public void run() {
        Bateria b;
        while (true){
            try {
                Thread.sleep(1000);
                b = crearBateria();
                plataforma.ponerBateria(b);
            } catch (InterruptedException e) {
                //Si hay un problema de hilos
                System.out.println(nombreHilo() + " interrumpida. Finalizando hilo.");
                break;
            } catch (Exception e){
                //Si hay un problema desconocido, como null pointer
                System.out.println(nombreHilo() + " sufrio un error " + e.toString());
                break;
            }
        }
    }

    public String nombreHilo(){
        return Thread.currentThread().getName();
    }
}
