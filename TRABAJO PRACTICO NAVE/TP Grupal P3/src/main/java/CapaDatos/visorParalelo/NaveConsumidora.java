package CapaDatos.visorParalelo;

/**
 * Tarea ejecutada en un hilo independiente (rol Consumidor en el patrón Productor-Consumidor)
 * que retira baterías de la plataforma de carga y simula su consumo durante la duración de cada batería.
 * <p><b>Invariante de clase:</b> ID > 0 && plataforma != null</p>
 */
public class NaveConsumidora implements Runnable{
    private static int contadorID = 1;
    private final int ID;
    private final PlataformaDeCarga plataforma;

    /**
     * Construye una CapaDatos.nave consumidora vinculada a una plataforma de carga y le asigna un ID único.
     *
     * @param plataforma Monitor compartido desde donde se extraerán las baterías.
     * @pre plataforma != null
     * @post getID() > 0 && this.plataforma == plataforma
     */
    public NaveConsumidora(PlataformaDeCarga plataforma){
        this.ID = aumentarID();
        this.plataforma = plataforma;
    }

    /**
     * Incrementa y devuelve de forma atómica (thread-safe) el contador global de identificadores de naves consumidoras.
     *
     * @return Entero positivo único asignado como ID.
     * @pre true
     * @post Retorna el valor previo de contadorID y lo incrementa en 1 de manera sincronizada.
     */
    private static synchronized int aumentarID(){
        return contadorID++;
    }

    /**
     * Obtiene el identificador único de la CapaDatos.nave consumidora.
     *
     * @return Entero positivo con el ID de la CapaDatos.nave.
     * @pre true
     * @post Retorna ID sin modificar el estado del objeto.
     */
    public int getID() {
        return ID;
    }

    /**
     * Bucle principal de ejecución del hilo consumidor: extrae baterías de la plataforma,
     * duerme el tiempo correspondiente a su duración y reporta el agotamiento hasta que el hilo es interrumpido.
     *
     * @pre plataforma != null
     * @post Consume baterías continuamente de plataforma y notifica eventos hasta recibir una interrupción o excepción.
     */
    @Override
    public void run() {
        Bateria b;
        while (true){
            try {
                b = plataforma.sacarBateria();
                Thread.sleep(b.getDuracion());
                plataforma.recibeMensajeNaves(nombreHilo() + " agoto la " + b);
            } catch (InterruptedException e){
                //Si hay un problema de hilos
                plataforma.recibeMensajeNaves(nombreHilo() + " interrumpida. Finalizando hilo.");
                break;
            } catch (Exception e){
                //Cualquier otro error
                plataforma.recibeMensajeNaves(nombreHilo() + " sufrio un error " + e.toString());
                break;
            }
        }
    }

    /**
     * Obtiene el nombre del hilo actual que está ejecutando este método.
     *
     * @return Cadena con el nombre del hilo en ejecución.
     * @pre true
     * @post Retorna Thread.currentThread().getName() sin efectos colaterales.
     */
    public String nombreHilo(){
        return Thread.currentThread().getName();
    }
}
