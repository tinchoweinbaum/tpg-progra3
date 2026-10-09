package nave;
import exceptions.*;

public class FactoryNaves {
    
    /**
     * Crea una nave del tipo indicado por el sistema
     * 
    * <b>pre:</b> El nombre del tipo de nave es valido, tipoNave != NULL o tipoNave != " ".
    * <b>post:</b> Se crea una nave del tipoNave ingresado por paramtro. 
    *
    * @param tipoNave Es el identificador de tipo de la nave a crear.
    * @throws TipoNaveInvalidoException si el tipoNave no es valido arroja una excepcion indicando que no se puede crear el tipo.
    * 
    */
    
    public static Nave getTipo(String tipoNave) throws TipoNaveInvalidoException{
            switch (tipoNave.toUpperCase()) {
                case "CARGUERO":
                    return new NaveCarguero();
                case "COMBATE":
                    return new NaveCombate();
                case "EXPLORADOR":
                    return new NaveExploradora();
                default:
                    throw new TipoNaveInvalidoException("Tipo de nave inválido: " + tipoNave);
            }
    }

}