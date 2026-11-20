package CapaDatos.visorParalelo;

import CapaNegocio.*;
import java.util.ArrayList;

/**
 * Gestor de la simulación concurrente del universo paralelo encargado de instanciar,
 * iniciar y detener los hilos de naves generadoras y consumidoras, comunicando eventos al controlador.
 * <p><b>Invariante de clase:</b> generadoras != null && consumidoras != null && plataforma != null && controlador != null</p>
 */
public class Visor implements IVisor {
    private ArrayList<Thread> generadoras;
    private ArrayList<Thread> consumidoras;
    private PlataformaDeCarga plataforma;
    private Controlador controlador;

    /**
     * Construye el visor paralelo inicializando las listas de hilos y una plataforma de carga con tope 3.
     *
     * @param controlador Controlador principal encargado de actualizar la vista gráfica.
     * @pre controlador != null
     * @post generadoras y consumidoras quedan vacías, plataforma se inicializa con tope 3 y this.controlador == controlador.
     */
    public Visor(Controlador controlador){
        generadoras = new ArrayList<>();
        consumidoras = new ArrayList<>();
        plataforma = new PlataformaDeCarga(3,this);
        this.controlador = controlador;
    }

    /**
     * Envía un mensaje de registro desde el entorno paralelo hacia el controlador para mostrarlo en la interfaz.
     *
     * @param text Texto descriptivo del evento ocurrido.
     * @pre controlador != null && text != null
     * @post Se invoca controlador.actualizaVisorParalelo(text).
     */
    public void actualizaBitacoraParalela(String text){
        controlador.actualizaVisorParalelo(text);
    }

    /**
     * Crea una nueva CapaDatos.nave generadora, la envuelve en un hilo identificado, la registra y comienza su ejecución.
     *
     * @pre generadoras != null && plataforma != null
     * @post Se añade un nuevo hilo activo de NaveGeneradora a la lista generadoras y se inicia su ejecución (start()).
     */
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

    /**
     * Crea una nueva CapaDatos.nave consumidora, la envuelve en un hilo identificado, la registra y comienza su ejecución.
     *
     * @pre consumidoras != null && plataforma != null
     * @post Se añade un nuevo hilo activo de NaveConsumidora a la lista consumidoras y se inicia su ejecución (start()).
     */
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

    /**
     * Remueve de la lista la CapaDatos.nave generadora más antigua (FIFO) e interrumpe su hilo para finalizar su ejecución.
     *
     * @pre generadoras != null
     * @post Si generadoras no estaba vacía, se elimina su primer elemento y se llama a interrupt() sobre dicho hilo.
     */
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

    /**
     * Remueve de la lista la CapaDatos.nave consumidora más antigua (FIFO) e interrumpe su hilo para finalizar su ejecución.
     *
     * @pre consumidoras != null
     * @post Si consumidoras no estaba vacía, se elimina su primer elemento y se llama a interrupt() sobre dicho hilo.
     */
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

    /**
     * Actualiza la capacidad máxima de baterías permitida en la plataforma de carga.
     *
     * @param t Nueva capacidad máxima de la plataforma.
     * @pre plataforma != null && t > 0
     * @post Se actualiza el tope de la plataforma a t mediante plataforma.setTope(t).
     */
    public void cambiarTope(int t){
        plataforma.setTope(t);
    }
}
