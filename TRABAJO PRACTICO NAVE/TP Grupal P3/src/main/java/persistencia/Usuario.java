package persistencia;

// dto???? no tiene comportamiento de por si esta clase.
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

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", contra='" + contra + '\'' +
                '}';
    }
}
