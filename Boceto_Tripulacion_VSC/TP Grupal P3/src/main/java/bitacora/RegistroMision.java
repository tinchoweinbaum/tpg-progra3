package bitacora;

import java.util.Date;
import misiones.*;

public class RegistroMision extends RegistroBitacora{
    Mision mision;


    public RegistroMision(Date fechaRegistro, String descripcionRegistro, Mision mision) {
        super(fechaRegistro, descripcionRegistro);
        this.mision = mision;
    }
}
