package CapaDatos.elementosUniverso;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.RegistroBitacora;
import CapaDatos.exceptions.*;

/**
 * Elemento espacial hostil que provoca una pérdida fija de energía al colisionar con la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> ENERGIA_DESCONTADA_COLISION == 10</p>
 */
public class Asteroide extends ElementoDelUniverso{
    private static final int ENERGIA_DESCONTADA_COLISION = 10;

    /**
     * Constructor por defecto de un Asteroide.
     *
     * @pre true
     * @post Se inicializa una instancia de Asteroide.
     */
    public Asteroide(){
        super();
    }

    /**
     * Registra el impacto en la bitácora y descuenta 10 unidades de energía de la CapaDatos.nave.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave afectada.
     * @throws CantEnergiaInvalidaException Si el monto a descontar resulta inválido.
     * @throws NaveDestruidaException Si la energía restante de la CapaDatos.nave cae a cero o menos tras el impacto.
     * @pre ac != null && ac.getBitacorasNave() != null && ac.getNave() != null
     * @post Se agrega un registro "IMPACTO CON ASTEROIDE" en la bitácora y la energía de la CapaDatos.nave disminuye en 10 unidades.
     */
    @Override
    public void interactuaConNave(Asistente ac) throws CantEnergiaInvalidaException, NaveDestruidaException {
        ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("IMPACTO CON ASTEROIDE"));
        ac.consumeEnergiaNave(ENERGIA_DESCONTADA_COLISION);
    }
}
