package CapaDatos.tripulantes;

/**
 * Subclase concreta de Tripulante que representa el rango de Teniente.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Tripulante && sueldoBase == 400 && bonoAntiguedad == 0.03</p>
 */
public class Teniente extends Tripulante{
    private final static double SUELDO_BASE_TENIENTE = 400;
    private final static double BONO_ANITGUEDAD_TENIENTE = 0.03;

    /**
     * Construye un tripulante con rango de Teniente.
     *
     * @param nombre Nombre del teniente.
     * @param antiguedad Años de servicio del teniente.
     * @pre nombre != null && !nombre.trim().isEmpty() && antiguedad >= 0
     * @post Se inicializa el Teniente con sueldo base 400 y bono de antigüedad 0.03.
     */
    public Teniente(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_TENIENTE, BONO_ANITGUEDAD_TENIENTE);
    }

    /**
     * Calcula el sueldo total del teniente aplicando su bonificación por antigüedad.
     *
     * @return Sueldo calculado mediante sueldoBase * (1 + bonoAntiguedad * antiguedad).
     * @pre getAntiguedad() >= 0
     * @post Retorna el sueldo total sin modificar el estado del objeto.
     */
    @Override 
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad());
    }

    /**
     * Devuelve la representación textual del teniente incluyendo su cargo.
     *
     * @return Cadena con el formato "<nombre> --- CARGO: Teniente".
     * @pre true
     * @post Retorna la descripción formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return super.toString() + " --- CARGO: Teniente";
    }

    @Override
    public boolean esCapitan(){
        return false;
    }
}
