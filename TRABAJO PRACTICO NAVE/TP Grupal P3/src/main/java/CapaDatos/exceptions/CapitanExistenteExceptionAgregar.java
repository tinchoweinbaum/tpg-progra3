package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando se intenta incorporar un tripulante con rol de Capitán
 * a una CapaDatos.nave que ya posee uno asignado.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class CapitanExistenteExceptionAgregar extends ErrorAgregarTripulacionException {
    public CapitanExistenteExceptionAgregar(String message) {
        super(message);
    }
}
