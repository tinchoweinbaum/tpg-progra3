package persistencia;
import java.sql.*;
import java.util.ArrayList;
import org.sqlite.JDBC;



public class UsuariosSqlDao implements UsuariosDAO{
    private static final String URL = "jdbc:sqlite:usuarios.db";

    public UsuariosSqlDao() {
        // Inicialización de la base de datos en el constructor, para que el modelo pueda acceder a la db.

        String queryInit = "CREATE TABLE IF NOT EXISTS usuarios (" + // Esta query se encarga de crear la base de datos con su única tabla si no existe previamente.
                "username TEXT PRIMARY KEY, " +
                "password TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            stmt.execute(queryInit);
            System.out.println("Base de datos inicializada");
        } catch (SQLException e) {
            System.out.println("Error en la incialización de la base de datos.");
        }
    }

    @Override
    public Usuario buscarPorUsername(Usuario usuario) {
        return null;
    }

    /**
     * <b>Pre: </b>La base de datos existe y fue inicializada por el constructor de esta clase correctamente.<br>
     * <b>Post: </b>No se modifican los datos de la base de datos, solamente se leen.<br>
     *
     * @return Devuelve una lista de objetos Usuario que representan los usuarios registrados en la base de datos.
     */
    @Override
    public ArrayList<Usuario> getUsuarios() {
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
            System.out.println("Error al consultar los usuarios de la base de datos local.");
        }
        return listaRet;
    }

    /**
     * <b>Pre: </b>
     * <ul>
     *     <li>El usuario no existe previamente en la base de datos</li>
     *     <li>El usuario es un objeto válido (usuario != null)</li>
     * </ul>
     * <b>Post: </b>Se inserta al usuario en la base de datos.
     *
     * @param usuario Objeto usuario a agregar a la base de datos.
     */
    @Override
    public void registrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement pstmt = conn.prepareStatement(query);){
            pstmt.setString(1, usuario.getUsername()); // Asigna valores a los ?
            pstmt.setString(2, usuario.getContra());

            pstmt.executeUpdate(); // Ejecuta la query con los valores reemplazados en los ?
        }
        catch (SQLException e){
            System.out.println("Error al insertar un usuario en la base de datos local.");
        }
    }
}
