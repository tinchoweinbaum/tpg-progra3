package motorwarp;
import exceptions.EstadoInvalidoException;

import java.util.Date;


public class Disponible implements State {

    private MotorWarp motor;
    private Date fecha = new Date();
    private final int ID = MotorWarp.DISPONIBLE;

    public int getIdEstado(){
        return ID;
    }
    public Disponible (MotorWarp motor){
        this.motor = motor;
    }

    @Override
    public void disponible() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya esta disponible");
    }

    @Override
    public void preparaSalto(){
        motor.setEstado(new PreparandoSalto(motor));
    }


    @Override
    public void saltoWarp() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor no preparo el salto warp");
    }

    @Override
    public void enfriamiento() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor todavia no se uso");
    }

    //Despues lo imprime cuando se printean los registros de motor
    @Override
    public String toString(){
        return "DISPONIBLE";
    }
}
