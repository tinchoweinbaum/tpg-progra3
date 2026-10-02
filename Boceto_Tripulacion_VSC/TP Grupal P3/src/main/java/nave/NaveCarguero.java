package nave;

public class NaveCarguero extends Nave{

    private final double cargaMAX;
    private double cargaActual = 0;

    public NaveCarguero(double carga){
        super();
        this.cargaMAX = carga;
        this.setCombustible(100);
        this.setEnergia(60);
    }

    public double getCargaMAX() {
        return cargaMAX;
    }

    public double getCargaActual() {
        return cargaActual;
    }

    public void setCargaActual(double cargaActual) {
        this.cargaActual = cargaActual;
    }
}