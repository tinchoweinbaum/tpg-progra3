package CapaDatos.motorwarp;
import CapaDatos.asistentes.Asistente;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.*;

/**
 * Estado concreto del patrón State que representa al motor warp listo para iniciar un salto.
 * <p><b>Invariante de clase:</b> motor != null</p>
 */
public class Disponible implements State {

    private final MotorWarp motor;

    /**
     * Construye el estado Disponible asociado a un MotorWarp.
     *
     * @param motor Instancia de MotorWarp propietaria de este estado.
     * @pre motor != null
     * @post this.motor == motor
     */
    public Disponible (MotorWarp motor){
        this.motor = motor;
    }

    @Override
    public void disponible() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya esta disponible");
    }

    /**
     * Cambia el estado del motor a PreparandoSalto, notifica a los observadores, avanza 2 turnos en el tablero
     * y, si la CapaDatos.nave conserva energía suficiente, dispara el salto warp.
     *
     * @param tablero Tablero de juego.
     * @param ac Asistente que controla los turnos y la CapaDatos.nave.
     * @param direccion Vector de desplazamiento del salto warp.
     * @throws CantCombustibleInvalidaException Si ocurre un error con el combustible durante los turnos de espera.
     * @throws NaveDestruidaException Si la CapaDatos.nave es destruida durante la preparación o el salto.
     * @throws CantEnergiaInvalidaException Si ocurre un error con la energía.
     * @throws CantDesgasteInvalidaException Si ocurre un error con el desgaste.
     * @pre tablero != null && ac != null && ac.getNave() != null && direccion != null
     * @post motor.getEstadoActual() cambia a PreparandoSalto, se notifica "MOTOR_PREPARANDO", transcurren 2 turnos y, si energia > CONSUMO_ENERGIA_SALTO_WARP, se ejecuta saltando().
     */
    @Override
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        motor.setEstado(new PreparandoSalto(motor));
        motor.notificarObservadores("MOTOR_PREPARANDO");
        ac.darTurnosTablero(2);
        if (ac.getNave().getEnergia()>MotorWarp.CONSUMO_ENERGIA_SALTO_WARP)
            motor.getEstadoActual().saltando(tablero,ac,direccion);
    }

    @Override
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor no preparo el salto warp");
    }
    
    @Override
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor todavia no se uso");
    }

    @Override
    public String toString(){
        return "DISPONIBLE";
    }
}
