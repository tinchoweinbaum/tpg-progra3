package CapaDatos.tripulantes;

/**
 * Subclase concreta de Tripulante que representa el rango de Alférez.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Tripulante && sueldoBase == 200 && bonoAntiguedad == 0.005</p>
 */
public class Alferez extends Tripulante{
    private static final double SUELDO_BASE_ALFEREZ = 200;
    private static final double BONO_ANTIGUEDAD_ALFEREZ = 0.005;

    /**
     * Construye un tripulante con rango de Alférez.
     *
     * @param nombre Nombre del alférez.
     * @param antiguedad Años de servicio del alférez.
     * @pre nombre != null && !nombre.trim().isEmpty() && antiguedad >= 0
     * @post Se inicializa el Alférez con sueldo base 200 y bono de antigüedad 0.005.
     */
    public Alferez(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_ALFEREZ, BONO_ANTIGUEDAD_ALFEREZ);
    }

    /**
     * Devuelve la representación textual del alférez incluyendo su cargo.
     *
     * @return Cadena con el formato "<nombre> --- CARGO: Alferez".
     * @pre true
     * @post Retorna la descripción formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return super.toString() + " --- CARGO: Alferez";
    }

    /**
     * Calcula el sueldo total del alférez aplicando su bonificación por antigüedad.
     *
     * @return Sueldo calculado mediante sueldoBase * (1 + bonoAntiguedad * antiguedad).
     * @pre getAntiguedad() >= 0
     * @post Retorna el sueldo total sin modificar el estado del objeto.
     */
    @Override
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad());
    }

    @Override
    public boolean esCapitan(){
        return false;
    }
}
