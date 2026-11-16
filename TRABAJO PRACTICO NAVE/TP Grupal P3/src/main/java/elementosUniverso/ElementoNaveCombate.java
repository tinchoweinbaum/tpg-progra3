package elementosUniverso;

import asistentes.Asistente;
import exceptions.*;

public class ElementoNaveCombate extends ElementoDelUniverso{
    public ElementoNaveCombate() {
        super();
    }

    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException{
        ac.getBitacorasNave().agregarRegistro(ac.getNave().atacadoPorCombate());
    }
}
