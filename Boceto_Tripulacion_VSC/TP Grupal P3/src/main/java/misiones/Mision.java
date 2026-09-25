package misiones;

import exceptions.*;
import asistentes.*;

/**
 * TO DO: Implementar esqueleto de la clase asistente.
 *        Definir flujo de checkeo, asignacion y ejecución de misión.
 *        Implementar esqueleto de la clase RegistroBitacora.
 */

// Superclase del patrón template de las misiones.
public abstract class Mision {
    private String nombre;
    private String descripcion;
    private Asistente asistente = null; // Las misiones tienen asistente = null cuando nacen hasta que alguien las reclama.

    protected abstract void prepararMision(); // Protected y no private porque se deben poder llamar desde los hijos.

    protected abstract void accionarMision();

    protected abstract void evaluarResultadoMision();

    protected abstract void cerrarMision();

    /**
     * Método principal del template pattern de las misiones
     * Cada clase hija va a llamar a este método, y se ejecutarán sus propias
     * implementaciones de cada uno de los métodos.
     *
     * No sé si conviene que este método esté en el asistente o en la misión, preguntar.
     * Hay que asignar una misión y después arrancarla o esta función también tiene que asignar?
     */
    public void iniciarMision() throws Exception{ // Definir las excepeciones
        try {
            this.prepararMision();
            this.accionarMision();
            this.evaluarResultadoMision();
            this.cerrarMision(); // No estoy seguro de cerrar misión
        }
        catch(Exception e){ // Este catch no va a ir acá, se tiene que propagar.
            System.out.println("Error al comenzar la misión: " + e.getMessage());
        }
    }

    private boolean puedeHacerMision(Asistente asistente){
        // En esta función se checkea si la nave tiene los recursos suficientes para encarar una misión.
        // Como hago una función booleana que si falla tira exception??????? no puedo retornar y a la vez arrrojar
        return true;
    }

    /**
     *
     * @param asistente -> Objeto del asistente que quiere hacer esta misión. Si puede hacerla, asigna el atributo asistente de esta misión a la referencia del asistente recibido por parámetro y vicecersa!!
     * @throws Exception Arroja una excepeción de MisionImposible (no es refe a la peli) si la nave no es capaz de ejecutar esta misión.
     */
    public void asignaMisionAsistente(Asistente asistente) throws Exception{
        try {
            if (this.puedeHacerMision(asistente)) {
                this.asistente = asistente;
                asistente.setMisionAct(this);
            }
            throw new Exception("No se pudo asignar la misión.");
        }
        catch(Exception e){ // Catcheo cualquier excepción de misión imposible.
            System.out.println(e.getMessage());
        }
    }
}
