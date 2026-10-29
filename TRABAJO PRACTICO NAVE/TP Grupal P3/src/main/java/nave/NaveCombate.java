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
        System.out.println("PACTO DE PAZ");
    }

    @Override
    public RegistroCombate atacadoPorCarguero() {
        System.out.println("Carguero vence a Combate");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() {
        System.out.println("Combate vence a Explorador");
    }
}