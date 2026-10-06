package misiones;

import bitacora.RegistroMision;

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

    abstract public void preparar(); // Cada misión implementa estas 4 funciones como lo necesite. Patrón Template.

    abstract public void ejecutar();

    abstract public void evaluar();

    public void cerrar(){
        System.out.println("MISION FINALIZADA - ACTUALIZANDO RECURSOS");
    }

    @Override
    public String toString(){
        return "REPORTE MISION : " + this.getNombre() + "\n" + this.getDescripcion() + "\nCombustible consumido:" + this.combustibleRequerido + " litros\nLa nave sufrio un desgaste de " + this.desgasteRequerido + " unidades" + (this.energiaAportada >0 ? ("\nSe incremento la energia de la nave en " + this.energiaAportada + " unidades") : "");
    }

}
