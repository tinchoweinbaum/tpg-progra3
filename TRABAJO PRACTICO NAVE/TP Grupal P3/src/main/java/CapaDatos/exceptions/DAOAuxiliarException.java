package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando ocurre un fallo técnico o de acceso a datos en la capa
 * de persistencia (por ejemplo, errores de conexión, inicialización o consultas SQL).
 * <p><b>Invariante de clase:</b> true</p>
 */
public class DAOAuxiliarException extends Exception {
    public DAOAuxiliarException(String message) {
        super(message);
    }
}
