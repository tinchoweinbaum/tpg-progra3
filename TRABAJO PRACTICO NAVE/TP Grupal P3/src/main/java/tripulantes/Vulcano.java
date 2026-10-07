package tripulantes;

public class Vulcano extends EspecieDecorator {
    private static final float BONO_VULCANO = 30;

    public Vulcano(Tripulante tripulante) {
        super(tripulante);
    }

    @Override
    public double getSueldo(){
        return BONO_VULCANO + this.getTripulante().getSueldo();
    }

    @Override
    public String getOrigen(){
        return "Vulcano";
    }
}
