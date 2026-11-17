package motorwarp;
import asistentes.Asistente;
import destinoytablero.*;
import exceptions.*;


public class PreparandoSalto implements State {
    
    private final MotorWarp motor;

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

    @Override
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException {
        motor.setEstado(new SaltoWarp(motor));
        motor.notificarObservadores("MOTOR_SALTANDO");
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
