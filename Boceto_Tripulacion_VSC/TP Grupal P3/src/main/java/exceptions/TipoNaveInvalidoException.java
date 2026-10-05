package exceptions;

/**
 * Exception que se arroja cuando el tipo de creacion de la nave es invalido.
 */
public class TipoNaveInvalidoException extends NaveException {
    public TipoNaveInvalidoException(String message) {
        super(message);
    }
}
