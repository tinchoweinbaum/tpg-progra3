package CapaDatos.elementosUniverso;

import CapaDatos.asistentes.Asistente;
import CapaDatos.exceptions.*;

/**
 * Elemento del universo que representa una CapaDatos.nave enemiga de tipo carguero.
 * <p><b>Invariante de clase:</b> true</p>
 */
public class ElementoNaveCarguera extends ElementoDelUniverso{

    /**
     * Constructor por defecto de ElementoNaveCarguera.
     *
     * @pre true
     * @post Se inicializa una instancia de ElementoNaveCarguera.
     */
    public ElementoNaveCarguera() {
        super();
    }

    /**
     * Ejecuta el ataque de un carguero enemigo contra la CapaDatos.nave del jugador y guarda el resultado en la bitácora.
     *
     * @param ac Asistente que gestiona la CapaDatos.nave del jugador.
     * @throws NaveDestruidaException Si el ataque destruye la CapaDatos.nave del jugador.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Se aplica el daño/efecto de ac.getNave().atacadoPorCarguero() y se registra el combate en la bitácora.
     */
    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException {
        ac.getBitacorasNave().agregarRegistro(ac.getNave().atacadoPorCarguero());
    }
}
