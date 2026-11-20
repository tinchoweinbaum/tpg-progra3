package CapaDatos.exceptions;

/**
 * Excepción lanzada durante el registro de un nuevo usuario cuando el nombre de usuario
 * o la contraseña ingresados no cumplen con el formato o las reglas de validación.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class DatosRegistroInvalidosException extends RegistroException {
    public DatosRegistroInvalidosException(String message) {
        super(message);
    }
}
