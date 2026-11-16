package exceptions;

/**
 * Excepeción para cuando se intenta ejecutar una misión que resulta imposible por falta de energía.
 */
public class EnergiaInsuficienteException extends MisionImposibleException {
    public EnergiaInsuficienteException(String message) {
        super(message);
    }
}
