package nave;
import exceptions.*;

public class FactoryNaves {

    public final static String TIPO_CARGUERO = "TIPO_CARGUERO";
    public final static String TIPO_EXPLORADORA = "TIPO_EXPLORADORA";
    public final static String TIPO_COMBATE = "TIPO_COMBATE";

    /**
     * Crea una nave del tipo indicado por el sistema
     * 
    * <b>pre:</b> El nombre del tipo de nave es valido, tipoNave != NULL o tipoNave != " ".
    * <b>post:</b> Se crea una nave del tipoNave ingresado por paramtro. 
    *
    * @param tipoNave Es el identificador de tipo de la nave a crear.
    * @throws TipoNaveInvalidoException si el tipoNave no es valido arroja una excepcion indicando que no se puede crear el tipo.
    * @return Devuelve una instancia del tipo de nave seleccionada
    */
    
    public static Nave getTipo(String tipoNave) throws TipoNaveInvalidoException{
            switch (tipoNave.toUpperCase()) {
                case TIPO_CARGUERO:
                    return new NaveCarguero();
                case TIPO_COMBATE:
                    return new NaveCombate();
                case TIPO_EXPLORADORA:
                    return new NaveExploradora();
                default:
                    throw new TipoNaveInvalidoException("Tipo de nave inválido: " + tipoNave);
            }
    }

}