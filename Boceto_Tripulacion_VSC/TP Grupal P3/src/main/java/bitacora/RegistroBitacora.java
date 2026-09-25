package bitacora;

import java.util.Date;

/**
 * Superclase de la que van a heredar todos los registros de la bitácora.
 * La bitácora va a ser entonces un ArrayList de variables de tipo RegistroBitácora.
 * Capaz conviene usar una interfaz en vez de herencia de esta manera.
 */
public abstract class RegistroBitacora {
    private Date fechaRegistro;
    private String descripcionRegistro;

    public RegistroBitacora(Date fechaRegistro, String descripcionRegistro) {
        this.fechaRegistro = fechaRegistro;
        this.descripcionRegistro = descripcionRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDescripcionRegistro() {
        return descripcionRegistro;
    }

    public void setDescripcionRegistro(String descripcionRegistro) {
        this.descripcionRegistro = descripcionRegistro;
    }
}
