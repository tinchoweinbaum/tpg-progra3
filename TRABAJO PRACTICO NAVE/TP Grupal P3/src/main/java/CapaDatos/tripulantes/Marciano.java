package CapaDatos.tripulantes;

/**
 * Decorador concreto de especie que otorga el origen Marciano y un subsidio fijo de 18 unidades al sueldo.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de EspecieDecorator && BONO_MARCIANO == 18</p>
 */
public class Marciano extends EspecieDecorator{
    private static final double BONO_MARCIANO = 18;

    /**
     * Construye un decorador Marciano para el tripulante indicado.
     *
     * @param tripulante Instancia de Tripulante a decorar.
     * @pre tripulante != null
     * @post Se inicializa el decorador Marciano envolviendo a tripulante.
     */
    public Marciano(Tripulante tripulante){
        super(tripulante);
    }

    /**
     * Calcula el sueldo del tripulante sumando el subsidio fijo por origen marciano (18).
     *
     * @return Sueldo del tripulante base más BONO_MARCIANO.
     * @pre getTripulante() != null
     * @post Retorna BONO_MARCIANO + getTripulante().getSueldo() sin modificar el estado del objeto.
     */
    @Override
    public double getSueldo(){
        return BONO_MARCIANO + this.getTripulante().getSueldo();
    }

    /**
     * Devuelve la representación textual completa del tripulante con su cargo, origen Marciano y sueldo redondeado.
     *
     * @return Cadena formateada con los datos del tripulante, su origen y sueldo a dos decimales.
     * @pre getTripulante() != null
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return this.getTripulante().toString() + " --- ORIGEN: Marciano --- SUELDO: $" + (Math.round(getSueldo() * 100.0) / 100.0);
    }
}
