package bitacora;

import java.util.Date;

/**
 * Superclase de la que van a heredar todos los registros de la bitácora.
 * La bitácora va a ser entonces un ArrayList de variables de tipo RegistroBitácora.
 * Capaz conviene usar una interfaz en vez de herencia de esta manera.
 */
public abstract class RegistroBitacora implements Comparable<RegistroBitacora>{
    private final Date fechaRegistro;
    private final String descripcionRegistro;

    /**
     * Constructor que crea un objeto de tipo RegistroBitacora
     * <b>Pre</b>
     * -fechaRegistro != null
     * -descripcionRegistro != null && descripcion no esta vacia
     * <b>Post</b>
     * - getFechaRegistro() == fechaRegistro
     * - getDescripcionRegistro() == descripcionRegistro
     * @param fechaRegistro la fecha en la que se guarda la informacion
     * @param descripcionRegistro s
     */
    public RegistroBitacora(Date fechaRegistro, String descripcionRegistro) {
        assert fechaRegistro != null : "La fecha no puede ser nula";
        assert descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() : "Descripción inválida";
        this.fechaRegistro = fechaRegistro;
        this.descripcionRegistro = descripcionRegistro;
    }

    // Métodos de consulta con respuesta por defecto "false"
    public boolean esRegistroMotor() {
        return false;
    }

    public boolean esRegistroMision() {
        return false;
    }

    public boolean esRegistroRecursos() {
        return false;
    }

    public boolean esRegistroError(){
        return false;
    }

    public RegistroBitacora(String descripcionRegistro) {
        this(new Date(), descripcionRegistro); // Llama al constructor principal asignando la fecha actual
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getDescripcionRegistro() {
        return descripcionRegistro;
    }

    // No se ponen setters porque los registros que quedan grabados en la bitacora NO se deben cambiar

    @Override
    public String toString() {
        return "[" + fechaRegistro + "] " + descripcionRegistro;
    }

    @Override
    public int compareTo(RegistroBitacora o) {
        return this.getFechaRegistro().compareTo(o.getFechaRegistro());
    }
}
