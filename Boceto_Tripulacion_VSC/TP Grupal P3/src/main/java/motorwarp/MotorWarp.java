package motorwarp;
import bitacora.RegistroMotor;
import java.util.Date;

public class MotorWarp {
    
    private State estadoActual;
    
    public MotorWarp () {
        this.estadoActual = new Disponible(this);
    }
    
    public int getEstado (){
        return this.estadoActual.getID;
    }

    public void llamaProximoEstado(int i){
        switch (i){
            case 0:
                this.prepararSalto();
                break;
            case 1:
                this.saltoWarp();
                break;
            case 2:
                this.enfriamiento();
                break;
            case 3:
                this.disponible();
                break;
        }
    }
       
    void setEstado(State estadoActual) {
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
