package tripulantes;

public class Teniente extends Tripulante{
    private final static double SUELDO_BASE_TENIENTE = 400;
    private final static double BONO_ANITGUEDAD_TENIENTE = 0.03;

    public Teniente(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_TENIENTE, BONO_ANITGUEDAD_TENIENTE);
    }
    
    /**
     *<b>pre:</b> antiguedad tiene que ser un entero positivo
    * @return Devuelve el sueldo de un teniente en base a su antiguedad 
    */
    
    @Override 
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad());
    }
}
