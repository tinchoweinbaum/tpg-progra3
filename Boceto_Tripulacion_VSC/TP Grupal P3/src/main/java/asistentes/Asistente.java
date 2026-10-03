package asistentes;
import misiones.*;
import java.util.*;
import nave.*;
import motorwarp.*;
import bitacora.*;
import exceptions.*;

public class Asistente {
    private Nave nave;
    private Bitacora bitacorasNave;
    private MotorWarp motor;

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


//    public void ejecutarSalto(){
//        int estadoActual = this.motor.getEstadoActual().getIdEstado();
//        do{
//            this.motor.llamaProximoEstado(estadoActual);
//            estadoActual = this.motor.getEstadoActual().getIdEstado();
//        } while(estadoActual == MotorWarp.DISPONIBLE);
//    }

    /**
     * Cicla por los estados del motor hasta saltar, es decir, ejecuta un salto warp independientemente del estado actual del motor.
     */
    public void ejecutarSalto(){
        int estadoActual = this.motor.getEstadoActual().getIdEstado();
        while(estadoActual != MotorWarp.PREPARANDO_SALTO){
            this.motor.llamaEstado((estadoActual + 1) % 4); // % 4 para que de 3 pase de nuevo a 0.
            estadoActual = this.motor.getEstadoActual().getIdEstado();
        }
        this.motor.llamaEstado(MotorWarp.SALTO_WARP);
        this.motor.llamaEstado(MotorWarp.ENFRIAMIENTO);
    }

    /**
     * <b> Pre:</b> Número válido de estado del motor, de 0 a 3.
     * Función para el escenario de llamar estado ilegal. Preguntar implementación con ciclo automático.
     * @param idEstado Estado deseado del motor
     */
    public void setEstadoMotor(int idEstado){
        this.motor.llamaEstado(idEstado);
    }

    public void aceptaMision(Mision mision) throws MisionImposibleException{
        if (mision.getCombustibleRequerido() > nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        if (mision.getDesgasteRequerido() > nave.getDesgaste())
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        this.bitacorasNave.agregarRegistro(mision.ejecutarMision());
    }

//    public void registraMisionExito(int gastoCombustible, int gastoDesgaste, int energiaGanada, Mision mision){
//        this.bitacorasNave.agregarRegistro(new RegistroMision("Mision lograda con exito",mision));
//        this.bitacorasNave.agregarRegistro(new RegistroRecursos("COMBUSTIBLE", -gastoCombustible, this.nave.getCombustible()));
//        this.bitacorasNave.agregarRegistro(new RegistroRecursos("DESGASTE", -gastoDesgaste, this.nave.getDesgaste()));

//        if (energiaGanada>0){
//            this.nave.setEnergia(this.nave.getEnergia() + energiaGanada);
//            this.bitacorasNave.agregarRegistro(new RegistroRecursos("ENERGIA", energiaGanada, this.nave.getEnergia()));
//        }
//    }


}
