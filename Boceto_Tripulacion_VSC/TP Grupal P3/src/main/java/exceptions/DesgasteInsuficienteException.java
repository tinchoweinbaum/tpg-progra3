package exceptions;

/**
 * Excepcion para cuando no puede realizarse el mantenimineto de la nave para llevar el desgaste a 0 porque el desgaste no supera las 80 unidades
 */
public class DesgasteInsuficienteException extends ActualizacionesErroneas{
    public DesgasteInsuficienteException(String message) {
        super(message);
    }
}
