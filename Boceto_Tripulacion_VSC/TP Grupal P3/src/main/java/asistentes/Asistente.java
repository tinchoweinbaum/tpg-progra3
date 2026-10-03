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
    private Mision misionAct = null;

    /**
     * <b> Pre:</b>Se asume que la nave existe y se creó exitosamente, también se asume qué no hay otro asistente que referencie a esta misma nave. (preguntar)
     * <b> Post:</b>Se asocia el asistente con la nave.
     * @param nave: referencia a un objeto nave válido.
     */
    public Asistente(Nave nave){
        this.nave = nave;
        this.bitacorasNave = new Bitacora();
        this.motor = new MotorWarp();
    }

    // Agregar a uso-ia.md que usamos ia para implementar los enums de los estados.

    /**
     * Cicla por los estados del motor hasta saltar, es decir, ejecuta un salto warp independientemente del estado actual del motor.
     */
    public void ejecutarSalto(){
        int estadoActual = this.motor.getEstadoActual().getIdEstado();
        do{
            this.motor.llamaProximoEstado(estadoActual);
            estadoActual = this.motor.getEstadoActual().getIdEstado();
        } while(estadoActual == MotorWarp.DISPONIBLE);
    }

//    public void ejecutarSalto(){
//        int estadoActual = this.motor.getEstadoActual().getIdEstado();
//        while(estadoActual != MotorWarp.DISPONIBLE){
//            this.motor.llamaEstado(estadoActual + 1);
//        }
//        this.motor.llamaEstado(MotorWarp.SALTO_WARP);
//    }

    /**
     * <b> Pre:</b> Número válido de estado del motor, de 0 a 3.
     * Función para el escenario de llamar estado ilegal. Preguntar implementación con ciclo automático. sharau a lucas.
     * @param idEstado Estado deseado del motor
     */
    public void setEstadoMotor(int idEstado){
        this.motor.llamaProximoEstado(idEstado - 1);
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
