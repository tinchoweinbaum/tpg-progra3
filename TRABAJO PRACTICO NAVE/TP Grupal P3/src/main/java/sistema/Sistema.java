package sistema;

import asistentes.Asistente;
import exceptions.TipoNaveInvalidoException;
import nave.*;


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
    * Metodo que instancia una nave y un asistente
    * 
    * <b>pre:</b> El nombre del tipo de nave es valido, tipoNave != NULL o tipoNave != " ".
    * <b>post:</b> (try) Se le asigna al sistema un tipo de nave y un asistente.
    *              (catch) No se le asigna nave ni asistente al sistema y se lanza una excepcion. 
    * 
    * 
    * @param tipoNave Es el identificador de tipo de la nave a crear.
    * @return Retorna un asistente null si el tipo de nave es invalido o el asistente con su nave referenciada.
    */

    public Asistente inicio(String tipoNave){
        
        Asistente asistente = null;
        Nave nave = null;
           
        try{
            nave = FactoryNaves.getTipo(tipoNave);
            asistente = new Asistente(nave);
            
        }catch(TipoNaveInvalidoException e){
            System.out.println(e.getMessage()); 
        }

        
        
        return asistente;
            
        
    }

}
