package tripulantes;

public class Capitan extends DecoradorCargo{

    public Capitan(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public void setSueldo(){
        this.sueldo = (getTripulante().getSueldo()+ 1000)*antiguedad*0.2; 
    }
}
