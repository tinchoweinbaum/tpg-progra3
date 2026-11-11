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
    public void preparaSalto(int tipoSalto) throws EstadoInvalidoException{
        if (tipoSalto == 4) // SALTO_CORTO = 4;
            motor.setEstado(new PreparandoSalto(motor)); // Me quedo en preparandoSalto
        else
            if (tipoSalto == 2) // SALTO_WARP = 2;
                this.saltoWarp(); // Paso al estado saltoWarp
            else
                throw new EstadoInvalidoException("El motor ya esta preparando el salto");
    }

    @Override
    public void saltoWarp(){
        motor.setEstado(new SaltoWarp(motor));
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
