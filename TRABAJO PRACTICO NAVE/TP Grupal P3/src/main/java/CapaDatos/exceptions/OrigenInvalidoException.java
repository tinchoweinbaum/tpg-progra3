package CapaDatos.exceptions;

/**
 * Excepción lanzada durante la creación de un tripulante cuando la especie u origen
 * especificado no corresponde a ninguno de los valores válidos del sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class OrigenInvalidoException extends ErrorCreacionPersonajeException {
    public OrigenInvalidoException(String message) {
        super(message);
    }
}
