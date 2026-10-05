package exceptions;

/**
 * Excepcion que maneja los cambios de estado invalido del motor warp
 */
public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String message) {
        super(message);
    }
}
