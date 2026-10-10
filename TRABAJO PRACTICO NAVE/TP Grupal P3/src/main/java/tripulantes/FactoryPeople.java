package tripulantes;
import exceptions.*;

public class FactoryPeople {
    /**
     * Crea una tripulante del tipo indicado por el usuario
     *<b>Pre:</b>El tipo de cargo y de origen son tipos válidos.Antiguedad es un valor > 0.
     *<b>Post:</b>El tripulante fue creado
     *
     *@param cargo Es el puesto del personaje. cargo!=null,cargo!=""
     *@param nombre Es el nombre del personaje. nombre!=null,nombre!=""
     *@param origen Es el origen del personaje. origen!=null,origen!=""
     *@param antiguedad Es la cantidad de anos en el cargo. antiguedad>=0,antiguedad!=null
     *@throws CargoInvalidoException es arrojada si no puede crearse el personaje
     */
    public static Tripulante creaPersonaje(String cargo,String origen,String nombre,int antiguedad) throws ErrorCreacionPersonajeException{
        Tripulante aux = null;
        switch (cargo.toUpperCase()) {
            case "CAPITAN":
                aux = new Capitan(nombre,antiguedad);
                break;
            case "ALFEREZ":
                aux = new Alferez(nombre,antiguedad);
                break;
            case "CONSEJERO":
                aux = new Consejero(nombre,antiguedad);
                break;
            case "TENIENTE":
                aux = new Teniente(nombre,antiguedad);
                break;
            default:
                throw new CargoInvalidoException("El cargo '" + cargo + "' no existe");
        }

        switch (origen.toUpperCase()) {
            case "TERRICOLA":
                return new Terricola(aux);
            case "MARCIANO":
                return new Marciano(aux);
            case "VULCANO":
                return new Vulcano(aux);
            default:
                throw new OrigenInvalidoException("El origen '" + origen + "' no existe");
        }
    }
}