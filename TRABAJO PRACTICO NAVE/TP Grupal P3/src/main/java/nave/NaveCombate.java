package nave;

import bitacora.RegistroCombate;

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
    public RegistroCombate atacar(Nave nave) {
        nave.atacadoPorCombate();
        return new RegistroCombate("Atacando nave" + nave); // Usa el toString de Nave
    }

    @Override
    public RegistroCombate atacadoPorCombate() {
        return new RegistroCombate("Pacto de paz entre naves de combate");
    }

    @Override
    public RegistroCombate atacadoPorCarguero() {
        return new RegistroCombate("Carguero vence a combate");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() {
        return new RegistroCombate("Combate vence a Explorador");
    }
}