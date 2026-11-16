package elementosUniverso;

import asistentes.Asistente;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;

public class ElementoNaveExploradora extends ElementoDelUniverso{

    public ElementoNaveExploradora(String nombre) {
        super(nombre);
    }

    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException {

    }
}
