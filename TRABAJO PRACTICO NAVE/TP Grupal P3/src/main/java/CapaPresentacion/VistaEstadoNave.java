package CapaPresentacion;

import CapaNegocio.Controlador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VistaEstadoNave extends JFrame implements IVista {

    public final static String MANTENIMIENTO = "MANTENIMIENTO_NAVE";
    public final static String CARGAR_COMBUSTIBLE = "CARGAR_COMBUSTIBLE";
    public final static String LLENAR_COMBUSTIBLE = "LLENAR_COMBUSTIBLE";
    public final static String CARGAR_ENERGIA = "CARGAR_ENERGIA";
    public final static String LLENAR_ENERGIA = "LLENAR_ENERGIA";

    private JPanel panelPrincipal;
    private JProgressBar barraEstado;
    private JProgressBar barraDesgaste;
    private JProgressBar barraCombustible;
    private JProgressBar barraEnergia;
    private JTextField txtCantidadCombustible;
    private JTextField txtCantidadEnergia;
    private JButton btnMantenimiento;
    private JButton btnCargarCombustible;
    private JButton btnLlenarCombustible;
    private JButton btnCargarEnergia;
    private JButton btnLlenarEnergia;

    public VistaEstadoNave() {
        setTitle("ESTADO DE LA NAVE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        setContentPane(panelPrincipal);

        // 5 Filas: Título | Estado Motor | Desgaste | Combustible | Energía
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] {125, 190, 50, 80, 80};
        gbl_panel.rowHeights = new int[] {35, 38, 38, 38, 38};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        panelPrincipal.setLayout(gbl_panel);

        Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
        Font fuenteLabels = new Font("Tahoma", Font.BOLD, 12);
        Font fuenteBarras = new Font("Tahoma", Font.BOLD, 11);
        Font fuenteControles = new Font("Tahoma", Font.PLAIN, 12);
        Font fuenteBotones = new Font("Tahoma", Font.BOLD, 11);

        // --------------------------------------------------------------------
        // FILA 0: TÍTULO PRINCIPAL
        // --------------------------------------------------------------------
        JLabel lblTitulo = new JLabel("RECURSOS Y ESTADO DE LA NAVE");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.gridwidth = 5;
        gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
        gbc_lblTitulo.insets = new Insets(0, 0, 20, 0);
        gbc_lblTitulo.gridx = 0;
        gbc_lblTitulo.gridy = 0;
        panelPrincipal.add(lblTitulo, gbc_lblTitulo);

        // --------------------------------------------------------------------
        // FILA 1: ESTADO MOTOR (Nueva barra indicadora de estado)
        // --------------------------------------------------------------------
        JLabel lblEstadoMotor = new JLabel("ESTADO MOTOR:");
        lblEstadoMotor.setFont(fuenteLabels);
        GridBagConstraints gbc_lblEstadoMotor = new GridBagConstraints();
        gbc_lblEstadoMotor.anchor = GridBagConstraints.WEST;
        gbc_lblEstadoMotor.insets = new Insets(0, 0, 14, 12);
        gbc_lblEstadoMotor.gridx = 0;
        gbc_lblEstadoMotor.gridy = 1;
        panelPrincipal.add(lblEstadoMotor, gbc_lblEstadoMotor);

        barraEstado = new JProgressBar(0, 100);
        barraEstado.setValue(100);
        barraEstado.setStringPainted(true); // Habilita el texto personalizado dentro de la barra
        barraEstado.setFont(fuenteBarras);
        GridBagConstraints gbc_barraEstado = new GridBagConstraints();
        gbc_barraEstado.gridwidth = 4; // Se extiende a lo ancho sobre las columnas de controles
        gbc_barraEstado.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraEstado.insets = new Insets(0, 0, 14, 0);
        gbc_barraEstado.gridx = 1;
        gbc_barraEstado.gridy = 1;
        panelPrincipal.add(barraEstado, gbc_barraEstado);

        // --------------------------------------------------------------------
        // FILA 2: DESGASTE + MANTENIMIENTO
        // --------------------------------------------------------------------
        JLabel lblDesgaste = new JLabel("DESGASTE:");
        lblDesgaste.setFont(fuenteLabels);
        GridBagConstraints gbc_lblDesgaste = new GridBagConstraints();
        gbc_lblDesgaste.anchor = GridBagConstraints.WEST;
        gbc_lblDesgaste.insets = new Insets(0, 0, 12, 12);
        gbc_lblDesgaste.gridx = 0;
        gbc_lblDesgaste.gridy = 2;
        panelPrincipal.add(lblDesgaste, gbc_lblDesgaste);

        barraDesgaste = new JProgressBar(0, 100);
        barraDesgaste.setValue(25);
        barraDesgaste.setStringPainted(true);
        barraDesgaste.setFont(fuenteBarras);
        barraDesgaste.setForeground(new Color(219, 68, 85));
        GridBagConstraints gbc_barraDesgaste = new GridBagConstraints();
        gbc_barraDesgaste.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraDesgaste.insets = new Insets(0, 0, 12, 12);
        gbc_barraDesgaste.gridx = 1;
        gbc_barraDesgaste.gridy = 2;
        panelPrincipal.add(barraDesgaste, gbc_barraDesgaste);

        btnMantenimiento = new JButton("Realizar Mantenimiento");
        btnMantenimiento.setActionCommand(MANTENIMIENTO);
        btnMantenimiento.setFont(fuenteBotones);
        GridBagConstraints gbc_btnMantenimiento = new GridBagConstraints();
        gbc_btnMantenimiento.gridwidth = 3;
        gbc_btnMantenimiento.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnMantenimiento.insets = new Insets(0, 0, 12, 0);
        gbc_btnMantenimiento.gridx = 2;
        gbc_btnMantenimiento.gridy = 2;
        panelPrincipal.add(btnMantenimiento, gbc_btnMantenimiento);

        // --------------------------------------------------------------------
        // FILA 3: COMBUSTIBLE + INPUT + CARGAR + LLENAR
        // --------------------------------------------------------------------
        JLabel lblCombustible = new JLabel("COMBUSTIBLE:");
        lblCombustible.setFont(fuenteLabels);
        GridBagConstraints gbc_lblCombustible = new GridBagConstraints();
        gbc_lblCombustible.anchor = GridBagConstraints.WEST;
        gbc_lblCombustible.insets = new Insets(0, 0, 12, 12);
        gbc_lblCombustible.gridx = 0;
        gbc_lblCombustible.gridy = 3;
        panelPrincipal.add(lblCombustible, gbc_lblCombustible);

        barraCombustible = new JProgressBar(0, 100);
        barraCombustible.setStringPainted(true);
        barraCombustible.setFont(fuenteBarras);
        barraCombustible.setForeground(new Color(231, 142, 34));
        GridBagConstraints gbc_barraCombustible = new GridBagConstraints();
        gbc_barraCombustible.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraCombustible.insets = new Insets(0, 0, 12, 12);
        gbc_barraCombustible.gridx = 1;
        gbc_barraCombustible.gridy = 3;
        panelPrincipal.add(barraCombustible, gbc_barraCombustible);

        txtCantidadCombustible = new JTextField();
        txtCantidadCombustible.setFont(fuenteControles);
        txtCantidadCombustible.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_txtCombustible = new GridBagConstraints();
        gbc_txtCombustible.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCombustible.insets = new Insets(0, 0, 12, 6);
        gbc_txtCombustible.gridx = 2;
        gbc_txtCombustible.gridy = 3;
        panelPrincipal.add(txtCantidadCombustible, gbc_txtCombustible);

        btnCargarCombustible = new JButton("Cargar");
        btnCargarCombustible.setActionCommand(CARGAR_COMBUSTIBLE);
        btnCargarCombustible.setFont(fuenteBotones);
        GridBagConstraints gbc_btnCargarComb = new GridBagConstraints();
        gbc_btnCargarComb.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCargarComb.insets = new Insets(0, 0, 12, 6);
        gbc_btnCargarComb.gridx = 3;
        gbc_btnCargarComb.gridy = 3;
        panelPrincipal.add(btnCargarCombustible, gbc_btnCargarComb);

        btnLlenarCombustible = new JButton("Llenar");
        btnLlenarCombustible.setActionCommand(LLENAR_COMBUSTIBLE);
        btnLlenarCombustible.setFont(fuenteBotones);
        GridBagConstraints gbc_btnLlenarComb = new GridBagConstraints();
        gbc_btnLlenarComb.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnLlenarComb.insets = new Insets(0, 0, 12, 0);
        gbc_btnLlenarComb.gridx = 4;
        gbc_btnLlenarComb.gridy = 3;
        panelPrincipal.add(btnLlenarCombustible, gbc_btnLlenarComb);

        // --------------------------------------------------------------------
        // FILA 4: ENERGÍA + INPUT + CARGAR + LLENAR
        // --------------------------------------------------------------------
        JLabel lblEnergia = new JLabel("ENERGÍA:");
        lblEnergia.setFont(fuenteLabels);
        GridBagConstraints gbc_lblEnergia = new GridBagConstraints();
        gbc_lblEnergia.anchor = GridBagConstraints.WEST;
        gbc_lblEnergia.insets = new Insets(0, 0, 0, 12);
        gbc_lblEnergia.gridx = 0;
        gbc_lblEnergia.gridy = 4;
        panelPrincipal.add(lblEnergia, gbc_lblEnergia);

        barraEnergia = new JProgressBar(0, 100);
        barraEnergia.setStringPainted(true);
        barraEnergia.setFont(fuenteBarras);
        barraEnergia.setForeground(new Color(46, 139, 87));
        GridBagConstraints gbc_barraEnergia = new GridBagConstraints();
        gbc_barraEnergia.fill = GridBagConstraints.HORIZONTAL;
        gbc_barraEnergia.insets = new Insets(0, 0, 0, 12);
        gbc_barraEnergia.gridx = 1;
        gbc_barraEnergia.gridy = 4;
        panelPrincipal.add(barraEnergia, gbc_barraEnergia);
        txtCantidadEnergia = new JTextField();
        txtCantidadEnergia.setFont(fuenteControles);
        txtCantidadEnergia.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_txtEnergia = new GridBagConstraints();
        gbc_txtEnergia.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEnergia.insets = new Insets(0, 0, 0, 6);
        gbc_txtEnergia.gridx = 2;
        gbc_txtEnergia.gridy = 4;
        panelPrincipal.add(txtCantidadEnergia, gbc_txtEnergia);
        btnCargarEnergia = new JButton("Cargar");
        btnCargarEnergia.setActionCommand(CARGAR_ENERGIA);
        btnCargarEnergia.setFont(fuenteBotones);
        GridBagConstraints gbc_btnCargarEnergia = new GridBagConstraints();
        gbc_btnCargarEnergia.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCargarEnergia.insets = new Insets(0, 0, 0, 6);
        gbc_btnCargarEnergia.gridx = 3;
        gbc_btnCargarEnergia.gridy = 4;
        panelPrincipal.add(btnCargarEnergia, gbc_btnCargarEnergia);
        btnLlenarEnergia = new JButton("Llenar");
        btnLlenarEnergia.setActionCommand(LLENAR_ENERGIA);
        btnLlenarEnergia.setFont(fuenteBotones);
        GridBagConstraints gbc_btnLlenarEnergia = new GridBagConstraints();
        gbc_btnLlenarEnergia.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnLlenarEnergia.gridx = 4;
        gbc_btnLlenarEnergia.gridy = 4;
        panelPrincipal.add(btnLlenarEnergia, gbc_btnLlenarEnergia);
    }
    @Override
    public void arranca() {
        pack();
        setSize(630, 290); // Alto ajustado para acomodar la fila extra de estado motor
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void setControlador(Controlador controlador) {
        btnMantenimiento.addActionListener(controlador);
        btnCargarCombustible.addActionListener(controlador);
        btnLlenarCombustible.addActionListener(controlador);
        btnCargarEnergia.addActionListener(controlador);
        btnLlenarEnergia.addActionListener(controlador);
    }
    public JProgressBar getBarraEstado() {
        return barraEstado;
    }
    public JProgressBar getBarraDesgaste() {
        return barraDesgaste;
    }
    public JProgressBar getBarraCombustible() {
        return barraCombustible;
    }
    public JProgressBar getBarraEnergia() {
        return barraEnergia;
    }
    public JTextField getTxtCantidadCombustible() {
        return txtCantidadCombustible;
    }
    public JTextField getTxtCantidadEnergia() {
        return txtCantidadEnergia;
    }
    public JButton getBtnMantenimiento() {
        return btnMantenimiento;
    }
    public JButton getBtnCargarCombustible() {
        return btnCargarCombustible;
    }
    public JButton getBtnLlenarCombustible() {
        return btnLlenarCombustible;
    }
    public JButton getBtnCargarEnergia() {
        return btnCargarEnergia;
    }
    public JButton getBtnLlenarEnergia() {
        return btnLlenarEnergia;
    }
}