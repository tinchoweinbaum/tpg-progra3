package motorwarp;

public interface State {
    void disponible();
    void preparaSalto();
    void saltoWarp();
    void enfriamiento();
}
