package CapaNegocio;

import CapaDatos.asistentes.Asistente;
import CapaDatos.bitacora.Bitacora;
import CapaDatos.destinoytablero.Tablero;
import CapaDatos.exceptions.*;
import CapaDatos.motorwarp.MotorWarp;
import CapaDatos.nave.Nave;
import CapaDatos.persistencia.*;
import CapaDatos.tripulantes.Tripulante;
import CapaDatos.visorParalelo.Visor;
import java.util.ArrayList;

/**
 * Clase fachada y mediadora (Facade) de la capa de negocio que unifica y delega las operaciones
 * hacia el Asistente de navegación, el Visor paralelo y la capa de CapaDatos.persistencia (UsuariosDAO),
 * encargándose además de las validaciones de negocio de login y registro.
 * <p><b>Invariante de clase:</b> CapaDatos.persistencia != null</p>
 */
public class Enlazador implements IAsistente, IVisor, UsuariosDAO {

    private Asistente asistente;
    private Visor visor;
    private final UsuariosDAO persistencia;

    /**
     * Constructor del enlazador que inicializa únicamente la dependencia de CapaDatos.persistencia,
     * permitiendo su uso en la etapa de inicio de sesión y registro antes de crear la CapaDatos.nave y el visor.
     *
     * @param persistencia Implementación de UsuariosDAO para el acceso a datos de usuarios.
     * @pre CapaDatos.persistencia != null
     * @post this.CapaDatos.persistencia == CapaDatos.persistencia && getAsistente() == null && getVisor() == null
     */
    public Enlazador(UsuariosDAO persistencia) {
        this.persistencia = persistencia;
    }

    /**
     * Obtiene la instancia del asistente de navegación asociada al enlazador.
     *
     * @return Instancia de Asistente (puede ser null antes de iniciar la partida).
     * @pre true
     * @post Retorna asistente sin modificar el estado del enlazador.
     */
    public Asistente getAsistente() {
        return asistente;
    }

    /**
     * Obtiene la instancia del visor del universo paralelo asociada al enlazador.
     *
     * @return Instancia de Visor (puede ser null antes de iniciar la partida).
     * @pre true
     * @post Retorna visor sin modificar el estado del enlazador.
     */
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
     * Valida las credenciales de inicio de sesión contra la capa de CapaDatos.persistencia.
     * Si el usuario no existe o la contraseña no coincide, lanza la excepción de negocio correspondiente.
     *
     * @param username Nombre de usuario ingresado.
     * @param contraIngresada Contraseña ingresada.
     * @throws UsuarioInexistenteException Si no existe ningún usuario registrado con ese username.
     * @throws ContrasenaInvalidaException Si el usuario existe pero la contraseña ingresada no coincide.
     * @pre CapaDatos.persistencia != null
     * @post No modifica el estado del sistema; retorna normalmente si y solo si el usuario existe y su contraseña coincide con contraIngresada.
     */
    public void validaLoginUsuario(String username, String contraIngresada) throws UsuarioInexistenteException, ContrasenaInvalidaException {
        Usuario usuarioRecuperado = this.buscarPorUsername(username);

        if(usuarioRecuperado == null)
            throw new UsuarioInexistenteException("No existe el usuario ingresado.");

        if (!usuarioRecuperado.getContra().equals(contraIngresada))
            throw new ContrasenaInvalidaException("La contraseña ingresada es incorrecta para el usuario indicado.");

    }

    /**
     * Valida las reglas de negocio para el alta de un nuevo usuario (unicidad del nombre y campos no nulos/vacíos)
     * y, si son válidas, lo persiste en el sistema.
     *
     * @param username Nombre de usuario deseado.
     * @param contraIngresada Contraseña deseada.
     * @throws UsuarioDuplicadoException Si ya existe un usuario registrado con el mismo username.
     * @throws DatosRegistroInvalidosException Si username o contraIngresada son nulos o están en blanco.
     * @pre CapaDatos.persistencia != null
     * @post Si no se lanza excepción, el nuevo usuario queda persistido en el sistema mediante registrarUsuario().
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
