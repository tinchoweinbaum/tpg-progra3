package exceptions;

/**
 * Clase abstracta padre de las excepeciones que se arrojan al no poder aceptar una misión por cualquier motivo.
 */
public abstract class MisionImposibleException extends Exception{
    public MisionImposibleException(String message) {
        super(message);
    }
}
