package motorwarp;

public interface State {
    void disponible();
    void preparaSalto(int tipoSalto);
    void saltoWarp();
    void enfriamiento();

    int getIdEstado();
}
