package asistentes;
import misiones.*;
import java.util.*;
import nave*;
import motorwarp.*;
import bitacora.*;

public class Asistente {
    private Nave nave;
    private ArrayList <Bitacora> bitacorasNave;
    private MotorWarp motor;

    public Asistente(String tipoNave){
        this.nave = FactoryNaves.getTipo(tipoNave);
        if  (this.nave){
            this.bitacorasNave = new ArrayList();
            this.motor = new MotorWarp();
        }
    }

}
