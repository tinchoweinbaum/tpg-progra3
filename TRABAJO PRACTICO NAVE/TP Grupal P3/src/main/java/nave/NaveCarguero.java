package nave;

public class NaveCarguero extends Nave{

    /**
     * Contructor de la nave carguero<br>
     * <b>Post:</b> Combustible = 100 y energia == 60
     */
    public NaveCarguero(){
        super();
        this.setCombustible(100f);
        this.setEnergia(60f);
    }

}