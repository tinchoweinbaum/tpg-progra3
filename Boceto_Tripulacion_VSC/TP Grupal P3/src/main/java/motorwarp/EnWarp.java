package motorwarp;
import java.util.Date;

public class EnWarp implements State {
    
    private MotorWarp motor;
    private Date fecha = new Date();
    private final int ID = 2;

    public int getID(){
        return ID;
    }
    
    
    
    public EnWarp (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible(){
        motor.registrarEvento(fecha,"El motor ya esta disponible",this);
        motor.setEstado(new Disponible(motor));
    }
    
    @Override
    public void preparaSalto(){        
        motor.registrarEvento(fecha,"ERROR: El motor ya esta preparando el salto warp",this);
        throw new IllegalStateException("El motor ya esta preparando el salto warp");        
    }
    
    @Override
    public void saltoWarp(){
        motor.registrarEvento(fecha,"ERROR : Ya se ha realizado el salto warp",this);
        throw new IllegalStateException("Ya se ha realizado el salto warp");
    }
    
    //De salto a enfriamiento para la 2da parte
    @Override
    public void enfriamiento(){}
       /* motor.registrarEvento(fecha,"Enfriando motor",this);
        motor.setEstado(new Enfriamiento(motor));
    }
    */
     
}

    
