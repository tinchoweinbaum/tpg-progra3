package CapaDatos.misiones;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.RegistroBitacora;
import CapaDatos.destinoytablero.Posicion;

/**
 * Misión M-03 (Retorno a punto seguro): la CapaDatos.nave debe regresar a la estación espacial central
 * para habilitar las operaciones de recarga y mantenimiento.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Mision</p>
 */
public class Mision3 extends Mision{

    /**
     * Construye una instancia de Mision3 con costos base (4 combustible, 0 energía, 4 desgaste).
     *
     * @param objetivo Coordenadas de la estación espacial segura en el tablero.
     * @pre objetivo != null
     * @post Se inicializa Mision3 con nombre "M-03", descripción "RETORNO A PUNTO SEGURO" y el objetivo indicado.
     */
    public Mision3(Posicion objetivo) {
        super("M-03", "RETORNO A PUNTO SEGURO", 4, 0, 4,objetivo);
    }

    /**
     * Registra el arribo a la estación espacial y notifica "FIN_MISION_BASE" para habilitar
     * el mantenimiento y recarga de recursos de la CapaDatos.nave.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y su bitácora.
     * @pre ac != null && ac.getBitacorasNave() != null
     * @post Si estadoActual == ESTADO_ACTIVA, agrega un RegistroBitacora informando el arribo y notifica "FIN_MISION_BASE".
     */
    @Override
    protected void cerrar(Asistente ac) {
        if (getEstadoActual().equals(Mision.ESTADO_ACTIVA)){
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("LA NAVE ARRIBO A LA ESTACION ESPACIAL Y PUEDE ABASTECER SUS RECURSOS"));
            notificarObservadores("FIN_MISION_BASE");
        }
    }
}
