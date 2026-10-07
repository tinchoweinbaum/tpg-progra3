package misiones;

import asistentes.Asistente;
import bitacora.Bitacora;
import bitacora.RegistroError;
import bitacora.RegistroMision;
import exceptions.*;
import motorwarp.MotorWarp;
import nave.Nave;

// Superclase del patrón template de las misiones.
public abstract class Mision {
    private final String nombre;
    private final String descripcion;
    private final float combustibleRequerido;
    private final float energiaAportada; // Valor de energía que la misión SUMA a la nave.
    private final float desgasteRequerido; // Desgaste mínimo que tiene que tener la nave para aceptar la misión.

    public Mision(String nombre, String descripcion, float combustibleRequerido, float energiaAportada, float desgasteRequerido) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.combustibleRequerido = combustibleRequerido;
        this.energiaAportada = energiaAportada;
        this.desgasteRequerido = desgasteRequerido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public float getCombustibleRequerido() {
        return this.combustibleRequerido;
    }

    public float getEnergiaAportada() {
        return this.energiaAportada;
    }

    public float getDesgasteRequerido() {
        return desgasteRequerido;
    }

    protected void preparar(Asistente ac) throws MisionImposibleException{
        Nave nave = ac.getNave();

        if (this.getCombustibleRequerido() > nave.getCombustible())
            throw new CombustibleInsuficienteException("Combustible insuficiente para realizar la mision");

        //Desgaste maximo = 100
        if (this.getDesgasteRequerido() + nave.getDesgaste() > 100)
            throw new ExcesoDesgasteException("Demasiado desgaste en la nave para realizar la mision");

        //Energia maxima = 100
        if (this.getEnergiaAportada()>0 && nave.getEnergia() + this.getEnergiaAportada() > 100)
            throw new ExcesoEnergiaException("Se supera la cantidad maxima de energia soportada por la nave");

        //Verifico que se pueda usar el motor
        if (ac.getMotor().getEstadoActual().getIdEstado() != MotorWarp.DISPONIBLE)
            throw new MotorNoDisponibleException("El motor no se encuentra disponible");
    }

    protected void saltar(Asistente ac){

    }

    abstract protected void ejecutar();

    abstract protected void evaluar();

    protected void cerrar(Asistente ac){
        System.out.println("MISION FINALIZADA - ACTUALIZANDO RECURSOS");

        Nave nave = ac.getNave();
        Bitacora bitacora = ac.getBitacorasNave();

        // Actualiza los recursos de la nave y escribe en la bitacora que lo hizo.
        bitacora.agregarRegistro(nave.setCombustible(nave.getCombustible() - this.combustibleRequerido));
        bitacora.agregarRegistro(nave.setDesgaste(nave.getDesgaste() + this.desgasteRequerido));
        bitacora.agregarRegistro(nave.setEnergia(nave.getEnergia() + this.energiaAportada));

        bitacora.agregarRegistro(new RegistroMision("Mision completa.",this));
        //esto no es una referencia nave bitacora????
    }

    public void ejecutarMision(Asistente ac){
        try {
            this.preparar(ac);
            this.ejecutar();
            this.evaluar();
            this.cerrar(ac);
        }
        catch (MisionImposibleException e){
            System.out.println("No se pudo aceptar la mision " + this.getNombre() + ": " + e.getMessage());
            Bitacora bitacora = ac.getBitacorasNave();
            bitacora.agregarRegistro(new RegistroError("No se pudo aceptar la mision", e));
        }
    }

    @Override
    public String toString(){
        return "REPORTE MISION : " + this.getNombre() + "\n" + this.getDescripcion() + "\nCombustible consumido:" + this.combustibleRequerido + " litros\nLa nave sufrio un desgaste de " + this.desgasteRequerido + " unidades" + (this.energiaAportada >0 ? ("\nSe incremento la energia de la nave en " + this.energiaAportada + " unidades") : "");
    }

}
