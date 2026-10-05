package asistentes;
import misiones.*;
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

    /**
        Si acepta la mision la ejecuta, sino tira excepcion
     */
    //Para cuando vean esto, cambie le tipo de retorno,ya que si la mision creaba un objeto bitacora,
    // iba a crear una referencia. ejecutar mision se hace void. el metodo toString de mision luego se recupera cuando
    //se printean las bitacoras . ante alguna duda comunicarse con el 223 6887474
    public void aceptaMision(Mision mision) throws MisionImposibleException{
        if (mision.getCombustibleRequerido() > nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        //Desgaste maximo = 100
        if (mision.getDesgasteRequerido()+nave.getDesgaste() > 100)
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        //Energia maxima = 100
        if (mision.getEnergiaAportada()>0 && nave.getEnergia()+mision.getEnergiaAportada() > 100)
            throw new ExcesoEnergiaException("Se supera la cantidad maxima de energia soportada por la nave");

        //Ejecuto mision y guardo bitacora
        mision.ejecutarMision();
        this.bitacorasNave.agregarRegistro(new RegistroMision("MISION REALIZADA",mision));

        //Actualizo recursos de la nave
        this.actualizaRecursosMision(mision.getCombustibleRequerido(),mision.getDesgasteRequerido(),mision.getEnergiaAportada());
    }

    /**
     * Metodo que actualiza los recursos de la nave segun requerimientos de la mision
     * @param combustible
     * @param desgaste
     * @param energia
     */
    private void actualizaRecursosMision(float combustible,float desgaste,float energia){
        this.nave.setCombustible(this.nave.getCombustible()-combustible);
        this.nave.setDesgaste(this.nave.getDesgaste()+desgaste);
        if (energia>0){
            this.nave.setEnergia(this.nave.getEnergia()+energia);
        }
        //No se crea un registro de recursos porque lo creo la mision cuando se guardo la bitacora de mision
    }

    /**
     * Metodo para cargar cierta cantidad de combustible, si supera 100,tira excepcion
     * @param carga
     */
    public void cargaCombustibleNave(float carga){
        try{
            if (this.nave.getCombustible() + carga <= 100){
                this.nave.setCombustible(this.nave.getCombustible() + carga);
                this.bitacorasNave.agregarRegistro(new RegistroRecursos("COMBUSTIBLE",carga,this.nave.getCombustible()));
            }else{
                throw new CargaInvalidaCombustibleException("La carga supera el limite del deposito de combustible");
            }
        } catch (CargaInvalidaCombustibleException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("No pudo realizarse carga de combustible",e));
        }
    }

    /**
     * Metodo para realizar mantenimiento de nave
     */
    public void mantenimientoNave(){
        try{
            if (this.nave.getDesgaste()>=80){
                this.bitacorasNave.agregarRegistro(new RegistroRecursos("DESGASTE",-this.nave.getDesgaste(),0));
                this.nave.setDesgaste(0);
            }else{
                throw new DesgasteInsuficienteException("Desgaste insuficiente para la operacion");
            }
        }catch(DesgasteInsuficienteException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("No pudo realizarse el mantenimiento",e));
        }

    }

    /**
     * Metodo para cargar cierta cantidad de energia, si supera 100,tira excepcion
     * @param carga
     */
    public void cargaEnergiaNave(float carga){
        try{
            if (this.nave.getEnergia() + carga <= 100){
                this.nave.setEnergia(this.nave.getEnergia() + carga);
                this.bitacorasNave.agregarRegistro(new RegistroRecursos("ENERGIA",carga,this.nave.getEnergia()));
            }else{
                throw new ExcesoEnergiaException("La carga supera el limite de energia");
            }
        } catch (ExcesoEnergiaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("No pudo realizarse carga de energia",e));
        }
    }

}
