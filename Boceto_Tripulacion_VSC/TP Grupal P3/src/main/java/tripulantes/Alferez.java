package tripulantes;

public class Alferez extends DecoradorCargo{

    public Alferez(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public void setSueldo(){
        this.sueldo = (getTripulante().getSueldo()+ 200)*antiguedad*0.005; 
    }
}
