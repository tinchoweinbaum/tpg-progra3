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
     * Constructor de la Bitácora de la nave
     * <b>post:</b>La lista de registros queda inicializada y vacia
     */
    
    public Bitacora() {
        this.registros = new ArrayList<>();
        assert invariant();
    }


    /**
     * Agrega de manera ordenada por fecha un nuevo evento a la bitacora
     * Metodo asistido por IA
     * 
     * <b>pre:</b> registro != null
     * <b>post:</b> Se incrementa la cantidad de registros guardados y se mantiene ordenada por fecha
     * 
     * @param registro el objeto de registro a guardar.
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
        
        assert invariant();
    }

    public List<RegistroBitacora> getRegistros() {
        return registros;
    }

}
