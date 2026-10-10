package asistentes;
import misiones.*;
import nave.*;
import motorwarp.*;
import bitacora.*;
import exceptions.*;
import tripulantes.Tripulante;

import java.util.ArrayList;

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

    public Nave getNave() {
        return nave;
    }

    public MotorWarp getMotor() {
        return motor;
    }

    /**
     * Función para el escenario de llamar estado ilegal. Preguntar implementación con ciclo automático.
     * <b> Pre:</b> Número válido de estado del motor, de 0 a 3.
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
    //ESTA LA DEJAMOS. ASÍ PROBAMOS QUE NO PUEDE PASAR A ESTADOS INVALIDOS Y TIRA EXCEPCIONES

    /**
     * Método que actualiza la bitácora con los cambios de estado del motor luego de saltar.
     */
    public void actualizaBitacoraMotor(){
        for (RegistroMotor r : this.motor.actualizaRegistrosMotor()){
            this.bitacorasNave.agregarRegistro(r);
        }
    }

    public Bitacora getBitacorasNave() {
        return bitacorasNave;
    }

    /**
     * <b>Pre: </b>cantCarga > 0<br>
     * <b>Post: </b>Se carga el combustible de la nave en la cantidad especificada.
     * @param cantCarga cantidad de combustible a cargar.
     */
    public void cargaCombustibleNave(float cantCarga){
        try{
            this.bitacorasNave.agregarRegistro(this.nave.cargaCombustible(cantCarga));
        } catch (CantCombustibleInvalidaException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al cargar combustible", e));
            System.out.println(e.getMessage());
        }
    }

    /**
     * <b>Pre: </b>cantConsumida > 0<br>
     * <b>Post: </b>Se consume la cantidad de combustible especificada.
     * @param cantConsumida cantidad de combustible consumida.
     */
    public void consumeCombustibleNave(float cantConsumida){
        try{
            this.bitacorasNave.agregarRegistro(this.nave.consumeCombustible(cantConsumida));
        } catch (CantCombustibleInvalidaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al consumir combustible" , e));
            System.out.println(e.getMessage());
        }
    }

    public void cargaComustibleNaveFull(){
        this.cargaCombustibleNave(Nave.MAX_COMBUSTIBLE - this.nave.getCombustible());
    }

    /**
     * <b>Pre: </b>cantDesgaste > 0<br>
     * <b>Post: </b>Se aumenta el desgaste de la nave en la cantidad especificada.
     * @param cantDesgaste cantidad de desgaste a agregar.
     */
    public void aumentaDesgasteNave(float cantDesgaste){
        try{
            this.bitacorasNave.agregarRegistro(this.nave.aumentaDesgaste(cantDesgaste));
        } catch (CantDesgasteInvalidaException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al aumentar el desgaste de la nave", e));
            System.out.println(e.getMessage());
        }
    }

    /**
     * <b>Pre: </b>cantReparada > 0<br>
     * <b>Post: </b>Se reduce el desgaste de la nave según la cantidad reparada.
     * @param cantReparada cantidad de desgaste que se reduce (reparación).
     */
    public void reparaDesgasteNave(float cantReparada){
        try{
            this.bitacorasNave.agregarRegistro(this.nave.reparaDesgaste(cantReparada));
        } catch (CantDesgasteInvalidaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al reparar la nave", e));
            System.out.println(e.getMessage());
        }
    }

    public void reparaDesgasteNaveFull(){
        this.reparaDesgasteNave(this.nave.getDesgaste());
    }

    /**
     * <b>Pre: </b>cantEnergia > 0<br>
     * <b>Post: </b>Se aumenta la energía de la nave según la cantidad indicada.
     * @param cantEnergia cantidad de energía a cargar.
     */
    public void cargaEnergiaNave(float cantEnergia){
        try{
            this.bitacorasNave.agregarRegistro(this.nave.cargaEnergia(cantEnergia));
        } catch (CantEnergiaInvalidaException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al cargar energía", e));
            System.out.println(e.getMessage());
        }
    }

    /**
     * <b>Pre: </b>cantConsumida > 0<br>
     * <b>Post: </b>Se consume la cantidad de energía especificada de la nave.
     * @param cantConsumida cantidad de energía consumida.
     */
    public void consumeEnergiaNave(float cantConsumida){
        try{
            this.bitacorasNave.agregarRegistro(this.nave.consumeEnergia(cantConsumida));
        } catch (CantEnergiaInvalidaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al consumir energía", e));
            System.out.println(e.getMessage());
        }
    }

    public void cargaEnergiaNaveFull(){
        this.cargaEnergiaNave(Nave.MAX_ENERGIA - this.nave.getEnergia());
    }

    /**
     * Método
     */
    public void muestraBitacora(){
        this.bitacorasNave.mostrarBitacora();
    }

    public void muestraNBitacoras(int N){
        this.bitacorasNave.mostrarNRegistros(N);
    }

    public void agregaTripulante(Tripulante tripulante){
        try{
            this.nave.agregaTripulante(tripulante);
        } catch(ErrorAgregarTripulacionException e){
            System.out.println(e.getMessage());
        }
    }

    public void agregaTripulante(ArrayList<Tripulante> tripulantes) {
        for (Tripulante t : tripulantes) {
            this.agregaTripulante(t);
        }
    }

    public boolean eliminaTripulante(Tripulante t){
        return this.nave.eliminaTripulante(t);
    }

    /**
     * toString de Asistente que printea todos los datos relevantes. Método hecho con Gemini.
     */
    @Override
    public String toString() {
        String string = "--- Estado del Asistente y la Nave ---\n" +
                "-> Tipo de la nave: " + this.nave.getClass().getSimpleName() + "\n" +
                "-> Tripulación: " + this.nave.getTripulacion() + "\n" +
                "-> Cantidad de recursos:\n" +
                "   - Combustible: " + this.nave.getCombustible() + " / " + Nave.MAX_COMBUSTIBLE + "\n" +
                "   - Energía: " + this.nave.getEnergia() + " / " + Nave.MAX_ENERGIA + "\n" +
                "   - Desgaste: " + this.nave.getDesgaste() + " / " + Nave.MAX_DESGASTE;

        return string;
    }
}
