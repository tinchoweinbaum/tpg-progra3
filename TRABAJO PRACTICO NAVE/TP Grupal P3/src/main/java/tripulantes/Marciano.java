package tripulantes;

public class Marciano extends EspecieDecorator{
    private static final double BONO_MARCIANO = 18;
    
    public Marciano(Tripulante tripulante){
        super(tripulante);
    }
    
    
    /**
     * Metodo sobreescrito que agrega el subsidio al tripulante
     * 
     * @return sueldo original mas subsidio
     */
    @Override
    public double getSueldo(){
        return BONO_MARCIANO + this.getTripulante().getSueldo();
    }

    @Override
    public String toString(){
        return this.getTripulante().toString() + "\nEs de origen Marciano";
    }
}
