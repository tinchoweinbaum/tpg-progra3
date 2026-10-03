package asistentes;
import misiones.*;
import java.util.*;
import nave.*;
import motorwarp.*;
import bitacora.*;
import nave.*;
import exceptions.*;

public class Asistente {
    private Nave nave;
    private Bitacora bitacorasNave;
    private MotorWarp motor;
    private Mision misionAct;

    public Asistente(String tipoNave){
        this.nave = FactoryNaves.getTipo(tipoNave);
        if  (this.nave!=null){
            this.bitacorasNave = new Bitacora();
            this.motor = new MotorWarp();
        }
    }

    public void ejecutarSalto(){
        int estadoActual = this.motor.getEstado();
        this.motor.llamaProximoEstado(estadoActual);
    }

    public void aceptaMision(Mision mision) throws MisionImposibleException{
        if (mision.getCombustibleRequerido() > nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        if (mision.getDesgasteRequerido() > nave.getDesgaste())
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        this.setMisionAct(mision);
    }

    public void setMisionAct(Mision misionAct) {
        this.misionAct = misionAct;
    }

    public void registraMisionExito(int gastoCombustible, int gastoDesgaste, int energiaGanada, Mision mision){
        this.bitacorasNave.agregarRegistro(new RegistroMision("Mision lograda con exito",mision));
        this.bitacorasNave.agregarRegistro(new RegistroRecursos("COMBUSTIBLE", -gastoCombustible, this.nave.getCombustible()));
        this.bitacorasNave.agregarRegistro(new RegistroRecursos("DESGASTE", -gastoDesgaste, this.nave.getDesgaste()));
        if (energiaGanada>0){
            this.nave.setEnergia(this.nave.getEnergia() + energiaGanada);
            this.bitacorasNave.agregarRegistro(new RegistroRecursos("ENERGIA", energiaGanada, this.nave.getEnergia()));
        }
    }
}
