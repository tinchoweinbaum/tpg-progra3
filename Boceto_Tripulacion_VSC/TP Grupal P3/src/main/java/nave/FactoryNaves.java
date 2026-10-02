package nave;


public class FactoryNaves {
    /**
    *<b> pre: </b> El tipo de nave es un tipo valido
    *<b> post: </b> El tipo de nave fue creado
    *
    *@param tipoNave Es el tipo de la nave. tipoNave!=null,tipoNave!=""
    *@throws tipoNaveInvalidoException si el tipoNave no es valido arroja una
    *                                  excepcion indicando que no se puede crear el tipo
     */
    public static Nave getTipo(String tipoNave){// throws tipoNaveInvalidoException{
        //try {
            switch (tipoNave.toUpperCase()) {
                case "CARGUERO":
                    return new NaveCarguero();
                case "COMBATE":
                    return new NaveCombate();
                case "EXPLORADOR":
                    return new NaveExploradora();
                default:
                    return null;
                    //throw tipoNaveInvalidoException;
            }
        //}catch (tipoNaveInvalidoException e)
    }

}