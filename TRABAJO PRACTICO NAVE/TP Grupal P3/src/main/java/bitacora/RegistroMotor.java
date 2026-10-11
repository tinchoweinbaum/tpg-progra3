package bitacora;

import java.util.Date;
import motorwarp.State;

public class RegistroMotor extends  RegistroBitacora{
    private State estado;

    /**
     * Constructor que crea un registro de bitácora asociado a un evento o cambio de estado del motor.
     * <b>pre:</b>  fechaRegistro != null, descripcionRegistro != null && descripcionRegistro no vacio y estado != null
     * <b>post:</b> getFechaRegistro() == fechaRegistro, getDescripcionRegistro() == descripcionRegistro y  getEstado() == estado
     * 
     * @param fechaRegistro la fecha en la que se guarda la información
     * @param descripcionRegistro la informacion que guarda el registro al crearse
     * @param estado el estado actual del motor que se va a registrar en la bitacora
     */
    
    public RegistroMotor(Date fechaRegistro, String descripcionRegistro, State estado){
        super(fechaRegistro, descripcionRegistro);
        assert estado != null: "El estado no puede ser nulo";
        this.estado = estado;
    }
    
    
    /**
    * Contructor que llama al contructor padre pasandole la fecha actual, una descripcion y un estado
    * 
    * @param descripcionRegistro string que contiene a descripcion completa del registro
    * @param estado el estado actual del motor que se va a registrar en la bitacora
    */
    

    public RegistroMotor(String descripcionRegistro, State estado){
        this(new Date(), descripcionRegistro, estado);
    }

    @Override
    public boolean esRegistroMotor() {
        return true;
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
