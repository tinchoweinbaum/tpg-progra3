package misiones;

import asistentes.Asistente;
import bitacora.RegistroBitacora;
import bitacora.RegistroError;
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
            estadoActual = ESTADO_FALLIDO;;
        }
    }


    @Override
    protected void cerrar(Asistente ac) {
        if(estadoActual.equals(ESTADO_ACTIVA))
            try {
                String texto = "";
                if (energiaSOS>0){
                    ac.consumeEnergiaNave(energiaSOS);
                    texto+= String.valueOf(energiaSOS) + " PUNTOS DE ENERGIA";
                }
                if (combustibleSOS>0){
                    ac.consumeCombustibleNave(combustibleSOS);
                    texto+= String.valueOf(combustibleSOS) + " LITROS DE COMBUSTIBLE";
                }
                ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("SE REALIZO CORRECTAMENTE LA ASISTENCIA A LA NAVE EN CUESTION - SE ENTREGARON " + texto));
                notificarObservadores("FIN_MISION");
            } catch (CantEnergiaInvalidaException | CantCombustibleInvalidaException e) {
                ac.getBitacorasNave().agregarRegistro(new RegistroError("No pudo realizarse la asistencia por recursos insuficientes",e));
            } catch (NaveDestruidaException e) {
                // ver esto!!!
            }
    }


}
