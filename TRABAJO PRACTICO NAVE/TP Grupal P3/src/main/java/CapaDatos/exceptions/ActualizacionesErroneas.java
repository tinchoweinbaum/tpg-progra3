package CapaDatos.exceptions;

/**
 * Clase abstracta base para las excepciones lanzadas cuando falla una modificación o actualización
 * sobre los recursos o el estado de la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> true</p>
 */
public abstract class ActualizacionesErroneas extends Exception {
    public ActualizacionesErroneas(String message) {
        super(message);
    }
}
