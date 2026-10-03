package misiones;

import bitacora.RegistroBitacora;

// Superclase del patrón template de las misiones.
public abstract class Mision {
    private final String nombre;
    private final String descripcion;
    private final float combustibleRequerido; //No se si estos valores son nros naturales o reales ¯\_(ツ)_/¯
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

    protected void preparar(){}; // Cada misión implementa estas 4 funciones como lo necesite. Patrón Template.

    protected void ejecutar(){};

    protected void evaluar(){};

    protected RegistroBitacora cerrar(){
        return null;
    };

    /**
     * <b>Pre: </b>
     * <b>Post: </b>Devuelve un objeto de tipo RegistroBitacora.
     * Método principal del template de las misiones, cada subclase implementa los pasos del algoritmo según necesite.
     */
    public RegistroBitacora ejecutarMision(){
        this.preparar();
        this.ejecutar();
        this.evaluar();
        return this.cerrar();
    }


}
