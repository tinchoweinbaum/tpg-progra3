package nave;

public class NaveCombate extends Nave{

    /**
     * Contructor de la nave combate
     * <b>Post:</b>  Combustible = 80 y energia = 100
     */
    public NaveCombate(){
        super();
        this.setCombustible(80f);
        this.setEnergia(100f);
    }

    @Override
    public void atacar(Nave nave) {
        nave.atacadoPorCombate();
    }

    @Override
    public void atacadoPorCombate() {
        System.out.println("PACTO DE PAZ");
    }

    @Override
    public void atacadoPorCarguero() {
        System.out.println("Carguero vence a Combate");
    }

    @Override
    public void atacadoPorExplorador() {
        System.out.println("Combate vence a Explorador");
    }
}