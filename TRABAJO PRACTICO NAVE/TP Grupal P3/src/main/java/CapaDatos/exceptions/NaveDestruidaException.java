package CapaDatos.exceptions;

/**
 * Excepción lanzada cuando la CapaDatos.nave sufre daños fatales, agota por completo su combustible
 * o energía, alcanza el desgaste máximo (100) o pierde un combate en el universo.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class NaveDestruidaException extends Exception {
    public NaveDestruidaException(String message) {
        super(message);
    }
}
