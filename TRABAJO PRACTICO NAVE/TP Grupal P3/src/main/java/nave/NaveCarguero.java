package nave;

import bitacora.RegistroCombate;
import exceptions.NaveDestruidaException;

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
    public String toString(){
        return "Nave Carguera";
    }


    @Override
    public RegistroCombate atacar(Nave nave) throws NaveDestruidaException {
        return nave.atacadoPorCarguero();
    }

    @Override
    public RegistroCombate atacadoPorCombate() {
        return new RegistroCombate("UNA NAVE DE COMBATE INTENTO ASEDIARTE - MILAGROSAMENTE NO PUDO CONTRA TU NAVE Y REPELIERON EL ATAQUE");
    }

    @Override
    public RegistroCombate atacadoPorCarguero() {
        return new RegistroCombate("TE HAS CRUZADO CONTRA UNA NAVE CARGUERA - PACTO DE PAZ");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() throws NaveDestruidaException {
        throw new NaveDestruidaException("LA NAVE NO PUDO CONTENER EL ATAQUE DE LA NAVE EXPLORADORA");
    }

}