package CapaDatos.tripulantes;

/**
 * Subclase concreta de Tripulante que representa el cargo de Consejero,
 * percibiendo un bono adicional por cada consejo brindado.
 * <p><b>Invariante de clase:</b> Mantiene el invariante de Tripulante && sueldoBase == 600 && bonoAntiguedad == 0.05 && consejosDados >= 0</p>
 */
public class Consejero extends Tripulante{
    private static final double SUELDO_BASE_CONSEJERO = 600;
    private static final double BONO_ANTIGUEDAD_CONSEJERO = 0.05;
    private static final double BONO_CONSEJO = 2;

    private int consejosDados = 0;

    /**
     * Construye un tripulante con cargo de Consejero.
     *
     * @param nombre Nombre del consejero.
     * @param antiguedad Años de servicio del consejero.
     * @pre nombre != null && !nombre.trim().isEmpty() && antiguedad >= 0
     * @post Se inicializa el Consejero con sueldo base 600, bono de antigüedad 0.05 y consejosDados == 0.
     */
    public Consejero(String nombre, int antiguedad){
        super(nombre, antiguedad, SUELDO_BASE_CONSEJERO, BONO_ANTIGUEDAD_CONSEJERO);
    }

    public int getConsejos(){
        return this.consejosDados;
    }

    /**
     * Registra un nuevo consejo brindado por el consejero incrementando su contador.
     *
     * @pre true
     * @post getConsejos() == old(getConsejos()) + 1
     */
    public void darConsejo(){
        this.consejosDados += 1;
    }

    /**
     * Calcula el sueldo total del consejero considerando su antigüedad y la cantidad de consejos dados.
     *
     * @return Sueldo calculado mediante sueldoBase * (1 + bonoAntiguedad * antiguedad) + consejosDados * BONO_CONSEJO.
     * @pre getAntiguedad() >= 0 && getConsejos() >= 0
     * @post Retorna el sueldo total sin modificar el estado del objeto.
     */
    @Override 
    public double getSueldo(){
        return this.sueldoBase * (1 + this.bonoAntiguedad * this.getAntiguedad()) + this.consejosDados * BONO_CONSEJO;
    }

    /**
     * Devuelve la representación textual del consejero incluyendo su cargo.
     *
     * @return Cadena con el formato "<nombre> --- CARGO: Consejero".
     * @pre true
     * @post Retorna la descripción formateada sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return super.toString() + " --- CARGO: Consejero";
    }

    @Override
    public boolean esCapitan(){
        return false;
    }
}
