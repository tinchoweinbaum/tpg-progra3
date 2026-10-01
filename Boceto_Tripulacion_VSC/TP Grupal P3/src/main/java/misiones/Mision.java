package misiones;

// Superclase del patrón template de las misiones.
public abstract class Mision {
    private String nombre;
    private String descripcion;
    private float combustibleRequerido; //No se si estos valores son nros naturales o reales ¯\_(ツ)_/¯
    private float energiaRequerida;

    public Mision(String nombre, String descripcion, float combustibleRequerido, float energiaRequerida) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.combustibleRequerido = combustibleRequerido;
        this.energiaRequerida = energiaRequerida;
    }

    /**
     * <b>pre: </b>Se asume que los valores recibidos son validos y mayores que 0 <br>
     * <b>post: </b>No tengo idea que poner en el post.
     *
     * @param combustibleDisponible Cantidad de combustible que la nave tiene actualmente
     * @param energiaDisponible Cantidad de energia que la nave tiene actualmente
     *
     * @return True si es posible para la nave aceptar la mision, false si no.
     */

    private boolean puedeHacerMision(float combustibleDisponible, float energiaDisponible){
        return (combustibleDisponible >= this.combustibleRequerido) && (energiaDisponible >= this.energiaRequerida);
    }
}
