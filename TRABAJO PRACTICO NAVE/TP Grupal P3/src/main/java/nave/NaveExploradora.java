package nave;

import bitacora.RegistroCombate;

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
    public int atacar(Nave nave) {
        int resultado = 0;
        switch (nave.atacadoPorExplorador()){
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
        return Nave.COMBATE_DERROTA;
    }

    @Override
    public int atacadoPorCarguero() {
        return Nave.COMBATE_VICTORIA;
    }

    @Override
    public int atacadoPorExplorador() {
        return Nave.COMBATE_PAZ;
    }

//    @Override
//    public RegistroCombate atacar(Nave nave) {
//        nave.atacadoPorExplorador();
//        return new RegistroCombate("Atacando nave " + nave);
//    }
//
//    @Override
//    public RegistroCombate atacadoPorCombate() {
//        return new RegistroCombate("Nave destruida por nave de combate :(");
//    }
//
//    @Override
//    public RegistroCombate atacadoPorCarguero() {
//        return new RegistroCombate("Nave carguera destruida.");
//    }
//
//    @Override
//    public RegistroCombate atacadoPorExplorador() {
//        return new RegistroCombate("Pacto de paz entre naves exploradoras.");
//    }
}