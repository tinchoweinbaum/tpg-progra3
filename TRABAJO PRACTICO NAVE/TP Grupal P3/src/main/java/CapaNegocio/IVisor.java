package CapaNegocio;

public interface IVisor {

    void actualizaBitacoraParalela(String text);

    void nuevaGeneradora();

    void nuevaConsumidora();

    void eliminarGeneradora();

    void eliminarConsumidora();

    void cambiarTope(int t);
}
