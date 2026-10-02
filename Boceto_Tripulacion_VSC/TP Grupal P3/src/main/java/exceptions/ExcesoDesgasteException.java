package exceptions;

import misiones.Mision;

/**
 * Exception que se arroja cuando no se logra aceptar una misión porque el desgaste de la nave supera el 100.
 */
public class ExcesoDesgasteException extends MisionImposibleException {
    public ExcesoDesgasteException(String message) {
        super(message);
    }
}
