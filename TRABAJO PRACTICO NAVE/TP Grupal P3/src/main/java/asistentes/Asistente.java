package asistentes;
import CapaNegocio.IAsistente;
import destinoytablero.*;
import elementosUniverso.ElementoDelUniverso;
import elementosUniverso.EstacionCarga;
import nave.*;
import motorwarp.*;
import bitacora.*;
import exceptions.*;
import tripulantes.Tripulante;

public class Asistente implements IAsistente {
    public static int tick = 0;

    private final Nave nave;
    private final Bitacora bitacorasNave;
    private final MotorWarp motor;
    private final Tablero tablero;
    private final Destino destino;

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
        this.motor = new MotorWarp();
        this.destino = new Destino(getTablero());
        
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

        if (cantCarga <= 0)
            throw new CantCombustibleInvalidaException("Monto Invalido");

        if (getNave().getCombustible()+cantCarga > Nave.MAX_COMBUSTIBLE)
            throw new CantCombustibleInvalidaException("El monto supera la capacidad maxima del tanque");

        getNave().setCombustible(getNave().getCombustible()+cantCarga);
        getBitacorasNave().agregarRegistro(new RegistroRecursos("COMBUSTIBLE",cantCarga,getNave().getCombustible()));
        
        assert invariant();
    }

    /**
     * <b>pre:</b> cantConsumida > 0
     * <b>post:</b> Se consume la cantidad de combustible especificada
     * 
     * @param cantConsumida cantidad de combustible consumida.
     */
    
    public void consumeCombustibleNave(float cantConsumida) throws CantCombustibleInvalidaException, NaveDestruidaException {
        assert cantConsumida > 0 : "La cantidad de combustible consumida debe de ser mayor que cero";
        assert invariant();
        
        if (cantConsumida<=0)
            throw new CantCombustibleInvalidaException("Monto Invalido");

        if (getNave().getCombustible()-cantConsumida<=0)
            throw new NaveDestruidaException("El Combustible se agotó");

        getNave().setCombustible(getNave().getCombustible()-cantConsumida);
        getBitacorasNave().agregarRegistro(new RegistroRecursos("COMBUSTIBLE",-cantConsumida,getNave().getCombustible()));
        
        assert invariant();
    }

    
    /**
    * Metodo para cargar totalmente el combustible de la nave.
    * Resta el maximo de combustible que soporta la nave con la cantidad de combustible actual,
    * el resultado es el restante de combustible que necesita la nave para estar llena.
    * 
    *<b>post:</b> Carga combustible hasta su maximo
    */
    public void cargaCombustibleNaveFull()throws CantCombustibleInvalidaException{
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
    
    public void aumentaDesgasteNave(float cantDesgaste) throws CantDesgasteInvalidaException, NaveDestruidaException {
        assert cantDesgaste > 0 : "La cantidad de desgaste debe de ser mayor que cero";
        assert invariant();     
        
        if (cantDesgaste<=0)
            throw new CantDesgasteInvalidaException("Monto Invalido");

        if (getNave().getDesgaste()+cantDesgaste >= Nave.MAX_DESGASTE )
            throw new NaveDestruidaException("Se alcanzo el desgaste maximo de la nave");

        getNave().setDesgaste(getNave().getDesgaste() + cantDesgaste);
        
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

        getBitacorasNave().agregarRegistro(new RegistroRecursos("DESGASTE",-getNave().getDesgaste(),0));
        getNave().setDesgaste(0);
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

        if (cantEnergia<=0)
            throw new CantEnergiaInvalidaException("Monto Invalido");

        if (getNave().getEnergia()+cantEnergia > Nave.MAX_ENERGIA)
            throw new CantEnergiaInvalidaException("El monto supera la capacidad maxima del tanque");

        getNave().setEnergia(getNave().getEnergia()+cantEnergia);
        getBitacorasNave().agregarRegistro(new RegistroRecursos("ENERGIA",cantEnergia, getNave().getEnergia()));

        assert invariant();
    }

    /**
     * <b>pre:</b> cantConsumida > 0
     * <b>post:</b> Se consume la cantidad de energía especificada de la nave
     * 
     * @param cantConsumida cantidad de energía consumida.
     */
    
    public void consumeEnergiaNave(float cantConsumida) throws CantEnergiaInvalidaException, NaveDestruidaException {
        assert cantConsumida > 0 : "La cantidad de energia consumida debe de ser mayor que cero";
        assert invariant();       
        
        if (cantConsumida<=0)
            throw new CantEnergiaInvalidaException("Monto Invalido");

        if (getNave().getEnergia()-cantConsumida<=0)
            throw new NaveDestruidaException("Las reservas de Energia se agotaron");

        getNave().setEnergia(getNave().getEnergia()-cantConsumida);
        getBitacorasNave().agregarRegistro(new RegistroRecursos("ENERGIA",-cantConsumida,getNave().getEnergia()));
        
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
     * <b>pre:</b> tripulante != null
     * <b>post:</b> Se intenta agregar un tripulante a la nave, de no ser posible se lanza una excepcion
     * 
     * @param tripulante Objeto de tipo tripulante que se desea agregar.
     */
    
    public void agregaTripulante(Tripulante tripulante) throws ErrorAgregarTripulacionException {
        assert tripulante != null : "El tripulante no puede ser nulo";
        assert invariant();
        
        getNave().agregaTripulante(tripulante);

        assert invariant();
    }


    public void eliminaTripulante(Tripulante t){
        this.nave.getTripulacion().remove(t);
    }


    public void chequeaPosicionActual() throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException {
        ElementoDelUniverso actual = getTablero().inspeccionarPosicion();
        if (actual!=null)
            actual.interactuaConNave(this);
    }

    public void darTurnosTablero(int i) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException {
        try {
            for (int j = 0; j < i; j++) {
                destino.turnoDestino();
                getTablero().notificarObservadores("ACTUALIZACION_VISTA_TABLERO");
                Thread.sleep(1500);
                chequeaPosicionActual();
            }
        }catch (InterruptedException e) {
            getBitacorasNave().agregarRegistro(new RegistroError("ERROR DESCONOCIDO",e));
        }
    }

    private boolean puedeSaltarNormal(Posicion pos){
        int x = pos.getX() + 1;
        int y = pos.getY() + 1;
        ElementoDelUniverso[][] radarCortoAlcance = getTablero().observarAlrededores();
        ElementoDelUniverso actual = radarCortoAlcance[x][y];
        return actual == null || actual.equals(new EstacionCarga()) || siguienteEsObj(pos);
    }

    private boolean siguienteEsObj(Posicion pos){
        int xNuevo = getTablero().getPosicionNave().getX() + pos.getX();
        int yNuevo = getTablero().getPosicionNave().getY() + pos.getY();
        return (xNuevo == getTablero().getObjetivo().getX() && yNuevo == getTablero().getObjetivo().getY());
    }

    public void mueveNave() throws CantEnergiaInvalidaException, CantCombustibleInvalidaException, NaveDestruidaException, CantDesgasteInvalidaException {
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
            if (!saltoFuera(nuevaPos) && !puedeSaltarNormal(nuevaPos) &&getNave().getEnergia()>MotorWarp.CONSUMO_ENERGIA_SALTO_WARP){
                consumeEnergiaNave(MotorWarp.CONSUMO_ENERGIA_SALTO_WARP);
                nuevaPos = new Posicion(nuevaPos.getX()*2,nuevaPos.getY()*2);
                getMotor().saltoWarp(getTablero(),this,nuevaPos);
                getBitacorasNave().agregarRegistro(new RegistroBitacora("SE REALIZO EL SALTO WARP - SE AVANZARON DOS CASILLEROS"));
            }else{
                consumeCombustibleNave(MotorWarp.CONSUMO_COMBUSTIBLE_SALTO);
                getBitacorasNave().agregarRegistro(getMotor().saltar(getTablero(),nuevaPos));
            }
        }
    }

    private boolean saltoFuera(Posicion posicion) {
        int xNueva = getTablero().getPosicionNave().getX() + posicion.getX()*2;
        int yNueva = getTablero().getPosicionNave().getY() + posicion.getY()*2;

        return getTablero().getX() < xNueva || getTablero().getY() < yNueva || xNueva<0 || yNueva<0;
    }

}
