package asistentes;
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

    private boolean invariant() {
        return this.nave != null && this.bitacorasNave != null && this.motor != null;
    }
    
    /**
     * Constructor que instancia y asigna referencias de tipo Bitacora y MotorWarp.
     * 
     * <b>pre:</b> nave != null.
     * <b>post:</b> Se asocia el asistente con la nave y se inicializa una bitacora y motorWarp, 
     *              ambos != null y motorWarp en Disponible.
     * 
     * @param nave referencia a un objeto nave valido, nave != null.
     */
    
    public Asistente(Nave nave){
        assert nave != null : "La nave no puede ser nula";
        
        this.nave = nave;
        this.bitacorasNave = new Bitacora();
        this.motor = new MotorWarp();
        
        assert invariant(): "Fallo en crear la bitacora y/o motorWarp";
    }
    
    public Nave getNave() {
        return nave;
    }

    public MotorWarp getMotor() {
        return motor;
    }
    
    /**
     * Metodo para establecer un estado al motor.
     * Si el estado es invalido se catchea la excepcion y registra el error en la bitacora.
     * (DISPONIBLE = 0, PREPARANDO_SALTO = 1, SALTO_WARP = 2, ENFRIAMIENTO = 3).
     * 
     * <b>pre:</b> Número valido de estado del motor, de 0 a 3.
     * <b>post:</b> Se actualiza el estado del motor, si falla no se cambia el estado y se registra en bitacora.
     * 
     * @param idEstado Estado deseado del motor (0 a 3 u otro valor para excepcion).

     */
    public void setEstadoMotor(int idEstado){
        assert invariant();
        
        try {
            this.motor.llamaEstado(idEstado);
        }
        catch (EstadoInvalidoException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("CAMBIO DE ESTADO INVALIDO", e));
        }
        finally {
            this.actualizaBitacoraMotor();
        }
        assert invariant();
    }
    
    //ESTA LA DEJAMOS. ASÍ PROBAMOS QUE NO PUEDE PASAR A ESTADOS INVALIDOS Y TIRA EXCEPCIONES

    /**
     * Metodo para probar el ciclo de salto y que quede registrado.
     * <b>pre:</b> Se ejecuta el salto y se guarda cada cambio de estado en bitacora
     */
    public void saltar(){
        this.motor.prepararSalto();
        actualizaBitacoraMotor();
    }

    /**
     * Método que actualiza la bitacora con los cambios de estado del motor luego de saltar.
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
     * <b>pre:</b> cantCarga > 0
     * <b>post:</b> Se carga el combustible de la nave en la cantidad especificada
     * 
     * @param cantCarga cantidad de combustible a cargar.
     */
    
    public void cargaCombustibleNave(float cantCarga){
        assert cantCarga > 0 : "La cantidad de combustible cargada debe de ser mayor que cero";
        assert invariant();
        
        try{
            this.bitacorasNave.agregarRegistro(this.nave.cargaCombustible(cantCarga));
        } catch (CantCombustibleInvalidaException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al cargar combustible", e));
            System.out.println(e.getMessage());
        }
        
        assert invariant();
    }

    /**
     * <b>pre:</b> cantConsumida > 0
     * <b>post:</b> Se consume la cantidad de combustible especificada
     * 
     * @param cantConsumida cantidad de combustible consumida.
     */
    
    public void consumeCombustibleNave(float cantConsumida){
        assert cantConsumida > 0 : "La cantidad de combustible consumida debe de ser mayor que cero";
        assert invariant();
        
        try{
            this.bitacorasNave.agregarRegistro(this.nave.consumeCombustible(cantConsumida));
        } catch (CantCombustibleInvalidaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al consumir combustible" , e));
            System.out.println(e.getMessage());
        }
        
        assert invariant();
    }

    
    /**
    * Metodo para cargar totalmente el combustible de la nave.
    * Resta el maximo de combustible que soporta la nave con la cantidad de combustible actual,
    * el resultado es el restante de combustible que necesita la nave para estar llena.
    * 
    *<b>post:</b> Carga combustible hasta su maximo
    */
    public void cargaComustibleNaveFull(){
        this.cargaCombustibleNave(Nave.MAX_COMBUSTIBLE - this.nave.getCombustible());
    }

    /**
     * <b>pre:</b> cantDesgaste > 0
     * <b>post:</b> Se aumenta el desgaste de la nave en la cantidad especificada
     * 
     * @param cantDesgaste cantidad de desgaste a agregar.
     */
    
    public void aumentaDesgasteNave(float cantDesgaste){
        assert cantDesgaste > 0 : "La cantidad de desgaste debe de ser mayor que cero";
        assert invariant();     
        
        try{
            this.bitacorasNave.agregarRegistro(this.nave.aumentaDesgaste(cantDesgaste));
        } catch (CantDesgasteInvalidaException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al aumentar el desgaste de la nave", e));
            System.out.println(e.getMessage());
        }
        
        assert invariant(); 
    }

    /**
     * <b>pre:</b> cantReparada > 0
     * <b>post:</b> Se reduce el desgaste de la nave según la cantidad reparada
     * 
     * @param cantReparada cantidad de desgaste que se reduce (reparacion).
     * 
     */
    
    public void reparaDesgasteNave(float cantReparada){
        assert cantReparada > 0 : "La cantidad de reparacion debe de ser mayor que cero";
        assert invariant();
        
        try{
            this.bitacorasNave.agregarRegistro(this.nave.reparaDesgaste(cantReparada));
        } catch (CantDesgasteInvalidaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al reparar la nave", e));
            System.out.println(e.getMessage());
        }
        
        assert invariant();
    }

    /**
    * Metodo para reparar la nave completamente.
    * Toma el desgaste actual de la nave y otro metodo en la clase "nave" le resta
    * el desgaste actual volviendolo 0.
    * <b>pre:</b> El desgaste de la nave tiene que ser mayor o igual a 80 unidades (desgaste >= 80)
    *<b>post:</b> Repara totalmente la nave (desgaste = 0)
    */
    
    public void reparaDesgasteNaveFull(){
        if (this.getNave().getDesgaste()>=80)
            this.reparaDesgasteNave(this.nave.getDesgaste());
    }

    /**
     * <b>pre:</b> cantEnergia > 0
     * <b>post:</b> Se aumenta la energía de la nave según la cantidad indicada
     * 
     * @param cantEnergia cantidad de energía a cargar.
     */
    
    public void cargaEnergiaNave(float cantEnergia){
        assert cantEnergia > 0 : "La cantidad de energia cargada debe de ser mayor que cero";
        assert invariant();
        
        try{
            this.bitacorasNave.agregarRegistro(this.nave.cargaEnergia(cantEnergia));
        } catch (CantEnergiaInvalidaException e){
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al cargar energía", e));
            System.out.println(e.getMessage());
        }
        
        assert invariant();
    }

    /**
     * <b>pre:</b> cantConsumida > 0
     * <b>post:</b> Se consume la cantidad de energía especificada de la nave
     * 
     * @param cantConsumida cantidad de energía consumida.
     */
    
    public void consumeEnergiaNave(float cantConsumida){
        assert cantConsumida > 0 : "La cantidad de energia consumida debe de ser mayor que cero";
        assert invariant();       
        
        try{
            this.bitacorasNave.agregarRegistro(this.nave.consumeEnergia(cantConsumida));
        } catch (CantEnergiaInvalidaException e) {
            this.bitacorasNave.agregarRegistro(new RegistroError("Error al consumir energía", e));
            System.out.println(e.getMessage());
        }
        
        assert invariant();
    }

    /**
    *<b>post:</b> Carga la cantidad energia restante para que la nave 
    *             este a su maxima capacidad  
    */
    public void cargaEnergiaNaveFull(){
        this.cargaEnergiaNave(Nave.MAX_ENERGIA - this.nave.getEnergia());
    }

    /**
    * Muestra el total de registro de la bitacora.
    */
    public void muestraBitacora(){
        this.bitacorasNave.mostrarBitacora();
    }

    /**
    * Muestra una cantidad especifica "N" de registro de la bitacora.
    * @param N numero de registros a mostrar
    */
    public void muestraNBitacoras(int N){
        this.bitacorasNave.mostrarNRegistros(N);
    }

    /**
     * <b>pre:</b> tripulante != null
     * <b>post:</b> Se intenta agregar un tripulante a la nave, de no ser posible se lanza una excepcion
     * 
     * @param tripulante Objeto de tipo tripulante que se desea agregar.
     */
    
    public void agregaTripulante(Tripulante tripulante){
        assert tripulante != null : "El tripulante no puede ser nulo";
        assert invariant();
        
        try{
            this.nave.agregaTripulante(tripulante);
        } catch(ErrorAgregarTripulacionException e){
            System.out.println(e.getMessage());
        }
        
        assert invariant();
    }

    /**
     * Agrega una lista de tripulantes a la nave iterando sobre ella.
     * 
     * <b>pre:</b> tripulantes != null y no contiene elementos nulos
     * <b>post:</b> Se intentan agregar todos los tripulantes de la lista a la nave, 
     * registrando los errores de forma individual
     * 
     * @param tripulantes Lista de objetos Tripulante a agregar, tripulantes != null
     */
    
    public void agregaTripulante(ArrayList<Tripulante> tripulantes) {
        assert tripulantes != null : "La lista de tripulantes no puede ser nula";
        assert invariant();
        
        for (Tripulante t : tripulantes) {
            this.agregaTripulante(t);
        }
        
        assert invariant();
    }

    /**
     * <b>pre:</b> t != null
     * <b>post:</b> Se elimina del ArrayList de tripulantes al tripulante "t" 
     * 
     * @param t Objeto de tipo tripulante a ser eliminado
     * 
     * @return Retorna true o false dependiendo se se puedo eliminar o no al tripulante
     */
    
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
