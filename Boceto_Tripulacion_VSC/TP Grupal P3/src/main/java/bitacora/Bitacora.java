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

    public List<RegistroBitacora> getRegistros() {
        return new ArrayList<>(this.registros); // Devuelve una copia para proteger la encapsulación
    }

    public void mostrarBitacora() {
        for (RegistroBitacora r : registros) {
            System.out.println(r);
        }
    }
}
