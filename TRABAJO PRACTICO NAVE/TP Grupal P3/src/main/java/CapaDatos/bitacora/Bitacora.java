package CapaDatos.bitacora;

import CapaDatos.destinoytablero.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Colección observable que almacena los registros de eventos de la CapaDatos.nave ordenados cronológicamente.
 * <p><b>Invariante de clase:</b> registros != null && para todo i en [0, registros.size()-2]: registros.get(i) != null && registros.get(i+1) != null && registros.get(i).compareTo(registros.get(i+1)) <= 0</p>
 */
public class Bitacora extends Observable {
    private List<RegistroBitacora> registros;

    
    private boolean invariant(){
        if (this.registros == null) // Lista inexistente
            return false;
        else{
            for (int i = 0; i < this.registros.size() - 1; i++) {
                RegistroBitacora actual = this.registros.get(i);
                RegistroBitacora siguiente = this.registros.get(i + 1);

               if (actual == null || siguiente == null) // No hay siguiente
                return false;
               
               if (actual.compareTo(siguiente) > 0) // Primer registro mas reciente que el segundo
                return false;                
            }  
        return true;
        }
    }

    /**
     * Constructor de la bitácora de la CapaDatos.nave.
     *
     * @pre true
     * @post La lista de registros queda inicializada y vacía (registros != null && registros.isEmpty()).
     */
    public Bitacora() {
        this.registros = new ArrayList<>();
        assert invariant();
    }

    /**
     * Agrega de manera ordenada por fecha un nuevo registro a la bitácora y notifica a los observadores.
     *
     * @param registro El objeto de registro a guardar.
     * @pre registro != null
     * @post registros.contains(registro), la cantidad de registros se incrementa en 1, se mantiene el orden cronológico y se notifica con el evento "BITACORA".
     */
    public void agregarRegistro(RegistroBitacora registro) {
        assert registro != null : "El registro a agregar no puede ser nulo";
        assert invariant();
                
        int pos = Collections.binarySearch(this.registros, registro);
        int indiceInsercion;

        //Si la busqueda binaria no encuentra un elemento devuelve su presunta posicion - 1
        if (pos < 0){ // Si no lo encuentra, lo que idealmente pasaria siempre, calculamos el indice en base al retorno
            indiceInsercion = -pos-1;
        } else {
            // Avanza hasta pasar todos los registros que tengan exactamente la misma fecha
            while (pos < this.registros.size() && this.registros.get(pos).compareTo(registro) == 0) {
                pos++;
            }
            indiceInsercion = pos;
        }
        this.registros.add(indiceInsercion, registro);
        notificarObservadores("BITACORA");

        assert invariant();
    }

    /**
     * Obtiene la lista de registros almacenados en la bitácora.
     *
     * @return Lista de RegistroBitacora ordenada por fecha.
     * @pre true
     * @post Retorna la referencia a la lista de registros sin modificar el estado de la bitácora.
     */
    public List<RegistroBitacora> getRegistros() {
        return registros;
    }

}
