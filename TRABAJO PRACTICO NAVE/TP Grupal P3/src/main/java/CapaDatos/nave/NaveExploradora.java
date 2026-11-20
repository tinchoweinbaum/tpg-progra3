package CapaDatos.nave;

import CapaDatos.bitacora.RegistroCombate;
import CapaDatos.exceptions.NaveDestruidaException;

/**
 * Subclase concreta de Nave especializada en reconocimiento: inicia con combustible reducido (60) y energía alta (80).
 * Repele a naves cargueras, pacta paz con exploradoras y es vulnerable ante naves de combate.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Nave.</p>
 */
public class NaveExploradora extends Nave{

    /**
     * Constructor de NaveExploradora que inicializa sus recursos base.
     *
     * @pre true
     * @post getCombustible() == 60f && getEnergia() == 80f && getDesgaste() == 0f
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
        throw new NaveDestruidaException("LA NAVE NO PUDO CONTENER EL ATAQUE DE LA NAVE DE COMBATE");
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