package bitacora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Un objeto que contiene una coleccion de los registros de la nave
 * <b>Invariante:</b>
 * -La lista siempre esta ordenada por fecha
 */
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
     * Agrega de manera ordenada por fecha un nuevo evento a la bitácora
     * <b>Pre:</b>
     * - registro != null
     * <b>Post:</b>
     * - Se incrementa en 1 la cantidad de registros guardados
     * - La lista se mantiene ordenada por fecha
     * @param registro el objeto de registro a guardar.
     */
    public void agregarRegistro(RegistroBitacora registro) {
        assert registro != null : "El registro a agregar no puede ser nulo";
        int pos = Collections.binarySearch(this.registros, registro);
        int indiceInsercion;

        //Si la busqueda binaria no encuentra un elemento devuelve su presunta posicion - 1
        if (pos < 0){ // Si no lo encuentra, lo que idealmente pasaria siempre, calculamos el indice en base al retorno
            indiceInsercion = -pos-1;
        } else { // Si por algun motivo la fecha es exacta, insertamos uno al lado del otro, sin importar el orden
            indiceInsercion = pos;
        }
        this.registros.add(indiceInsercion, registro);
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
