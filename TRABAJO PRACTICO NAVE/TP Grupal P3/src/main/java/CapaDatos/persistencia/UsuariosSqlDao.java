package CapaDatos.persistencia;
import CapaDatos.exceptions.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementación concreta de UsuariosDAO que gestiona la CapaDatos.persistencia de usuarios
 * utilizando una base de datos relacional embebida SQLite.
 * <p><b>Invariante de clase:</b> URL != null && !URL.isEmpty()</p>
 */
public class UsuariosSqlDao implements UsuariosDAO{
    private static final String URL = "jdbc:sqlite:usuarios.db";

    /**
     * Constructor que establece la conexión con la base de datos SQLite e inicializa
     * la tabla {@code usuarios} si aún no existe.
     *
     * @throws DAOAuxiliarException Si ocurre un error SQL al conectar o crear la tabla en la base de datos.
     * @pre El driver JDBC de SQLite está disponible en el classpath.
     * @post La tabla {@code usuarios} queda creada y lista para recibir consultas en la base de datos local.
     */
    public UsuariosSqlDao() throws DAOAuxiliarException {
        // Inicialización de la base de datos en el constructor, para que el modelo pueda acceder a la db.

        String queryInit = "CREATE TABLE IF NOT EXISTS usuarios (" + // Esta query se encarga de crear la base de datos con su única tabla si no existe previamente.
                "username TEXT PRIMARY KEY, " +
                "password TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            stmt.execute(queryInit);
        } catch (SQLException e) {
            throw new DAOAuxiliarException("Error en la incialización de la base de datos.");
        }
    }

    /**
     * Consulta la base de datos SQLite para obtener el usuario cuyo {@code username} coincida con el parámetro recibido.
     *
     * @param username Nombre de usuario a buscar en la base de datos.
     * @return El objeto Usuario si existe en la tabla {@code usuarios}, o null si no se encuentra registrado.
     * @throws DatosRegistroInvalidosException Si ocurre un error SQL al consultar la base de datos local.
     * @pre username != null && la base de datos fue inicializada correctamente.
     * @post No modifica los registros de la base de datos y retorna el Usuario hallado o null si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) throws DatosRegistroInvalidosException {
        String query = "SELECT * FROM usuarios WHERE username = ?";
        Usuario usuarioRet = null;

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);

            try (ResultSet queryRes = pstmt.executeQuery()) {
                if (queryRes.next()) { // Usamos 'if' en vez de 'while' porque username es PRIMARY KEY (hay como máximo 1 fila)
                    String usernameAct = queryRes.getString("username");
                    String passwordAct = queryRes.getString("password");
                    usuarioRet = new Usuario(usernameAct, passwordAct);
                }
            }
        } catch (SQLException e) {
            throw new DatosRegistroInvalidosException("Error al buscar el usuario en la base de datos local.");
        }

        return usuarioRet;
    }

    /**
     * Recupera todos los registros de la tabla {@code usuarios} de la base de datos SQLite.
     *
     * @return ArrayList con todos los objetos Usuario almacenados (vacía si no hay registros).
     * @throws DAOAuxiliarException Si ocurre un error SQL al consultar los usuarios en la base de datos local.
     * @pre La base de datos fue inicializada correctamente.
     * @post No modifica los registros de la base de datos y retorna la lista de usuarios recuperados.
     */
    @Override
    public ArrayList<Usuario> getUsuarios() throws DAOAuxiliarException {
        String query = "SELECT * FROM usuarios";
        ArrayList<Usuario> listaRet = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement(); ResultSet queryRes = stmt.executeQuery(query)) {
            while (queryRes.next()){ // .next es una funcion booleana que devuelve si existe el próximo elemento del resultSet.
                String usernameAct = queryRes.getString("username");
                String passwordAct = queryRes.getString("password");
                Usuario u = new Usuario(usernameAct, passwordAct);
                listaRet.add(u);
            }
        } catch (SQLException e) {
            throw new DAOAuxiliarException("Error al consultar los usuarios de la base de datos local.");
        }
        return listaRet;
    }

    /**
     * Inserta un nuevo registro de usuario en la tabla {@code usuarios} de la base de datos SQLite.
     *
     * @param usuario Objeto Usuario válido a persistir.
     * @throws DAOAuxiliarException Si ocurre un error SQL al insertar el usuario en la base de datos local.
     * @pre usuario != null && usuario.getUsername() != null && usuario.getContra() != null && buscarPorUsername(usuario.getUsername()) == null
     * @post Se inserta la fila (username, password) en la tabla {@code usuarios} de la base de datos.
     */
    @Override
    public void registrarUsuario(Usuario usuario) throws DAOAuxiliarException {
        String query = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement pstmt = conn.prepareStatement(query);){
            pstmt.setString(1, usuario.getUsername()); // Asigna valores a los ?
            pstmt.setString(2, usuario.getContra());

            pstmt.executeUpdate(); // Ejecuta la query con los valores reemplazados en los ?
        }
        catch (SQLException e){
            throw new DAOAuxiliarException("Error al insertar un usuario en la base de datos local.");
        }
    }
}
