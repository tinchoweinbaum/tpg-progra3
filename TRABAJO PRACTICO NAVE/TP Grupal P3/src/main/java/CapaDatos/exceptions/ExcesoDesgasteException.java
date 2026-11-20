package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando no es posible aceptar una misión debido a que el desgaste actual
 * de la CapaDatos.nave sumado al desgaste requerido por la misión alcanza o supera el límite máximo (100).
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ExcesoDesgasteException extends MisionImposibleException {
    public ExcesoDesgasteException(String message) {
        super(message);
    }
}
