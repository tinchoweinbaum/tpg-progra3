package CapaDatos.bitacora;

import java.util.Date;

/**
 * Superclase base para todos los registros almacenados en la bitácora de la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> fechaRegistro != null && descripcionRegistro != null && !descripcionRegistro.trim().isEmpty()</p>
 */
public class RegistroBitacora implements Comparable<RegistroBitacora>{
    private final Date fechaRegistro;
    private final String descripcionRegistro;
    
    private boolean invarianteRegistro() {
        return this.fechaRegistro != null && this.descripcionRegistro != null  && !descripcionRegistro.trim().isEmpty();
    }

    /**
     * Constructor que crea un registro de bitácora a partir de una fecha base (sumándole 5 segundos) y una descripción.
     *
     * @param fechaRegistro La fecha base en la que se genera el evento.
     * @param descripcionRegistro Texto descriptivo del registro.
     * @pre fechaRegistro != null && descripcionRegistro != null && !descripcionRegistro.trim().isEmpty()
     * @post getFechaRegistro().equals(Date.from(fechaRegistro.toInstant().plusMillis(5000))) && getDescripcionRegistro().equals(descripcionRegistro)
     */
    public RegistroBitacora(Date fechaRegistro, String descripcionRegistro) {
        assert fechaRegistro != null : "La fecha no puede ser nula";
        assert descripcionRegistro != null && !descripcionRegistro.trim().isEmpty() : "Descripción inválida";
        
        this.fechaRegistro = Date.from(fechaRegistro.toInstant().plusMillis(5000));
        this.descripcionRegistro = descripcionRegistro;
        
        assert invarianteRegistro(): "El registro no es valido";
    }

    /**
     * Constructor sobrecargado que crea un registro utilizando la fecha y hora actual del sistema.
     *
     * @param descripcionRegistro Texto descriptivo del registro.
     * @pre descripcionRegistro != null && !descripcionRegistro.trim().isEmpty()
     * @post Se inicializa el registro con la fecha actual ajustada y la descripción indicada.
     */
    public RegistroBitacora(String descripcionRegistro) {
        this(new Date(), descripcionRegistro); // Llama al constructor principal  
        assert invarianteRegistro(): "El registro no es valido";
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getDescripcionRegistro() {
        return descripcionRegistro;
    }

    /**
     * Devuelve una representación en texto del registro con su fecha y descripción.
     *
     * @return Cadena formateada como "[fechaRegistro] descripcionRegistro".
     * @pre true
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString() {
        return "[" + fechaRegistro + "] " + descripcionRegistro;
    }

    /**
     * Compara cronológicamente este registro con otro según su fecha.
     *
     * @param o Otro objeto RegistroBitacora a comparar.
     * @return Valor negativo si este registro es anterior, cero si son simultáneos, o positivo si es posterior.
     * @pre o != null && o.getFechaRegistro() != null
     * @post Retorna el resultado de comparar ambas fechas sin modificar ninguno de los objetos.
     */
    @Override
    public int compareTo(RegistroBitacora o) {
        return this.getFechaRegistro().compareTo(o.getFechaRegistro());
    }
}
