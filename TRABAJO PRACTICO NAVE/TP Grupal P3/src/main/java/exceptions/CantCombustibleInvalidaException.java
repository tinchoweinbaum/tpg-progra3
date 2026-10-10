package exceptions;

/**
 * Excepcion cuando la intencion de cargar combustible supera las 100 unidades
 */
public class CantCombustibleInvalidaException extends ActualizacionesErroneas {
    public CantCombustibleInvalidaException(String message) {
        super(message);
    }
}
