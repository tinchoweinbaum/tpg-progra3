package motorwarp;
import asistentes.Asistente;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import exceptions.EstadoInvalidoException;


public class Enfriamiento implements State {
    
    private MotorWarp motor;

    public Enfriamiento (MotorWarp motor){
        this.motor = motor;
    }
    
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

