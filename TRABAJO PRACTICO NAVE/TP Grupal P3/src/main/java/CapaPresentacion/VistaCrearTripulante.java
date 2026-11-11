package CapaPresentacion;

import CapaNegocio.Controlador;
import tripulantes.FactoryPeople;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VistaCrearTripulante extends JFrame implements IVista {
    public final static String CREAR_TRIPULANTE = "CREAR_TRIPULANTE";
    private JPanel panelPrincipal;

    private JTextField txtNombreTripulante;
    private JTextField txtAntiguedadTripulante;
    private JComboBox<String> comboOrigenTripulante;
    private JComboBox<String> comboCargoTripulante;
    private JButton btnCrear = new JButton("Registrar Tripulante");

    public VistaCrearTripulante() {
        setTitle("REGISTRAR TRIPULANTE");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        panelPrincipal = new JPanel();
        setContentPane(panelPrincipal);

        // GridBagLayout con filas espaciadas para título, 4 campos y botón
        GridBagLayout gbl_panelPrincipal = new GridBagLayout();
        gbl_panelPrincipal.columnWidths = new int[] {0};
        gbl_panelPrincipal.rowHeights = new int[] {15, 30, 15, 40, 10, 40, 10, 40, 10, 40, 15, 35, 15};
        gbl_panelPrincipal.columnWeights = new double[]{1.0};
        gbl_panelPrincipal.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        panelPrincipal.setLayout(gbl_panelPrincipal);

        Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
        Font fuenteCampos = new Font("Tahoma", Font.BOLD, 12);
        Font fuenteInputs = new Font("Tahoma", Font.PLAIN, 12);

        // 1. TÍTULO (Fila 1)
        JLabel lblTitulo = new JLabel("DATOS DEL TRIPULANTE");
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
        gbc_lblTitulo.insets = new Insets(0, 10, 10, 10);
        gbc_lblTitulo.gridx = 0;
        gbc_lblTitulo.gridy = 1;
        panelPrincipal.add(lblTitulo, gbc_lblTitulo);

        // 2. CAMPO NOMBRE (Fila 3)
        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteCampos);
        panelNombre.add(lblNombre);

        txtNombreTripulante = new JTextField();
        txtNombreTripulante.setFont(fuenteInputs);
        txtNombreTripulante.setColumns(12);
        panelNombre.add(txtNombreTripulante);

        GridBagConstraints gbc_panelNombre = new GridBagConstraints();
        gbc_panelNombre.anchor = GridBagConstraints.CENTER;
        gbc_panelNombre.insets = new Insets(0, 15, 5, 15);
        gbc_panelNombre.gridx = 0;
        gbc_panelNombre.gridy = 3;
        panelPrincipal.add(panelNombre, gbc_panelNombre);

        // 3. CAMPO ANTIGÜEDAD (Fila 5)
        JPanel panelAntiguedad = new JPanel();
        panelAntiguedad.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblAntiguedad = new JLabel("Antigüedad:");
        lblAntiguedad.setFont(fuenteCampos);
        panelAntiguedad.add(lblAntiguedad);

        txtAntiguedadTripulante = new JTextField();
        txtAntiguedadTripulante.setFont(fuenteInputs);
        txtAntiguedadTripulante.setColumns(8);
        panelAntiguedad.add(txtAntiguedadTripulante);

        GridBagConstraints gbc_panelAntiguedad = new GridBagConstraints();
        gbc_panelAntiguedad.anchor = GridBagConstraints.CENTER;
        gbc_panelAntiguedad.insets = new Insets(0, 15, 5, 15);
        gbc_panelAntiguedad.gridx = 0;
        gbc_panelAntiguedad.gridy = 5;
        panelPrincipal.add(panelAntiguedad, gbc_panelAntiguedad);

        // 4. CAMPO ORIGEN - MENÚ DESPLEGABLE (Fila 7)
        JPanel panelOrigen = new JPanel();
        panelOrigen.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setFont(fuenteCampos);
        panelOrigen.add(lblOrigen);

        String[] origenes = {FactoryPeople.MARCIANO, FactoryPeople.TERRICOLA, FactoryPeople.VULCANO};
        comboOrigenTripulante = new JComboBox<>(origenes);
        comboOrigenTripulante.setFont(fuenteInputs);
        panelOrigen.add(comboOrigenTripulante);

        GridBagConstraints gbc_panelOrigen = new GridBagConstraints();
        gbc_panelOrigen.anchor = GridBagConstraints.CENTER;
        gbc_panelOrigen.insets = new Insets(0, 15, 5, 15);
        gbc_panelOrigen.gridx = 0;
        gbc_panelOrigen.gridy = 7;
        panelPrincipal.add(panelOrigen, gbc_panelOrigen);

        // 5. CAMPO CARGO - MENÚ DESPLEGABLE (Fila 9)
        JPanel panelCargo = new JPanel();
        panelCargo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setFont(fuenteCampos);
        panelCargo.add(lblCargo);

        String[] cargos = {FactoryPeople.ALFEREZ,FactoryPeople.CONSEJERO,FactoryPeople.TENIENTE};
        comboCargoTripulante = new JComboBox<>(cargos);
        comboCargoTripulante.setFont(fuenteInputs);
        panelCargo.add(comboCargoTripulante);

        GridBagConstraints gbc_panelCargo = new GridBagConstraints();
        gbc_panelCargo.anchor = GridBagConstraints.CENTER;
        gbc_panelCargo.insets = new Insets(0, 15, 10, 15);
        gbc_panelCargo.gridx = 0;
        gbc_panelCargo.gridy = 9;
        panelPrincipal.add(panelCargo, gbc_panelCargo);

        // 6. BOTÓN REGISTRAR (Fila 11)
        btnCrear.setActionCommand(CREAR_TRIPULANTE);
        btnCrear.setEnabled(false);
        btnCrear.setFont(new Font("Tahoma", Font.BOLD, 13));
        GridBagConstraints gbc_btnCrear = new GridBagConstraints();
        gbc_btnCrear.insets = new Insets(0, 10, 10, 10);
        gbc_btnCrear.anchor = GridBagConstraints.CENTER;
        gbc_btnCrear.gridx = 0;
        gbc_btnCrear.gridy = 11;
        panelPrincipal.add(btnCrear, gbc_btnCrear);
    }

    @Override
    public void arranca() {
        pack();
        setSize(340, 380);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setControlador(Controlador controlador) {
        getBtnCrear().addActionListener(controlador);
        getTxtNombreTripulante().getDocument().addDocumentListener(controlador);
        getTxtAntiguedadTripulante().getDocument().addDocumentListener(controlador);
    }

    public JTextField getTxtNombreTripulante() {
        return txtNombreTripulante;
    }

    public JTextField getTxtAntiguedadTripulante() {
        return txtAntiguedadTripulante;
    }

    public JComboBox<String> getComboOrigenTripulante() {
        return comboOrigenTripulante;
    }

    public JComboBox<String> getComboCargoTripulante() {
        return comboCargoTripulante;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }
}
