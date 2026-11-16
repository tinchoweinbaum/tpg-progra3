package nave;

import bitacora.RegistroCombate;

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
    public int atacar(Nave nave) {
        int resultado = 0;
        switch (nave.atacadoPorCarguero()){
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
        return Nave.COMBATE_VICTORIA;
    }

    @Override
    public int atacadoPorCarguero() {
        return Nave.COMBATE_PAZ;
    }

    @Override
    public int atacadoPorExplorador() {
        return Nave.COMBATE_DERROTA;
    }

//    @Override
//    public RegistroCombate atacar(Nave nave) {
//        nave.atacadoPorCarguero();
//        return new RegistroCombate("Atacando nave " + nave);
//    }
//
//    @Override
//    public RegistroCombate atacadoPorCombate() {
//        return new RegistroCombate("Nave de Combate destruida");
//    }
//
//    @Override
//    public RegistroCombate atacadoPorCarguero() {
//        return new RegistroCombate("Pacto de Paz entre naves cargueras.");
//    }
//
//    @Override
//    public RegistroCombate atacadoPorExplorador() {
//        return new RegistroCombate("Nave Destruida por nave exploradora."); // finalize?? no devolvemos??' void?? que asemos?!?!??!?!
//    }

}