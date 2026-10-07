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
     * <b> Pre:</b>Se asume que la nave existe y se creó exitosamente <br>
     * <b> Post:</b>Se asocia el asistente con la nave. <br>
     * @param nave: referencia a un objeto nave válido.
     */
    public Asistente(Nave nave){
        this.nave = nave;
        this.bitacorasNave = new Bitacora();
        this.motor = new MotorWarp();
    }

//    /**
//     * Cicla por los estados del motor hasta saltar, es decir, ejecuta un salto warp independientemente del estado actual del motor.
//     */
//    public void ejecutarSalto(){
//        int estadoActual = this.motor.getEstadoActual().getIdEstado();
//        while(estadoActual != MotorWarp.PREPARANDO_SALTO){
//            this.motor.llamaEstado((estadoActual + 1) % 4); // % 4 para que de 3 pase de nuevo a 0.
//            estadoActual = this.motor.getEstadoActual().getIdEstado();
//        }
//        this.motor.llamaEstado(MotorWarp.SALTO_WARP);
//        this.motor.llamaEstado(MotorWarp.ENFRIAMIENTO);
//    }
    //VAMOS A COMENTARLA PARA NO UTILIZAR EL SWITCH-CASE EN EL AVANZE; SOLO USAMOS LLAMA ESTADO PARA PASAR MANUAL Y PROBAR EL ESCENARIO DE CAMINO ERRONEO

    /**
     * <b> Pre:</b> Número válido de estado del motor, de 0 a 3.
     * Función para el escenario de llamar estado ilegal. Preguntar implementación con ciclo automático.
     * @param idEstado Estado deseado del motor
     */
    public void setEstadoMotor(int idEstado){
        try {
            this.motor.llamaEstado(idEstado);
            this.bitacorasNave.agregarRegistro(new RegistroMotor("CAMBIO DE ESTADO EXITOSO", motor.getEstadoActual()));
        } catch (EstadoInvalidoException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("CAMBIO DE ESTADO INVALIDO", e));
        }
    }
    //ESTA LA DEJAMOS ASI PROBAMOS QUE NO PUEDE PASAR A ESTADOS INVALIDOS Y TIRA EXCEPCIONES

    public void ejecucionMision(Mision mision){
        try{
            mision.preparar();
            RegistroMision registroMisionAct = null;
            registroMisionAct = this.aceptaMision(mision);
            this.bitacorasNave.agregarRegistro(registroMisionAct);
            mision.ejecutar();
            this.motor.prepararSalto();
            mision.evaluar();
            mision.cerrar();
            this.actualizaRecursosMision(mision);
            this.bitacorasNave.agregarRegistro(new RegistroMision("MISION REALIZADA", mision));
        }catch(MisionImposibleException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("NO PUDO REALIZARSE LA MISION",e));
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
    private RegistroMision aceptaMision(Mision mision) throws MisionImposibleException{
        if (mision.getCombustibleRequerido() > nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        //Desgaste maximo = 100
        if (mision.getDesgasteRequerido()+nave.getDesgaste() > 100)
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        //Energia maxima = 100
        if (mision.getEnergiaAportada()>0 && nave.getEnergia()+mision.getEnergiaAportada() > 100)
            throw new ExcesoEnergiaException("Se supera la cantidad maxima de energia soportada por la nave");

        //Verifico que se pueda usar el motor
        if (this.motor.getEstadoActual().getIdEstado() != MotorWarp.DISPONIBLE)
            throw new MotorNoDisponibleException("El motor no se encuentra disponible");

        return new RegistroMision("MISION PREPARADA: SE CHEQUEARON LOS RECURSOS",mision);
    }


    // Escribir contrato de esta misión y revisar como funciona RegistroBitacora, no tiene sentido que me pida hacer la cuenta del nivel resultante.
    private void actualizaRecursosMision(Mision mision){

        this.bitacorasNave.agregarRegistro(this.nave.setCombustible(this.nave.getCombustible() - mision.getCombustibleRequerido()));

        this.bitacorasNave.agregarRegistro(this.nave.setDesgaste(this.nave.getDesgaste()+ mision.getDesgasteRequerido()));

        float energia = mision.getEnergiaAportada();
        if (energia > 0){
            this.bitacorasNave.agregarRegistro(this.nave.setEnergia(this.nave.getEnergia() + energia));
        }
    }

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
                this.bitacorasNave.agregarRegistro(this.nave.setDesgaste(0));
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
