package CapaDatos.motorwarp;
import CapaDatos.asistentes.Asistente;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.*;

/**
 * Estado concreto del patrón State que representa al motor ejecutando el desplazamiento hiperespacial.
 * <p><b>Invariante de clase:</b> motor != null</p>
 */
public class SaltoWarp implements State {
    
    private MotorWarp motor;

    /**
     * Construye el estado SaltoWarp asociado a un MotorWarp.
     *
     * @param motor Instancia de MotorWarp propietaria de este estado.
     * @pre motor != null
     * @post this.motor == motor
     */
    public SaltoWarp(MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible() throws EstadoInvalidoException {
        throw new EstadoInvalidoException("El motor ya esta usandose");
    }
    
    @Override
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya preparo el salto warp");
    }

    @Override
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("Ya se ha realizado el salto warp");
    }

    /**
     * Transiciona el motor a Enfriamiento, notifica a los observadores, avanza 1 turno en el tablero,
     * aplica el desgaste por salto warp a la CapaDatos.nave y finalmente restablece el motor a Disponible.
     *
     * @param tablero Tablero de juego.
     * @param ac Asistente que gestiona turnos y desgaste.
     * @param direccion Vector de desplazamiento utilizado.
     * @throws CantCombustibleInvalidaException Si ocurre un error con el combustible durante el turno.
     * @throws NaveDestruidaException Si el desgaste acumulado alcanza o supera el máximo permitido.
     * @throws CantEnergiaInvalidaException Si ocurre un error con la energía durante el turno.
     * @throws CantDesgasteInvalidaException Si el monto de desgaste es inválido.
     * @pre tablero != null && ac != null && direccion != null
     * @post Se notifica "MOTOR_ENFRIANDO", transcurre 1 turno, se suma CONSUMO_DESGASTE_SALTO_WARP al desgaste de la CapaDatos.nave y el motor queda en estado Disponible.
     */
    @Override
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        motor.setEstado(new Enfriamiento(motor));
        motor.notificarObservadores("MOTOR_ENFRIANDO");
        ac.darTurnosTablero(1);
        ac.aumentaDesgasteNave(MotorWarp.CONSUMO_DESGASTE_SALTO_WARP);
        motor.getEstadoActual().disponible();
    }

    @Override
    public String toString(){
        return "EN SALTO WARP";
    }
     
}

    
