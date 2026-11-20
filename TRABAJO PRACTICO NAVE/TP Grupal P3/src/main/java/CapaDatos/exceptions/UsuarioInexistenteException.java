package CapaDatos.exceptions;

/**
 * Excepción lanzada durante el inicio de sesión cuando el nombre de usuario
 * ingresado no corresponde a ningún usuario registrado en el sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class UsuarioInexistenteException extends UsuarioLoginException {
    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
