package CapaDatos.motorwarp;
import CapaDatos.bitacora.*;
import CapaDatos.destinoytablero.*;
import CapaDatos.exceptions.*;
import CapaDatos.asistentes.Asistente;

/**
 * Contexto del patrón State que representa el motor de propulsión warp de la CapaDatos.nave,
 * gestionando sus estados, notificaciones a observadores y desplazamientos.
 * <p><b>Invariante de clase:</b> estadoActual != null</p>
 */
public class MotorWarp extends Observable implements State{

    public static final int DISPONIBLE = 0;
    public static final int PREPARANDO_SALTO = 1;
    public static final int SALTO_WARP = 2;
    public static final int ENFRIAMIENTO = 3;
    public static final int CONSUMO_ENERGIA_SALTO_WARP = 5;
    public static final int CONSUMO_COMBUSTIBLE_SALTO = 2;
    public static final int CONSUMO_DESGASTE_SALTO_WARP = 3;

    private State estadoActual;

    /**
     * Constructor que inicializa el motor warp en estado Disponible.
     *
     * @pre true
     * @post estadoActual != null && estadoActual instanceof Disponible
     */
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
     * Solicita la transición del motor hacia el estado Disponible (válida únicamente desde Enfriamiento).
     *
     * @throws EstadoInvalidoException Si el estado actual no es Enfriamiento.
     * @pre estadoActual != null
     * @post Si estadoActual era Enfriamiento, pasa a Disponible y notifica "MOTOR_DISPONIBLE"; de lo contrario lanza EstadoInvalidoException.
     */
    public void disponible() throws EstadoInvalidoException {
        estadoActual.disponible();
    }

    /**
     * Solicita la transición del motor hacia PreparandoSalto e inicia la secuencia completa de salto warp.
     *
     * @param tablero Tablero donde se encuentra la CapaDatos.nave.
     * @param ac Asistente que controla la CapaDatos.nave y los turnos.
     * @param direccion Vector de desplazamiento del salto.
     * @throws EstadoInvalidoException Si el estado actual no es Disponible.
     * @throws CantCombustibleInvalidaException Si ocurre un error de combustible en los turnos de espera.
     * @throws NaveDestruidaException Si la CapaDatos.nave es destruida durante la secuencia.
     * @throws CantEnergiaInvalidaException Si ocurre un error al descontar energía.
     * @throws CantDesgasteInvalidaException Si ocurre un error al aplicar desgaste.
     * @pre estadoActual != null && tablero != null && ac != null && direccion != null
     * @post Si estadoActual era Disponible, ejecuta el ciclo de salto warp; de lo contrario lanza EstadoInvalidoException.
     */
    public void prepararSalto(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException, CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        estadoActual.prepararSalto(tablero, ac, direccion);
    }


    /**
     * Solicita la ejecución del salto warp desde el estado PreparandoSalto hacia SaltoWarp.
     *
     * @param tablero Tablero donde se encuentra la CapaDatos.nave.
     * @param ac Asistente que gestiona los recursos de la CapaDatos.nave.
     * @param direccion Vector de desplazamiento del salto.
     * @throws EstadoInvalidoException Si el estado actual no es PreparandoSalto.
     * @throws NaveDestruidaException Si la CapaDatos.nave es destruida durante el salto o enfriamiento.
     * @throws CantEnergiaInvalidaException Si ocurre un error con la energía.
     * @throws CantCombustibleInvalidaException Si ocurre un error con el combustible.
     * @throws CantDesgasteInvalidaException Si ocurre un error con el desgaste.
     * @pre estadoActual != null && tablero != null && ac != null && direccion != null
     * @post Si estadoActual era PreparandoSalto, realiza el salto y continúa al enfriamiento; de lo contrario lanza EstadoInvalidoException.
     */
    public void saltando(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException, NaveDestruidaException, CantEnergiaInvalidaException, CantCombustibleInvalidaException, CantDesgasteInvalidaException {
        estadoActual.saltando(tablero, ac, direccion);
    }

    /**
     * Solicita la transición del motor hacia Enfriamiento (válida únicamente desde SaltoWarp).
     *
     * @param tablero Tablero donde se encuentra la CapaDatos.nave.
     * @param ac Asistente que gestiona turnos y desgaste.
     * @param direccion Vector de desplazamiento utilizado.
     * @throws EstadoInvalidoException Si el estado actual no es SaltoWarp.
     * @throws CantCombustibleInvalidaException Si ocurre un error de combustible en el turno de enfriamiento.
     * @throws NaveDestruidaException Si el desgaste destruye la CapaDatos.nave.
     * @throws CantEnergiaInvalidaException Si ocurre un error de energía en el turno de enfriamiento.
     * @throws CantDesgasteInvalidaException Si ocurre un error al aplicar desgaste.
     * @pre estadoActual != null && tablero != null && ac != null && direccion != null
     * @post Si estadoActual era SaltoWarp, ejecuta el enfriamiento, aplica desgaste y vuelve a Disponible; de lo contrario lanza EstadoInvalidoException.
     */
    public void enfriamiento(Tablero tablero, Asistente ac, Posicion direccion) throws EstadoInvalidoException, CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        estadoActual.enfriamiento(tablero, ac, direccion);
    }

    /**
     * Inicia un salto warp delegando la ejecución al estado actual mediante prepararSalto.
     *
     * @param tablero Tablero donde se desplazará la CapaDatos.nave.
     * @param ac Asistente de la CapaDatos.nave.
     * @param direccion Vector de desplazamiento de dos casilleros.
     * @throws CantCombustibleInvalidaException Si ocurre un error con el combustible.
     * @throws NaveDestruidaException Si la CapaDatos.nave es destruida durante el proceso.
     * @throws CantEnergiaInvalidaException Si ocurre un error con la energía.
     * @throws CantDesgasteInvalidaException Si ocurre un error con el desgaste.
     * @pre estadoActual != null && tablero != null && ac != null && direccion != null
     * @post Se dispara la secuencia de salto warp en la dirección indicada.
     */
    public void saltoWarp(Tablero tablero, Asistente ac, Posicion direccion) throws CantCombustibleInvalidaException, NaveDestruidaException, CantEnergiaInvalidaException, CantDesgasteInvalidaException {
        estadoActual.prepararSalto(tablero,ac,direccion);
    }

    /**
     * Realiza un desplazamiento convencional de un casillero en el tablero sin utilizar el salto warp.
     *
     * @param tablero Tablero donde se mueve la CapaDatos.nave.
     * @param direccion Vector de desplazamiento unitario.
     * @return RegistroMotor documentando el avance y el estado actual del motor.
     * @pre tablero != null && tablero.getPosicionNave() != null && direccion != null
     * @post La posición de la CapaDatos.nave en el tablero se actualiza a (x + direccion.x, y + direccion.y) y se retorna un RegistroMotor.
     */
    public RegistroMotor saltar (Tablero tablero,Posicion direccion)  {
        int x = tablero.getPosicionNave().getX();
        int y = tablero.getPosicionNave().getY();
        tablero.moverNave(new Posicion(x + direccion.getX(),y + direccion.getY()));
        return new RegistroMotor("LA NAVE AVANZA UN CASILLERO",getEstadoActual());
    }

}
