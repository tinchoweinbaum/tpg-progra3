package tripulantes;

public abstract class EspecieDecorator extends Tripulante{
    private Tripulante tripulante;

    public EspecieDecorator(Tripulante tripulante) {
        super(tripulante.getNombre(), tripulante.getAntiguedad(), tripulante.getSueldoBase(), tripulante.getBonoAntiguedad());
        this.tripulante = tripulante;
    }

    @Override
    public abstract double getSueldo(); // Sobreescribe getSueldo haciéndolo abstracto para poder usar las implementaciones de las clases hijas de esta.

    public Tripulante getTripulante(){
        return this.tripulante;
    }

    abstract public String getOrigen();

    //estos metodos ya los va a tener. para que los sobreescribis?
    // --- Métodos del tripulante decorado ---
//    @Override
//    public String getNombre() {
//        return this.tripulante.getNombre();
//    }
//    @Override
//    public int getAntiguedad() {
//        return this.tripulante.getAntiguedad();
//    }
//
//    @Override
//    public void aumentaAntiguedad() {
//        this.tripulante.aumentaAntiguedad();
//    }
//
//    @Override
//    public double getSueldoBase() {
//        return this.tripulante.getSueldoBase();
//    }
//
//    @Override
//    public double getBonoAntiguedad() {
//        return this.tripulante.getBonoAntiguedad();
//    }
}
