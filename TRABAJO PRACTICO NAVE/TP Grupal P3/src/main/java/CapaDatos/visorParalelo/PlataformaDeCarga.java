package CapaDatos.visorParalelo;

import java.util.ArrayDeque;

/**
 * Monitor sincronizado (búfer acotado FIFO) que coordina el depósito y retiro concurrente
 * de baterías entre naves generadoras y consumidoras.
 * <p><b>Invariante de clase:</b> baterias != null && tope > 0 && visor != null && baterias.size() >= 0 && baterias.size() <= tope</p>
 */
public class PlataformaDeCarga {
    private ArrayDeque<Bateria> baterias;
    private int tope;
    private Visor visor;

    /**
     * Construye una plataforma de carga con capacidad máxima personalizada y un visor asociado.
     *
     * @param t Capacidad máxima de baterías que soporta la plataforma.
     * @param visor Visor encargado de reportar los eventos de la plataforma.
     * @pre t > 0 && visor != null
     * @post baterias != null && baterias.isEmpty() && getTope() == t && this.visor == visor
     */
    public PlataformaDeCarga(int t,Visor visor){
        this.baterias = new ArrayDeque<>();
        this.tope = t;
        this.visor = visor;
    }

    /**
     * Construye una plataforma de carga con capacidad máxima por defecto de 5 baterías.
     *
     * @param visor Visor encargado de reportar los eventos de la plataforma.
     * @pre visor != null
     * @post baterias != null && baterias.isEmpty() && getTope() == 5 && this.visor == visor
     */
    public PlataformaDeCarga(Visor visor){
        this(5,visor);
    }

    /**
     * Deposita una batería en el extremo frontal de la cola de la plataforma.
     * Si la plataforma alcanzó su capacidad máxima, el hilo productor queda en espera (wait) hasta liberar espacio.
     *
     * @param b Batería a depositar en la plataforma.
     * @throws InterruptedException Si el hilo es interrumpido mientras espera espacio disponible.
     * @throws NullPointerException Si la batería b recibida es null.
     * @pre b != null
     * @post b queda agregada en la plataforma (baterias.size() <= tope), se reporta el evento al visor y se notifica a todos los hilos en espera (notifyAll).
     */
    public synchronized void ponerBateria(Bateria b) throws InterruptedException, NullPointerException {
        if (b != null){
            while (baterias.size() >= tope) {
                visor.actualizaBitacoraParalela(nombreHilo() + " intento poner una bateria pero no hay mas capacidad");
                wait();
            }
            baterias.addFirst(b);
            visor.actualizaBitacoraParalela(nombreHilo() + " puso la bateria " + b.getID() + " en la plataforma | Ahora hay " + baterias.size() + " baterias");
            notifyAll();
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Retira y devuelve la batería más antigua (extremo final de la cola) de la plataforma.
     * Si la plataforma está vacía, el hilo consumidor queda en espera (wait) hasta que se deposite una batería.
     *
     * @return Instancia de Bateria extraída de la plataforma.
     * @throws InterruptedException Si el hilo es interrumpido mientras espera una batería disponible.
     * @pre true
     * @post Retorna la batería más antigua retirada de la cola, decrementa baterias.size() en 1, reporta el evento al visor y ejecuta notifyAll().
     */
    public synchronized Bateria sacarBateria() throws InterruptedException {
        Bateria b;
        while (baterias.isEmpty()){
            visor.actualizaBitacoraParalela(nombreHilo() + " intento sacar una bateria pero no hay");
            wait();
        }
        b = baterias.removeLast();
        visor.actualizaBitacoraParalela(nombreHilo() + " saco la " + b + " de la plataforma | Ahora hay " + baterias.size() + " baterias");
        notifyAll();
        return b;
    }

    /**
     * Reenvía un mensaje de actividad de las naves hacia el visor paralelo para su visualización.
     *
     * @param text Mensaje de evento a mostrar en consola.
     * @pre visor != null && text != null
     * @post El mensaje text es enviado a visor.actualizaBitacoraParalela(text).
     */
    public void recibeMensajeNaves(String text){
        visor.actualizaBitacoraParalela(text);
    }

    /**
     * Obtiene la capacidad máxima actual de baterías de la plataforma.
     *
     * @return Entero positivo con el tope de almacenamiento.
     * @pre true
     * @post Retorna tope sin modificar el estado de la plataforma.
     */
    public int getTope() {
        return tope;
    }

    /**
     * Modifica la capacidad máxima de baterías de la plataforma y despierta a los hilos en espera.
     *
     * @param tope Nueva capacidad máxima de almacenamiento.
     * @pre tope > 0
     * @post getTope() == tope y se ejecuta notifyAll() para reevaluar las condiciones de espera.
     */
    public synchronized void setTope(int tope) {
        this.tope = tope;
        notifyAll();
    }

    /**
     * Obtiene el nombre del hilo actual que está invocando el método.
     *
     * @return Cadena con el nombre del hilo actual.
     * @pre true
     * @post Retorna Thread.currentThread().getName() sin efectos colaterales.
     */
    public String nombreHilo(){
        return Thread.currentThread().getName();
    }
}
