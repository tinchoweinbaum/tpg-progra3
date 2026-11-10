package motorwarp;
import exceptions.EstadoInvalidoException;


public class PreparandoSalto implements State {
    
    private final MotorWarp motor;
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
    public void saltoCorto() {
        throw new EstadoInvalidoException("El motor ya esta preparando el salto corto");
    }

    @Override
    public void saltoWarp(){
        if (motor.getEnergia() >= 5 ) {
            motor.setEstado(new SaltoWarp(motor));
        }
        else
            motor.setEstado(new PreparandoSalto(motor)); // sin recursos = me quedo en preparandoSalto

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
