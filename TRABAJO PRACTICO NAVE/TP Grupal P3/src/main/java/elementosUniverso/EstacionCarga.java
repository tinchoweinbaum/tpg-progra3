package elementosUniverso;

import asistentes.Asistente;
import bitacora.RegistroBitacora;
import exceptions.*;
import nave.Nave;

public class EstacionCarga extends ElementoDelUniverso {
    private final static int RECARGA_COMBUSTIBLE = (int) (0.2 * Nave.MAX_COMBUSTIBLE);
    private final static int RECARGA_ENERGIA = (int) (0.2 * Nave.MAX_ENERGIA);

    public EstacionCarga() {
        super();
    }

    /**
     * La estación de carga recarga energía y combustible de la nave en un 20% del máximo.
     */
    @Override
    public void interactuaConNave(Asistente ac) throws CantCombustibleInvalidaException, CantEnergiaInvalidaException {
        ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("LLEGADA A ESTACION DE CARGA - INTENTANDO REABASTECER COMBUSTIBLE Y ENERGÍA"));

        if (ac.getNave().getCombustible()!=Nave.MAX_COMBUSTIBLE)
            if(ac.getNave().getCombustible() + RECARGA_COMBUSTIBLE <= Nave.MAX_COMBUSTIBLE)
                ac.cargaCombustibleNave(RECARGA_COMBUSTIBLE);
            else
                ac.cargaCombustibleNaveFull();

        if (ac.getNave().getEnergia()!=Nave.MAX_ENERGIA)
            if(ac.getNave().getEnergia() + RECARGA_ENERGIA <= Nave.MAX_ENERGIA)
                ac.cargaEnergiaNave(RECARGA_ENERGIA);
            else
                ac.cargaEnergiaNaveFull();
    }
}
