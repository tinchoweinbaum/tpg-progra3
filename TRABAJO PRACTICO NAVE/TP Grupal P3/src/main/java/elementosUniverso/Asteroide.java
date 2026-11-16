package elementosUniverso;

import asistentes.Asistente;
import bitacora.RegistroBitacora;
import exceptions.*;

public class Asteroide extends ElementoDelUniverso{
    private static final int ENERGIA_DESCONTADA_COLISION = 10; // Los asteroides consumen un monto fijo de energía al chocar con la nave.

    public Asteroide(){
        super();
    }

    /**
     * El asteroide consume 10 de energía de la nave por chocarlo.
     */
    @Override
    public void interactuaConNave(Asistente ac) throws CantEnergiaInvalidaException, NaveDestruidaException {
        ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("IMPACTO CON ASTEROIDE"));
        ac.consumeEnergiaNave(ENERGIA_DESCONTADA_COLISION);
    }
}
