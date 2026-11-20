package CapaDatos.motorwarp;
import CapaDatos.asistentes.Asistente;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.*;

/**
 * Estado concreto del patrón State que representa al motor warp cargando energía antes de ejecutar el salto.
 * <p><b>Invariante de clase:</b> motor != null</p>
 */
public class PreparandoSalto implements State {
    
    private final MotorWarp motor;

    /**
     * Construye el estado PreparandoSalto asociado a un MotorWarp.
     *
     * @param motor Instancia de MotorWarp propietaria de este estado.
     * @pre motor != null
     * @post this.motor == motor
     */
    public PreparandoSalto (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible() throws EstadoInvalidoException {
        throw new EstadoInvalidoException("El motor ya esta usandose");
    }
    
    @Override
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya esta preparandose");
    }

    /**
     * Transiciona el motor a SaltoWarp, notifica a los observadores, simula el tiempo de salto,
     * descuenta la energía requerida, mueve la CapaDatos.nave en el tablero e inicia el enfriamiento.
     *
     * @param tablero Tablero donde se desplaza la CapaDatos.nave.
     * @param ac Asistente que gestiona la energía y turnos.
     * @param direccion Vector de desplazamiento del salto.
     * @throws NaveDestruidaException Si la energía se agota o la CapaDatos.nave sufre daño fatal.
     * @throws CantEnergiaInvalidaException Si el descuento de energía resulta inválido.
     * @throws CantCombustibleInvalidaException Si ocurre un error con el combustible durante el enfriamiento.
     * @throws CantDesgasteInvalidaException Si ocurre un error con el desgaste durante el enfriamiento.
     * @pre tablero != null && tablero.getPosicionNave() != null && ac != null && direccion != null
     * @post Se notifica "MOTOR_SALTANDO", se consume CONSUMO_ENERGIA_SALTO_WARP, se actualiza la posición de la CapaDatos.nave en tablero y se llama a enfriamiento().
     */
    @Override
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException, CantDesgasteInvalidaException {
        motor.setEstado(new SaltoWarp(motor));
        motor.notificarObservadores("MOTOR_SALTANDO");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            motor.getEstadoActual().enfriamiento(tablero,ac,direccion);
        }
        ac.consumeEnergiaNave(MotorWarp.CONSUMO_ENERGIA_SALTO_WARP);
        int x = tablero.getPosicionNave().getX() + direccion.getX();
        int y = tablero.getPosicionNave().getY() + direccion.getY();
        tablero.moverNave(new Posicion(x,y));
        motor.getEstadoActual().enfriamiento(tablero,ac,direccion);
    }
    
    @Override
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor no está disponible");
    }

    @Override
    public String toString(){
        return "PREPARANDO SALTO";
    }
     
}
