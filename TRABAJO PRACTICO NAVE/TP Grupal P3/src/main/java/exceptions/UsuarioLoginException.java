package exceptions;

/**
 * Excepcion abstracta que extiende en las dos excepciones de LOGIN.
 */
public abstract class UsuarioLoginException extends Exception {
    public UsuarioLoginException(String message) {
        super(message);
    }
}
