package tripulantes;

public class Teniente extends DecoradorCargo{

    public Teniente(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public void setSueldo(){
        this.sueldo = (getTripulante().getSueldo()+ 400)*antiguedad*0.03; 
    }
}
