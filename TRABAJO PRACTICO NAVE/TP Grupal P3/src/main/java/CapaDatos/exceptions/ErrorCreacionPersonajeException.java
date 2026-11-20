package CapaDatos.exceptions;

/**
 * Excepción base lanzada cuando fallan las validaciones durante la instanciación
 * de un nuevo tripulante (por ejemplo, cargo u origen inválido).
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ErrorCreacionPersonajeException extends Exception {
    public ErrorCreacionPersonajeException(String message) {
        super(message);
    }
}
