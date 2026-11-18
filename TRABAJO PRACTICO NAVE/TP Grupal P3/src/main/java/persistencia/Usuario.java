package persistencia;

// No se crea una clase UsuarioDTO porque el objeto Usuario ya es P.O.J.O
public class Usuario {
    private String username;
    private String contra;

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
