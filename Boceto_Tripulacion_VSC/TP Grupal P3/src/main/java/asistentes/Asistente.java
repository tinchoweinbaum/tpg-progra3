package asistentes;
import misiones.*;
import java.util.*;
import nave.*;
import motorwarp.*;
import bitacora.*;

public class Asistente {
    private Nave nave;
    private Bitacora bitacorasNave;
    private MotorWarp motor;

    public Asistente(String tipoNave){
        this.nave = FactoryNaves.getTipo(tipoNave);
        if  (this.nave!=null){
            this.bitacorasNave = new Bitacora();
            this.motor = new MotorWarp();
        }
    }

    public void ejecutarSalto(){
        int estadoActual = this.motor.getEstado();
        this.motor.llamarProximoEstado(estadoActual);
    }

    public boolean aceptaMision(int gastoCombustible,int gastoDesgaste){
        try{
            if (nave.getCombustible - gastoCombustible >= 0) {
                if (nave.getDesgaste + gastoDesgaste <= 100) {
                    this.nave.setCombustible(this.nave.getCombustible() - gastoCombustible);
                    this.nave.setDesgaste(this.nave.getDesgaste() + gastoDesgaste);
                    return true;
                }else {
                    throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");
                }
            }else {
                throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");
            }
        }catch(MisionImposibleException e){
            bitacorasNave.agregarRegistro(new RegistroError("Mision cancelada",e));
        }
    }

    public void registraMisionExito(int gastoCombustible,int gastoDesgaste,int energiaGanada,Mision mision){
        this.bitacorasNave.agregarRegistro(new RegistroMision("Mision lograda con exito",mision));
        this.bitacorasNave.agregarRegistro(new RegistroRecursos("COMBUSTIBLE", -gastoCombustible, this.nave.getCombustible()));
        this.bitacorasNave.agregarRegistro(new RegistroRecursos("DESGASTE", -gastoDesgaste, this.nave.getDesgaste()));
        if (energiaGanada>0){
            this.nave.setEnergia(this.nave.getEnergia() + energiaGanada);
            this.bitacorasNave.agregarRegistro(new RegistroRecursos("ENERGIA", energiaGanada, this.nave.getEnergia()));
        }
    }
}
