package CapaDatos.tripulantes;

/**
 * Subclase concreta de Tripulante que representa el rango de Capitán de la CapaDatos.nave.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Tripulante && sueldoBase == 1000 && bonoAntiguedad == 0.2</p>
 */
public class Capitan extends Tripulante{
    private static final double SUELDO_BASE_CAPITAN = 1000;
    private static final double BONO_ANTIGUEDAD_CAPITAN = 0.2;

    /**
     * Construye un tripulante con rango de Capitán.
     *
     * @param nombre Nombre del capitán.
     * @param antiguedad Años de servicio del capitán.
     * @pre nombre != null && !nombre.trim().isEmpty() && antiguedad >= 0
     * @post Se inicializa el Capitán con sueldo base 1000 y bono de antigüedad 0.2.
     */
    public Capitan(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_CAPITAN, BONO_ANTIGUEDAD_CAPITAN);
    }

    /**
     * Calcula el sueldo total del capitán aplicando su bonificación por antigüedad.
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
        return true;
    }

    /**
     * Devuelve la representación textual del capitán incluyendo su cargo.
     *
     * @return Cadena con el formato "<nombre> --- CARGO: Capitan".
     * @pre true
     * @post Retorna la descripción formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return super.toString() + " --- CARGO: Capitan";
    }
}
