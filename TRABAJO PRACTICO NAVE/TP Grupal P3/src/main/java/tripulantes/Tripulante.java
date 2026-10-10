package tripulantes;

public abstract class Tripulante {

    protected final String nombre;
    protected int antiguedad;
    protected final double sueldoBase;
    protected final double bonoAntiguedad;

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

    @Override
    public String toString(){
        return this.getNombre();
    }

    public void aumentaAntiguedad() {
        this.antiguedad += 1;
    }

    public abstract double getSueldo();


    abstract public boolean esCapitan();
}