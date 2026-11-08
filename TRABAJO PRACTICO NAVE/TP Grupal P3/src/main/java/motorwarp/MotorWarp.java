package motorwarp;
import bitacora.RegistroMotor;
import exceptions.EstadoInvalidoException;
import sistema.Sistema;
import asistentes.Asistente;
import destinoytablero.*;

import java.util.ArrayList;


public class MotorWarp {

    public static final int DISPONIBLE = 0;
    public static final int PREPARANDO_SALTO = 1;
    public static final int SALTO_WARP = 2;
    public static final int ENFRIAMIENTO = 3;
    public static final int SALTO_NORMAL = 4;
    private State estadoActual;
    private ArrayList<RegistroMotor> registrosTransiciones = new  ArrayList<>();
    private final Asistente asistente;

    
    public MotorWarp (Asistente asistente) {
        this.estadoActual = new Disponible(this);
        this.asistente = asistente;
    }

    public ArrayList<RegistroMotor> actualizaRegistrosMotor(){
        ArrayList<RegistroMotor> auxiliar = registrosTransiciones;
        registrosTransiciones = new ArrayList<>();
        return auxiliar;
    }

    public void llamaEstado(int i) throws EstadoInvalidoException {
        switch (i){
            case DISPONIBLE:
                this.disponible();
                break;
            case PREPARANDO_SALTO:
                this.prepararSalto();
                break;
            case SALTO_NORMAL:
                this.saltar();
                break;
            case SALTO_WARP:
                this.saltoWarp();
                break;
            case ENFRIAMIENTO:
                this.enfriamiento();
                break;
            default: throw new EstadoInvalidoException("No existe el estado nro " + i);
        }
    }

    public State getEstadoActual() {
        return estadoActual;
    }

    public void setEstado(State estadoActual) {
        this.estadoActual = estadoActual;
    }

    public double getEnergia (){
        return asistente.getNave().getEnergia();
    }

    public double getCombustible (){
        return asistente.getNave().getCombustible();
    }

    public void consumeEnergia (float energia){
        asistente.consumeEnergiaNave(energia);
    }

    public void consumeCombustible (float combustible){
        asistente.consumeCombustibleNave(combustible);
    }

    public double getDesgaste (){
        return asistente.getNave().getDesgaste();
    }

    public double getDesgasteMaximo(){
        return asistente.getNave().getMaxDesgaste();
    }
    public void aumentaDesgaste(float cantidad) {
        asistente.aumentaDesgasteNave(cantidad);
    }


    /**
    * Metodo que permite un cambio de estado hacia disponible.
    * Solo puede ser de enfriamiento --> disponible.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser enfriamiento
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto enfriamiento.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a disponible.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, preparaSalto o saltoWarp) -/-> disponible.
    */
    public void disponible() throws EstadoInvalidoException {
        estadoActual.disponible();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: DISPONIBLE",this.estadoActual));
    }

    /**
    * Metodo que permite un cambio de estado hacia prepararSalto.
    * Solo puede ser de disponible --> prepararSalto.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser disponible
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto disponible.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a prepararSalto.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(preparaSalto, saltoWarp o enfriamiento) -/-> prepararSalto.
    */
    public void prepararSalto() throws EstadoInvalidoException {
        estadoActual.preparaSalto();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: PREPARANDO SALTO",this.estadoActual));
        Sistema.esperar(500);
        this.saltoWarp();
    }

    public void saltar () throws EstadoInvalidoException {
        if (getCombustible() >= 2) {
            consumeCombustible(2);
            estadoActual.saltoCorto();  // Desde saltoWarp pasa a saltar y de vuelta a saltoWarp
            this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: SALTO NORMAL", this.estadoActual));
            Sistema.esperar(500);
        }
    }

    /**
    * Metodo que permite un cambio de estado hacia saltoWarp.
    * Solo puede ser de prepararSalto --> saltoWarp.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser prepararSalto.
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto prepararSalto.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a saltoWarp.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, saltoWarp o enfriamiento) -/-> saltoWarp.
    */
    public void saltoWarp() throws EstadoInvalidoException {
        estadoActual.saltoWarp();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: SALTO WARP",this.estadoActual));
        Sistema.esperar(500); // Espera medio segundo para que no haya race condition al agregar elementos a la bitácora.
    }
    
    /**
    * Metodo que permite un cambio de estado hacia disponible.
    * Solo puede ser de saltoWarp --> enfriamiento.
    * 
    * <b>pre:</b> (Exito) El estado actual debe ser saltoWarp.
    *             (Fracaso) El estado actual tiene que ser cualquiera de los restantes excepto saltoWarp.
    * 
    * <b>post:</b> (Exito) Se actualiza el estado actual a enfriamiento.
    *              (Fracaso) Se genera una excepcion debido a la invalidez de cambio de estado. 
    * 
    * @throws EstadoInvalidoException No se puede pasar del estado actual(disponible, preparaSalto o enfriamiento) -/-> enfriamiento.
    */
    public void enfriamiento() throws EstadoInvalidoException {
        estadoActual.enfriamiento();
        this.registrosTransiciones.add(new RegistroMotor("CAMBIO DE ESTADO MOTOR A: ENFRIAMIENTO",this.estadoActual));
        Sistema.esperar(500);
        this.disponible();
    }
}