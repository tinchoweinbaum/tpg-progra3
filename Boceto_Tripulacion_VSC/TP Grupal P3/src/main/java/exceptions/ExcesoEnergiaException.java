package exceptions;

/**
 * Excepcion para cuando quiere cargarse energia de modo tal que supere las 100 unidades. Aplicable para misiones y para cargas manuales de energia
 */
public class ExcesoEnergiaException extends MisionImposibleException {
    public ExcesoEnergiaException(String message) {
        super(message);
    }
}
