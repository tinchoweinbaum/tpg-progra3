package tripulantes;

public class Capitan extends Tripulante{
    private static final double SUELDO_BASE_CAPITAN = 1000;
    private static final double BONO_ANTIGUEDAD_CAPITAN = 0.2;

    public Capitan(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_CAPITAN, BONO_ANTIGUEDAD_CAPITAN);
    }
    
    /** 
    * <b>pre:</b> antiguedad tiene que ser un entero positivo
    * @return Devuelve el sueldo de un capitan en base a su antiguedad 
    */
    
    @Override 
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad());
    }

    /**
    * Funcion booleana para determinar si un tripulante es capitan, 
    * se usa para saber si la tripulacion tiene al menos un capitan en la flota 
   * 
    * @return Devuelve true ya que el tripulante es un capitan  
    */
    @Override
    public boolean esCapitan(){
        return true;
    }

    @Override
    public String toString(){
        return super.toString() + " es Capitan";
    }
}
