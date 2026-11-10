package CapaPresentacion;

import CapaNegocio.Controlador;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField; // <--- Importante para el campo de nombre
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

	public VistaCrearNave() {
		setTitle("CREAR NAVE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// 1. Panel Principal con GridLayout de 1 sola celda para expandir el panel interno
		panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(1, 1, 0, 0));

		JPanel panel = new JPanel();
		panelPrincipal.add(panel);

		// 2. GridBagLayout configurado con pesos horizontales para permitir el centrado real
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		// CAMBIO: Añadimos una fila extra (pasó de 7 a 9 elementos de altura) para alojar el campo de texto
		gbl_panel.rowHeights = new int[] {15, 30, 15, 80, 15, 40, 15, 30, 15};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);

		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteOpciones = new Font("Tahoma", Font.PLAIN, 13);
		Font fuenteCampos = new Font("Tahoma", Font.BOLD, 12);

		// 3. TÍTULO (Fila 1) - Configurado al CENTRO
		JLabel lblTitulo = new JLabel("SELECCIONAR TIPO DE NAVE");
		lblTitulo.setFont(fuenteTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
		gbc_lblTitulo.insets = new Insets(0, 10, 10, 10);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);

		// 4. PANEL DE CONTENEDOR RADIO BUTTONS (Fila 3) - Configurado al CENTRO
		JPanel panelRadioButtons = new JPanel();
		GridBagConstraints gbc_panelRadioButtons = new GridBagConstraints();
		gbc_panelRadioButtons.anchor = GridBagConstraints.CENTER;
		gbc_panelRadioButtons.insets = new Insets(0, 10, 10, 10);
		gbc_panelRadioButtons.gridx = 0;
		gbc_panelRadioButtons.gridy = 3;
		panel.add(panelRadioButtons, gbc_panelRadioButtons);
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

		// 5. NUEVA FILA 5: CAMPO DE TEXTO PARA NOMBRE DE LA NAVE
		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(fuenteCampos);
		panelNombre.add(lblNombre);
		txtNombreNave = new JTextField();
		txtNombreNave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombreNave.setColumns(12); // Tamaño ideal para nombres de naves estándar
		panelNombre.add(txtNombreNave);

		GridBagConstraints gbc_panelNombre = new GridBagConstraints();
		gbc_panelNombre.anchor = GridBagConstraints.CENTER;
		gbc_panelNombre.insets = new Insets(0, 15, 10, 15);
		gbc_panelNombre.gridx = 0;
		gbc_panelNombre.gridy = 5; // Ubicado estratégicamente justo antes del botón de creación
		panel.add(panelNombre, gbc_panelNombre);

		// 6. BOTÓN PRINCIPAL (Se desplaza a la Fila 7) - Configurado al CENTRO
		btnCrear.setActionCommand(CREAR_NAVE);
		btnCrear.setEnabled(false);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.insets = new Insets(0, 10, 10, 10);
		gbc_btnCrear.anchor = GridBagConstraints.CENTER;
		gbc_btnCrear.gridx = 0;
		gbc_btnCrear.gridy = 7; // <--- Cambiado de 5 a 7
		panel.add(btnCrear, gbc_btnCrear);
	}

	@Override
	public void arranca() {
		pack();
		// CAMBIO: Aumentado el alto de la ventana de 280 a 330 para alojar el campo de texto de forma holgada
		setSize(280, 330);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador) {
		getBtnCrear().addActionListener(controlador);
		getTxtNombreNave().getDocument().addDocumentListener(controlador);

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

}
