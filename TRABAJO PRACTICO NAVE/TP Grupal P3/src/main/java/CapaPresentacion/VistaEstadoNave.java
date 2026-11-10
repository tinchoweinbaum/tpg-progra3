package CapaPresentacion;

import CapaNegocio.Controlador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VistaEstadoNave extends JFrame implements IVista {

    private JPanel panelPrincipal;

    // Declaramos las barras como atributos para poder actualizarlas desde la lógica
    private JProgressBar barraDesgaste;
    private JProgressBar barraCombustible;
    private JProgressBar barraEnergia;

    public VistaEstadoNave() {
        setTitle("ESTADO DE LA NAVE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // 1. Panel Principal con margen perimetral respirable
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        setContentPane(panelPrincipal);

        // 2. GridBagLayout: Columna 0 (Nombres de indicadores) y Columna 1 (Barras de progreso)
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] {120, 220};
        gbl_panel.rowHeights = new int[] {35, 35, 35, 35}; // Filas proporcionales para el título y los 3 indicadores
        gbl_panel.columnWeights = new double[]{0.0, 1.0};  // La columna de las barras absorbe el ancho de forma elástica
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        panelPrincipal.setLayout(gbl_panel);

        Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
        Font fuenteLabels = new Font("Tahoma", Font.BOLD, 12);
        Font fuenteBarras = new Font("Tahoma", Font.BOLD, 11);

        // --------------------------------------------------------------------
        // 3. TÍTULO PRINCIPAL (Fila 0, abarca ambas columnas)
        // --------------------------------------------------------------------
        JLabel lblTitulo = new JLabel("RECURSOS DE LA NAVE");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.gridwidth = 2; // Une las dos columnas
        gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
        gbc_lblTitulo.insets = new Insets(0, 0, 20, 0); // Espacio inferior de separación
        gbc_lblTitulo.gridx = 0;
        gbc_lblTitulo.gridy = 0;
        panelPrincipal.add(lblTitulo, gbc_lblTitulo);

        // --------------------------------------------------------------------
        // 4. FILA 1: DESGASTE (Estructura de la Nave)
        // --------------------------------------------------------------------
        JLabel lblDesgaste = new JLabel("DESGASTE:");
        lblDesgaste.setFont(fuenteLabels);
        GridBagConstraints gbc_lblDesgaste = new GridBagConstraints();
        gbc_lblDesgaste.anchor = GridBagConstraints.WEST;
        gbc_lblDesgaste.insets = new Insets(0, 0, 12, 15);
        gbc_lblDesgaste.gridx = 0;
        gbc_lblDesgaste.gridy = 1;
        panelPrincipal.add(lblDesgaste, gbc_lblDesgaste);

        barraDesgaste = new JProgressBar(0, 100);
        barraDesgaste.setValue(25); // Valor de prueba (25% de desgaste)
        barraDesgaste.setStringPainted(true); // Hace visible el texto del porcentaje (e.g., "25%")
        barraDesgaste.setFont(fuenteBarras);
        barraDesgaste.setForeground(new Color(219, 68, 85)); // Rojo/Rosa de advertencia para el desgaste
        GridBagConstraints gbc_barraDesgaste = new GridBagConstraints();
        gbc_barraDesgaste.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraDesgaste.insets = new Insets(0, 0, 12, 0);
        gbc_barraDesgaste.gridx = 1;
        gbc_barraDesgaste.gridy = 1;
        panelPrincipal.add(barraDesgaste, gbc_barraDesgaste);

        // --------------------------------------------------------------------
        // 5. FILA 2: COMBUSTIBLE
        // --------------------------------------------------------------------
        JLabel lblCombustible = new JLabel("COMBUSTIBLE:");
        lblCombustible.setFont(fuenteLabels);
        GridBagConstraints gbc_lblCombustible = new GridBagConstraints();
        gbc_lblCombustible.anchor = GridBagConstraints.WEST;
        gbc_lblCombustible.insets = new Insets(0, 0, 12, 15);
        gbc_lblCombustible.gridx = 0;
        gbc_lblCombustible.gridy = 2;
        panelPrincipal.add(lblCombustible, gbc_lblCombustible);

        barraCombustible = new JProgressBar(0, 100);
        barraCombustible.setValue(85); // Valor de prueba (85% de combustible)
        barraCombustible.setStringPainted(true);
        barraCombustible.setFont(fuenteBarras);
        barraCombustible.setForeground(new Color(231, 142, 34)); // Naranja/Amarillo estilo depósito de combustible
        GridBagConstraints gbc_barraCombustible = new GridBagConstraints();
        gbc_barraCombustible.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraCombustible.insets = new Insets(0, 0, 12, 0);
        gbc_barraCombustible.gridx = 1;
        gbc_barraCombustible.gridy = 2;
        panelPrincipal.add(barraCombustible, gbc_barraCombustible);

        // --------------------------------------------------------------------
        // 6. FILA 3: ENERGÍA
        // --------------------------------------------------------------------
        JLabel lblEnergia = new JLabel("ENERGÍA:");
        lblEnergia.setFont(fuenteLabels);
        GridBagConstraints gbc_lblEnergia = new GridBagConstraints();
        gbc_lblEnergia.anchor = GridBagConstraints.WEST;
        gbc_lblEnergia.insets = new Insets(0, 0, 0, 15);
        gbc_lblEnergia.gridx = 0;
        gbc_lblEnergia.gridy = 3;
        panelPrincipal.add(lblEnergia, gbc_lblEnergia);

        barraEnergia = new JProgressBar(0, 100);
        barraEnergia.setValue(60); // Valor de prueba (60% de energía)
        barraEnergia.setStringPainted(true);
        barraEnergia.setFont(fuenteBarras);
        barraEnergia.setForeground(new Color(46, 139, 87)); // Verde esmeralda para representar energía activa estable
        GridBagConstraints gbc_barraEnergia = new GridBagConstraints();
        gbc_barraEnergia.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraEnergia.gridx = 1;
        gbc_barraEnergia.gridy = 3;
        panelPrincipal.add(barraEnergia, gbc_barraEnergia);
    }

    @Override
    public void arranca() {
        pack();
        // Formato horizontal estirado para que las barras tengan suficiente recorrido visual
        setSize(440, 240);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setControlador(Controlador controlador) {

    }

    /**
     * Métodos públicos para actualizar los valores de los indicadores desde tu controlador en tiempo real
     */
    public void setDesgaste(int valor) {
        if(valor >= 0 && valor <= 100) barraDesgaste.setValue(valor);
    }

    public void setCombustible(int valor) {
        if(valor >= 0 && valor <= 100) barraCombustible.setValue(valor);
    }

    public void setEnergia(int valor) {
        if(valor >= 0 && valor <= 100) barraEnergia.setValue(valor);
    }

    public static void main(String[] args) {
        VistaEstadoNave vista = new VistaEstadoNave();
        vista.arranca();
    }
}
