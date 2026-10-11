package bitacora;

import java.util.Date;

public class RegistroError extends RegistroBitacora {
    private Exception error;

    /**
     * Constructor que crea un registro de bitácora asociado a una excepción
     * <b>pre:</b> fechaRegistro != null , descripcionRegistro != null && descripcion!= "" y error != null
     * <b>post:</b> getFechaRegistro() == fechaRegistro, getDescripcionRegistro() == descripcionRegistro y getError() == error
     * 
     * @param fechaRegistro la fecha en la que se guarda la informacion
     * @param descripcionRegistro la informacion que guarda el registro al crearse
     * @param error la excepción capturada
     */
    
    public RegistroError(Date fechaRegistro, String descripcionRegistro, Exception error) {
        super(fechaRegistro, descripcionRegistro);
        assert fechaRegistro != null : "La fecha no puede ser nula";
        assert descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() : "Descripción inválida";
        assert error != null : "El error no puede ser nulo";
        this.error = error;
    }
    
    /**
     * Contructor que llama al contructor padre pasandole la fecha actual, una descripcion y un error
     * 
     * @param descripcionRegistro string que contiene a descripcion completa del registro
     * @param error la excepción capturada
     */

    public RegistroError(String descripcionRegistro, Exception error) {
        this(new Date(), descripcionRegistro, error);
        assert descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() : "Descripción inválida";
        assert error != null : "El error no puede ser nulo";
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
        return super.toString() + " | " + error.getMessage();
    }
}
