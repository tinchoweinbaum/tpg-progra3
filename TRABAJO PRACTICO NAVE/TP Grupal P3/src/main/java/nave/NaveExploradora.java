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
    public RegistroCombate atacar(Nave nave) {
        nave.atacadoPorExplorador();
        return new RegistroCombate("Atacando nave " + nave);
    }

    @Override
    public RegistroCombate atacadoPorCombate() {
        return new RegistroCombate("Nave destruida por nave de combate :(");
    }

    @Override
    public RegistroCombate atacadoPorCarguero() {
        return new RegistroCombate("Nave carguera destruida.");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() {
        return new RegistroCombate("Pacto de paz entre naves exploradoras.");
    }
}