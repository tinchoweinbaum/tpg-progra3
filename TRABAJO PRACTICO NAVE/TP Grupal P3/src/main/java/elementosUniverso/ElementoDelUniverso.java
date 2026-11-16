package elementosUniverso;

import asistentes.Asistente;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;

public abstract class ElementoDelUniverso {

    public ElementoDelUniverso() {
    }

    public abstract void interactuaConNave(Asistente ac) throws NaveDestruidaException, CantCombustibleInvalidaException, CantEnergiaInvalidaException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return getClass() != o.getClass();
    }
}
