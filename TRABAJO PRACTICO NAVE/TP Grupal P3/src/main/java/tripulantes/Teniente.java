package tripulantes;

public class Teniente extends Tripulante{
    private final static float SUELDO_BASE_TENIENTE = 400;
    private final static float BONO_ANITGUEDAD_TENIENTE = 0.03F;

    public Teniente(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_TENIENTE, BONO_ANITGUEDAD_TENIENTE);
    }
    
    @Override 
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad());
    }
}
