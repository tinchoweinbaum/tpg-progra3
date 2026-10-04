package misiones;


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

    abstract protected void preparar(); // Cada misión implementa estas 4 funciones como lo necesite. Patrón Template.

    abstract protected void ejecutar();

    abstract protected void evaluar();

    protected void cerrar(){
        System.out.println("MISION FINALIZADA - ACTUALIZANDO RECURSOS");
    };

    //metodo sobreescrito para que le pase al asistente la mision resumida y evitar la referencia bitacora mision
    @Override
    public String toString(){
        return "REPORTE MISION : " + this.nombre + "\n" + this.descripcion + "\nCombustible consumido:" + this.combustibleRequerido + " litros\nLa nave sufrio un desgaste de " + this.desgasteRequerido + " unidades" + (this.energiaAportada >0 ? ("\nSe incremento la energia de la nave en " + this.energiaAportada + " unidades") : "");
    }


    /**
     * <b>Pre: </b>
     * <b>Post: </b>
     * Método principal del template de las misiones, cada subclase implementa los pasos del algoritmo según necesite.
     */
    public void ejecutarMision(){
        this.preparar();
        this.ejecutar();
        this.evaluar();
        this.cerrar();
    }
    //por el momento solo imprimen mensajes distintos excepto que comparten cerrar()

}
