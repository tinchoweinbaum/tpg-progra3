package CapaDatos.exceptions;

/**
 * Clase abstracta padre de las excepciones lanzadas cuando una misión no puede
 * ser aceptada o completada por incumplimiento de requisitos de recursos o tripulación.
 * <p><b>Invariante de clase:</b> true</p>
 */
public abstract class MisionImposibleException extends Exception{
    public MisionImposibleException(String message) {
        super(message);
    }
}
