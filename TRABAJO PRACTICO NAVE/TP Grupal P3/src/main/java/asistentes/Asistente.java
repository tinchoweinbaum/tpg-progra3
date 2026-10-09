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
     * Constructor que instancia y asigna referencias de tipo Bitacora y MotorWarp.<br>
     * 
     * <b> Pre:</b>Se asume que la nave existe y se creó exitosamente. <br>
     * <b> Post:</b>Se asocia el asistente con la nave y se inicializa una Bitacora y MotorWarp. <br>
     * 
     * @param nave: referencia a un objeto nave valido, nave != NULL.
     */
    
    public Asistente(Nave nave){
        this.nave = nave;
        this.bitacorasNave = new Bitacora();
        this.motor = new MotorWarp();
    }

    /**
     * Función para el escenario de llamar estado ilegal. Preguntar implementación con ciclo automático.
     * 
     * <b> Pre:</b> Número válido de estado del motor, de 0 a 3.
     * 
     * @param idEstado Estado deseado del motor
     */
    public void setEstadoMotor(int idEstado){
        try {
            this.motor.llamaEstado(idEstado);
        }
        catch (EstadoInvalidoException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("CAMBIO DE ESTADO INVALIDO", e));
        }
        finally {
            this.actualizaBitacoraMotor();
        }
    }
    //ESTA LA DEJAMOS ASI PROBAMOS QUE NO PUEDE PASAR A ESTADOS INVALIDOS Y TIRA EXCEPCIONES

    public void actualizaBitacoraMotor(){
        for (RegistroMotor r : this.motor.actualizaRegistrosMotor()){
            this.bitacorasNave.agregarRegistro(r);
        }
    }

    public Bitacora getBitacorasNave() {
        return bitacorasNave;
    }

    /** Contrato mejorado con llm de navegador.<br>
     * Acepta una misión para la nave, validando los recursos necesarios y el estado actual.
     *
     * <p><b>Pre:</b></p>
     * <ul>
     *   <li>Se asume que la misión existe y es válida (no es nula y sus datos son consistentes).</li>
     *   <li>La nave del asistente se encuentra inicializada con un estado válido.</li>
     * </ul>
     *
     * <p><b>Post:</b></p>
     * <ul>
     *   <li>Se ejecuta la misión.</li>
     *   <li>Se agrega un {@code RegistroMision} a la bitácora de la nave.</li>
     *   <li>Si se ejecuta correctamente, los recursos de la nave se actualizan acorde al resultado.</li>
     * </ul>
     *
     * @param mision La misión que se desea aceptar y ejecutar.
     * @throws CombustibleInsuficienteException si el combustible requerido supera al disponible en la nave.
     * @throws ExcesoDesgasteException si el desgaste actual más el requerido supera el máximo permitido (100).
     * @throws ExcesoEnergiaException si la energía actual más el aporte de la misión supera el máximo permitido (100).
     * @throws MisionImposibleException si ocurre cualquier otro error general que impida realizar la misión.
     */

    /**
     * Metodo para cargar cierta cantidad de combustible, si supera 100,tira excepcion
     * @param carga
     */
    public void cargaCombustibleNave(float carga){
        try{
            if (this.nave.getCombustible() + carga <= 100){
                this.bitacorasNave.agregarRegistro(this.nave.setCombustible(this.nave.getCombustible() + carga));
            }else{
                throw new CargaInvalidaCombustibleException("La carga supera el limite del deposito de combustible");
            }
        } catch (CargaInvalidaCombustibleException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("No pudo realizarse carga de combustible",e));
        }
    }

    /**
     * Metodo para realizar mantenimiento de nave y llevar desgaste a cero
     */
    public void mantenimientoNave(){
        try{
            if (this.nave.getDesgaste()>=80){
                this.bitacorasNave.agregarRegistro(this.nave.setDesgaste(-this.nave.getDesgaste()));
            }else{
                throw new DesgasteInsuficienteException("Desgaste insuficiente para la operacion");
            }
        }
        // Este catch NO va a ir acá en la 2da parte, tiene que propagar la excepción.
        catch(DesgasteInsuficienteException e){
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
                this.bitacorasNave.agregarRegistro(this.nave.setEnergia(this.nave.getEnergia() + carga));
            }else{
                throw new ExcesoEnergiaException("La carga supera el limite de energia");
            }
        } catch (ExcesoEnergiaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("No pudo realizarse carga de energia",e));
        }
    }

    public Nave getNave() {
        return nave;
    }

    public MotorWarp getMotor() {
        return motor;
    }
}
