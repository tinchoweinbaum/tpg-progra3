package tripulantes;

public class Vulcano extends EspecieDecorator {
    private static final double BONO_VULCANO = 30;

    public Vulcano(Tripulante tripulante) {
        super(tripulante);
    }
    
    
    /**
     * Metodo sobreescrito que agrega el subsidio al tripulante
     * 
     * @return sueldo original mas subsidio
     */

    @Override
    public double getSueldo(){
        return BONO_VULCANO + this.getTripulante().getSueldo();
    }

    @Override
    public String getOrigen(){
        return "Vulcano";
    }
}
