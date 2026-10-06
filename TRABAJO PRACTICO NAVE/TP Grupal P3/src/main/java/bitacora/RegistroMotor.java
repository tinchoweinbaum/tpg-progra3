package bitacora;

import java.util.Date;
import motorwarp.State;

public class RegistroMotor extends  RegistroBitacora{
    private State estado;

    /**
     * Constructor que crea un registro de bitácora asociado a un evento o cambio de estado del motor.
     * <b>Pre:</b>
     * - fechaRegistro != null
     * - descripcionRegistro != null && descripcionRegistro no vacio
     * - estado != null
     * <b>Post:</b>
     * - getFechaRegistro() == fechaRegistro
     * - getDescripcionRegistro() == descripcionRegistro
     * - getEstado() == estado
     * @param fechaRegistro la fecha en la que se guarda la información
     * @param descripcionRegistro la informacion que guarda el registro al crearse
     * @param estado el estado actual del motor que se va a registrar en la bitácora
     */
    public RegistroMotor(Date fechaRegistro, String descripcionRegistro, State estado){
        super(fechaRegistro, descripcionRegistro);
        assert estado != null: "El estado no puede ser nulo";
        this.estado = estado;
    }

    public RegistroMotor(String descripcionRegistro, State estado){
        this(new Date(), descripcionRegistro, estado);
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return super.toString() + " | Estado del motor: " + estado;
    }
}
