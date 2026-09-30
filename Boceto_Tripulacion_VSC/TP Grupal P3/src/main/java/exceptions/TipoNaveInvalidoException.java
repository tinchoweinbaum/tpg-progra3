package exceptions;


/**
 * Exception que se arroja cuando no se logra aceptar una misión porque no alcanza el combustible de la nave.
 */
public class TipoNaveInvalidoException extends NaveException {
    public TipoNaveInvalidoException(String message) {
        super(message);
    }
}
