package CapaPresentacion;

import CapaNegocio.Controlador;
import CapaDatos.tripulantes.FactoryPeople;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

public class VistaCrearNave extends JFrame implements IVista {
	public final static String CREAR_NAVE = "CREAR_NAVE";
	private JPanel panelPrincipal;
	private final ButtonGroup buttonGroupNaves = new ButtonGroup();
	private JTextField txtNombreNave;
	private JButton btnCrear = new JButton("Crear Nave");
	private JRadioButton radioExploradora = new JRadioButton("Nave Exploradora");
	private JRadioButton radioCarguera = new JRadioButton("Nave Carguera");
	private JRadioButton radioCombate = new JRadioButton("Nave de Combate");

	private JTextField txtNombreCapitan;
	private JTextField txtAntiguedadCapitan;
	private JComboBox<String> comboOrigenCapitan;

	public VistaCrearNave() {
		setTitle("CREAR NAVE Y CAPITÁN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		panelPrincipal = new JPanel(new BorderLayout(10, 10));
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		setContentPane(panelPrincipal);

		JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteOpciones = new Font("Tahoma", Font.PLAIN, 13);
		Font fuenteCampos = new Font("Tahoma", Font.BOLD, 12);
		Font fuenteInputs = new Font("Tahoma", Font.PLAIN, 12);

		JPanel panelSeccionNave = new JPanel(new BorderLayout(10, 0));
		panelSeccionNave.setBorder(BorderFactory.createTitledBorder("Datos de la Nave"));
		panelCentral.add(panelSeccionNave);

		JLabel lblIconoNave = new JLabel(formatearImagen("naveUser.png", 64, 64));
		lblIconoNave.setVerticalAlignment(SwingConstants.CENTER);
		lblIconoNave.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 5));
		panelSeccionNave.add(lblIconoNave, BorderLayout.WEST);

		JPanel panelNave = new JPanel(new GridBagLayout());
		panelSeccionNave.add(panelNave, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblTitulo = new JLabel("TIPO DE NAVE");
		lblTitulo.setFont(fuenteTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panelNave.add(lblTitulo, gbc);

		JPanel panelRadioButtons = new JPanel(new GridLayout(0, 1, 0, 4));
		radioExploradora.setFont(fuenteOpciones);
		radioCarguera.setFont(fuenteOpciones);
		radioCombate.setFont(fuenteOpciones);

		buttonGroupNaves.add(radioExploradora);
		buttonGroupNaves.add(radioCarguera);
		buttonGroupNaves.add(radioCombate);

		panelRadioButtons.add(radioExploradora);
		panelRadioButtons.add(radioCarguera);
		panelRadioButtons.add(radioCombate);

		gbc.gridy = 1;
		gbc.insets = new Insets(5, 5, 12, 5);
		panelNave.add(panelRadioButtons, gbc);

		gbc.gridwidth = 1;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(fuenteCampos);
		panelNave.add(lblNombre, gbc);

		gbc.gridx = 1;
		txtNombreNave = new JTextField(12);
		txtNombreNave.setFont(fuenteInputs);
		panelNave.add(txtNombreNave, gbc);

		JPanel panelSeccionCapitan = new JPanel(new BorderLayout(10, 0));
		panelSeccionCapitan.setBorder(BorderFactory.createTitledBorder("Datos del Capitán"));
		panelCentral.add(panelSeccionCapitan);

		JLabel lblIconoCapitan = new JLabel(formatearImagen("capitan.png", 64, 64));
		lblIconoCapitan.setVerticalAlignment(SwingConstants.CENTER);
		lblIconoCapitan.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 5));
		panelSeccionCapitan.add(lblIconoCapitan, BorderLayout.WEST);

		JPanel panelCapitan = new JPanel(new GridBagLayout());
		panelSeccionCapitan.add(panelCapitan, BorderLayout.CENTER);

		GridBagConstraints gbcCap = new GridBagConstraints();
		gbcCap.insets = new Insets(8, 5, 8, 5);
		gbcCap.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblTituloCapitan = new JLabel("TRIPULACIÓN AL MANDO");
		lblTituloCapitan.setFont(fuenteTitulo);
		gbcCap.gridx = 0;
		gbcCap.gridy = 0;
		gbcCap.gridwidth = 2;
		panelCapitan.add(lblTituloCapitan, gbcCap);

		gbcCap.gridwidth = 1;
		gbcCap.gridy = 1;
		gbcCap.gridx = 0;
		JLabel lblNombreCapitan = new JLabel("Nombre:");
		lblNombreCapitan.setFont(fuenteCampos);
		panelCapitan.add(lblNombreCapitan, gbcCap);

		gbcCap.gridx = 1;
		txtNombreCapitan = new JTextField(12);
		txtNombreCapitan.setFont(fuenteInputs);
		panelCapitan.add(txtNombreCapitan, gbcCap);

		gbcCap.gridy = 2;
		gbcCap.gridx = 0;
		JLabel lblOrigenCapitan = new JLabel("Origen:");
		lblOrigenCapitan.setFont(fuenteCampos);
		panelCapitan.add(lblOrigenCapitan, gbcCap);

		gbcCap.gridx = 1;
		String[] origenes = {FactoryPeople.MARCIANO, FactoryPeople.TERRICOLA, FactoryPeople.VULCANO};
		comboOrigenCapitan = new JComboBox<>(origenes);
		comboOrigenCapitan.setFont(fuenteInputs);
		panelCapitan.add(comboOrigenCapitan, gbcCap);

		gbcCap.gridy = 3;
		gbcCap.gridx = 0;
		JLabel lblAntiguedadCapitan = new JLabel("Antigüedad:");
		lblAntiguedadCapitan.setFont(fuenteCampos);
		panelCapitan.add(lblAntiguedadCapitan, gbcCap);

		gbcCap.gridx = 1;
		txtAntiguedadCapitan = new JTextField(12);
		txtAntiguedadCapitan.setFont(fuenteInputs);
		panelCapitan.add(txtAntiguedadCapitan, gbcCap);

		JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBoton.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

		btnCrear.setActionCommand(CREAR_NAVE);
		btnCrear.setEnabled(false);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelBoton.add(btnCrear);

		panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
	}

	/**
	 * Carga cualquier imagen (.png, .jpg, .jpeg, .gif) desde la carpeta del proyecto
	 * y la formatea automáticamente al tamaño indicado sin usar getClass().
	 */
	private ImageIcon formatearImagen(String nombreArchivo, int ancho, int alto) {
		try {
			File archivo = new File("src/main/java/CapaPresentacion/iconosFolder/" + nombreArchivo);

			if (!archivo.exists()) {
				System.err.println("Archivo no encontrado: " + archivo.getPath());
				return null;
			}

			Image imagenOriginal = ImageIO.read(archivo);
			Image imagenFormateada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			return new ImageIcon(imagenFormateada);

		} catch (Exception e) {
			System.err.println("Error al procesar la imagen " + nombreArchivo + ": " + e.getMessage());
			return null;
		}
	}


	@Override
	public void arranca() {
		pack();
		setSize(680, 320);
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
