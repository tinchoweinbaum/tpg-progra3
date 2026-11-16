package nave;

import bitacora.RegistroCombate;
import exceptions.NaveDestruidaException;

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
    public String toString(){
        return "Nave Exploradora";
    }


    @Override
    public RegistroCombate atacar(Nave nave)throws NaveDestruidaException {
        return nave.atacadoPorExplorador();
    }

    @Override
    public RegistroCombate atacadoPorCombate() throws NaveDestruidaException {
        throw new NaveDestruidaException("Encuentro con nave de Combate");
    }

    @Override
    public RegistroCombate atacadoPorCarguero() {
        return new RegistroCombate("UNA NAVE CARGUERA INTENTO ASEDIARTE - NO PUDO CONTRA TU NAVE Y REPELIERON EL ATAQUE");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() {
        return new RegistroCombate("TE HAS CRUZADO CONTRA UNA NAVE EXPLORADORA - PACTO DE PAZ");
    }
}