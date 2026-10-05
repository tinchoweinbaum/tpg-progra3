package motorwarp;
import exceptions.EstadoInvalidoException;


public class PreparandoSalto implements State {
    
    private MotorWarp motor;
    private final int ID = MotorWarp.PREPARANDO_SALTO;

    public int getIdEstado(){
        return ID;
    }
    
    
    
    public PreparandoSalto (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible() throws EstadoInvalidoException {
        throw new EstadoInvalidoException("El motor ya esta usandose");
    }
    
    @Override
    public void preparaSalto() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya esta preparando el salto warp");
    }
    
    @Override
    public void saltoWarp(){
        motor.setEstado(new EnWarp(motor));
    }
    
    @Override
    public void enfriamiento() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor no está disponible");
    }

    //Despues lo imprime cuando se printean los registros de motor
    @Override
    public String toString(){
        return "PREPARANDO SALTO";
    }
     
}
