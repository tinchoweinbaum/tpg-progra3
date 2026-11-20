package CapaDatos.visorParalelo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Recurso producido por las naves generadoras y consumido por las naves consumidoras
 * dentro de la simulación concurrente del universo paralelo.
 * <p><b>Invariante de clase:</b> ID > 0 && duracion > 0 && creador != null && !creador.trim().isEmpty()</p>
 */
public class Bateria {
    private static int contadorID = 1;
    private final int ID;
    private final int duracion;
    private final String creador;

    /**
     * Construye una batería con un identificador único autoincremental, un creador y una duración específica en milisegundos.
     *
     * @param creador Nombre del hilo o entidad que fabricó la batería.
     * @param duracion Tiempo de duración de la batería en milisegundos.
     * @pre creador != null && !creador.trim().isEmpty() && duracion > 0
     * @post getID() > 0 && getDuracion() == duracion && toString().contains(creador)
     */
    public Bateria (String creador,int duracion){
        this.ID = aumentarID();
        this.duracion = duracion;
        this.creador = creador;
    }

    /**
     * Construye una batería con un identificador único autoincremental y una duración aleatoria entre 5000 y 14999 ms.
     *
     * @param creador Nombre del hilo o entidad que fabricó la batería.
     * @pre creador != null && !creador.trim().isEmpty()
     * @post getID() > 0 && getDuracion() >= 5000 && getDuracion() < 15000 && toString().contains(creador)
     */
    public Bateria (String creador){
        this.ID = aumentarID();
        this.duracion = ThreadLocalRandom.current().nextInt(5000, 15000);
        this.creador = creador;
    }

    /**
     * Incrementa y devuelve de forma atómica (thread-safe) el contador global de identificadores de baterías.
     *
     * @return Entero positivo único asignado como ID.
     * @pre true
     * @post Retorna el valor previo de contadorID y lo incrementa en 1 de manera sincronizada.
     */
    private static synchronized int aumentarID(){
        return contadorID++;
    }

    /**
     * Obtiene la duración de la batería en milisegundos.
     *
     * @return Entero positivo con la duración de la batería.
     * @pre true
     * @post Retorna duracion sin modificar el estado del objeto.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el identificador único de la batería.
     *
     * @return Entero positivo con el ID de la batería.
     * @pre true
     * @post Retorna ID sin modificar el estado del objeto.
     */
    public int getID() {
        return ID;
    }

    /**
     * Devuelve la representación textual de la batería indicando su ID y el hilo creador.
     *
     * @return Cadena formateada como "bateria <ID> [Creada por <creador>]".
     * @pre true
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString() {
        return "bateria " + this.ID + " [Creada por " + this.creador + "]";
    }
}
