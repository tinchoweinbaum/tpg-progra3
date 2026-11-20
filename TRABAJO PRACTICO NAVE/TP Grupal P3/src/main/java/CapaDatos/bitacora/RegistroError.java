package CapaDatos.bitacora;

import java.util.Date;

/**
 * Registro de bitácora asociado a la captura de una excepción o fallo durante la ejecución.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de RegistroBitacora && error != null</p>
 */
public class RegistroError extends RegistroBitacora {
    private Exception error;

    /**
     * Constructor que crea un registro de bitácora asociado a una excepción en una fecha específica.
     *
     * @param fechaRegistro La fecha base en la que se guarda la información.
     * @param descripcionRegistro Descripción del contexto del error.
     * @param error La excepción capturada.
     * @pre fechaRegistro != null && descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() && error != null
     * @post getFechaRegistro() queda inicializada con fechaRegistro (+5000 ms), getDescripcionRegistro().equals(descripcionRegistro) y getError() == error.
     */
    public RegistroError(Date fechaRegistro, String descripcionRegistro, Exception error) {
        super(fechaRegistro, descripcionRegistro);
        assert fechaRegistro != null : "La fecha no puede ser nula";
        assert descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() : "Descripción inválida";
        assert error != null : "El error no puede ser nulo";
        this.error = error;
    }

    /**
     * Constructor que crea un registro de error utilizando la fecha actual del sistema.
     *
     * @param descripcionRegistro Descripción del contexto del error.
     * @param error La excepción capturada.
     * @pre descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() && error != null
     * @post Se inicializa el registro con la fecha actual ajustada, la descripción y la excepción indicada.
     */
    public RegistroError(String descripcionRegistro, Exception error) {
        this(new Date(), descripcionRegistro, error);
        assert descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() : "Descripción inválida";
        assert error != null : "El error no puede ser nulo";
    }

    public Exception getError() {
        return error;
    }

    /**
     * Devuelve la representación textual del registro incluyendo el mensaje de la excepción.
     *
     * @return Cadena con el formato base seguido de " | " y error.getMessage().
     * @pre error != null
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString() {
        return super.toString() + " | " + error.getMessage();
    }
}
