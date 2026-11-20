package CapaPresentacion;

import CapaNegocio.Controlador;

/**
 * Interfaz base que define el contrato común para todas las vistas gráficas de la capa de presentación,
 * permitiendo su inicialización visual y su vinculación con el controlador.
 * <p><b>Invariante de clase:</b> true</p>
 */
public interface IVista {

    /**
     * Configura las propiedades finales de la ventana (como tamaño, ubicación o título) y la hace visible en pantalla.
     *
     * @pre true
     * @post La vista queda visible y operativa para la interacción del usuario.
     */
    void arranca();

    /**
     * Asocia el controlador principal a la vista y registra los listeners necesarios sobre sus componentes interactivos.
     *
     * @param controlador Instancia de Controlador encargada de gestionar los eventos de la vista.
     * @pre controlador != null
     * @post Los componentes interactivos de la vista quedan escuchados por controlador.
     */
    void setControlador(Controlador controlador);
}
