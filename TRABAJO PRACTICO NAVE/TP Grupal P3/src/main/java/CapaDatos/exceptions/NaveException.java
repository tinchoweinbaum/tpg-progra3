package CapaDatos.exceptions;

/**
 * Clase abstracta padre de las excepciones relacionadas con la creación,
 * configuración o validación de tipos de naves espaciales.
 * <p><b>Invariante de clase:</b> true</p>
 */
public abstract class NaveException extends Exception{
    public NaveException(String message) {
        super(message);
    }
}
