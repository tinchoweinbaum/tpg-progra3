package tripulantes;

public class Capitan extends Tripulante{
    private final static double SUELDO_BASE_CAPITAN = 1000;
    private final static double BONO_ANTIGUEDAD_CAPITAN = 0.2F;

    public Capitan(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_CAPITAN, BONO_ANTIGUEDAD_CAPITAN);
    }
    
    @Override 
    public double getSueldo(){
        return SUELDO_BASE_CAPITAN * (1 + BONO_ANTIGUEDAD_CAPITAN * this.antiguedad);
    }

    @Override
    public boolean esCapitan(){
        return true;
    }
}
