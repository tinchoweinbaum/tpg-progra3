package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando el identificador de tipo de CapaDatos.nave especificado durante
 * su creación no corresponde a ninguna de las variantes válidas del sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class TipoNaveInvalidoException extends NaveException {
    public TipoNaveInvalidoException(String message) {
        super(message);
    }
}
