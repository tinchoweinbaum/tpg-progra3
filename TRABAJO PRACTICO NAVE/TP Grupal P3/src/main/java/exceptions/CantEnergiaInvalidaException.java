package exceptions;

/**
 * Excepcion para cuando se quiere cargar más energía que la máxima capacidad, o para cuando se intenta consumir por debajo de 0 unidades de energía.
 */
public class CantEnergiaInvalidaException extends ActualizacionesErroneas {
    public CantEnergiaInvalidaException(String message) {
        super(message);
    }
}
