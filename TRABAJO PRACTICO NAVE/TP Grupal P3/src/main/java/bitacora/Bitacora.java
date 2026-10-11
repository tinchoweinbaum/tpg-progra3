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
        } else { // Si por algun motivo la fecha es exacta, insertamos uno al lado del otro, sin importar el orden
            indiceInsercion = pos;
        }
        this.registros.add(indiceInsercion, registro);
        
        assert invariant();
    }

    /**
     * Muestra todos los registros de la bitacora
     * 
     * <b>post:</b> Se muestran en pantalla todos los registros de la bitacora
     * 
     */
    public void mostrarBitacora() {
        assert invariant();
        
        for (RegistroBitacora r : this.registros) {
            System.out.println(r);
        }
    }

    /**
     * Muestra los ultimos n registros de la bitacora en orden cronologico, si se piden mas que lo que se tienen se muestran todas.
     * <b>pre:</b>  n > 0
     * <b>post:</b>  Se muestran en pantalla los ultimos n registros de forma cronologica
     * 
     * @param n es la cantidad de registros a mostrar
     */
    
    public void mostrarNRegistros(int n){
        
        assert n > 0: "La cantidad de registros a mostrar debe ser positiva";
        assert invariant();
                
        int tamanio = this.registros.size();
        int i;

        // Empieza N posiciones antes del final y avanza hacia adelante.
        if (n > tamanio)
            n = tamanio;
        for (i = tamanio-n; i < tamanio; i += 1){
            System.out.println(this.registros.get(i));
        }
        
        assert invariant();
    }

    /**
     * Muestra todos los registros de tipo motor de la bitacora
     * <b>post:</b> Se muestran en pantalla todos los registros de tipo motor de la bitacora
     */
    
    public void mostrarRegistrosMotor(){
        assert invariant();
        
        for (RegistroBitacora r : this.registros){
            if (r.esRegistroMotor()){
                System.out.println(r);
            }
        }
    }

    /**
     * Muestra todos los registros de tipo error de la bitacora
     * <b>post:</b> Se muestran en pantalla todos los registros de tipo error de la bitacora
     */
    
    public void mostrarRegistrosError(){
        assert invariant();
        
        for (RegistroBitacora r : this.registros){
            if (r.esRegistroError()){
                System.out.println(r);
            }
        }
    }

    /**
     * Muestra todos los registros de tipo mision de la bitacora
     * <b>post:</b>  Se muestran en pantalla todos los registros de tipo mision de la bitacora
     */
    
    public void mostrarRegistrosMision(){
        assert invariant();
        
        for (RegistroBitacora r : this.registros){
            if (r.esRegistroMision()){
                System.out.println(r);
            }
        }
    }

    /**
     * Muestra todos los registros de tipo recursos de la bitacora
     * <b>post:</b> Se muestran en pantalla todos los registros de tipo recursos de la bitacora
     */
    public void mostrarRegistrosRecursos(){
        assert invariant();
        
        for (RegistroBitacora r : this.registros){
            if (r.esRegistroRecursos()){
                System.out.println(r);
            }
        }
    }
}
