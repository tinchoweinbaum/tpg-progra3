package tripulantes;

abstract class DecoradorCargo extends Tripulante{
    
    private Tripulante tripulante;

    public DecoradorCargo(Tripulante tripulante){
        super(tripulante.getNombre(),tripulante.getAntiguedad());
        this.tripulante = tripulante;
    }


    @Override
    abstract public double getSueldo();

    public Tripulante getTripulante(){
        return this.tripulante;
    }

}
