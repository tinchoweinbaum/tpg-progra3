package bitacora;

import java.util.Date;

public class RegistroError extends RegistroBitacora {
    private Exception error;

    /**
     * Constructor que crea un registro de bitácora asociado a una excepción
     * <b>Pre:</b>
     * - fechaRegistro != null
     * - descripcionRegistro != null && descripcionRegistro no vacio
     * - error != null
     * <b>Post:</b>
     * - getFechaRegistro() == fechaRegistro
     * - getDescripcionRegistro() == descripcionRegistro
     * - getError() == error
     * @param fechaRegistro la fecha en la que se guarda la información
     * @param descripcionRegistro la informacion que guarda el registro al crearse
     * @param error la excepción capturada
     */
    public RegistroError(Date fechaRegistro, String descripcionRegistro, Exception error) {
        super(fechaRegistro, descripcionRegistro);
        assert error != null : "El error no puede ser nulo";
        this.error = error;
    }

    public RegistroError(String descripcionRegistro, Exception error) {
        this(new Date(), descripcionRegistro, error);
    }

    @Override
    public boolean esRegistroError() {
        return true;
    }

    public Exception getError() {
        return error;
    }

    @Override
    public String toString() {
        return super.toString() + " | Excepción: " + error.getMessage();
    }
}
