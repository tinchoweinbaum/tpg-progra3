package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando la CapaDatos.nave no cuenta con el nivel de energía necesario
 * para afrontar una misión.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class EnergiaInsuficienteException extends MisionImposibleException {
    public EnergiaInsuficienteException(String message) {
        super(message);
    }
}
