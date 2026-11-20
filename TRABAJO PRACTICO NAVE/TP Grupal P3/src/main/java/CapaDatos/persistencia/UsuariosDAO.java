package CapaDatos.persistencia;
import java.util.ArrayList;

/**
 * Interfaz del patrón DAO que define las operaciones atómicas de CapaDatos.persistencia (CRUD básico)
 * para la entidad Usuario, delegando las validaciones de negocio a la capa correspondiente.
 * <p><b>Invariante de clase:</b> true</p>
 */
public interface UsuariosDAO {
    Usuario buscarPorUsername(String username);
    ArrayList<Usuario> getUsuarios();
    void registrarUsuario(Usuario usuario);
}
