package CapaDatos.motorwarp;

import CapaDatos.asistentes.Asistente;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.*;

/**
 * Interfaz del patrón State que define las transiciones y operaciones posibles
 * según el estado actual del motor warp.
 * <p><b>Invariante de clase:</b> true</p>
 */
public interface State {
    void disponible();
    void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException;
    void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException, CantDesgasteInvalidaException;
    void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException;
}
