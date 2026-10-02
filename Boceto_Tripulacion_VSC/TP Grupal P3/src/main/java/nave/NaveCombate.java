package nave;

public class NaveCombate extends Nave{

    private final int canones;

    public NaveCombate(int canones){
        super();
        this.canones = canones;
        this.setCombustible(80);
        this.setEnergia(100);
    }

    public double getCanones() {
        return canones;
    }
}