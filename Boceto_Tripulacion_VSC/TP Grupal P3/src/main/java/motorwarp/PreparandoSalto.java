package motorwarp;
import java.util.Date;

public class PreparandoSalto implements State {
    
    private MotorWarp motor;
    private Date fecha = new Date();
    private final int ID = MotorWarp.PREPARANDO_SALTO;

    public int getID(){
        return ID;
    }
    
    
    
    public PreparandoSalto (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible(){
        motor.registrarEvento(fecha,"ERROR: El motor ya esta disponible",this);
        throw new IllegalStateException("El motor ya esta disponible");
    }
    
    @Override
    public void preparaSalto(){        
        motor.registrarEvento(fecha,"ERROR: El motor ya esta preparando el salto warp",this);
        throw new IllegalStateException("El motor ya esta preparando el salto warp");        
    }
    
    @Override
    public void saltoWarp(){
        motor.registrarEvento(fecha,"Realizando salto warp",this);
        motor.setEstado(new EnWarp(motor));
    }
    
    @Override
    public void enfriamiento(){
        motor.registrarEvento(fecha,"ERROR: No se puede entrar en enfriamiento",this);
        throw new IllegalStateException("El motor no está disponible");
    }
     
}
