package motorwarp;
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
    public void disponible(){
        //motor.registrarEvento(fecha,"ERROR: El motor ya esta disponible", this);
        throw new IllegalStateException("El motor ya esta disponible");
    }

    @Override
    public void preparaSalto(){
        motor.registrarEvento(fecha,"Preparando salto warp",this);
        motor.setEstado(new PreparandoSalto(motor));
    }


    @Override
    public void saltoWarp(){
        //motor.registrarEvento(fecha,"ERROR: No se puede realizar salto warp, el motor no preparo el salto warp",this);
        throw new IllegalStateException("El motor no preparo el salto warp");
    }

    @Override
    public void enfriamiento(){
        //motor.registrarEvento(fecha,"ERROR: No se puede entrar en enfriamiento, el motor no esta disponible",this);
        throw new IllegalStateException("El motor no está disponible");
    }


}
