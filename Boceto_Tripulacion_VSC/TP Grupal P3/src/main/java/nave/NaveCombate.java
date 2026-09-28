package nave;

public class NaveCombate extends Nave{

    private final int canones;

    public NaveCombate(Asistente ac,Warp motor,int canones){
        super(ac,motor);
        this.canones = canones;
    }

    public double getCanones() {
        return canones;
    }
}