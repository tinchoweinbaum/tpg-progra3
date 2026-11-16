package elementosUniverso;

import asistentes.Asistente;
import exceptions.*;

public class ElementoNaveExploradora extends ElementoDelUniverso{

    public ElementoNaveExploradora() {
        super();
    }

    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException {
        ac.getBitacorasNave().agregarRegistro(ac.getNave().atacadoPorExplorador());
    }
}
