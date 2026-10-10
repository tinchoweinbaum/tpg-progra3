package exceptions;

import tripulantes.*;

public class TripulanteInvalidoException extends ErrorAgregarTripulacionException {
    public TripulanteInvalidoException(String message) {
        super(message);
    }
}
