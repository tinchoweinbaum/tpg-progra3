package misiones;

import asistentes.Asistente;
import bitacora.RegistroBitacora;
import bitacora.RegistroMision;
import destinoytablero.Posicion;

import java.util.Random;

public class Mision2 extends Mision{

    private Random rand = new Random();

    public Mision2() {
        super("M-02", "RECOLECCION", 4, 10, 4);
    }

    @Override
    protected void cerrar(Asistente ac) {
        if (getEstadoActual().equals(Mision.ESTADO_ACTIVA)){
            int gemas = rand.nextInt(20,100);
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("LA NAVE REALIZO UNA RECOLECCION DE " + gemas + " gemas"));
        }
        notificarObservadores("FIN_MISION");
    }
}
