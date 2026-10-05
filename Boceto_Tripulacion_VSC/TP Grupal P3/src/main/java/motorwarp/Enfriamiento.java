package motorwarp;
import exceptions.EstadoInvalidoException;

import java.util.Date;

public class Enfriamiento implements State {
    
    private MotorWarp motor;
    private Date fecha = new Date();
    private final int ID = MotorWarp.ENFRIAMIENTO;

    public int getIdEstado(){
        return ID;
    }

    public Enfriamiento (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible(){
        motor.setEstado(new Disponible(motor));
    }
    
    @Override
    public void preparaSalto() throws EstadoInvalidoException {
        throw new EstadoInvalidoException("El motor ya preparo el salto warp");
    }
    
    @Override
    public void saltoWarp() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("Ya se ha realizado el salto warp");
    }
    
    @Override
    public void enfriamiento() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("Ya se ha enfriado el motor");
    }

    //Despues lo imprime cuando se printean los registros de motor
    @Override
    public String toString(){
        return "ENFRIAMIENTO";
    }
}

