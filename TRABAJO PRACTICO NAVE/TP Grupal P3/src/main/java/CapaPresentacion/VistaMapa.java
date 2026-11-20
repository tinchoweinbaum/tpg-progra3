package CapaPresentacion;

import CapaNegocio.Controlador;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VistaMapa extends JFrame implements IVista {
    private int filas;
    private int columnas;
    private int tamanoIcono;
    private JPanel panelPrincipal;
    private JPanel panelMatriz;
    private ButtonsMapa[][] botonesMatriz;

    public VistaMapa(int x, int y) {
        this.filas = x;
        this.columnas = y;

        int anchoCelda = 560 / this.columnas;
        int altoCelda = 440 / this.filas;
        this.tamanoIcono = Math.max(16, Math.min(anchoCelda, altoCelda) - 8);

        setTitle("MAPA DEL UNIVERSO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(panelPrincipal);

        GridBagLayout gbl_panelPrincipal = new GridBagLayout();
        gbl_panelPrincipal.columnWidths = new int[] {500};
        gbl_panelPrincipal.rowHeights = new int[] {420};
        gbl_panelPrincipal.columnWeights = new double[]{1.0};
        gbl_panelPrincipal.rowWeights = new double[]{1.0};
        panelPrincipal.setLayout(gbl_panelPrincipal);

        panelMatriz = new JPanel();
        panelMatriz.setBorder(new TitledBorder(null, "Mapa del Universo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelMatriz.setLayout(new GridLayout(this.filas, this.columnas, 0, 0));

        botonesMatriz = new ButtonsMapa[this.filas][this.columnas];

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                ButtonsMapa botonCelda = new ButtonsMapa(i, j);
                botonesMatriz[i][j] = botonCelda;
                panelMatriz.add(botonCelda);
            }
        }

        GridBagConstraints gbc_panelMatriz = new GridBagConstraints();
        gbc_panelMatriz.fill = GridBagConstraints.BOTH;
        gbc_panelMatriz.gridx = 0;
        gbc_panelMatriz.gridy = 0;
        panelPrincipal.add(panelMatriz, gbc_panelMatriz);
    }

    @Override
    public void arranca() {
        pack();
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setControlador(Controlador controlador) {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                botonesMatriz[i][j].addActionListener(controlador);
            }
        }
    }

    public ButtonsMapa[][] getBotonesMatriz() {
        return botonesMatriz;
    }

    public ButtonsMapa getBotonCelda(int fila, int columna) {
        return botonesMatriz[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

}
