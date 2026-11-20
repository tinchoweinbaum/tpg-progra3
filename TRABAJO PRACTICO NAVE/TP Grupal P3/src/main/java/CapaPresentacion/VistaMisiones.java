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

		panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {20, 30, 20, 80, 20, 30, 20};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteOpciones = new Font("Tahoma", Font.PLAIN, 13);
		
		JLabel lblNewLabel = new JLabel("MISIONES DISPONIBLES");
		lblNewLabel.setFont(fuenteTitulo);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.CENTER;
		gbc_lblNewLabel.insets = new Insets(0, 10, 10, 10);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panelRadioButtons = new JPanel();
		GridBagConstraints gbc_panelRadioButtons = new GridBagConstraints();
		gbc_panelRadioButtons.anchor = GridBagConstraints.CENTER;
		gbc_panelRadioButtons.insets = new Insets(0, 10, 10, 10);
		gbc_panelRadioButtons.gridx = 0;
		gbc_panelRadioButtons.gridy = 3;
		panel.add(panelRadioButtons, gbc_panelRadioButtons);
		panelRadioButtons.setLayout(new GridLayout(0, 1, 0, 5));

		radioOne.setFont(fuenteOpciones);
		panelRadioButtons.add(radioOne);
		buttonGroupMisiones.add(radioOne);

		radioTwo.setFont(fuenteOpciones);
		panelRadioButtons.add(radioTwo);
		buttonGroupMisiones.add(radioTwo);

		radioThree.setFont(fuenteOpciones);
		panelRadioButtons.add(radioThree);
		buttonGroupMisiones.add(radioThree);

		btnNewButton.setActionCommand(SELECCIONA_MISION);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton.anchor = GridBagConstraints.CENTER;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		panel.add(btnNewButton, gbc_btnNewButton);
	}
	
	@Override
	public void arranca() {
		pack();
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
