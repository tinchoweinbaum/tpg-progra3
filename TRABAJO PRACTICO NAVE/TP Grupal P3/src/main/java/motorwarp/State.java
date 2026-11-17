package motorwarp;

import asistentes.Asistente;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantDesgasteInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;

public interface State {
    void disponible();
    void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException;
    void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException, CantDesgasteInvalidaException;
    void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException;
}
