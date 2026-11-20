package CapaDatos.exceptions;

/**
 * Clase abstracta base para las excepciones lanzadas durante el proceso de
 * autenticación (inicio de sesión) de un usuario en el sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public abstract class UsuarioLoginException extends Exception {
    public UsuarioLoginException(String message) {
        super(message);
    }
}
