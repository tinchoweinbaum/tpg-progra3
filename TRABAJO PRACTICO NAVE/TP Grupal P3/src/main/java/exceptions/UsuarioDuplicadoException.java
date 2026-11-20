package exceptions;

/**
 * Excepcion de registro, se arroja cuando se intenta registar a un usuario con un nombre de usuario ocupado.
 */
public class UsuarioDuplicadoException extends RegistroException {
    public UsuarioDuplicadoException(String message) {
        super(message);
    }
}
