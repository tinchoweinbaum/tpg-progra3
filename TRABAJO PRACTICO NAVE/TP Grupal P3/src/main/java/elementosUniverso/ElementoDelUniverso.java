package elementosUniverso;

import asistentes.Asistente;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;

public abstract class ElementoDelUniverso {
    private String nombre;

    public ElementoDelUniverso(String nombre) {
        this.nombre = nombre;
    }

    // mét0do abstracto para despacho polimórfico desde asistente, evita switch gigante.
    public abstract void interactuaConNave(Asistente ac) throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException;
}
