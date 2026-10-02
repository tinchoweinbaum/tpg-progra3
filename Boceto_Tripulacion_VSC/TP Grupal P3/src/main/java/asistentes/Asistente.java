package asistentes;
import misiones.*;
import java.util.*;
import nave*;
import motorwarp.*;
import bitacora.*;

public class Asistente {
    private Nave nave;
    private Bitacora bitacorasNave;
    private MotorWarp motor;

    public Asistente(String tipoNave){
        this.nave = FactoryNaves.getTipo(tipoNave);
        if  (this.nave){
            this.bitacorasNave = new Bitacora();
            this.motor = new MotorWarp();
        }
    }

    private void motorDisponible(){
        motor.disponible();
    }

    private void motorPrepara(){
        motor.preparaSalto();
    }

    private void motorWarp(){
        motor.saltoWarp();
    }

    private void motorEnfriamiento(){
        motor.enfriamiento();
    }

    public void ejecutarSalto(){

    }
}
