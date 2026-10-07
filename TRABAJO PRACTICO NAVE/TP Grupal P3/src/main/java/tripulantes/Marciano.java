package tripulantes;

public class Marciano extends EspecieDecorator{
    private static final double BONO_MARCIANO = 18;
    
    public Marciano(Tripulante tripulante){
        super(tripulante);
    }

    @Override
    public double getSueldo(){
        return BONO_MARCIANO + this.getTripulante().getSueldo();
    }

    @Override
    public String getOrigen(){
        return "Marciano";
    }
}
