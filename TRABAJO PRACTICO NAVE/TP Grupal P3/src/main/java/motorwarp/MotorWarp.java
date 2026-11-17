package motorwarp;
import bitacora.*;
import destinoytablero.*;
import exceptions.*;
import asistentes.Asistente;

public class MotorWarp extends Observable implements State{

    public static final int DISPONIBLE = 0;
    public static final int PREPARANDO_SALTO = 1;
    public static final int SALTO_WARP = 2;
    public static final int ENFRIAMIENTO = 3;

    public static final int CONSUMO_ENERGIA_SALTO_WARP = 5;
    public static final int CONSUMO_COMBUSTIBLE_SALTO = 2;
    public static final int CONSUMO_DESGASTE_SALTO_WARP = 3;

    private State estadoActual;
    
    public MotorWarp () {
        setEstado(new Disponible(this));
    }

    public State getEstadoActual() {
        return estadoActual;
    }

    public void setEstado(State estadoActual) {
        this.estadoActual = estadoActual;
    }

    /**
    * Metodo que permite un cambio de estado hacia disponible.
    * Solo puede ser de enfriamiento --> disponible.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser enfriamiento
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto enfriamiento.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a disponible.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, preparaSalto o saltoWarp) -/-> disponible.
    */
    public void disponible() throws EstadoInvalidoException {
        estadoActual.disponible();
    }

    /**
    * Metodo que permite un cambio de estado hacia prepararSalto.
    * Solo puede ser de disponible --> prepararSalto.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser disponible
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto disponible.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a prepararSalto.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(preparaSalto, saltoWarp o enfriamiento) -/-> prepararSalto.
    */
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException, CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        estadoActual.prepararSalto(tablero, ac, direccion);
    }


    /**
    * Metodo que permite un cambio de estado hacia saltoWarp.
    * Solo puede ser de prepararSalto --> saltoWarp.
    *
    * <b>pre:</b> (Exito) El estado actual debe ser prepararSalto.
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto prepararSalto.
    *
    * <b>post:</b> (Exito) Se actualiza el estado actual a saltoWarp.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado.
    *
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, saltoWarp o enfriamiento) -/-> saltoWarp.
    */
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException, NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException, CantDesgasteInvalidaException {
        estadoActual.saltando(tablero, ac, direccion);
    }

    /**
    * Metodo que permite un cambio de estado hacia disponible.
    * Solo puede ser de saltoWarp --> enfriamiento.
    *
    * <b>pre:</b> (Exito) El estado actual debe ser saltoWarp.
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto saltoWarp.
    *
    * <b>post:</b> (Exito) Se actualiza el estado actual a enfriamiento.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado.
    *
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, preparaSalto o enfriamiento) -/-> enfriamiento.
    */
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException, CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        estadoActual.enfriamiento(tablero, ac, direccion);
    }

    public void saltoWarp(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        estadoActual.prepararSalto(tablero,ac,direccion);
    }

    public RegistroMotor saltar (Tablero tablero,Posicion direccion)  {
        int x = tablero.getPosicionNave().getX();
        int y = tablero.getPosicionNave().getY();
        tablero.moverNave(new Posicion(x + direccion.getX(),y + direccion.getY()));
        return new RegistroMotor("LA NAVE AVANZA UN CASILLERO",getEstadoActual());
    }

}
