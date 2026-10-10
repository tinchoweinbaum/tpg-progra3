package nave;

public class NaveExploradora extends Nave{

    /**
     * Contructor de la nave explorador
     * <b>Post:</b> Combustible = 60 y energia == 80
     */
    public NaveExploradora(){
        super();
        this.setCombustible(60f);
        this.setEnergia(80f);
    }
}