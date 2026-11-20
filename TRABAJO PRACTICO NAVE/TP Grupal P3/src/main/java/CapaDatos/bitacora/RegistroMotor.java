package CapaDatos.bitacora;

import java.util.Date;
import CapaDatos.motorwarp.State;

/**
 * Registro de bitácora asociado a las transiciones de estado del motor warp.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de RegistroBitacora && estado != null</p>
 */
public class RegistroMotor extends  RegistroBitacora{
    private State estado;

    /**
     * Constructor que crea un registro de bitácora asociado a un cambio de estado del motor en una fecha específica.
     *
     * @param fechaRegistro La fecha base en la que se guarda la información.
     * @param descripcionRegistro Descripción del evento del motor.
     * @param estado El estado actual del motor warp.
     * @pre fechaRegistro != null && descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() && estado != null
     * @post getFechaRegistro() queda inicializada con fechaRegistro (+5000 ms), getDescripcionRegistro().equals(descripcionRegistro) y getEstado() == estado.
     */
    public RegistroMotor(Date fechaRegistro, String descripcionRegistro, State estado){
        super(fechaRegistro, descripcionRegistro);
        assert estado != null: "El estado no puede ser nulo";
        this.estado = estado;
    }

    /**
     * Constructor que crea un registro de motor utilizando la fecha actual del sistema.
     *
     * @param descripcionRegistro Descripción del evento del motor.
     * @param estado El estado actual del motor warp.
     * @pre descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() && estado != null
     * @post Se inicializa el registro con la fecha actual ajustada, la descripción y el estado indicado.
     */
    public RegistroMotor(String descripcionRegistro, State estado){
        this(new Date(), descripcionRegistro, estado);
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    /**
     * Devuelve la representación textual del registro incluyendo el estado del motor.
     *
     * @return Cadena con el formato base seguido de " | Estado del motor: " y el estado.
     * @pre true
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString() {
        return super.toString() + " | Estado del motor: " + estado;
    }
}
