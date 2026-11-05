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
                Thread.sleep(b.getDuracion());
                System.out.println(nombreHilo() + " agoto la " + b);
            } catch (InterruptedException e){
                //Si hay un problema de hilos
                System.out.println(nombreHilo() + " interrumpida. Finalizando hilo.");
                break;
            } catch (Exception e){
                //Cualquier otro error
                System.out.println(nombreHilo() + " sufrio un error " + e.toString());
                break;
            }
        }
    }

    public String nombreHilo(){
        return Thread.currentThread().getName();
    }
}
