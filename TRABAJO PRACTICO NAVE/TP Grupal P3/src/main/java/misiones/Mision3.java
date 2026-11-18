package misiones;

import asistentes.Asistente;
import bitacora.RegistroBitacora;

public class Mision3 extends Mision{

    public Mision3() {
        super("M-03", "RETORNO A PUNTO SEGURO", 4, 0, 4);
    }

    @Override
    protected void cerrar(Asistente ac) {
        if (getEstadoActual().equals(Mision.ESTADO_ACTIVA)){
            ac.getBitacorasNave().agregarRegistro(new RegistroBitacora("LA NAVE ARRIBO A LA ESTACION ESPACIAL Y PUEDE ABASTECER SUS RECURSOS"));
        }
        notificarObservadores("FIN_MISION_BASE");
    }
}
