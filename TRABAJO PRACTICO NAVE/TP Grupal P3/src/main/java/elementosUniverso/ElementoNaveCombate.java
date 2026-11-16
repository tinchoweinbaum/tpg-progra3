package elementosUniverso;

import asistentes.Asistente;
import bitacora.RegistroCombate;
import exceptions.CantCombustibleInvalidaException;
import exceptions.CantEnergiaInvalidaException;
import exceptions.NaveDestruidaException;
import nave.Nave;

public class ElementoNaveCombate extends ElementoDelUniverso{
    public ElementoNaveCombate(String nombre) {
        super(nombre);
    }

    @Override
    public void interactuaConNave(Asistente ac) throws NaveDestruidaException{
        switch (ac.getNave().atacadoPorCombate()){
            case Nave.COMBATE_DERROTA -> {
                ac.getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE DE COMBATE - LA NAVE ESTA SIENDO ASEDIADA"));
                throw new NaveDestruidaException("LA NAVE PERDIO EL ENFRENTAMIENTO FRENTE A UNA NAVE DE COMBATE");
            }

            case Nave.COMBATE_PAZ ->{
                ac.getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE DE COMBATE - PACTO DE PAZ REALIZADO"));
            }

            case Nave.COMBATE_VICTORIA -> {
                ac.getBitacorasNave().agregarRegistro(new RegistroCombate("IMPACTO CON NAVE DE COMBATE - EL OPONENTE NO PUDO CON TU EQUIPO Y RESULTASTE VENCEDOR"));
            }
        }
    }
}
