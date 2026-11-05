package visorParalelo;

import java.util.ArrayList;

public class Visor {
    private ArrayList<Thread> generadoras;
    private ArrayList<Thread> consumidoras;
    private PlataformaDeCarga plataforma;

    public Visor(){
        generadoras = new ArrayList<>();
        consumidoras = new ArrayList<>();
        plataforma = new PlataformaDeCarga();
    }

    public void nuevaGeneradora(){
        NaveGeneradora nuevaGen;
        Thread hilo;

        nuevaGen = new NaveGeneradora(plataforma);
        hilo = new Thread(nuevaGen, "Nave Generadora " + nuevaGen.getID());

        synchronized (generadoras) {
            generadoras.add(hilo);
        }

        hilo.start();
    }

    public void nuevaConsumidora(){
        NaveConsumidora nuevaCon;
        Thread hilo;

        nuevaCon = new NaveConsumidora(plataforma);
        hilo = new Thread(nuevaCon, "Nave Consumidora " + nuevaCon.getID());

        synchronized (consumidoras) {
            consumidoras.add(hilo);
        }

        hilo.start();
    }

    // Se pueden cambiar para que se elimine el ultimo o uno en concreto

    public void eliminarGeneradora(){
        Thread primerHilo = null;
        synchronized (generadoras) {
            if (!generadoras.isEmpty()) {
                primerHilo = generadoras.remove(0);
            }
        }

        if (primerHilo != null) {
            primerHilo.interrupt();
        }
    }

    public void eliminarConsumidora(){
        Thread primerHilo = null;
        synchronized (consumidoras) {
            if (!consumidoras.isEmpty()) {
                primerHilo = consumidoras.remove(0);
            }
        }
        if (primerHilo != null){
            primerHilo.interrupt();
        }
    }

    public void cambiarTope(int t){
        plataforma.setTope(t);
    }
}
