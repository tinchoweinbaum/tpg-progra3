package CapaDatos.exceptions;

/**
 * Clase abstracta base para las excepciones lanzadas durante el proceso
 * de registro de un nuevo usuario en el sistema.
 * <p><b>Invariante de clase:</b> true</p>
 */
public abstract class RegistroException extends Exception {
    public RegistroException(String message) {
        super(message);
    }
}
