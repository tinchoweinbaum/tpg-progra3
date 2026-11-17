package motorwarp;
import asistentes.Asistente;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.EstadoInvalidoException;
import exceptions.NaveDestruidaException;


public class SaltoWarp implements State {
    
    private MotorWarp motor;
    
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
    
    @Override
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException {
        motor.setEstado(new Enfriamiento(motor));
        motor.notificarObservadores("MOTOR_ENFRIANDO");
        ac.darTurnosTablero(1);
        motor.getEstadoActual().disponible();
    }

    @Override
    public String toString(){
        return "EN SALTO WARP";
    }
     
}

    
