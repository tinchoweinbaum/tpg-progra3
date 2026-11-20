package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando se intenta cargar o consumir una cantidad inválida de combustible
 * (por ejemplo, montos menores o iguales a cero, o que superan la capacidad máxima del tanque).
 * <p><b>Invariante de clase:</b> true</p>
 */
public class CantCombustibleInvalidaException extends ActualizacionesErroneas {
    public CantCombustibleInvalidaException(String message) {
        super(message);
    }
}
