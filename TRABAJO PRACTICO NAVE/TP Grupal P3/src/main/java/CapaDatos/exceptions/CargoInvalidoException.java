package CapaDatos.exceptions;

/**
 * Excepción lanzada durante la creación de un tripulante cuando el cargo especificado
 * no corresponde a ninguno de los roles válidos del sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class CargoInvalidoException extends ErrorCreacionPersonajeException {
    public CargoInvalidoException(String message) {
        super(message);
    }
}
