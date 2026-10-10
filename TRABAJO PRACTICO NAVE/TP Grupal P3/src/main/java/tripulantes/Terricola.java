package tripulantes;

public class Terricola extends EspecieDecorator{
    private static final double BONO_TERRICOLA = 20;

    public Terricola(Tripulante tripulante){
        super(tripulante);
    }

    /**
     * Metodo sobreescrito que agrega el subsidio al tripulante
     * 
     * @return sueldo original mas subsidio
     */
    
    @Override
    public double getSueldo(){
        return BONO_TERRICOLA + this.getTripulante().getSueldo();
    }

    @Override
    public String toString(){
        return this.getTripulante().toString() + "\nEs de origen Terricola";
    }
}
