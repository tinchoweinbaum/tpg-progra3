package asistentes;
import destinoytablero.*;
import nave.*;
import motorwarp.*;
import bitacora.*;
import exceptions.*;
import tripulantes.Tripulante;

import java.util.ArrayList;
import java.util.Random;

public class Asistente {
    private final Nave nave;
    private final Bitacora bitacorasNave;
    private final MotorWarp motor;
    private final Tablero tablero;

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
    
    public Asistente(Nave nave,Tablero tablero){
        assert nave != null : "La nave no puede ser nula";
        
        this.nave = nave;
        this.tablero = tablero;
        this.bitacorasNave = new Bitacora();
        this.motor = new MotorWarp(this);
        
        assert invariant(): "Fallo en crear la bitacora y/o motorWarp";
    }

    public Tablero getTablero() {
        return tablero;
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
        this.motor.prepararSalto(2); // 2 -> SALTO_WARP = 2
        actualizaBitacoraMotor();
    }

    public void saltarWarp(){
        this.motor.prepararSalto(4); // SALTO_NORMAL = 4;
    }

    /**
     * Actualiza la bitacora con los cambios de estado del motor luego de saltar.
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
    
    public void cargaCombustibleNave(float cantCarga)throws CantCombustibleInvalidaException{
        assert cantCarga > 0 : "La cantidad de combustible cargada debe de ser mayor que cero";
        assert invariant();
        
        this.bitacorasNave.agregarRegistro(this.nave.cargaCombustible(cantCarga));
        
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
    public void cargaComustibleNaveFull()throws CantCombustibleInvalidaException{
        if (getNave().getCombustible()==Nave.MAX_COMBUSTIBLE)
            throw new CantCombustibleInvalidaException("Tanque lleno");
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
    
    public void reparaDesgasteNave(float cantReparada)throws CantDesgasteInvalidaException{
        assert cantReparada > 0 : "La cantidad de reparacion debe de ser mayor que cero";
        assert invariant();
        
        this.bitacorasNave.agregarRegistro(this.nave.reparaDesgaste(cantReparada));

        
        assert invariant();
    }

    /**
    * Metodo para reparar la nave completamente.
    * Toma el desgaste actual de la nave y otro metodo en la clase "nave" le resta
    * el desgaste actual volviendolo 0.
    * <b>pre:</b> El desgaste de la nave tiene que ser mayor o igual a 80 unidades (desgaste >= 80)
    *<b>post:</b> Repara totalmente la nave (desgaste = 0)
    */
    
    public void reparaDesgasteNaveFull()throws CantDesgasteInvalidaException{
        if (this.getNave().getDesgaste()<80)
            throw new CantDesgasteInvalidaException("No puede realizarse el mantenimiento con un nivel de Desgaste menor a 80");
        this.reparaDesgasteNave(this.nave.getDesgaste());
    }

    /**
     * <b>pre:</b> cantEnergia > 0
     * <b>post:</b> Se aumenta la energía de la nave según la cantidad indicada
     * 
     * @param cantEnergia cantidad de energía a cargar.
     */
    
