package motorwarp;
import bitacora.RegistroMotor;
import exceptions.EstadoInvalidoException;
import java.util.ArrayList;


public class MotorWarp {

    public static final int DISPONIBLE = 0;
    public static final int PREPARANDO_SALTO = 1;
    public static final int SALTO_WARP = 2;
    public static final int ENFRIAMIENTO = 3;
    private State estadoActual;
    private ArrayList<RegistroMotor> registrosTransiciones = new  ArrayList<>();
    
    public MotorWarp () {
        this.estadoActual = new Disponible(this);
    }

    public ArrayList<RegistroMotor> actualizaRegistrosMotor(){
        ArrayList<RegistroMotor> auxiliar = registrosTransiciones;
        registrosTransiciones = new ArrayList<>();
        return auxiliar;
    }


    public void llamaEstado(int i) throws EstadoInvalidoException {
        switch (i){
            case DISPONIBLE:
                this.disponible();
                break;
            case PREPARANDO_SALTO:
                this.prepararSalto();
                break;
            case SALTO_WARP:
                this.saltoWarp();
                break;
            case ENFRIAMIENTO:
                this.enfriamiento();
                break;
            default: throw new EstadoInvalidoException("No existe el estado nro " + i);
        }
    }

    public State getEstadoActual() {
        return estadoActual;
    }

    public void setEstado(State estadoActual) {
        this.estadoActual = estadoActual;
    }


    /**
    * Metodo que permite un cambio de estado hacia disponible.
    * Solo puede ser de enfriamiento --> disponible.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser enfriamiento
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto enfriamiento.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a disponible.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, preparaSalto o saltoWarp) -/-> disponible.
    */
    
     
    public void disponible() throws EstadoInvalidoException {
        estadoActual.disponible();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: DISPONIBLE",this.estadoActual));
    }

    /**
    * Metodo que permite un cambio de estado hacia prepararSalto.
    * Solo puede ser de disponible --> prepararSalto.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser disponible
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto disponible.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a prepararSalto.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(preparaSalto, saltoWarp o enfriamiento) -/-> prepararSalto.
    */
    
    public void prepararSalto() throws EstadoInvalidoException {
        estadoActual.preparaSalto();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: PREPARANDO SALTO",this.estadoActual));
        this.saltoWarp();
    }
    
    /**
    * Metodo que permite un cambio de estado hacia saltoWarp.
    * Solo puede ser de prepararSalto --> saltoWarp.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser prepararSalto.
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto prepararSalto.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a saltoWarp.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, saltoWarp o enfriamiento) -/-> saltoWarp.
    */

    public void saltoWarp() throws EstadoInvalidoException {
        estadoActual.saltoWarp();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: SALTO WARP",this.estadoActual));
        this.enfriamiento();
    }
    
    /**
    * Metodo que permite un cambio de estado hacia disponible.
    * Solo puede ser de saltoWarp --> enfriamiento.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser saltoWarp.
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto saltoWarp.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a enfriamiento.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, preparaSalto o enfriamiento) -/-> enfriamiento.
    */

    public void enfriamiento() throws EstadoInvalidoException {
        estadoActual.enfriamiento();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: ENFRIAMIENTO",this.estadoActual));
        this.disponible();
    }
}