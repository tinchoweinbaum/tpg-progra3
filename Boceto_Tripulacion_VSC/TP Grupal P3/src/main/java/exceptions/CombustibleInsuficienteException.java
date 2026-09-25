package exceptions;

import misiones.Mision;

/**
 * Exception que se arroja cuando no se logra aceptar una misión porque no alcanza el combustible de la nave.
 */
public class CombustibleInsuficienteException extends MisionImposibleException {
    public CombustibleInsuficienteException(String message) {
        super(message);
    }
}
