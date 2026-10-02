package bitacora;

import java.util.ArrayList;
import java.util.List;

public class Bitacora {
    private List<RegistroBitacora> registros;

    /**
     * Constructor de la Bitácora de la nave
     * <b>Post:</b>
     * - La lista de registros queda inicializada y vacía.
     */
    public Bitacora() {
        this.registros = new ArrayList<>();
    }

    /**
     * Agrega un nuevo evento a la bitácora
     * <b>Pre:</b>
     * - registro != null
     * <b>Post:</b>
     * - Se incrementa en 1 la cantidad de registros guardados
     * @param registro el objeto de registro a guardar.
     */
    public void agregarRegistro(RegistroBitacora registro) {
        assert registro != null : "El registro a agregar no puede ser nulo";
        this.registros.add(registro);
    }

    /**
     * Devuelve una copia de la lista de registros
     * <b>Post:</b>
     * - Se genera un nueva instancia de la lista de registros
     */
    public List<RegistroBitacora> getRegistros() {
        return new ArrayList<>(this.registros);
    }

    /**
     * Muestra todos los registros de la bitacora
     * <b>Post:</b>
     * - Se muestran en pantalla todos los registros de la bitacora
     */
    public void mostrarBitacora() {
        for (RegistroBitacora r : registros) {
            System.out.println(r);
        }
    }
}
