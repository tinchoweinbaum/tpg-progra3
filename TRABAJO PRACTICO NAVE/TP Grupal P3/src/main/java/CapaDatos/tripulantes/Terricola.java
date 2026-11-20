package CapaDatos.tripulantes;

/**
 * Decorador concreto de especie que otorga el origen Terrícola y un subsidio fijo de 20 unidades al sueldo.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de EspecieDecorator && BONO_TERRICOLA == 20</p>
 */
public class Terricola extends EspecieDecorator{
    private static final double BONO_TERRICOLA = 20;

    /**
     * Construye un decorador Terrícola para el tripulante indicado.
     *
     * @param tripulante Instancia de Tripulante a decorar.
     * @pre tripulante != null
     * @post Se inicializa el decorador Terricola envolviendo a tripulante.
     */
    public Terricola(Tripulante tripulante){
        super(tripulante);
    }

    /**
     * Calcula el sueldo del tripulante sumando el subsidio fijo por origen terrícola (20).
     *
     * @return Sueldo del tripulante base más BONO_TERRICOLA.
     * @pre getTripulante() != null
     * @post Retorna BONO_TERRICOLA + getTripulante().getSueldo() sin modificar el estado del objeto.
     */
    @Override
    public double getSueldo(){
        return BONO_TERRICOLA + this.getTripulante().getSueldo();
    }

    /**
     * Devuelve la representación textual completa del tripulante con su cargo, origen Terrícola y sueldo redondeado.
     *
     * @return Cadena formateada con los datos del tripulante, su origen y sueldo a dos decimales.
     * @pre getTripulante() != null
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return this.getTripulante().toString() + " --- ORIGEN: Terricola --- SUELDO: $" + (Math.round(getSueldo() * 100.0) / 100.0);
    }
}
