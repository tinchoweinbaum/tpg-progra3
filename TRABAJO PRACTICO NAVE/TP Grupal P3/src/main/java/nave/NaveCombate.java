package nave;

import bitacora.RegistroCombate;
import exceptions.NaveDestruidaException;

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
    public String toString(){
        return "Nave de Combate";
    }

    @Override
    public RegistroCombate atacar(Nave nave) throws NaveDestruidaException {
        return nave.atacadoPorCombate();
    }

    @Override
    public RegistroCombate atacadoPorCombate() {
        return new RegistroCombate("TE HAS CRUZADO CONTRA UNA NAVE DE COMBATE - PACTO DE PAZ");
    }

    @Override
    public RegistroCombate atacadoPorCarguero() throws NaveDestruidaException {
        throw new NaveDestruidaException("TU NAVE FUE DESTRUÍDA EN COMBATE FRENTE A UNA NAVE CARGUERA.");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() {
        return new RegistroCombate("UNA NAVE EXPLORADORA INTENTO ASEDIARTE - NO PUDO CONTRA TU NAVE Y REPELIERON EL ATAQUE");
    }
}