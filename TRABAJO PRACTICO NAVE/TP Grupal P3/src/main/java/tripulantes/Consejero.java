package tripulantes;

public class Consejero extends Tripulante{
    private static final double SUELDO_BASE_CONSEJERO = 600;
    private static final double BONO_ANTIGUEDAD_CONSEJERO = 0.05;
    private static final double BONO_CONSEJO = 2;

    private int consejosDados = 0;

    public Consejero(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_CONSEJERO, BONO_ANTIGUEDAD_CONSEJERO);
    }

    public int getConsejos(){
        return this.consejosDados;
    }

    public void darConsejo(){
        this.consejosDados += 1;
    }
    
    /**
    * <b>pre:</b> antiguedad tiene que ser un entero positivo
    * @return Devuelve el sueldo de un consejero en base a su antiguedad 
    */
    
    @Override 
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad()) + this.consejosDados * BONO_CONSEJO;
    }

    @Override
    public String toString(){
        return super.toString() + " es Consejero";
    }

    /**
     * Funcion booleana para determinar si un tripulante es capitan,
     * se usa para saber si la tripulacion tiene al menos un capitan en la flota
     *
     * @return Devuelve false ya que el tripulante no es un capitan
     */
    @Override
    public boolean esCapitan(){
        return false;
    }
}
