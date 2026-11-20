package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando se intenta aplicar un incremento de desgaste inválido o realizar
 * un mantenimiento cuando la CapaDatos.nave no cumple el umbral mínimo de desgaste requerido.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class CantDesgasteInvalidaException extends ActualizacionesErroneas {
    public CantDesgasteInvalidaException(String message) {
        super(message);
    }
}
