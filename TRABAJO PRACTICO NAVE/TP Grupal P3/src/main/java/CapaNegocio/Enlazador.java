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

    private Asistente asistente;
    private Visor visor;
    private final UsuariosDAO persistencia;

    /**
     * Constructor del enlazador que usa solamente persistencia para poder instanciarlo en el login, cuando no existen ni el asistente ni el visor.
     */
    public Enlazador(UsuariosDAO persistencia) {
        this.persistencia = persistencia;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public Visor getVisor() {
        return visor;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public void setVisor(Visor visor) {
        this.visor = visor;
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

    // --- Validacion y delegacion del DAO ---

    /**
     * <b>Pre: </b>Ninguna.<br>
     * <b>Post: </b>Ninguna.<br>
     * Si el usuario no existe o la contraseña es incorrecta arroja una excepcion correspondiente.<br>
     * En caso de que el usuario exista y la contraseña sea valida, simplemente retorna sin arrojar excepciones.
     *
     * @param username Nombre de usuario a buscar para validar si YA existe
     * @param contraIngresada Contraseña del usuario ingresado
     *
     * @throws UsuarioInexistenteException Si no se encontro el usuario ingresado.
     * @throws ContrasenaInvalidaException Si la contraseña ingresada no corresponde con el usuario que se ingreso.
     */
    public void validaLoginUsuario(String username, String contraIngresada) throws UsuarioInexistenteException, ContrasenaInvalidaException {
        Usuario usuarioRecuperado = this.buscarPorUsername(username);

        if(usuarioRecuperado == null)
            throw new UsuarioInexistenteException("No existe el usuario ingresado.");

        if (!usuarioRecuperado.getContra().equals(contraIngresada))
            throw new ContrasenaInvalidaException("La contraseña ingresada es incorrecta para el usuario indicado.");

        // Si no arroja ninguna excepcion es porque el usuario existe con su contraseña correcta, no hace falta devolver nada.
    }

    /**
     *<b>Pre: </b>Ninguna<br>
     * <b>Post: </b> Si no se arrojo ninguna excepcion, se registra al usuario en el sistema de persistencia.
     * @param username Username a registar.
     * @param contraIngresada Contraseña con la que se desea registrar al usuario.
     *
     * @throws UsuarioDuplicadoException Si el usuario que se quiere registrar ya existe, identificado por username.
     */
    public void validaRegistroUsuario(String username, String contraIngresada) throws UsuarioDuplicadoException, DatosRegistroInvalidosException{
        Usuario usuarioRecuperado = buscarPorUsername(username);
        if(usuarioRecuperado != null)
            throw new UsuarioDuplicadoException("Nombre de usuario ingresado ocupado, intente con otro.");

        if (username == null || username.isBlank() || contraIngresada == null || contraIngresada.isBlank()) {
            throw new DatosRegistroInvalidosException("Los datos ingresados para el registro son invalidos, intente nuevamente");
        }

        this.registrarUsuario(new Usuario(username, contraIngresada));
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return this.persistencia.buscarPorUsername(username);
    }

    @Override
    public ArrayList<Usuario> getUsuarios() {
        return this.persistencia.getUsuarios();
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        this.persistencia.registrarUsuario(usuario);
    }
}
