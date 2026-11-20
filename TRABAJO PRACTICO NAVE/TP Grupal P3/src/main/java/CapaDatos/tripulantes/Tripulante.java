package CapaDatos.tripulantes;

/**
 * Clase abstracta base que modela a un integrante de la tripulación de la CapaDatos.nave,
 * almacenando su nombre, antigüedad, sueldo base y bonificación por antigüedad.
 * <p><b>Invariante de clase:</b> nombre != null && !nombre.trim().isEmpty() && antiguedad >= 0 && sueldoBase >= 0 && bonoAntiguedad >= 0</p>
 */
public abstract class Tripulante {

    protected final String nombre;
    protected int antiguedad;
    protected final double sueldoBase;
    protected final double bonoAntiguedad;

    /**
     * Constructor base de un tripulante.
     *
     * @param nom Nombre del tripulante.
     * @param ant Antigüedad en años del tripulante.
     * @param sueldoBase Remuneración base asociada a su cargo.
     * @param bonoAntiguedad Porcentaje o factor de incremento por año de antigüedad.
     * @pre nom != null && !nom.trim().isEmpty() && ant >= 0 && sueldoBase >= 0 && bonoAntiguedad >= 0
     * @post getNombre().equals(nom) && getAntiguedad() == ant && getSueldoBase() == sueldoBase && getBonoAntiguedad() == bonoAntiguedad
     */
    public Tripulante(String nom, int ant, double sueldoBase, double bonoAntiguedad) {
        super();
        this.nombre = nom;
        this.antiguedad = ant;
        this.sueldoBase = sueldoBase;
        this.bonoAntiguedad = bonoAntiguedad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getAntiguedad() {
        return this.antiguedad;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public double getBonoAntiguedad() {
        return bonoAntiguedad;
    }

    /**
     * Devuelve la representación textual básica del tripulante (su nombre).
     *
     * @return Cadena con el nombre del tripulante.
     * @pre true
     * @post Retorna nombre sin modificar el estado del objeto.
     */
    @Override
    public String toString(){
        return this.getNombre();
    }

    /**
     * Incrementa en una unidad los años de antigüedad del tripulante.
     *
     * @pre true
     * @post getAntiguedad() == old(getAntiguedad()) + 1
     */
    public void aumentaAntiguedad() {
        this.antiguedad += 1;
    }

    public abstract double getSueldo();

    /**
     * Indica si el tripulante ocupa el cargo de Capitán de la CapaDatos.nave.
     *
     * @return true si el tripulante es Capitán; false en caso contrario.
     * @pre true
     * @post Retorna el booleano correspondiente sin modificar el estado del tripulante.
     */
    abstract public boolean esCapitan();
}