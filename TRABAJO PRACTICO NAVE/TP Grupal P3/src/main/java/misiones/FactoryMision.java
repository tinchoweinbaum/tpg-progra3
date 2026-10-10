package misiones;
import exceptions.*;

public class FactoryMision {

    /**
     * Crea una mision del tipo indicado por el sistema
     *
     * <b>pre:</b> El tipo de mision se considera valido, toma valores entre 1 y 3, tipoMision != NULL o tipoMision != " ".
     * <b>post:</b> Se crea una mision del tipoMision ingresado por paramtro.
     *
     * @param tipoMision Es el identificador de tipo de la mision a crear.
     * @throws TipoMisionInvalidoException si el tipoMision no es valido arroja una excepcion indicando que no se puede crear el tipo.
     *
     */

    public static Mision getTipo(int tipoMision) throws TipoMisionInvalidoException{
        switch (tipoMision) {
            case 1:
                return new Mision1(4,5,4);
            case 2:
                return new Mision2(4,5,4);
            case 3:
                return new Mision3(4,4);
            default:
                throw new TipoMisionInvalidoException("Tipo de mision inválido");
        }
    }

}