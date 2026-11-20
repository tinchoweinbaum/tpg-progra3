package CapaDatos.bitacora;

import java.util.Date;
import CapaDatos.misiones.Mision;

/**
 * Registro de bitácora vinculado a los eventos o estados de una misión espacial.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de RegistroBitacora && mision != null</p>
 */
public class RegistroMision extends RegistroBitacora{
    private Mision mision;

    /**
     * Constructor que crea un registro asociado a una misión en una fecha específica.
     *
     * @param fechaRegistro La fecha base en la que se guarda la información.
     * @param descripcionRegistro Descripción del evento de la misión.
     * @param mision La misión asociada al registro.
     * @pre fechaRegistro != null && descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() && mision != null
     * @post getFechaRegistro() queda inicializada con fechaRegistro (+5000 ms), getDescripcionRegistro().equals(descripcionRegistro) y getMision() == mision.
     */
    public RegistroMision(Date fechaRegistro, String descripcionRegistro, Mision mision) {
        super(fechaRegistro, descripcionRegistro);
        assert mision != null: "La misión no puede ser nula";
        this.mision = mision;
    }

    /**
     * Constructor que crea un registro de misión utilizando la fecha actual del sistema.
     *
     * @param descripcionRegistro Descripción del evento de la misión.
     * @param mision La misión asociada al registro.
     * @pre descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() && mision != null
     * @post Se inicializa el registro con la fecha actual ajustada, la descripción y la misión indicada.
     */
    public RegistroMision(String descripcionRegistro, Mision mision){
        this(new Date(), descripcionRegistro, mision);
    }

    public Mision getMision() {
        return mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    /**
     * Devuelve la representación textual del registro incluyendo los detalles de la misión asociada.
     *
     * @return Cadena con el formato base seguido de " | Misión asociada: " y la representación de mision.
     * @pre true
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString() {
        return super.toString() + " | Misión asociada: " + mision;
    }
}
