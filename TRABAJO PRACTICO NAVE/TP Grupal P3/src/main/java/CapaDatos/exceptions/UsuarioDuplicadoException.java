package CapaDatos.exceptions;

/**
 * Excepción lanzada durante el registro de un nuevo usuario cuando el nombre de usuario
 * ingresado ya se encuentra ocupado en el sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class UsuarioDuplicadoException extends RegistroException {
    public UsuarioDuplicadoException(String message) {
        super(message);
    }
}
