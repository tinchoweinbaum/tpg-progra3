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

    @Override
    public void atacar(Nave nave) {
        nave.atacadoPorExplorador();
    }

    @Override
    public void atacadoPorCombate() {
        System.out.println("Combate vence a Explorador");
    }

    @Override
    public void atacadoPorCarguero() {
        System.out.println("Explorador vence a Combate");
    }

    @Override
    public void atacadoPorExplorador() {
        System.out.println("PACTO DE PAZ");
    }
}