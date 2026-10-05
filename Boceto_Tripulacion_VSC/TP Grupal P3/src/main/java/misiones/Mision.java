package misiones;

import bitacora.RegistroMision;

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
    }

    //metodo sobreescrito para que le pase al asistente la mision resumida y evitar la referencia bitacora mision
    @Override
    public String toString(){
        return "REPORTE MISION : " + this.nombre + "\n" + this.descripcion + "\nCombustible consumido:" + this.combustibleRequerido + " litros\nLa nave sufrio un desgaste de " + this.desgasteRequerido + " unidades" + (this.energiaAportada >0 ? ("\nSe incremento la energia de la nave en " + this.energiaAportada + " unidades") : "");
    }


    /** Contrato mejorado con gemini.
     * Ejecuta las etapas correspondientes de la misión y genera su registro.
     *
     * <p><b>Pre:</b> La nave que va a ejecutar esta misión ya checkeó si tiene los recursos necesarios para hacerla.</p>
     * <p><b>Post:</b> Se ejecutan las 4 etapas de la misión y se crea un {@code RegistroMision}.</p>
     *
     * @return Un objeto de tipo {@code RegistroMision} compatible con la bitácora de cualquier nave.
     */
    public RegistroMision ejecutarMision(){
        this.preparar();
        this.ejecutar();
        this.evaluar();
        this.cerrar();

        return new RegistroMision("MISION REALIZADA", this);
    }
    //por el momento solo imprimen mensajes distintos excepto que comparten cerrar()
}
