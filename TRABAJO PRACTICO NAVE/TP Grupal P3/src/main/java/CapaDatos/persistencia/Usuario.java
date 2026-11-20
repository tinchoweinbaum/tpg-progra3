package CapaDatos.persistencia;

/**
 * Objeto de dominio (POJO) que representa las credenciales de un usuario registrado en el sistema.
 * <p><b>Invariante de clase:</b> username != null && !username.trim().isEmpty() && contra != null && !contra.trim().isEmpty()</p>
 */
public class Usuario {
    private String username;
    private String contra;

    /**
     * Construye una nueva instancia de Usuario con su nombre de usuario y contraseña.
     *
     * @param username Nombre identificador del usuario.
     * @param contra Contraseña asociada al usuario.
     * @pre username != null && !username.trim().isEmpty() && contra != null && !contra.trim().isEmpty()
     * @post getUsername().equals(username) && getContra().equals(contra)
     */
    public Usuario(String username, String contra) {
        this.username = username;
        this.contra = contra;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
