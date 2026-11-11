package CapaPresentacion;

import CapaNegocio.Controlador;
import tripulantes.FactoryPeople;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField; // <--- Importante para los campos de texto
import javax.swing.JComboBox; // <--- Importante para el menú desplegable de orígenes
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class VistaCrearNave extends JFrame implements IVista {
	public final static String CREAR_NAVE = "CREAR_NAVE";
	private JPanel panelPrincipal;
	private final ButtonGroup buttonGroupNaves = new ButtonGroup();
	private JTextField txtNombreNave; // <--- Atributo encapsulado para el nombre de la nave
	private JButton btnCrear = new JButton("Crear Nave");
	private JRadioButton radioExploradora = new JRadioButton("Nave Exploradora");
	private JRadioButton radioCarguera = new JRadioButton("Nave Carguera");
	private JRadioButton radioCombate = new JRadioButton("Nave de Combate");

	// Nuevos atributos encapsulados para los datos del Capitán
	private JTextField txtNombreCapitan;
	private JTextField txtAntiguedadCapitan;
	private JComboBox<String> comboOrigenCapitan;

	public VistaCrearNave() {
		setTitle("CREAR NAVE Y CAPITÁN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// 1. Panel Principal con GridLayout de 1 fila y 2 columnas para extender al costado
		panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(1, 2, 10, 0));

		// =========================================================================
		// COLUMNA IZQUIERDA: SECCIÓN CREAR NAVE
		// =========================================================================
		JPanel panelNave = new JPanel();
		panelPrincipal.add(panelNave);

		// 2. GridBagLayout configurado con pesos horizontales para permitir el centrado real
		GridBagLayout gbl_panelNave = new GridBagLayout();
		gbl_panelNave.columnWidths = new int[] {0};
		gbl_panelNave.rowHeights = new int[] {15, 30, 15, 80, 15, 40, 15, 30, 15};
		gbl_panelNave.columnWeights = new double[]{1.0};
		gbl_panelNave.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelNave.setLayout(gbl_panelNave);

		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteOpciones = new Font("Tahoma", Font.PLAIN, 13);
		Font fuenteCampos = new Font("Tahoma", Font.BOLD, 12);
		Font fuenteInputs = new Font("Tahoma", Font.PLAIN, 12);

		// 3. TÍTULO NAVE (Fila 1) - Configurado al CENTRO
		JLabel lblTitulo = new JLabel("SELECCIONAR TIPO DE NAVE");
		lblTitulo.setFont(fuenteTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
		gbc_lblTitulo.insets = new Insets(0, 10, 10, 10);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		panelNave.add(lblTitulo, gbc_lblTitulo);

		// 4. PANEL DE CONTENEDOR RADIO BUTTONS (Fila 3) - Configurado al CENTRO
		JPanel panelRadioButtons = new JPanel();
		GridBagConstraints gbc_panelRadioButtons = new GridBagConstraints();
		gbc_panelRadioButtons.anchor = GridBagConstraints.CENTER;
		gbc_panelRadioButtons.insets = new Insets(0, 10, 10, 10);
		gbc_panelRadioButtons.gridx = 0;
		gbc_panelRadioButtons.gridy = 3;
		panelNave.add(panelRadioButtons, gbc_panelRadioButtons);
		panelRadioButtons.setLayout(new GridLayout(0, 1, 0, 5));

		radioExploradora.setFont(fuenteOpciones);
		panelRadioButtons.add(radioExploradora);
		buttonGroupNaves.add(radioExploradora);

		radioCarguera.setFont(fuenteOpciones);
		panelRadioButtons.add(radioCarguera);
		buttonGroupNaves.add(radioCarguera);

		radioCombate.setFont(fuenteOpciones);
		panelRadioButtons.add(radioCombate);
		buttonGroupNaves.add(radioCombate);

		// 5. FILA 5: CAMPO DE TEXTO PARA NOMBRE DE LA NAVE
		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(fuenteCampos);
		panelNombre.add(lblNombre);
		txtNombreNave = new JTextField();
		txtNombreNave.setFont(fuenteInputs);
		txtNombreNave.setColumns(12); // Tamaño ideal para nombres de naves estándar
		panelNombre.add(txtNombreNave);

		GridBagConstraints gbc_panelNombre = new GridBagConstraints();
		gbc_panelNombre.anchor = GridBagConstraints.CENTER;
		gbc_panelNombre.insets = new Insets(0, 15, 10, 15);
		gbc_panelNombre.gridx = 0;
		gbc_panelNombre.gridy = 5;
		panelNave.add(panelNombre, gbc_panelNombre);

		// 6. BOTÓN PRINCIPAL (Fila 7) - Configurado al CENTRO
		btnCrear.setActionCommand(CREAR_NAVE);
		btnCrear.setEnabled(false);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.insets = new Insets(0, 10, 10, 10);
		gbc_btnCrear.anchor = GridBagConstraints.CENTER;
		gbc_btnCrear.gridx = 0;
		gbc_btnCrear.gridy = 7;
		panelNave.add(btnCrear, gbc_btnCrear);

		// =========================================================================
		// COLUMNA DERECHA: NUEVA SECCIÓN CREAR CAPITÁN
		// =========================================================================
		JPanel panelCapitan = new JPanel();
		panelPrincipal.add(panelCapitan);

		GridBagLayout gbl_panelCapitan = new GridBagLayout();
		gbl_panelCapitan.columnWidths = new int[] {0};
		gbl_panelCapitan.rowHeights = new int[] {15, 30, 15, 40, 10, 40, 10, 40, 15};
		gbl_panelCapitan.columnWeights = new double[]{1.0};
		gbl_panelCapitan.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelCapitan.setLayout(gbl_panelCapitan);

		// TÍTULO CAPITÁN (Fila 1)
		JLabel lblTituloCapitan = new JLabel("DATOS DEL CAPITÁN");
		lblTituloCapitan.setFont(fuenteTitulo);
		lblTituloCapitan.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTituloCapitan = new GridBagConstraints();
		gbc_lblTituloCapitan.anchor = GridBagConstraints.CENTER;
		gbc_lblTituloCapitan.insets = new Insets(0, 10, 10, 10);
		gbc_lblTituloCapitan.gridx = 0;
		gbc_lblTituloCapitan.gridy = 1;
		panelCapitan.add(lblTituloCapitan, gbc_lblTituloCapitan);

		// CAMPO NOMBRE DEL CAPITÁN (Fila 3)
		JPanel panelNombreCapitan = new JPanel();
		panelNombreCapitan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		JLabel lblNombreCapitan = new JLabel("Nombre:");
		lblNombreCapitan.setFont(fuenteCampos);
		panelNombreCapitan.add(lblNombreCapitan);

		txtNombreCapitan = new JTextField();
		txtNombreCapitan.setFont(fuenteInputs);
		txtNombreCapitan.setColumns(12);
		panelNombreCapitan.add(txtNombreCapitan);

		GridBagConstraints gbc_panelNombreCapitan = new GridBagConstraints();
		gbc_panelNombreCapitan.anchor = GridBagConstraints.CENTER;
		gbc_panelNombreCapitan.insets = new Insets(0, 15, 5, 15);
		gbc_panelNombreCapitan.gridx = 0;
		gbc_panelNombreCapitan.gridy = 3;
		panelCapitan.add(panelNombreCapitan, gbc_panelNombreCapitan);

		// CAMPO ORIGEN DEL CAPITÁN - MENÚ DESPLEGABLE (Fila 5)
		JPanel panelOrigenCapitan = new JPanel();
		panelOrigenCapitan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		JLabel lblOrigenCapitan = new JLabel("Origen:");
		lblOrigenCapitan.setFont(fuenteCampos);
		panelOrigenCapitan.add(lblOrigenCapitan);

		String[] origenes = {FactoryPeople.MARCIANO, FactoryPeople.TERRICOLA, FactoryPeople.VULCANO};
		comboOrigenCapitan = new JComboBox<>(origenes);
		comboOrigenCapitan.setFont(fuenteInputs);
		panelOrigenCapitan.add(comboOrigenCapitan);

		GridBagConstraints gbc_panelOrigenCapitan = new GridBagConstraints();
		gbc_panelOrigenCapitan.anchor = GridBagConstraints.CENTER;
		gbc_panelOrigenCapitan.insets = new Insets(0, 15, 5, 15);
		gbc_panelOrigenCapitan.gridx = 0;
		gbc_panelOrigenCapitan.gridy = 5;
		panelCapitan.add(panelOrigenCapitan, gbc_panelOrigenCapitan);

		// CAMPO ANTIGÜEDAD DEL CAPITÁN (Fila 7)
		JPanel panelAntiguedadCapitan = new JPanel();
		panelAntiguedadCapitan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		JLabel lblAntiguedadCapitan = new JLabel("Antigüedad:");
		lblAntiguedadCapitan.setFont(fuenteCampos);
		panelAntiguedadCapitan.add(lblAntiguedadCapitan);

		txtAntiguedadCapitan = new JTextField();
		txtAntiguedadCapitan.setFont(fuenteInputs);
		txtAntiguedadCapitan.setColumns(8);
		panelAntiguedadCapitan.add(txtAntiguedadCapitan);

		GridBagConstraints gbc_panelAntiguedadCapitan = new GridBagConstraints();
		gbc_panelAntiguedadCapitan.anchor = GridBagConstraints.CENTER;
		gbc_panelAntiguedadCapitan.insets = new Insets(0, 15, 10, 15);
		gbc_panelAntiguedadCapitan.gridx = 0;
		gbc_panelAntiguedadCapitan.gridy = 7;
		panelCapitan.add(panelAntiguedadCapitan, gbc_panelAntiguedadCapitan);
	}

	@Override
	public void arranca() {
		pack();
		// CAMBIO: Aumentado el ancho de la ventana de 280 a 560 para alojar ambos paneles lado a lado
		setSize(560, 330);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador) {
		getBtnCrear().addActionListener(controlador);
		getTxtNombreNave().getDocument().addDocumentListener(controlador);
		getTxtNombreCapitan().getDocument().addDocumentListener(controlador);
		getTxtAntiguedadCapitan().getDocument().addDocumentListener(controlador);
	}

	public ButtonGroup getButtonGroupNaves() {
		return buttonGroupNaves;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public JRadioButton getRadioExploradora() {
		return radioExploradora;
	}

	public JRadioButton getRadioCarguera() {
		return radioCarguera;
	}

	public JRadioButton getRadioCombate() {
		return radioCombate;
	}

	public JTextField getTxtNombreNave() {
		return txtNombreNave;
	}

	public JTextField getTxtNombreCapitan() {
		return txtNombreCapitan;
	}

	public JTextField getTxtAntiguedadCapitan() {
		return txtAntiguedadCapitan;
	}
	public JComboBox getComboOrigenCapitan() {
		return comboOrigenCapitan;
	}

}