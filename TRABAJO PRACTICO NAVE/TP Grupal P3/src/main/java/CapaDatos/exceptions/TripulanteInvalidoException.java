package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando se intenta incorporar a la tripulación un tripulante nulo
 * o que ya forma parte de la tripulación actual de la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class TripulanteInvalidoException extends ErrorAgregarTripulacionException {
    public TripulanteInvalidoException(String message) {
        super(message);
    }
}
