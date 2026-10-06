package tripulantes;

public class Alferez extends DecoradorCargo{

    public Alferez(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public double getSueldo(){
        return  (getTripulante().getSueldo()+ 200)*antiguedad*1.005;
    }
}
