package elementosUniverso;

import asistentes.Asistente;
import bitacora.RegistroBitacora;
import exceptions.CantEnergiaInvalidaException;
import exceptions.EnergiaInsuficienteException;

import java.util.Random;

public class Asteroide extends ElementoDelUniverso{
    private static final int ENERGIA_DESCONTADA_COLISION = 10; // Los asteroides consumen un monto fijo de energía al chocar con la nave.

    public Asteroide(String nombre) {
        super(nombre);
    }

    /**
     * El asteroide consume 10 de energía de la nave por chocarlo.
     */
    @Override
    public void interactuaConNave(Asistente ac) throws CantEnergiaInvalidaException {
        ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("IMPACTO CON ASTEROIDE"));

        if (ac.getNave().getEnergia() - ENERGIA_DESCONTADA_COLISION <= 0)
            throw new CantEnergiaInvalidaException("SE AGOTO LA ENERGIA DE LA NAVE");
        ac.consumeEnergiaNave(ENERGIA_DESCONTADA_COLISION);
    }
}
