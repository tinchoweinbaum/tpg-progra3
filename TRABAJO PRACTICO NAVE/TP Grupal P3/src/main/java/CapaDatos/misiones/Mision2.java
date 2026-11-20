package CapaDatos.misiones;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.RegistroBitacora;
import CapaDatos.destinoytablero.Posicion;
import java.util.Random;

/**
 * Misión M-02 (Recolección): la CapaDatos.nave debe desplazarse hasta el objetivo para recolectar
 * una cantidad aleatoria de gemas espaciales.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Mision && rand != null</p>
 */
public class Mision2 extends Mision{

    private Random rand = new Random();

    /**
     * Construye una instancia de Mision2 con costos base (4 combustible, 10 energía, 4 desgaste).
     *
     * @param objetivo Coordenadas del objetivo en el tablero.
     * @pre objetivo != null
     * @post Se inicializa Mision2 con nombre "M-02", descripción "RECOLECCION" y el objetivo indicado.
     */
    public Mision2(Posicion objetivo) {
        super("M-02", "RECOLECCION", 4, 10, 4,objetivo);
    }

    /**
     * Registra en la bitácora la cantidad aleatoria de gemas recolectadas (entre 20 y 99)
     * y notifica el fin de la misión si esta se encuentra activa.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y su bitácora.
     * @pre ac != null && ac.getBitacorasNave() != null
     * @post Si estadoActual == ESTADO_ACTIVA, agrega un RegistroBitacora con las gemas recolectadas y notifica "FIN_MISION".
     */
    @Override
    protected void cerrar(Asistente ac) {
        if (getEstadoActual().equals(Mision.ESTADO_ACTIVA)){
            int gemas = rand.nextInt(20,100);
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("LA NAVE REALIZO UNA RECOLECCION DE " + gemas + " GEMAS"));
            notificarObservadores("FIN_MISION");
        }
    }
}
