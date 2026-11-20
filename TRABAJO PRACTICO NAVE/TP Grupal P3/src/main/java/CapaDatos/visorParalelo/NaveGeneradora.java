package CapaDatos.visorParalelo;

/**
 * Tarea ejecutada en un hilo independiente (rol Productor en el patrón Productor-Consumidor)
 * que fabrica baterías periódicamente y las deposita en la plataforma de carga.
 * <p><b>Invariante de clase:</b> ID > 0 && plataforma != null</p>
 */
public class NaveGeneradora implements Runnable{
    private static int contadorID = 1;
    private final int ID;
    private final PlataformaDeCarga plataforma;

    /**
     * Construye una CapaDatos.nave generadora vinculada a una plataforma de carga y le asigna un ID único.
     *
     * @param plataforma Monitor compartido donde se depositarán las baterías creadas.
     * @pre plataforma != null
     * @post getID() > 0 && this.plataforma == plataforma
     */
    public NaveGeneradora(PlataformaDeCarga plataforma){
        this.ID = aumentarID();
        this.plataforma = plataforma;
    }

    /**
     * Incrementa y devuelve de forma atómica (thread-safe) el contador global de identificadores de naves generadoras.
     *
     * @return Entero positivo único asignado como ID.
     * @pre true
     * @post Retorna el valor previo de contadorID y lo incrementa en 1 de manera sincronizada.
     */
    private static synchronized int aumentarID(){
        return contadorID++;
    }

    /**
     * Obtiene el identificador único de la CapaDatos.nave generadora.
     *
     * @return Entero positivo con el ID de la CapaDatos.nave.
     * @pre true
     * @post Retorna ID sin modificar el estado del objeto.
     */
    public int getID() {
        return ID;
    }

    /**
     * Instancia una nueva batería asignándole el nombre del hilo actual y reporta su creación en el visor.
     *
     * @return Nueva instancia de Bateria.
     * @pre plataforma != null
     * @post Retorna una Bateria != null y envía el mensaje de creación a plataforma.
     */
    private Bateria crearBateria(){
        Bateria b;
        b = new Bateria(nombreHilo());
        plataforma.recibeMensajeNaves(nombreHilo() + " creo una nueva bateria " + b.getID());
        return b;
    }

    /**
     * Bucle principal de ejecución del hilo productor: cada 5 segundoS fabrica una batería
     * y la deposita en la plataforma de carga hasta que el hilo es interrumpido.
     *
     * @pre plataforma != null
     * @post Produce y deposita baterías periódicamente en plataforma hasta recibir una interrupción o excepción.
     */
    @Override
    public void run() {
        Bateria b;
        while (true){
            try {
                Thread.sleep(5000);
                b = crearBateria();
                plataforma.ponerBateria(b);
            } catch (InterruptedException e) {
                //Si hay un problema de hilos
                plataforma.recibeMensajeNaves(nombreHilo() + " interrumpida. Finalizando hilo.");
                break;
            } catch (Exception e){
                //Si hay un problema desconocido, como null pointer
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
