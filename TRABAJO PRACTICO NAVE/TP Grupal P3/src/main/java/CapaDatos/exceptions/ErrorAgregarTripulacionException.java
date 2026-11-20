package CapaDatos.exceptions;

/**
 * Excepción base lanzada cuando ocurre un error de validación al intentar incorporar
 * un nuevo tripulante a la tripulación de una CapaDatos.nave.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ErrorAgregarTripulacionException extends Exception {
    public ErrorAgregarTripulacionException(String message) {
        super(message);
    }
}
