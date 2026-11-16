package elementosUniverso;

import asistentes.Asistente;
import exceptions.*;

public class ElementoNaveCarguera extends ElementoDelUniverso{
    public ElementoNaveCarguera() {
        super();
    }

    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException {
        ac.getBitacorasNave().agregarRegistro(ac.getNave().atacadoPorCarguero());
    }
}
