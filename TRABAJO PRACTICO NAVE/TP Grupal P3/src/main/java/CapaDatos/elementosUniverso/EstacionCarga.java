package CapaDatos.elementosUniverso;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.RegistroBitacora;
import CapaDatos.exceptions.*;
import CapaDatos.nave.Nave;

/**
 * Elemento del universo aliado que reabastece el combustible y la energía de la CapaDatos.nave en hasta un 20% de su capacidad máxima.
 * <p><b>Invariante de clase:</b> RECARGA_COMBUSTIBLE == (int) (0.2 * Nave.MAX_COMBUSTIBLE) && RECARGA_ENERGIA == (int) (0.2 * Nave.MAX_ENERGIA)</p>
 */
public class EstacionCarga extends ElementoDelUniverso {
    private final static int RECARGA_COMBUSTIBLE = (int) (0.2 * Nave.MAX_COMBUSTIBLE);
    private final static int RECARGA_ENERGIA = (int) (0.2 * Nave.MAX_ENERGIA);

    /**
     * Constructor por defecto de EstacionCarga.
     *
     * @pre true
     * @post Se inicializa una instancia de EstacionCarga.
     */
    public EstacionCarga() {
        super();
    }

    /**
     * Registra la llegada a la estación y recarga el combustible y la energía de la CapaDatos.nave en un 20% de su máximo (sin exceder el tope).
     *
     * @param ac Asistente encargado de gestionar las recargas y la bitácora de la CapaDatos.nave.
     * @throws CantCombustibleInvalidaException Si ocurre un error durante la carga de combustible.
     * @throws CantEnergiaInvalidaException Si ocurre un error durante la carga de energía.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Se registra la llegada en la bitácora; si el combustible y/o energía no estaban al máximo, se incrementan hasta en un 20% sin superar Nave.MAX_COMBUSTIBLE ni Nave.MAX_ENERGIA.
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
