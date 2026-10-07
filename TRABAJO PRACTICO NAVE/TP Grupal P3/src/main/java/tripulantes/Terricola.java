package tripulantes;

public class Terricola extends EspecieDecorator{
    private static final double BONO_TERRICOLA = 20;

    public Terricola(Tripulante tripulante){
        super(tripulante);
    }

    @Override
    public double getSueldo(){
        return BONO_TERRICOLA + this.getTripulante().getSueldo();
    }

    @Override
    public String getOrigen(){
        return "Terricola";
    }
}
