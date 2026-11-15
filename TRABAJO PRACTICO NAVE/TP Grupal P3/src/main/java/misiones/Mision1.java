package misiones;

import asistentes.Asistente;
import bitacora.RegistroError;
import destinoytablero.Posicion;
import exceptions.*;

import java.util.Random;

public class Mision1 extends Mision{

    private Random rand = new Random();
    private int energiaSOS = 0;
    private int combustibleSOS = 0;

    public Mision1(){
        super("M-01", "INTERCEPCION Y ASISTENCIA", 4, 5, 4);
        deterRecursoSOS();
    }

    private void deterRecursoSOS(){
        int opcion = rand.nextInt(2)+1;
        switch (opcion){
            case 1:
                energiaSOS = rand.nextInt(50)+1;
                break;
            case 2:
                combustibleSOS = rand.nextInt(50)+1;
                break;
        }
    }

    @Override
    public void ejecutar(Asistente ac){
        super.ejecutar(ac);
        try{
            if (energiaSOS>0 && ac.getNave().getEnergia()<=energiaSOS)//agregar que cambie estado
                throw new EnergiaInsuficienteException("No alcanza la reserva de energia para realizar el salvataje");

            if (combustibleSOS>0 && ac.getNave().getCombustible()<=combustibleSOS)
                throw new CombustibleInsuficienteException("No alcanza la reserva de combustible para realizar el salvataje");
        } catch (Exception e) {
            notificarObservadores("MISION");
            estadoActual = ESTADO_FALLIDO;;
        }
    }


    @Override
    protected void cerrar(Asistente ac) {
        try {
            if (energiaSOS>0)
                ac.getNave().consumeEnergia(energiaSOS);
            if (combustibleSOS>0)
                ac.getNave().consumeCombustible(combustibleSOS);
        } catch (CantEnergiaInvalidaException | CantCombustibleInvalidaException e) {
            ac.getBitacorasNave().agregarRegistro(new RegistroError("ERROR INESPERADO",e));
        }
        finally {
            notificarObservadores("BITACORA");
        }
    }
}
