package CapaDatos.bitacora;

import java.util.Date;

/**
 * Registro de bitácora que documenta variaciones (cargas, consumos o reparaciones) en los recursos de la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de RegistroBitacora && tipoRecurso != null && esTipoRecursoValido(tipoRecurso) && cantidadModificada != 0 && nivelResultante >= 0</p>
 */
public class RegistroRecursos extends RegistroBitacora {
    private String tipoRecurso;
    private float cantidadModificada;
    private float nivelResultante;

    /**
     * Constructor que crea un registro de bitácora asociado a operaciones sobre recursos en una fecha específica.
     *
     * @param fechaRegistro La fecha base en la que se guarda la información.
     * @param tipoRecurso Categoría del recurso ("COMBUSTIBLE", "ENERGIA", "DESGASTE").
     * @param cantidadModificada Unidades agregadas (+) o consumidas (-).
     * @param nivelResultante Saldo o nivel final del recurso tras la operación.
     * @pre fechaRegistro != null && tipoRecurso != null && esTipoRecursoValido(tipoRecurso) && cantidadModificada != 0 && nivelResultante >= 0
     * @post getFechaRegistro() queda inicializada con fechaRegistro (+5000 ms), getDescripcionRegistro().equals("Actualizacion de recursos"), getTipoRecurso().equals(tipoRecurso), getCantidadModificada() == cantidadModificada y getNivelResultante() == nivelResultante.
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

    /**
     * Constructor que crea un registro de modificación de recursos utilizando la fecha actual del sistema.
     *
     * @param tipoRecurso Categoría del recurso ("COMBUSTIBLE", "ENERGIA", "DESGASTE").
     * @param cantidadModificada Unidades agregadas (+) o consumidas (-).
     * @param nivelResultante Saldo o nivel final del recurso tras la operación.
     * @pre tipoRecurso != null && esTipoRecursoValido(tipoRecurso) && cantidadModificada != 0 && nivelResultante >= 0
     * @post Se inicializa el registro con la fecha actual ajustada y los valores de recurso especificados.
     */
    public RegistroRecursos(String tipoRecurso, float cantidadModificada, float nivelResultante){
        this(new Date(), tipoRecurso, cantidadModificada, nivelResultante);
    }

    /**
     * Verifica si una cadena corresponde a una categoría válida de recurso de la CapaDatos.nave.
     *
     * @param tipo Nombre del tipo de recurso a validar.
     * @return true si tipo es "COMBUSTIBLE", "ENERGIA" o "DESGASTE" (ignorando mayúsculas/minúsculas); false en caso contrario.
     * @pre tipo != null
     * @post Retorna la validez del tipo de recurso sin efectos colaterales.
     */
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

    /**
     * Devuelve una representación detallada de la variación de recursos y su nivel resultante.
     *
     * @return Cadena formateada con fecha, descripción, tipo de recurso, variación con signo y nivel actual.
     * @pre true
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString() {
        String signo = cantidadModificada >= 0 ? "+" : "";
        return super.toString() + " | Recurso: " + tipoRecurso +
                " | Variación: " + signo + cantidadModificada +
                " | Nivel actual: " + nivelResultante;
    }
}
