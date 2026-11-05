package persistencia;
import java.util.ArrayList;

public interface UsuariosDAO {
    public Usuario buscarPorUsername(Usuario usuario);
    public ArrayList<Usuario> getUsuarios();

}
