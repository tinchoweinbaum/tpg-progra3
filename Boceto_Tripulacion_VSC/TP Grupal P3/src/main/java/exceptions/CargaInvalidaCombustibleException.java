package exceptions;

/**
 * Excepcion cuando la intencion de cargar combustible supera las 100 unidades
 */
public class CargaInvalidaCombustibleException extends ActualizacionesErroneas {
    public CargaInvalidaCombustibleException(String message) {
        super(message);
    }
}
