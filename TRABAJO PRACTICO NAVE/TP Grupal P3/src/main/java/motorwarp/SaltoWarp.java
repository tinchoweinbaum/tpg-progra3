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
    public void preparaSalto() throws EstadoInvalidoException{
        throw new EstadoInvalidoException("El motor ya preparo el salto warp");
    }

    @Override
    public void saltoCorto() {
        // Salto en el tablero
        motor.setEstado(new SaltoWarp(motor));
    }


    @Override
    public void saltoWarp() throws EstadoInvalidoException{
        if (motor.getEnergia() >= 5 ) {     // Vuelvo a verificar que siga con recursos
            motor.consumeEnergia(5);
            motor.setEstado(new SaltoWarp(motor));
        }
    }
    
    //De salto a enfriamiento para la 2da parte
    @Override
    public void enfriamiento(){
        if (motor.getDesgaste() + 3 <= motor.getDesgasteMaximo()){
            // Salto doble en el tablero
            motor.setEstado(new Enfriamiento(motor));
        }

    }


    //Despues lo imprime cuando se printean los registros de motor
    @Override
    public String toString(){
        return "EN SALTO WARP";
    }
     
}

    
