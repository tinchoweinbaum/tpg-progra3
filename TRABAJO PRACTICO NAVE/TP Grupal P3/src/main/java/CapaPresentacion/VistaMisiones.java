package CapaPresentacion;

import CapaNegocio.Controlador;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VistaMisiones extends JFrame implements IVista {
	public final static String SELECCIONA_MISION = "SELECCIONA_MISION";
	private JPanel panelPrincipal;
	private final ButtonGroup buttonGroupMisiones = new ButtonGroup();
	private JRadioButton radioOne = new JRadioButton("MISION 1 - Intercepción y asistencia");
	private JRadioButton radioTwo = new JRadioButton("MISION 2 - Recolección");
	private JRadioButton radioThree = new JRadioButton("MISION 3 - Retorno a punto seguro");
	private JButton btnNewButton = new JButton("Realizar Mision");

	public VistaMisiones() {
		setTitle("MISIONES");
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
		gbl_panel.rowHeights = new int[] {20, 30, 20, 80, 20, 30, 20}; // Márgenes y alturas proporcionales
		gbl_panel.columnWeights = new double[]{1.0}; // El peso 1.0 obliga a centrar respecto al ancho total
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteOpciones = new Font("Tahoma", Font.PLAIN, 13);
		
		// 3. TÍTULO (Fila 1) - Configurado al CENTRO
		JLabel lblNewLabel = new JLabel("MISIONES DISPONIBLES");
		lblNewLabel.setFont(fuenteTitulo);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.CENTER; // <--- Cambiado de WEST a CENTER
		gbc_lblNewLabel.insets = new Insets(0, 10, 10, 10);
		gbc_lblNewLabel.gridx = 0; // Columna única
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		// 4. PANEL DE CONTENEDOR RADIO BUTTONS (Fila 3) - Configurado al CENTRO
		JPanel panelRadioButtons = new JPanel();
		GridBagConstraints gbc_panelRadioButtons = new GridBagConstraints();
		gbc_panelRadioButtons.anchor = GridBagConstraints.CENTER; // <--- Cambiado de NORTHWEST a CENTER
		gbc_panelRadioButtons.insets = new Insets(0, 10, 10, 10);
		gbc_panelRadioButtons.gridx = 0;
		gbc_panelRadioButtons.gridy = 3;
		panel.add(panelRadioButtons, gbc_panelRadioButtons);
		panelRadioButtons.setLayout(new GridLayout(0, 1, 0, 5)); // 5px de separación vertical entre opciones

		radioOne.setFont(fuenteOpciones);
		panelRadioButtons.add(radioOne);
		buttonGroupMisiones.add(radioOne); // Agregado al grupo (Faltaba en tu código)

		radioTwo.setFont(fuenteOpciones);
		panelRadioButtons.add(radioTwo);
		buttonGroupMisiones.add(radioTwo);

		radioThree.setFont(fuenteOpciones);
		panelRadioButtons.add(radioThree);
		buttonGroupMisiones.add(radioThree);
		
		
		// 5. BOTÓN PRINCIPAL (Fila 5) - Configurado al CENTRO

		btnNewButton.setActionCommand(SELECCIONA_MISION);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton.anchor = GridBagConstraints.CENTER; // <--- Cambiado de WEST a CENTER
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		panel.add(btnNewButton, gbc_btnNewButton);
	}
	
	@Override
	public void arranca() {
		pack();
		// Le asignamos un tamaño mínimo cómodo para que se aprecie bien el centrado
		setSize(300, 280);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador) {
		getBtnAceptaMision().addActionListener(controlador);
	}

	public ButtonGroup getButtonGroupMisiones() {
		return buttonGroupMisiones;
	}

	public JRadioButton getRadioOne() {
		return radioOne;
	}

	public JRadioButton getRadioTwo() {
		return radioTwo;
	}

	public JRadioButton getRadioThree() {
		return radioThree;
	}

	public JButton getBtnAceptaMision() {
		return btnNewButton;
	}
}
