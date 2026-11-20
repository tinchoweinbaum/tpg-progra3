package exceptions;

/**
 * Excepcion abstracta para extender para excepciones al registrar.
 */
public abstract class RegistroException extends Exception {
    public RegistroException(String message) {
        super(message);
    }
}
