package nave;

public class NaveExploradora extends Nave{

    public NaveExploradora(Asistente ac,Warp motor){
        super(ac,motor);
        this.setCombustible(60);
        this.setEnergia(80);
    }

}