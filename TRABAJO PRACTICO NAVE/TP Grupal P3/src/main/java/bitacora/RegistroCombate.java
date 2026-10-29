package bitacora;

import java.util.Date;

/**
 * Clase de registro que guardas los combates de las naves. Si la nave muere creo que no debe guardarse un registro porque muere XD
 */
public class RegistroCombate extends RegistroBitacora{

    public RegistroCombate(String descripcionRegistro) {
        super(descripcionRegistro);
    }

    public RegistroCombate(Date fechaRegistro, String descripcionRegistro) {
        super(fechaRegistro, descripcionRegistro);
    }
}
