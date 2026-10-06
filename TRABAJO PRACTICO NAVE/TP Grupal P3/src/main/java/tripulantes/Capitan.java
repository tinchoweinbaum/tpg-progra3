package tripulantes;

public class Capitan extends DecoradorCargo{

    public Capitan(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public double getSueldo(){
        return  (getTripulante().getSueldo()+ 1000)*antiguedad*1.2;
    }
}
