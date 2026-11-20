package exceptions;

/**
 * Excepcion que se arroja cuando existe un usuario pero la contraseña dada es incorrecta.
 */
public class ContrasenaInvalidaException extends UsuarioLoginException {
    public ContrasenaInvalidaException(String message) {
        super(message);
    }
}
