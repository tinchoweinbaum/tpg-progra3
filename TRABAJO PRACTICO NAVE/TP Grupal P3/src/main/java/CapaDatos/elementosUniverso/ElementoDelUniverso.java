package CapaDatos.elementosUniverso;

import CapaDatos.asistentes.Asistente;
import CapaDatos.exceptions.CantCombustibleInvalidaException;
import CapaDatos.exceptions.CantEnergiaInvalidaException;
import CapaDatos.exceptions.NaveDestruidaException;

/**
 * Clase base abstracta que representa cualquier entidad espacial ubicada en una celda del tablero
 * con la cual puede interactuar la CapaDatos.nave del jugador.
 * <p><b>Invariante de clase:</b> true</p>
 */
public abstract class ElementoDelUniverso {

    /**
     * Constructor por defecto de un elemento del universo.
     *
     * @pre true
     * @post Se crea una instancia válida de ElementoDelUniverso.
     */
    public ElementoDelUniverso() {
    }

    /**
     * Define el comportamiento y los efectos que produce este elemento al coincidir con la posición de la CapaDatos.nave.
     *
     * @param ac Asistente encargado de gestionar la CapaDatos.nave y su bitácora.
     * @throws NaveDestruidaException Si la interacción provoca la destrucción de la CapaDatos.nave.
     * @throws CantCombustibleInvalidaException Si ocurre una operación inválida sobre el combustible.
     * @throws CantEnergiaInvalidaException Si ocurre una operación inválida sobre la energía.
     * @pre ac != null && ac.getNave() != null && ac.getBitacorasNave() != null
     * @post Se aplican los efectos correspondientes sobre la CapaDatos.nave y se registra el evento en la bitácora.
     */
    public abstract void interactuaConNave(Asistente ac) throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException;

    /**
     * Compara este elemento del universo con otro objeto basándose únicamente en si pertenecen a la misma clase concreta.
     *
     * @param o Objeto a comparar.
     * @return true si o != null y pertenece exactamente a la misma clase que this; false en caso contrario.
     * @pre true
     * @post Retorna true si y solo si (this == o || (o != null && this.getClass() == o.getClass())), sin modificar el estado del objeto.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return getClass() == o.getClass();
    }
}
