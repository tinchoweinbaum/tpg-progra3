package motorwarp;
import bitacora.RegistroMotor;
import exceptions.EstadoInvalidoException;


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


    // Delegaciones de comportamiento al estado actual // 
    //Como no estan definidas las caracteristicas del viaje warp cada estado pasa automaticamente al siguitente
    public RegistroMotor disponible() throws EstadoInvalidoException {
        estadoActual.disponible();
    }
    
    public RegistroMotor prepararSalto() throws EstadoInvalidoException {
        estadoActual.preparaSalto();
        this.saltoWarp();
    }

    public RegistroMotor saltoWarp() throws EstadoInvalidoException {
        estadoActual.saltoWarp();
        this.enfriamiento();
    }

    public RegistroMotor enfriamiento() throws EstadoInvalidoException {
        estadoActual.enfriamiento();
        this.disponible();
    }
}
