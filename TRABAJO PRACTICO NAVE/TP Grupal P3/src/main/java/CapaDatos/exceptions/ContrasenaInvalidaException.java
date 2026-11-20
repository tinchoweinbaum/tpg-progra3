package CapaDatos.exceptions;

/**
 * Excepción lanzada durante el inicio de sesión cuando el usuario existe en el sistema
 * pero la contraseña ingresada no coincide con la registrada.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ContrasenaInvalidaException extends UsuarioLoginException {
    public ContrasenaInvalidaException(String message) {
        super(message);
    }
}
