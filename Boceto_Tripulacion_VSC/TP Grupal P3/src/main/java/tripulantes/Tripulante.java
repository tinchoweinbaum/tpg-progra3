package tripulantes;

public abstract class Tripulante {
    
    protected final String nombre;
    protected int antiguedad;
    protected double sueldo = 0;

    public Tripulante(String nom,int ant){
        super();
        this.nombre = nom;
        this.antiguedad = ant;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int getAntiguedad(){
        return this.antiguedad;
    }

    public void aumentaAntiguedad(){
        this.antiguedad += 1;
    }
    
    public double getSueldo(){
        return this.sueldo;
    }

}
