package nave;

public class NaveCarguero extends Nave{

    /**
     * Contructor de la nave carguero
     * <b>Post:</b>
     * -Combustible == 100
     * -Energia == 60
     */
    public NaveCarguero(){
        super();
        this.setCombustible(100);
        this.setEnergia(60);
    }

}