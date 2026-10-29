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

    @Override
    public void atacar(Nave nave) {
        nave.atacadoPorCarguero();
    }

    @Override
    public void atacadoPorCombate() {
        System.out.println("Carguero vence a Combate");
    }

    @Override
    public void atacadoPorCarguero() {
        System.out.println("PACTO DE PAZ");
    }

    @Override
    public void atacadoPorExplorador() {
        System.out.println("Explorador vence a Carguero");
    }
}