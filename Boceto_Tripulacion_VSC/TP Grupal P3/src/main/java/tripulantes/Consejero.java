package tripulantes;

public class Consejero extends DecoradorCargo{

    private int consejosDados = 0;

    public Consejero(Tripulante tripulante){
        super(tripulante);
    }

    public int getConsejos(){
        return this.consejosDados;
    }

    public void dioConsejo(){
        this.consejosDados+=1;
    }
    
    @Override 
    public double getSueldo(){
        return  (getTripulante().getSueldo()+ 600)*antiguedad*1.05 + this.consejosDados*2;
    }
}
