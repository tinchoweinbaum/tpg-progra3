package CapaDatos.tripulantes;

/**
 * Decorador abstracto del patrón Decorator que envuelve a un Tripulante para añadirle
 * atributos, bonificaciones salariales y descripción correspondientes a su especie de origen.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Tripulante && tripulante != null</p>
 */
public abstract class EspecieDecorator extends Tripulante{
    private Tripulante tripulante;

    /**
     * Construye un decorador de especie envolviendo a un tripulante existente.
     *
     * @param tripulante Instancia de Tripulante a decorar.
     * @pre tripulante != null
     * @post this.tripulante == tripulante y se copian sus atributos base (nombre, antigüedad, sueldoBase y bonoAntiguedad).
     */
    public EspecieDecorator(Tripulante tripulante) {
        super(tripulante.getNombre(), tripulante.getAntiguedad(), tripulante.getSueldoBase(), tripulante.getBonoAntiguedad());
        this.tripulante = tripulante;
    }

    /**
     * Calcula el sueldo total del tripulante incluyendo el subsidio propio de su especie.
     *
     * @return Sueldo del tripulante decorado más el bono de especie.
     * @pre tripulante != null
     * @post Retorna el sueldo total con el subsidio de especie aplicado.
     */
    @Override
    public abstract double getSueldo();

    /**
     * Obtiene la referencia al tripulante envuelto por este decorador.
     *
     * @return Instancia interna de Tripulante.
     * @pre true
     * @post Retorna tripulante sin modificar el estado del objeto.
     */
    public Tripulante getTripulante(){
        return this.tripulante;
    }

    /**
     * Delega en el tripulante envuelto la verificación de si ocupa el cargo de Capitán.
     *
     * @return true si el tripulante envuelto es Capitán; false en caso contrario.
     * @pre tripulante != null
     * @post Retorna tripulante.esCapitan() sin modificar el estado del objeto.
     */
    @Override
    public boolean esCapitan(){
        return this.tripulante.esCapitan();
    }
}
