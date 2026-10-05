package exceptions;


/**
 * Excepcion cuando no puede realizarse alguna mision por falta de combustible
 */
public class CombustibleInsuficienteException extends MisionImposibleException {
    public CombustibleInsuficienteException(String message) {
        super(message);
    }
}
