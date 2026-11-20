package exceptions;

/**
 * Excepcion de LOGIN que se arroja cuando no existe el usuario que se intento acceder.
 */
public class UsuarioInexistenteException extends UsuarioLoginException {
    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
