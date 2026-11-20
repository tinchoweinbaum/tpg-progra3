package CapaDatos.nave;
import CapaDatos.exceptions.*;

/**
 * Fábrica encargada de instanciar subclases concretas de Nave utilizando el patrón Factory Method.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class FactoryNaves {

    public final static String TIPO_CARGUERO = "TIPO_CARGUERO";
    public final static String TIPO_EXPLORADORA = "TIPO_EXPLORADORA";
    public final static String TIPO_COMBATE = "TIPO_COMBATE";

    /**
     * Crea y retorna una instancia concreta de Nave según el identificador de tipo recibido.
     *
     * @param tipoNave Identificador del tipo de CapaDatos.nave ("TIPO_CARGUERO", "TIPO_EXPLORADORA", "TIPO_COMBATE").
     * @return Instancia concreta de Nave correspondiente al tipo solicitado.
     * @throws TipoNaveInvalidoException Si tipoNave no coincide con ninguno de los tipos válidos.
     * @pre tipoNave != null && !tipoNave.trim().isEmpty()
     * @post Retorna una nueva instancia de NaveCarguero, NaveCombate o NaveExploradora según tipoNave; si no es válido, lanza TipoNaveInvalidoException.
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
                    throw new TipoNaveInvalidoException("Tipo de CapaDatos.nave inválido: " + tipoNave);
            }
    }
}