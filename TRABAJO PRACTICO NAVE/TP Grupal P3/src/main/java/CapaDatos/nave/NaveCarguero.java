package CapaDatos.nave;

import CapaDatos.bitacora.RegistroCombate;
import CapaDatos.exceptions.NaveDestruidaException;

/**
 * Subclase concreta de Nave especializada en transporte: inicia con máximo combustible (100) y energía moderada (60).
 * Vence/repele a naves de combate, pacta paz con cargueros y es vulnerable ante exploradoras.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Nave.</p>
 */
public class NaveCarguero extends Nave{

    /**
     * Constructor de NaveCarguero que inicializa sus recursos base.
     *
     * @pre true
     * @post getCombustible() == 100f && getEnergia() == 60f && getDesgaste() == 0f
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