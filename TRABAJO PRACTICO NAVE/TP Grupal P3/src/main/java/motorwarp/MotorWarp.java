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

    //Metodo solo para probar pasaje a un estado invalido
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

    // Delegaciones de comportamiento al estado actual
    // Como no estan definidas las caracteristicas del viaje warp cada estado pasa automaticamente al siguitente
    public void disponible() throws EstadoInvalidoException {
        estadoActual.disponible();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: DISPONIBLE",this.estadoActual));
    }

    public void prepararSalto() throws EstadoInvalidoException {
        estadoActual.preparaSalto();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: PREPARANDO SALTO",this.estadoActual));
        this.saltoWarp();
    }

    public void saltoWarp() throws EstadoInvalidoException {
        estadoActual.saltoWarp();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: SALTO WARP",this.estadoActual));
        this.enfriamiento();
    }

    public void enfriamiento() throws EstadoInvalidoException {
        estadoActual.enfriamiento();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: ENFRIAMIENTO",this.estadoActual));
        this.disponible();
    }
}