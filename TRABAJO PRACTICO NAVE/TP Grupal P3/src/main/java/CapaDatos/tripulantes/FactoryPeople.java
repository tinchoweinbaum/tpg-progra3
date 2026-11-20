package CapaDatos.tripulantes;
import CapaDatos.exceptions.*;

/**
 * Fábrica encargada de instanciar CapaDatos.tripulantes combinando su cargo base (Capitan, Consejero, Alferez, Teniente)
 * con su decorador de especie de origen (Terricola, Marciano, Vulcano).
 * <p><b>Invariante de clase:</b> true</p>
 */
public class FactoryPeople {
    public final static String CAPITAN = "CAPITAN";
    public final static String CONSEJERO = "CONSEJERO";
    public final static String ALFEREZ = "ALFEREZ";
    public final static String TENIENTE = "TENIENTE";
    public final static String TERRICOLA = "TERRICOLA";
    public final static String MARCIANO = "MARCIANO";
    public final static String VULCANO = "VULCANO";

    /**
     * Crea y retorna una instancia de Tripulante decorada según el cargo y origen especificados.
     *
     * @param cargo Puesto del tripulante ("CAPITAN", "CONSEJERO", "ALFEREZ", "TENIENTE").
     * @param origen Especie de origen del tripulante ("TERRICOLA", "MARCIANO", "VULCANO").
     * @param nombre Nombre identificador del tripulante.
     * @param antiguedad Años de servicio del tripulante.
     * @return Instancia de Tripulante envuelta en el EspecieDecorator correspondiente.
     * @throws CargoInvalidoException Si el cargo recibido no coincide con ninguno de los puestos válidos.
     * @throws OrigenInvalidoException Si el origen recibido no coincide con ninguna de las especies válidas.
     * @throws ErrorCreacionPersonajeException Si ocurre cualquier error de validación en los parámetros de creación.
     * @pre cargo != null && origen != null && nombre != null && !nombre.trim().isEmpty() && antiguedad >= 0
     * @post Retorna un Tripulante válido decorado con su especie de origen; si cargo u origen son inválidos, lanza la excepción correspondiente.
     */
    public static Tripulante creaPersonaje(String cargo,String origen,String nombre,int antiguedad) throws ErrorCreacionPersonajeException{
        Tripulante aux = null;
        switch (cargo.toUpperCase()) {
            case CAPITAN:
                aux = new Capitan(nombre,antiguedad);
                break;
            case ALFEREZ:
                aux = new Alferez(nombre,antiguedad);
                break;
            case CONSEJERO:
                aux = new Consejero(nombre,antiguedad);
                break;
            case TENIENTE:
                aux = new Teniente(nombre,antiguedad);
                break;
            default:
                throw new CargoInvalidoException("El cargo '" + cargo + "' no existe");
        }

        switch (origen.toUpperCase()) {
            case TERRICOLA:
                return new Terricola(aux);
            case MARCIANO:
                return new Marciano(aux);
            case VULCANO:
                return new Vulcano(aux);
            default:
                throw new OrigenInvalidoException("El origen '" + origen + "' no existe");
        }
    }
}