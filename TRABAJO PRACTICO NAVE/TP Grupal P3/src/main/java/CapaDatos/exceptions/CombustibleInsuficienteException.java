package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando la CapaDatos.nave no dispone del combustible mínimo requerido
 * para iniciar o completar una misión.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class CombustibleInsuficienteException extends MisionImposibleException {
    public CombustibleInsuficienteException(String message) {
        super(message);
    }
}
