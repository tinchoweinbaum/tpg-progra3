package CapaPresentacion;

import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonsMapa extends JButton {
    public final static String PREFIJO_CELDA = "CELDA_";

    private int fila;
    private int columna;
    private String rutaIcono;

    public ButtonsMapa(int fila, int columna) {
        super();
        this.fila = fila;
        this.columna = columna;

        // Configuración visual y de eventos por defecto
        setFont(new Font("Tahoma", Font.PLAIN, 11));
        setMargin(new Insets(0, 0, 0, 0));
        setActionCommand(PREFIJO_CELDA + fila + "_" + columna);

    }

    public void actualizarIcono(String rutaIcono) {
        if (rutaIcono == null || rutaIcono.isEmpty()) {
            setIcon(null);
            return;
        }

        // Solo escalamos si el botón ya tiene dimensiones reales en pantalla
        int ancho = getWidth() - 4;
        int alto = getHeight() - 4;

        if (ancho > 0 && alto > 0) {
            ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagenEscalada));
        }
    }

    // --------------------------------------------------------------------
    // GETTERS DE COORDENADAS
    // --------------------------------------------------------------------
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

}
