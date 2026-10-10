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

    /**
     * Funcion booleana para determinar si un tripulante es capitan,
     * se usa para saber si la tripulacion tiene al menos un capitan en la flota
     *
     * @return Devuelve false ya que el tripulante no es un capitan
     */
    @Override
    public boolean esCapitan(){
        return this.tripulante.esCapitan();
    }
}
