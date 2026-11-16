package motorwarp;
import exceptions.EstadoInvalidoException;


public class SaltoWarp implements State {
    
    private MotorWarp motor;
    private final int ID = MotorWarp.SALTO_WARP;

    public int getIdEstado(){
        return ID;
    }
    
    
    
    public SaltoWarp(MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible() throws EstadoInvalidoException {
        throw new EstadoInvalidoException("El motor ya esta usandose");
    }
    
    @Override
    public void preparaSalto(int tipoSalto) throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya preparo el salto warp");
    }

    @Override
    public void saltoWarp() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("Ya se ha realizado el salto warp");
    }
    
    //De salto a enfriamiento para la 2da parte
    @Override
    public void enfriamiento(){
        motor.setEstado(new Enfriamiento(motor));
        //da turno a tablero, se mueven fichas || thread sleep
        //pasa a estado dispnbe
    }

    //Despues lo imprime cuando se printean los registros de motor
    @Override
    public String toString(){
        return "EN SALTO WARP";
    }
     
}

    
