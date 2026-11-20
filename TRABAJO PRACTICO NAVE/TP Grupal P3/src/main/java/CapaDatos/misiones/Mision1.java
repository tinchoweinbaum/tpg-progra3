package CapaDatos.misiones;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.RegistroBitacora;
import CapaDatos.destinoytablero.Posicion;
import CapaDatos.exceptions.*;
import java.util.Random;

/**
 * Misión M-01 (Intercepción y asistencia): la CapaDatos.nave debe llegar al objetivo y entregar
 * una cantidad aleatoria de energía o combustible para rescatar a una CapaDatos.nave en peligro.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Mision && rand != null && ((energiaSOS > 0 && combustibleSOS == 0) || (energiaSOS == 0 && combustibleSOS > 0))</p>
 */
public class Mision1 extends Mision{

    private Random rand = new Random();
    private int energiaSOS = 0;
    private int combustibleSOS = 0;

    /**
     * Construye una instancia de Mision1 con costos base (4 combustible, 5 energía, 4 desgaste)
     * y determina aleatoriamente el recurso requerido para el salvataje.
     *
     * @param objetivo Coordenadas del objetivo en el tablero.
     * @pre objetivo != null
     * @post Se inicializa Mision1 con nombre "M-01", descripción "INTERCEPCION Y ASISTENCIA" y un requerimiento SOS positivo de energía o combustible.
     */
    public Mision1(Posicion objetivo){
        super("M-01", "INTERCEPCION Y ASISTENCIA", 4, 5, 4,objetivo);
        deterRecursoSOS();
    }

    /**
     * Determina de forma aleatoria si la asistencia requerirá energía (1 a 50 unidades) o combustible (1 a 50 litros).
     *
     * @pre rand != null
     * @post Asigna un valor entero entre 1 y 50 a energiaSOS o a combustibleSOS, dejando el otro en 0.
     */
    private void deterRecursoSOS(){
        int opcion = rand.nextInt(2)+1;
        switch (opcion){
            case 1:
                energiaSOS = rand.nextInt(50)+1;
                break;
            case 2:
                combustibleSOS = rand.nextInt(50)+1;
                break;
        }
    }

    /**
     * Ejecuta el desplazamiento de la misión y verifica al llegar si la CapaDatos.nave cuenta con reservas suficientes
     * para entregar el recurso SOS requerido.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y el tablero.
     * @pre ac != null && ac.getNave() != null && ac.getTablero() != null && ac.getBitacorasNave() != null
     * @post Ejecuta el recorrido base; si la CapaDatos.nave no dispone de más energía o combustible que el requerido por el salvataje, cambia estadoActual a ESTADO_FALLIDO y guarda el motivo en causaError.
     */
    @Override
    public void ejecutar(Asistente ac){
        super.ejecutar(ac);
        try{
            if (energiaSOS > 0 && ac.getNave().getEnergia() <= energiaSOS)//agregar que cambie estado
                throw new EnergiaInsuficienteException("No alcanza la reserva de energia para realizar el salvataje");

            if (combustibleSOS>0 && ac.getNave().getCombustible()<=combustibleSOS)
                throw new CombustibleInsuficienteException("No alcanza la reserva de combustible para realizar el salvataje");
        } catch (EnergiaInsuficienteException | CombustibleInsuficienteException e) {
            estadoActual = ESTADO_FALLIDO;;
            causaError = e.getMessage();
        }
    }

    /**
     * Concreta la entrega del recurso de asistencia a la CapaDatos.nave rescatada, lo registra en la bitácora
     * y notifica el fin de la misión si esta sigue activa.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave y su bitácora.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Si estadoActual == ESTADO_ACTIVA, descuenta energiaSOS o combustibleSOS de la CapaDatos.nave, registra el salvataje en bitácora y notifica "FIN_MISION".
     */
    @Override
    protected void cerrar(Asistente ac) {
        if(estadoActual.equals(ESTADO_ACTIVA)) {
            String texto = "";
            if (energiaSOS > 0) {
                ac.getNave().setEnergia(ac.getNave().getEnergia() - energiaSOS);
                texto += String.valueOf(energiaSOS) + " UNIDADES DE ENERGIA";
            }
            if (combustibleSOS > 0) {
                ac.getNave().setCombustible(ac.getNave().getCombustible() - combustibleSOS);
                texto += String.valueOf(combustibleSOS) + " LITROS DE COMBUSTIBLE";
            }
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("SE REALIZO CORRECTAMENTE LA ASISTENCIA A LA NAVE EN CUESTION - SE ENTREGARON " + texto));
            notificarObservadores("FIN_MISION");
        }
    }
}
