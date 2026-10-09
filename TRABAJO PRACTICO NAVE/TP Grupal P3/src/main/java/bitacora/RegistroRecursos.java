package bitacora;

import java.util.Date;

public class RegistroRecursos extends RegistroBitacora {
    private String tipoRecurso;
    private float cantidadModificada;
    private float nivelResultante;

    /**
     * Constructor que crea un registro de bitácora asociado a operaciones sobre recursos<br>
     * <b>Pre:</b>
     * - fechaRegistro != null
     * - descripcionRegistro != null && descripcionRegistro no vacia
     * - tipoRecurso != null && esTipoRecursoValido(tipoRecurso)
     * - cantidadModificada != 0
     * - nivelResultante >= 0<br>
     * <b>Post:</b>
     * - getFechaRegistro() == fechaRegistro
     * - getDescripcionRegistro() == descripcionRegistro
     * - getTipoRecurso() == tipoRecurso
     * - getCantidadModificada() == cantidadModificada
     * - getNivelResultante() == nivelResultante
     * @param fechaRegistro la fecha en la que se guarda la información
     * @param tipoRecurso la categoría del recurso ("COMBUSTIBLE", "ENERGIA", "DESGASTE")
     * @param cantidadModificada unidades agregadas (+) o consumidas (-)
     * @param nivelResultante el saldo o nivel final del recurso tras la operación
     */
    public RegistroRecursos(Date fechaRegistro, String tipoRecurso, float cantidadModificada, float nivelResultante) {
        super(fechaRegistro, "Actualizacion de recursos");
        assert tipoRecurso != null && esTipoRecursoValido(tipoRecurso) : "Tipo de recurso inválido";
        assert cantidadModificada != 0 : "La cantidad modificada no puede ser cero";
        assert nivelResultante >= 0 : "El nivel resultante no puede ser negativo";
        this.tipoRecurso = tipoRecurso;
        this.cantidadModificada = cantidadModificada;
        this.nivelResultante = nivelResultante;
    }

    public RegistroRecursos(String tipoRecurso, float cantidadModificada, float nivelResultante){
        this(new Date(), tipoRecurso, cantidadModificada, nivelResultante);
    }

    @Override
    public boolean esRegistroRecursos() {
        return true;
    }

    private static boolean esTipoRecursoValido(String tipo) {
        return tipo.equalsIgnoreCase("COMBUSTIBLE") ||
                tipo.equalsIgnoreCase("ENERGIA") ||
                tipo.equalsIgnoreCase("DESGASTE");
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public float getCantidadModificada() {
        return cantidadModificada;
    }

    public float getNivelResultante() {
        return nivelResultante;
    }

    @Override
    public String toString() {
        String signo = cantidadModificada >= 0 ? "+" : "-";
        return super.toString() + " | Recurso: " + tipoRecurso +
                " | Variación: " + signo + cantidadModificada +
                " | Nivel actual: " + nivelResultante;
    }
}
