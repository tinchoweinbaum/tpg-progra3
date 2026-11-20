package CapaDatos.nave;

import CapaDatos.bitacora.RegistroCombate;
import CapaDatos.exceptions.NaveDestruidaException;

/**
 * Subclase concreta de Nave especializada en ofensiva: inicia con combustible moderado (80) y máxima energía (100).
 * Repele a naves exploradoras, pacta paz con naves de combate y es vulnerable ante cargueros.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Nave.</p>
 */
public class NaveCombate extends Nave{

    /**
     * Constructor de NaveCombate que inicializa sus recursos base.
     *
     * @pre true
     * @post getCombustible() == 80f && getEnergia() == 100f && getDesgaste() == 0f
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
        throw new NaveDestruidaException("LA NAVE NO PUDO CONTENER EL ATAQUE DE LA NAVE CARGUERA");
    }

    @Override
    public RegistroCombate atacadoPorExplorador() {
        return new RegistroCombate("UNA NAVE EXPLORADORA INTENTO ASEDIARTE - NO PUDO CONTRA TU NAVE Y REPELIERON EL ATAQUE");
    }
}