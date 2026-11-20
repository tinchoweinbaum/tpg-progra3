package CapaDatos.asistentes;
import CapaNegocio.IAsistente;
import CapaDatos.destinoytablero.*;
import CapaDatos.elementosUniverso.ElementoDelUniverso;
import CapaDatos.elementosUniverso.EstacionCarga;
import CapaDatos.nave.*;
import CapaDatos.motorwarp.*;
import CapaDatos.bitacora.*;
import CapaDatos.exceptions.*;
import CapaDatos.tripulantes.Tripulante;

/**
 * Clase encargada de coordinar las operaciones de la CapaDatos.nave, el consumo y recarga de recursos,
 * la gestión de la tripulación, el registro en bitácora y la navegación en el tablero.
 * <p><b>Invariante de clase:</b> CapaDatos.nave != null && bitacorasNave != null && motor != null && tablero != null && destino != null</p>
 */
public class Asistente implements IAsistente {

    private final Nave nave;
    private final Bitacora bitacorasNave;
    private final MotorWarp motor;
    private final Tablero tablero;
    private final Destino destino;

    private boolean invariant() {
        return this.nave != null && this.bitacorasNave != null && this.motor != null;
    }

    /**
     * Constructor que instancia y asigna las referencias principales del asistente de navegación.
     *
     * @param nave Referencia a un objeto Nave válido.
     * @param tablero Referencia al tablero espacial donde opera la CapaDatos.nave.
     * @pre CapaDatos.nave != null && tablero != null
     * @post Se asocia el asistente con la CapaDatos.nave y el tablero, y se inicializan bitacorasNave, motor (en estado Disponible) y destino distintos de null.
     */
    public Asistente(Nave nave,Tablero tablero){
        assert nave != null : "La CapaDatos.nave no puede ser nula";
        
        this.nave = nave;
        this.tablero = tablero;
        this.bitacorasNave = new Bitacora();
        this.motor = new MotorWarp();
        this.destino = new Destino(getTablero());
        
        assert invariant(): "Fallo en crear la CapaDatos.bitacora y/o motorWarp";
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

    public Destino getDestino() {
        return destino;
    }

    public Bitacora getBitacorasNave() {
        return bitacorasNave;
    }

    /**
     * Incrementa el combustible actual de la CapaDatos.nave y deja constancia en la bitácora.
     *
     * @param cantCarga Cantidad de combustible a cargar.
     * @throws CantCombustibleInvalidaException Si cantCarga <= 0 o si la carga supera Nave.MAX_COMBUSTIBLE.
     * @pre cantCarga > 0 && getNave().getCombustible() + cantCarga <= Nave.MAX_COMBUSTIBLE
     * @post Se incrementa el combustible de la CapaDatos.nave en cantCarga y se agrega un RegistroRecursos en la bitácora.
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
     * Reduce el combustible actual de la CapaDatos.nave y registra el consumo en la bitácora.
     *
     * @param cantConsumida Cantidad de combustible a consumir.
     * @throws CantCombustibleInvalidaException Si cantConsumida <= 0.
     * @throws NaveDestruidaException Si el combustible restante resulta menor o igual a 0.
     * @pre cantConsumida > 0 && getNave().getCombustible() - cantConsumida > 0
     * @post Se descuenta cantConsumida del combustible de la CapaDatos.nave y se agrega un RegistroRecursos en la bitácora.
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
     * Carga el tanque de combustible de la CapaDatos.nave hasta su capacidad máxima.
     *
     * @throws CantCombustibleInvalidaException Si el tanque de combustible ya se encuentra lleno.
     * @pre getNave().getCombustible() < Nave.MAX_COMBUSTIBLE
     * @post getNave().getCombustible() == Nave.MAX_COMBUSTIBLE y se registra la recarga en la bitácora.
     */
    public void cargaCombustibleNaveFull()throws CantCombustibleInvalidaException{
        if (getNave().getCombustible()==Nave.MAX_COMBUSTIBLE)
            throw new CantCombustibleInvalidaException("Tanque lleno");

        this.cargaCombustibleNave(Nave.MAX_COMBUSTIBLE - this.nave.getCombustible());
    }

    /**
     * Incrementa el nivel de desgaste estructural de la CapaDatos.nave.
     *
     * @param cantDesgaste Cantidad de desgaste a sumar.
     * @throws CantDesgasteInvalidaException Si cantDesgaste <= 0.
     * @throws NaveDestruidaException Si el desgaste acumulado alcanza o supera Nave.MAX_DESGASTE.
     * @pre cantDesgaste > 0 && getNave().getDesgaste() + cantDesgaste < Nave.MAX_DESGASTE
     * @post Se incrementa el desgaste de la CapaDatos.nave en cantDesgaste.
     */
    public void aumentaDesgasteNave(float cantDesgaste) throws CantDesgasteInvalidaException, NaveDestruidaException {
        assert cantDesgaste > 0 : "La cantidad de desgaste debe de ser mayor que cero";
        assert invariant();     
        
        if (cantDesgaste<=0)
            throw new CantDesgasteInvalidaException("Monto Invalido");

        if (getNave().getDesgaste()+cantDesgaste >= Nave.MAX_DESGASTE )
            throw new NaveDestruidaException("Se alcanzo el desgaste maximo de la CapaDatos.nave");

        getNave().setDesgaste(getNave().getDesgaste() + cantDesgaste);
        
        assert invariant(); 
    }

    /**
     * Realiza el mantenimiento completo de la CapaDatos.nave restableciendo su desgaste a cero y registrándolo en bitácora.
     *
     * @throws CantDesgasteInvalidaException Si el desgaste actual de la CapaDatos.nave es menor a 80 unidades.
     * @pre getNave().getDesgaste() >= 80
     * @post getNave().getDesgaste() == 0 y se agrega un RegistroRecursos en la bitácora.
     */
    public void reparaDesgasteNaveFull()throws CantDesgasteInvalidaException{
        if (this.getNave().getDesgaste()<80)
            throw new CantDesgasteInvalidaException("No puede realizarse el mantenimiento con un nivel de Desgaste menor a 80");

        getBitacorasNave().agregarRegistro(new RegistroRecursos("DESGASTE",-getNave().getDesgaste(),0));
        getNave().setDesgaste(0);
    }

    /**
     * Incrementa la reserva de energía de la CapaDatos.nave y registra la operación en la bitácora.
     *
     * @param cantEnergia Cantidad de energía a cargar.
     * @throws CantEnergiaInvalidaException Si cantEnergia <= 0 o si supera Nave.MAX_ENERGIA.
     * @pre cantEnergia > 0 && getNave().getEnergia() + cantEnergia <= Nave.MAX_ENERGIA
     * @post Se incrementa la energía de la CapaDatos.nave en cantEnergia y se agrega un RegistroRecursos en la bitácora.
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
     * Reduce la reserva de energía de la CapaDatos.nave y registra el consumo en la bitácora.
     *
     * @param cantConsumida Cantidad de energía a consumir.
     * @throws CantEnergiaInvalidaException Si cantConsumida < 0.
     * @throws NaveDestruidaException Si la energía restante resulta menor o igual a 0.
     * @pre cantConsumida > 0 && getNave().getEnergia() - cantConsumida > 0
     * @post Se descuenta cantConsumida de la energía de la CapaDatos.nave y se agrega un RegistroRecursos en la bitácora.
     */
    public void consumeEnergiaNave(float cantConsumida) throws CantEnergiaInvalidaException, NaveDestruidaException {
        assert cantConsumida > 0 : "La cantidad de energia consumida debe de ser mayor que cero";
        assert invariant();       
        
        if (cantConsumida<0)
            throw new CantEnergiaInvalidaException("Monto Invalido");

        if (getNave().getEnergia()-cantConsumida<=0)
            throw new NaveDestruidaException("Las reservas de Energia se agotaron");

        getNave().setEnergia(getNave().getEnergia()-cantConsumida);
        if (cantConsumida>0)
            getBitacorasNave().agregarRegistro(new RegistroRecursos("ENERGIA",-cantConsumida,getNave().getEnergia()));
        
        assert invariant();
    }

    /**
     * Recarga la reserva de energía de la CapaDatos.nave hasta su capacidad máxima.
     *
     * @throws CantEnergiaInvalidaException Si la energía ya se encuentra al máximo.
     * @pre getNave().getEnergia() < Nave.MAX_ENERGIA
     * @post getNave().getEnergia() == Nave.MAX_ENERGIA y se registra la recarga en la bitácora.
     */
    public void cargaEnergiaNaveFull()throws CantEnergiaInvalidaException{
        if(getNave().getEnergia()==Nave.MAX_ENERGIA)
            throw new CantEnergiaInvalidaException("Tanque lleno");
        this.cargaEnergiaNave(Nave.MAX_ENERGIA - this.nave.getEnergia());
    }

    /**
     * Incorpora un nuevo tripulante a la tripulación de la CapaDatos.nave.
     *
     * @param tripulante Objeto Tripulante que se desea agregar.
     * @throws ErrorAgregarTripulacionException Si no se cumplen las condiciones de la CapaDatos.nave para admitir al tripulante.
     * @pre tripulante != null
     * @post El tripulante queda añadido a la lista de tripulación de la CapaDatos.nave.
     */
    public void agregaTripulante(Tripulante tripulante) throws ErrorAgregarTripulacionException {
        assert tripulante != null : "El tripulante no puede ser nulo";
        assert invariant();
        
        getNave().agregaTripulante(tripulante);

        assert invariant();
    }

    /**
     * Elimina un tripulante específico de la tripulación de la CapaDatos.nave.
     *
     * @param t Tripulante a remover.
     * @pre t != null
     * @post El tripulante t deja de pertenecer a la tripulación de la CapaDatos.nave (si estaba presente).
     */
    public void eliminaTripulante(Tripulante t){
        this.nave.getTripulacion().remove(t);
    }

    /**
     * Inspecciona la celda actual de la CapaDatos.nave en el tablero y ejecuta la interacción si existe un elemento.
     *
     * @throws NaveDestruidaException Si la interacción provoca la destrucción de la CapaDatos.nave.
     * @throws CantCombustibleInvalidaException Si ocurre un error en la modificación de combustible durante la interacción.
     * @throws CantEnergiaInvalidaException Si ocurre un error en la modificación de energía durante la interacción.
     * @pre getTablero() != null
     * @post Si hay un ElementoDelUniverso en la posición actual de la CapaDatos.nave, se ejecuta su método interactuaConNave(this).
     */
    public void chequeaPosicionActual() throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException {
        ElementoDelUniverso actual = getTablero().inspeccionarPosicion();
        if (actual!=null)
            actual.interactuaConNave(this);
    }

    /**
     * Avanza la simulación del tablero durante una cantidad determinada de turnos, actualizando el entorno y la vista en cada paso.
     *
     * @param i Cantidad de turnos a ejecutar.
     * @throws CantCombustibleInvalidaException Si ocurre un error con el combustible durante un evento de turno.
     * @throws NaveDestruidaException Si la CapaDatos.nave es destruida durante alguno de los turnos.
     * @throws CantEnergiaInvalidaException Si ocurre un error con la energía durante un evento de turno.
     * @pre i >= 0 && destino != null && getTablero() != null
     * @post Se ejecutan i turnos de destino, se verifica la posición actual y se notifica a los observadores del tablero en cada iteración.
     */
    public void darTurnosTablero(int i) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException {
        try {
            for (int j = 0; j < i; j++) {
                destino.turnoDestino();
                chequeaPosicionActual();
                Thread.sleep(1000);
                getTablero().notificarObservadores("ACTUALIZACION_VISTA_TABLERO");
            }
        }catch (InterruptedException e) {
            getBitacorasNave().agregarRegistro(new RegistroError("ERROR DESCONOCIDO",e));
        }
    }

    /**
     * Evalúa mediante el radar de corto alcance si es seguro realizar un desplazamiento normal de una casilla hacia el desplazamiento relativo indicado.
     *
     * @param pos Vector de desplazamiento relativo (valores en {-1, 0, 1}).
     * @return true si la celda destino está vacía, contiene una EstacionCarga o corresponde al objetivo; false en caso contrario.
     * @pre pos != null && pos.getX() >= -1 && pos.getX() <= 1 && pos.getY() >= -1 && pos.getY() <= 1
     * @post Retorna la viabilidad del salto normal sin alterar el estado del tablero ni de la CapaDatos.nave.
     */
    private boolean puedeSaltarNormal(Posicion pos){
        int x = pos.getX() + 1;
        int y = pos.getY() + 1;
        ElementoDelUniverso[][] radarCortoAlcance = getTablero().observarAlrededores();
        ElementoDelUniverso actual = radarCortoAlcance[x][y];
        return actual == null || actual.equals(new EstacionCarga()) || siguienteEsObj(pos);
    }

    /**
     * Verifica si el desplazamiento relativo indicado posiciona a la CapaDatos.nave exactamente sobre las coordenadas del objetivo actual.
     *
     * @param pos Vector de desplazamiento relativo.
     * @return true si la posición resultante coincide con la posición del objetivo; false en caso contrario.
     * @pre pos != null && getTablero().getObjetivo() != null
     * @post Retorna true si (xActual + pos.x == xObjetivo && yActual + pos.y == yObjetivo), sin modificar el estado.
     */
    private boolean siguienteEsObj(Posicion pos){
        int xNuevo = getTablero().getPosicionNave().getX() + pos.getX();
        int yNuevo = getTablero().getPosicionNave().getY() + pos.getY();
        return (xNuevo == getTablero().getObjetivo().getX() && yNuevo == getTablero().getObjetivo().getY());
    }

    /**
     * Calcula la dirección hacia el objetivo y desplaza la CapaDatos.nave un casillero (salto normal) o dos casilleros (salto warp) para esquivar obstáculos.
     *
     * @throws CantEnergiaInvalidaException Si hay un error en el consumo de energía.
     * @throws CantCombustibleInvalidaException Si hay un error en el consumo de combustible.
     * @throws NaveDestruidaException Si la CapaDatos.nave se queda sin recursos o sufre daño fatal al moverse.
     * @throws CantDesgasteInvalidaException Si hay un error al aplicar desgaste durante el salto warp.
     * @pre getTablero() != null && getTablero().getObjetivo() != null && getMotor() != null
     * @post Si la CapaDatos.nave no estaba en el objetivo, avanza 1 o 2 casilleros hacia él, consume los recursos correspondientes y registra la acción en bitácora.
     */
    public void mueveNave() throws CantEnergiaInvalidaException, CantCombustibleInvalidaException, NaveDestruidaException, CantDesgasteInvalidaException {
        Posicion nuevaPos =null;
        int orientacionX = getTablero().getPosicionNave().getX() - getTablero().getObjetivo().getX();
        int orientacionY = getTablero().getPosicionNave().getY() - getTablero().getObjetivo().getY();
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
                nuevaPos = new Posicion(nuevaPos.getX()*2,nuevaPos.getY()*2);
                getMotor().saltoWarp(getTablero(),this,nuevaPos);
                getBitacorasNave().agregarRegistro(new RegistroBitacora("SE REALIZO EL SALTO WARP - SE AVANZARON DOS CASILLEROS"));
            }else{
                consumeCombustibleNave(MotorWarp.CONSUMO_COMBUSTIBLE_SALTO);
                getBitacorasNave().agregarRegistro(getMotor().saltar(getTablero(),nuevaPos));
            }
        }
    }

    /**
     * Determina si un salto warp de dos casilleros en la dirección indicada saldría de los límites del tablero o pasaría por encima del objetivo sin detenerse en él.
     *
     * @param posicion Vector de dirección unitario del movimiento.
     * @return true si el salto doble excede los límites del tablero o saltea el objetivo; false si es seguro ejecutarlo.
     * @pre posicion != null && getTablero() != null
     * @post Retorna true si el salto warp es inválido por límites o por sobrepasar el objetivo, sin alterar el estado del sistema.
     */
    private boolean saltoFuera(Posicion posicion) {
        int xAct = getTablero().getPosicionNave().getX();
        int yAct = getTablero().getPosicionNave().getY();
        int xNueva = xAct + posicion.getX()*2;
        int yNueva = yAct + posicion.getY()*2;

        if (getTablero().getX() <= xNueva || getTablero().getY() <= yNueva || xNueva < 0 || yNueva < 0)
            return true;

        if (getTablero().getObjetivo() != null) {
            int xObj = getTablero().getObjetivo().getX();
            int yObj = getTablero().getObjetivo().getY();

            int xMedio = xAct + posicion.getX();
            int yMedio = yAct + posicion.getY();

            if (xMedio == xObj && yMedio == yObj) {
                return true;
            }
        }

        return false;
    }

}
