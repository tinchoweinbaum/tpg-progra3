package CapaNegocio;

import asistentes.Asistente;
import bitacora.Bitacora;
import destinoytablero.Posicion;
import destinoytablero.Tablero;
import exceptions.*;
import motorwarp.MotorWarp;
import nave.Nave;
import persistencia.Usuario;
import persistencia.UsuariosDAO;
import tripulantes.Tripulante;
import visorParalelo.Visor;

import java.util.ArrayList;

public class Enlazador implements IAsistente, IVisor, UsuariosDAO {

    private final Asistente asistente;
    private final Visor visor;

    public Enlazador(Asistente asistente, Visor visor){
        this.asistente = asistente;
        this.visor = visor;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public Visor getVisor() {
        return visor;
    }

    // --- Delegación de métodos al asistente ---
    @Override
    public Tablero getTablero() {
        return getAsistente().getTablero();
    }

    @Override
    public Nave getNave() {
        return getAsistente().getNave();
    }

    @Override
    public MotorWarp getMotor() {
        return getAsistente().getMotor();
    }

    @Override
    public Bitacora getBitacorasNave() {
        return getAsistente().getBitacorasNave();
    }

    @Override
    public void cargaCombustibleNave(float cantCarga) throws CantCombustibleInvalidaException {
        getAsistente().cargaCombustibleNave(cantCarga);
    }

    @Override
    public void consumeCombustibleNave(float cantConsumida) throws CantCombustibleInvalidaException, NaveDestruidaException {
        getAsistente().consumeCombustibleNave(cantConsumida);
    }

    @Override
    public void cargaCombustibleNaveFull() throws CantCombustibleInvalidaException {
        getAsistente().cargaCombustibleNaveFull();
    }

    @Override
    public void aumentaDesgasteNave(float cantDesgaste) throws NaveDestruidaException, CantDesgasteInvalidaException {
        getAsistente().aumentaDesgasteNave(cantDesgaste);
    }


    @Override
    public void reparaDesgasteNaveFull() throws CantDesgasteInvalidaException {
        getAsistente().reparaDesgasteNaveFull();
    }

    @Override
    public void cargaEnergiaNave(float cantEnergia) throws CantEnergiaInvalidaException {
        getAsistente().cargaEnergiaNave(cantEnergia);
    }

    @Override
    public void consumeEnergiaNave(float cantConsumida) throws NaveDestruidaException, CantEnergiaInvalidaException {
        getAsistente().consumeEnergiaNave(cantConsumida);
    }

    @Override
    public void cargaEnergiaNaveFull() throws CantEnergiaInvalidaException {
        getAsistente().cargaEnergiaNaveFull();
    }

    @Override
    public void agregaTripulante(Tripulante tripulante) throws ErrorAgregarTripulacionException {
        getAsistente().agregaTripulante(tripulante);
    }

    @Override
    public void eliminaTripulante(Tripulante t) {
        getAsistente().eliminaTripulante(t);
    }

    @Override
    public void chequeaPosicionActual() throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException {
        getAsistente().chequeaPosicionActual();
    }

    @Override
    public void darTurnosTablero(int i) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException {
        getAsistente().darTurnosTablero(i);
    }

    @Override
    public void mueveNave() throws CantEnergiaInvalidaException, CantCombustibleInvalidaException, NaveDestruidaException, CantDesgasteInvalidaException {
        getAsistente().mueveNave();
    }

    // --- Delegación de métodos al visor paralelo ---
    @Override
    public void actualizaBitacoraParalela(String text) {
        getVisor().actualizaBitacoraParalela(text);
    }

    @Override
    public void nuevaGeneradora() {
        getVisor().nuevaGeneradora();
    }

    @Override
    public void nuevaConsumidora() {
        getVisor().nuevaConsumidora();
    }

    @Override
    public void eliminarGeneradora() {
        getVisor().eliminarGeneradora();
    }

    @Override
    public void eliminarConsumidora() {
        getVisor().eliminarConsumidora();
    }

    @Override
    public void cambiarTope(int t) {
        getVisor().cambiarTope(t);
    }

    // --- Delegación de métodos al DAO ---


    @Override
    public Usuario buscarPorUsername(String username) {
        return null;
    }

    @Override
    public ArrayList<Usuario> getUsuarios() {
        return null;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {

    }
}
