package tripulantes;

public class Consejero extends DecoradorCargo{

    private int consejosDados = 0;

    public Consejero(Tripulante tripulante,int consejos){
        super(tripulante);
        this.consejosDados = consejos;
    }

    public Consejero(Tripulante tripulante){
        super(tripulante);
    }
    
    @Override 
    public void setSueldo(){
        this.sueldo = (getTripulante().getSueldo()+ 600)*antiguedad*0.05 + this.consejosDados*2; 
    }
}
