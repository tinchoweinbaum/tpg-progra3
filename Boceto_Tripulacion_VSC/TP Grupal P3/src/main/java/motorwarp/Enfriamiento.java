package motorwarp;
import java.util.Date;

public class Enfriamiento implements State {
    
    private MotorWarp motor;
    private Date fecha = new Date();
    
    
    
    public Enfriamiento (MotorWarp motor){
        this.motor = motor;
    }
    
    @Override
    public void disponible(){
        motor.registrarEvento(fecha,"El motor ya esta disponible",this);
        motor.setEstado(new Disponible(motor));
    }
    
    @Override
    public void preparaSalto(){        
        motor.registrarEvento(fecha,"ERROR: El motor ya preparo el salto warp",this);
        throw new IllegalStateException("El motor ya preparo el salto warp");        
    }
    
    @Override
    public void saltoWarp(){
        motor.registrarEvento(fecha,"ERROR : Ya se ha realizado el salto warp",this);
        throw new IllegalStateException("Ya se ha realizado el salto warp");
    }
    
    @Override
    public void enfriamiento(){
        motor.registrarEvento(fecha,"ERROR : Ya se ha enfriado el motor",this);
        throw new IllegalStateException("Ya se ha enfriado el motor");
    }
     
}

