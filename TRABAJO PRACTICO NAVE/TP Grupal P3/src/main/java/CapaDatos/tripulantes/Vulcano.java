package CapaDatos.tripulantes;

/**
 * Decorador concreto de especie que otorga el origen Vulcano y un subsidio fijo de 30 unidades al sueldo.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de EspecieDecorator && BONO_VULCANO == 30</p>
 */
public class Vulcano extends EspecieDecorator {
    private static final double BONO_VULCANO = 30;

    /**
     * Construye un decorador Vulcano para el tripulante indicado.
     *
     * @param tripulante Instancia de Tripulante a decorar.
     * @pre tripulante != null
     * @post Se inicializa el decorador Vulcano envolviendo a tripulante.
     */
    public Vulcano(Tripulante tripulante) {
        super(tripulante);
    }

    /**
     * Calcula el sueldo del tripulante sumando el subsidio fijo por origen vulcano (30).
     *
     * @return Sueldo del tripulante base más BONO_VULCANO.
     * @pre getTripulante() != null
     * @post Retorna BONO_VULCANO + getTripulante().getSueldo() sin modificar el estado del objeto.
     */
    @Override
    public double getSueldo(){
        return BONO_VULCANO + this.getTripulante().getSueldo();
    }

    /**
     * Devuelve la representación textual completa del tripulante con su cargo, origen Vulcano y sueldo redondeado.
     *
     * @return Cadena formateada con los datos del tripulante, su origen y sueldo a dos decimales.
     * @pre getTripulante() != null
     * @post Retorna la cadena formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return this.getTripulante().toString() + " --- ORIGEN: Vulcano --- SUELDO: $" + (Math.round(getSueldo() * 100.0) / 100.0);
    }
}
