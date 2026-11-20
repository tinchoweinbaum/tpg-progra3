package CapaDatos.persistencia;
import CapaDatos.exceptions.DAOAuxiliarException;
import CapaDatos.exceptions.DatosRegistroInvalidosException;

import java.util.ArrayList;

/**
 * Interfaz del patrón DAO que define las operaciones atómicas de CapaDatos.persistencia (CRUD básico)
 * para la entidad Usuario, delegando las validaciones de negocio a la capa correspondiente.
 * <p><b>Invariante de clase:</b> true</p>
 */
public interface UsuariosDAO {
    Usuario buscarPorUsername(String username) throws DatosRegistroInvalidosException;
    ArrayList<Usuario> getUsuarios() throws DAOAuxiliarException;
    void registrarUsuario(Usuario usuario) throws DAOAuxiliarException;
}
