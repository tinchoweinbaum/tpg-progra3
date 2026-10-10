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
    */
    
    public static Sistema getInstance(){
        if(_instance == null)
            _instance = new Sistema();
        
        return _instance;
    }
    
    /**
    * Metodo que instancia una nave y un asistente<br>
    * 
    * <b>pre:</b> El nombre del tipo de nave es valido, tipoNave != NULL && tipoNave != " ".<br>
    * <b>post:</b> (try) Se le asigna al sistema un tipo de nave y un asistente.<br>
    *              (catch) No se le asigna nave ni asistente al sistema y se lanza una excepcion.<br>
    * 
    * 
    * @param tipoNave Es el identificador de tipo de la nave a crear. Valores válidos (no es case sensitive):
     *                 <ul>
     *                  <li>CARGUERO</li>
     *                  <li>COMBATE</li>
     *                  <li>EXPLORADOR</li>
     *                 </ul>
    * @return Retorna un asistente null si el tipo de nave es invalido o el asistente con su nave referenciada.
    */

    public Asistente inicio(String tipoNave){
        Asistente asistente = null;
        try{
            Nave nave = FactoryNaves.getTipo(tipoNave);
            asistente = new Asistente(nave);
        }catch(TipoNaveInvalidoException e){
            System.out.println(e.getMessage()); 
        }
        return asistente;
    }

    /**
     * Metodo que crea un nuevo Tripulante en el Sistema
     * <b>pre:</b>
     * @param cargo "Capitan - Alferez - Consejero - Teniente", cargo!=null cargo!=""
     * @param origen "Terricola - Vulcano - Marciano", origen!=null origen!=""
     * @param nombre nombre!=null nombre!=""
     * @param antiguedad antiguedad!=null antiguedad>0
     * @return Un nuevo tripulante decorado
     */

    public Tripulante creaTripulante(String cargo, String origen, String nombre, int antiguedad){
        Tripulante personaje = null;
        try{
            personaje = FactoryPeople.creaPersonaje(cargo, origen, nombre, antiguedad);
        }catch(ErrorCreacionPersonajeException e){
            System.out.println(e.getMessage());
        }
        return personaje;
    }

    /**
     * Metodo que crea un nuevo tipo de mision valido
     * @param tipoMision {1 - 2 - 3}
     * @return Un nuevo objeto mision que luego le podra ser asignado un asistente
     */

    public Mision creaMision(int tipoMision){
        Mision misionNueva = null;
        try{
            misionNueva = FactoryMision.getTipo(tipoMision);
        }catch(TipoMisionInvalidoException e){
            System.out.println(e.getMessage());
        }
        return misionNueva;
    }

    /** Método asistido con Inteligencia artificial.
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
