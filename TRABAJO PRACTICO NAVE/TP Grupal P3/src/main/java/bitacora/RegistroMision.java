package bitacora;

import java.util.Date;
import misiones.Mision;

public class RegistroMision extends RegistroBitacora{
    private Mision mision;

    /**
     * Constructor que crea un registro asociado a una misión
     * <b>Pre:</b>
     * - fechaRegistro != null
     * - descripcionRegistro != null && descripcionRegistro no esta vacia
     * - mision != null
     * <b>Post:</b>
     * - getFechaRegistro() == fechaRegistro
     * - getDescripcionRegistro() == descripcionRegistro
     * - getMision() == mision
     * @param fechaRegistro la fecha en la que se guarda la información
     * @param descripcionRegistro la informacion que guarda el registro al crearse
     * @param mision la mision a la que se asocia el registros
     */
    public RegistroMision(Date fechaRegistro, String descripcionRegistro, Mision mision) {
        super(fechaRegistro, descripcionRegistro);
        assert mision != null: "La misión no puede ser nula";
        this.mision = mision;
    }

    public  RegistroMision(String descripcionRegistro, Mision mision){
        this(new Date(), descripcionRegistro, mision);
    }

    @Override
    public boolean esRegistroMision() {
        return true;
    }

    public Mision getMision() {
        return mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    @Override
    public String toString() {
        return super.toString() + " | Misión asociada: " + mision;
        // Al ver el objeto mision, Java llama automáticamente al toString() de la clase Mision
    }
}
