package tripulantes;

abstract class DecoradorCargo extends Tripulante{
    
    protected Tripulante tripulante;

    public DecoradorCargo(Tripulante tripulante){
        super(tripulante.getNombre(),tripulante.getAntiguedad());
        this.tripulante = tripulante;
    }

    abstract public void setSueldo();

    public Tripulante getTripulante(){
        return this.tripulante;
    }

}
