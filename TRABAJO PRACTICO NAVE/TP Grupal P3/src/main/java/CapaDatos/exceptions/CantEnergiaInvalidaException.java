package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando se intenta cargar energía por encima de la capacidad máxima,
 * con montos no positivos, o consumir una cantidad inválida de energía.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class CantEnergiaInvalidaException extends ActualizacionesErroneas {
    public CantEnergiaInvalidaException(String message) {
        super(message);
    }
}
