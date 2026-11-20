package CapaDatos.motorwarp;
import CapaDatos.asistentes.Asistente;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.EstadoInvalidoException;

/**
 * Estado concreto del patrón State que representa la fase de disipación térmica del motor warp
 * luego de completar un salto.
 * <p><b>Invariante de clase:</b> motor != null</p>
 */
public class Enfriamiento implements State {
    
    private MotorWarp motor;

    /**
     * Construye el estado Enfriamiento asociado a un MotorWarp.
     *
     * @param motor Instancia de MotorWarp propietaria de este estado.
     * @pre motor != null
     * @post this.motor == motor
     */
    public Enfriamiento (MotorWarp motor){
        this.motor = motor;
    }

    /**
     * Completa el enfriamiento transicionando el motor nuevamente al estado Disponible y notificando a los observadores.
     *
     * @pre motor != null
     * @post motor.getEstadoActual() instanceof Disponible y se notifica "MOTOR_DISPONIBLE".
     */
    @Override
    public void disponible(){
        motor.setEstado(new Disponible(motor));
        motor.notificarObservadores("MOTOR_DISPONIBLE");
    }
    
    @Override
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException {
        throw new EstadoInvalidoException("El motor ya preparo el salto warp");
    }

    
    @Override
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("Ya se ha realizado el salto warp");
    }
    
    @Override
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("Ya se ha enfriado el motor");
    }

    @Override
    public String toString(){
        return "ENFRIAMIENTO";
    }
}

