package CapaPresentacion;

import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonsMapa extends JButton {
    private int fila;
    private int columna;

    public ButtonsMapa(int fila, int columna) {
        super();
        this.fila = fila;
        this.columna = columna;

        setFont(new Font("Tahoma", Font.PLAIN, 11));
        setMargin(new Insets(0, 0, 0, 0));

    }

    public void actualizarIcono(String rutaIcono) {
        if (rutaIcono == null || rutaIcono.isEmpty()) {
            setIcon(null);
            return;
        }

        int ancho = getWidth() - 4;
        int alto = getHeight() - 4;

        if (ancho > 0 && alto > 0) {
            ImageIcon iconoOriginal = new ImageIcon(rutaIcono);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(imagenEscalada));
        }
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

}
