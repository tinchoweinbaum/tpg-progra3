package misiones;

// Superclase del patrón template de las misiones.
public abstract class Mision {
    private String nombre;
    private String descripcion;
    private float combustibleRequerido; //No se si estos valores son nros naturales o reales ¯\_(ツ)_/¯
    private float energiaAportada; // Valor de energía que la misión SUMA a la nave.
    private float desgasteRequerido; // Desgaste mínimo que tiene que tener la nave para aceptar la misión.

    public Mision(String nombre, String descripcion, float combustibleRequerido, float energiaRequerida) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.combustibleRequerido = combustibleRequerido;
        this.energiaAportada = energiaAportada;
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

}
