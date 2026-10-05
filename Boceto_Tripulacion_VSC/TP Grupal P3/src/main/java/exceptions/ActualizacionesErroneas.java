package exceptions;

/**
 * Clase abstracta padre de excepciones cuando no se puede realizar alguna modificacion de atributo de manera manual
 */
public abstract class ActualizacionesErroneas extends Exception {
    public ActualizacionesErroneas(String message) {
        super(message);
    }
}
