package persistencia;
import java.util.ArrayList;

/**
 * La interfaz DAO contiene las operaciones MICRO del acceso a los datos, es el MODELO el que se encarga de las verificaciones y operaciones MACRO.
 * Acá va a haber unícamente operaciones atómicas.
 */
public interface UsuariosDAO {
    public Usuario buscarPorUsername(Usuario usuario);
    public ArrayList<Usuario> getUsuarios();
    public void registrarUsuario(Usuario usuario);
}
