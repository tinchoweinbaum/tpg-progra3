package motorwarp;
import bitacora.RegistroMotor;
import exceptions.EstadoInvalidoException;

import java.util.Date;

public class MotorWarp {

    public static final int DISPONIBLE = 0;
    public static final int PREPARANDO_SALTO = 1;
    public static final int SALTO_WARP = 2;
    public static final int ENFRIAMIENTO = 3;
    
    private State estadoActual;
    
    public MotorWarp () {
        this.estadoActual = new Disponible(this);
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
     * Se genera un nuevo registro en la bitacora a parir de los resultado de los estados del motor
     * @pre mensaje != null and mensaje != ""
     * @post Se envia el mensaje a la bitacora
     */
    public void registrarEvento(Date fechaRegistro, String descripcionRegistro,State estado) {
        RegistroMotor registro = new RegistroMotor (fechaRegistro, descripcionRegistro, estado);
    }

    // Delegaciones de comportamiento al estado actual // 
    
    public void disponible() {
        estadoActual.disponible();
    }
    
    public void prepararSalto() {
        estadoActual.preparaSalto();
    }

    public void saltoWarp() {
        estadoActual.saltoWarp();
    }

    public void enfriamiento() {
        estadoActual.enfriamiento();
    }
}
