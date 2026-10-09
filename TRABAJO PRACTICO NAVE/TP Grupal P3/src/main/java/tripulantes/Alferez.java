package tripulantes;

public class Alferez extends Tripulante{
    private static final double SUELDO_BASE_ALFEREZ = 200;
    private static final double BONO_ANTIGUEDAD_ALFEREZ = 0.005;
       
    public Alferez(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_ALFEREZ, BONO_ANTIGUEDAD_ALFEREZ);
    }

    /**
    * <b>pre:</b> antiguedad tiene que ser un entero positivo
    * @return Devuelve el sueldo de un alferez en base a su antiguedad 
    */
    
    @Override
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad());
    }
}
