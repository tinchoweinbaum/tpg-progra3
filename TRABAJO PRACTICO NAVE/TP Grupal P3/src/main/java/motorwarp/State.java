package motorwarp;

import asistentes.Asistente;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;

public interface State {
    void disponible();
    void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException;
    void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException;
    void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException;
}
