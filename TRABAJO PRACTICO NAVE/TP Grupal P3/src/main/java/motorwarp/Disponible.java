package motorwarp;
import asistentes.Asistente;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.EstadoInvalidoException;
import exceptions.NaveDestruidaException;

public class Disponible implements State {

    private final MotorWarp motor;

    public Disponible (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya esta disponible");
    }

    @Override
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException {
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
