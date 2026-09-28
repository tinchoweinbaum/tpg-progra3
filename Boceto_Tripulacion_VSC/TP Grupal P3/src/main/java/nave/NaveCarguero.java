package nave;

public class NaveCarguero extends Nave{

    private final double cargaMAX;

    public NaveCarguero(Asistente ac,Warp motor,double carga){
        super(ac,motor);
        this.cargaMAX = carga;
    }

    public double getCargaMAX() {
        return cargaMAX;
    }
}