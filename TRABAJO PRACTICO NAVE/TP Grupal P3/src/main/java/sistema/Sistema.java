package sistema;

import asistentes.Asistente;
import exceptions.*;
import misiones.*;
import nave.*;
import tripulantes.*;


public class Sistema {

    private static Sistema _instance = null;

    private Sistema(){
        super();
    }

    /**
    * Constructor del sistema utilizando el Patron Singleton para una unica instancia.
    * 
    * <b>post:</b>  Se crea un sistema o se pasa su referencia si ya existia uno.
    * 
    * @return Devuelve la referencia del sistema
    */

    
    public static Sistema getInstance(){
        if(_instance == null)
            _instance = new Sistema();
        
        return _instance;
    }

    /** Asistido con Inteligencia artificial.
     * Función encapsulada para detener un hilo de ejecución, si recibe un valor negativo crashea el programa.
     * @param milisegundos tiempo en milisegundos.
     */
    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