    public void cargaEnergiaNave(float cantEnergia)throws CantEnergiaInvalidaException{
        assert cantEnergia > 0 : "La cantidad de energia cargada debe de ser mayor que cero";
        assert invariant();

        this.bitacorasNave.agregarRegistro(this.nave.cargaEnergia(cantEnergia));
        
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
    public void cargaEnergiaNaveFull()throws CantEnergiaInvalidaException{
        if(getNave().getEnergia()==Nave.MAX_ENERGIA)
            throw new CantEnergiaInvalidaException("Tanque lleno");
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
     * toString de Asistente que printea todos los datos relevantes. Hecho con Gemini.
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

    public void chequeaPosicionActual() throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException, EnergiaInsuficienteException {
        Random rand = new Random();
        ElementoDelUniverso actual = getTablero().inspeccionarPosicion();
        if(actual!=null)
            switch (actual){
                case ASTEROIDE -> {
                    getBitacorasNave().agregarRegistro(new RegistroBitacora("IMPACTO CON ASTEROIDE"));
                    float energiaTomada = rand.nextInt(5)+1;
                    if (getNave().getEnergia()-energiaTomada<=0)
                        throw new EnergiaInsuficienteException("SE AGOTO LA ENERGIA DE LA NAVE");
                    else
                        consumeEnergiaNave(energiaTomada);
                }
                case NAVE_COMBATE ->  {
                    switch (getNave().atacadoPorCombate()){
                        case -1 -> throw new NaveDestruidaException("LA NAVE PERDIO EL ENFRENTAMIENTO FRENTE A UNA NAVE DE COMBATE");
                        case 0 -> getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE DE COMBATE - PACTO DE PAZ REALIZADO"));
                        case 1 -> getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE DE COMBATE - EL OPONENTE NO PUDO CON TU EQUIPO Y RESULTASTE VENCEDOR"));
                    }
                }
                case ESTACION_CARGA -> {
                    getBitacorasNave().agregarRegistro(new RegistroBitacora("LLEGADA A ESTACION DE CARGA"));
                    int opcion = rand.nextInt(2)+1;
                    float carga = rand.nextInt(50)+1;
                    switch (opcion) {
                        case 1 -> {
                            if (getNave().getCombustible()+carga > Nave.MAX_COMBUSTIBLE)
                                cargaComustibleNaveFull();
                            else
                                cargaCombustibleNave(carga);
                        }
                        case 2 -> {
                            if (getNave().getEnergia()+carga > Nave.MAX_ENERGIA)
                                cargaEnergiaNaveFull();
                            else
                                cargaEnergiaNave(carga);
                        }
                    }
                }
                case NAVE_CARGUERA -> {
                    switch (getNave().atacadoPorCarguero()){
                        case -1 -> throw new NaveDestruidaException("LA NAVE PERDIO EL ENFRENTAMIENTO FRENTE A UNA NAVE CARGUERA");
                        case 0 -> getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE CARGUERA - PACTO DE PAZ REALIZADO"));
                        case 1 -> getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE CARGUERA - EL OPONENTE NO PUDO CON TU EQUIPO Y RESULTASTE VENCEDOR"));
                    }
                }
                case NAVE_EXPLORADORA -> {
                    switch (getNave().atacadoPorExplorador()){
                        case -1 -> throw new NaveDestruidaException("LA NAVE PERDIO EL ENFRENTAMIENTO FRENTE A UNA NAVE EXPLORADORA");
                        case 0 -> getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE EXPLORADORA - PACTO DE PAZ REALIZADO"));
                        case 1 -> getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE EXPLORADORA - EL OPONENTE NO PUDO CON TU EQUIPO Y RESULTASTE VENCEDOR"));
                    }
                }
            }

    }

    private boolean analizaAvanceRadarCortoAlcance(Posicion pos){
        int x = pos.getX() + 1;
        int y = pos.getY() + 1;
        ElementoDelUniverso[][] radarCortoAlcance = getTablero().observarAlrededores();
        if (radarCortoAlcance[x][y]==null){
                System.out.println("DETECTO NULL");
            return true;
        }else
            switch (radarCortoAlcance[x][y]){
                case ESTACION_CARGA -> {
                    System.out.println("DETECTO ALGO GOOD");
                    return true;
                }//ver cuantos usamos y como interactuan
                default -> {
                    System.out.println("DETECTO ALGO MALO");
                    return false;
                }
        }
    }

    public void mueveNave() throws CantCombustibleInvalidaException, CombustibleInsuficienteException {
        Posicion nuevaPos =null;
        int Xnave = getTablero().getPosicionNave().getX();
        int Ynave = getTablero().getPosicionNave().getY();
        //si es negativo ,el objetivo esta a la derecha (mueve de a 1)
        //si es positivo , esta a la izuiqerda (mueve de a -1)
        //si es cero , estan en la misma linea
        int orientacionX = getTablero().getPosicionNave().getX()-getTablero().getObjetivo().getX();
        //si es negativo ,el objetivo esta arriba (mueve de a 1)
        //si es positivo , esta abajo (mueve de a -1)
        //si es cero , estan en la misma linea
        int orientacionY = getTablero().getPosicionNave().getY()-getTablero().getObjetivo().getY();
        //claramente si ambas dan cero es poreque llegue
        //primero muevo en X , luego en Y
        if (orientacionX != 0) {
            if (orientacionX<0)
                nuevaPos = new Posicion(1,0);
            if (orientacionX>0)
                nuevaPos = new Posicion(-1,0);
        }else if(orientacionY != 0){
            if (orientacionY<0)
                nuevaPos = new Posicion(0,1);
            if (orientacionY>0)
                nuevaPos = new Posicion(0,-1);
        }
        if (orientacionX !=0 || orientacionY!=0){
            if (!analizaAvanceRadarCortoAlcance(nuevaPos) && getMotor().getEstadoActual().getIdEstado()==MotorWarp.DISPONIBLE && getNave().getCombustible()>5){
                getNave().consumeCombustible(5);
                getBitacorasNave().agregarRegistro(new RegistroBitacora("SALTO WARP"));
                //getMotor().saltoWarp();
            }else{
                if (getNave().getCombustible() < 2)
                    throw new CombustibleInsuficienteException("NAVE SIN COMBUSTIBLE SUFICIENTE PARA MOVERSE");
                getNave().consumeCombustible(2);
                getBitacorasNave().agregarRegistro(new RegistroBitacora("SALTO NORMAL"));
                //getMotor().saltar();
            }
            getTablero().moverNave(new Posicion(getTablero().getPosicionNave().getX() + nuevaPos.getX(),getTablero().getPosicionNave().getY() + nuevaPos.getY()));
        }
    }


}
