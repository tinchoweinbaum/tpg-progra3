package tripulantes;

public class Teniente extends DecoradorCargo{

    public Teniente(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public double getSueldo(){
        return (getTripulante().getSueldo()+ 400)*antiguedad*1.03;
    }
}
