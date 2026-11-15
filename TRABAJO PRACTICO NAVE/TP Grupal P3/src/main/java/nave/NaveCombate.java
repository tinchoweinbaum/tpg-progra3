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
    public int atacar(Nave nave) {
        int resultado = 0;
        switch (nave.atacadoPorCombate()){
            case 1 -> {
                resultado = -1;
            }
            case -1 -> {
                resultado = 1;
            }
        }
        return resultado;
    }

    @Override
    public int atacadoPorCombate() {
        return 0;
    }

    @Override
    public int atacadoPorCarguero() {
        return -1;
    }

    @Override
    public int atacadoPorExplorador() {
        return 1;
    }

//    @Override
//    public RegistroCombate atacar(Nave nave) {
//        nave.atacadoPorCombate();
//        return new RegistroCombate("Atacando nave" + nave); // Usa el toString de Nave
//    }
//
//    @Override
//    public RegistroCombate atacadoPorCombate() {
//        return new RegistroCombate("Pacto de paz entre naves de combate");
//    }
//
//    @Override
//    public RegistroCombate atacadoPorCarguero() {
//        return new RegistroCombate("Carguero vence a combate");
//    }
//
//    @Override
//    public RegistroCombate atacadoPorExplorador() {
//        return new RegistroCombate("Combate vence a Explorador");
//    }
}