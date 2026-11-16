package elementosUniverso;

import asistentes.Asistente;
import bitacora.RegistroCombate;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;
import nave.Nave;

public class ElementoNaveCarguera extends ElementoDelUniverso{
    public ElementoNaveCarguera(String nombre) {
        super(nombre);
    }

    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException {

    }
}
