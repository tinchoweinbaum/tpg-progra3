package CapaDatos.exceptions;

/**
 * Excepción no verificada (RuntimeException) lanzada cuando se solicita una transición
 * o acción incompatible con el estado actual del motor warp.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String message) {
        super(message);
    }
}
