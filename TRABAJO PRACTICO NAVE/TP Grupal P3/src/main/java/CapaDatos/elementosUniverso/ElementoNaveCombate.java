package CapaDatos.elementosUniverso;

import CapaDatos.asistentes.Asistente;
import CapaDatos.exceptions.*;

/**
 * Elemento del universo que representa una CapaDatos.nave enemiga de combate.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ElementoNaveCombate extends ElementoDelUniverso{

    /**
     * Constructor por defecto de ElementoNaveCombate.
     *
     * @pre true
     * @post Se inicializa una instancia de ElementoNaveCombate.
     */
    public ElementoNaveCombate() {
        super();
    }

    /**
     * Ejecuta el ataque de una CapaDatos.nave de combate enemiga contra la CapaDatos.nave del jugador y guarda el resultado en la bitácora.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave del jugador.
     * @throws NaveDestruidaException Si el ataque destruye la CapaDatos.nave del jugador.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Se aplica el daño/efecto de ac.getNave().atacadoPorCombate() y se registra el combate en la bitácora.
     */
    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException{
        ac.getBitacorasNave().agregarRegistro(ac.getNave().atacadoPorCombate());
    }
}
