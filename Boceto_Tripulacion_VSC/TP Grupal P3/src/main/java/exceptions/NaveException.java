package exceptions;

/**
 * Clase abstracta padre de las excepeciones que se arrojan al momento de crear naves por cualquier motivo.
 */
public abstract class NaveException extends Exception{
    public NaveException(String message) {
        super(message);
    }
}
