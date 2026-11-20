package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando la CapaDatos.nave no cumple con los requisitos mínimos de tripulación
 * (ausencia de capitán o cantidad insuficiente de CapaDatos.tripulantes) para iniciar una misión.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ErrorTripulantesException extends MisionImposibleException {
    public ErrorTripulantesException(String message) {
        super(message);
    }
}
