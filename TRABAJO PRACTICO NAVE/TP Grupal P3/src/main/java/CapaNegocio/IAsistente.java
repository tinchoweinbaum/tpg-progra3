package CapaNegocio;

import destinoytablero.Tablero;
import nave.Nave;
import motorwarp.MotorWarp;
import bitacora.Bitacora;
import exceptions.*;
import tripulantes.Tripulante;

public interface IAsistente {

    // Getters
    Tablero getTablero();
    Nave getNave();
    MotorWarp getMotor();
    Bitacora getBitacorasNave();

    // Gestión de combustible
    void cargaCombustibleNave(float cantCarga) throws CantCombustibleInvalidaException;
    void consumeCombustibleNave(float cantConsumida) throws CantCombustibleInvalidaException, NaveDestruidaException;
    void cargaCombustibleNaveFull() throws CantCombustibleInvalidaException;

    // Gestión de desgaste y reparación
    void aumentaDesgasteNave(float cantDesgaste) throws CantDesgasteInvalidaException, NaveDestruidaException;
    void reparaDesgasteNaveFull() throws CantDesgasteInvalidaException;

    // Gestión de energía
    void cargaEnergiaNave(float cantEnergia) throws CantEnergiaInvalidaException;
    void consumeEnergiaNave(float cantConsumida) throws CantEnergiaInvalidaException, NaveDestruidaException;
    void cargaEnergiaNaveFull() throws CantEnergiaInvalidaException;

    // Gestión de tripulación
    void agregaTripulante(Tripulante tripulante) throws ErrorAgregarTripulacionException;
    void eliminaTripulante(Tripulante t);

    // Tablero y movimiento
    void chequeaPosicionActual() throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException;
    void darTurnosTablero(int i) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException;
    void mueveNave() throws CantEnergiaInvalidaException, CombustibleInsuficienteException, CantCombustibleInvalidaException, NaveDestruidaException, CantDesgasteInvalidaException;
}
