package CapaPresentacion;

import CapaNegocio.Controlador;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;

public class VistaInicioSesion extends JFrame implements IVista {
	public final static String INICIO_SESION = "INICIARSESION";
	public final static String ACCESO_REGISTRO = "IRREGISTRARSE";
	private JPanel panelPrincipal;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton = new JButton("Iniciar Sesión");
	private JButton btnNewButton_1 = new JButton("Registrarse");

	public VistaInicioSesion() {
		setTitle("INICIO SESIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// 1. Panel principal unificado con un GridBagLayout limpio
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25)); // Margen perimetral idéntico
		setContentPane(panelPrincipal);
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {130, 200}; // Ancho idéntico de columnas para consistencia visual
		gbl.rowHeights = new int[] {40, 40, 50};  // Alturas de fila proporcionales
		gbl.columnWeights = new double[]{0.0, 1.0};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0};
		panelPrincipal.setLayout(gbl);
		
		Font fuenteLabels = new Font("Tahoma", Font.BOLD, 14); // Misma tipografía elegante
		Font fuenteInputs = new Font("Tahoma", Font.PLAIN, 14);
		
		// --------------------------------------------------------------------
		// 2. FILA 0: USUARIO
		// --------------------------------------------------------------------
		JLabel labelName = new JLabel("USUARIO:");
		labelName.setFont(fuenteLabels);
		labelName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.WEST;
		gbc_labelName.insets = new Insets(0, 0, 15, 15);
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		panelPrincipal.add(labelName, gbc_labelName);
		
		textField = new JTextField();
		textField.setFont(fuenteInputs);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 15, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panelPrincipal.add(textField, gbc_textField);
		
		// --------------------------------------------------------------------
		// 3. FILA 1: CONTRASEÑA
		// --------------------------------------------------------------------
		JLabel labelPassword = new JLabel("CONTRASEÑA:");
		labelPassword.setFont(fuenteLabels);
		labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelPassword = new GridBagConstraints();
		gbc_labelPassword.anchor = GridBagConstraints.WEST;
		gbc_labelPassword.insets = new Insets(0, 0, 20, 15);
		gbc_labelPassword.gridx = 0;
		gbc_labelPassword.gridy = 1;
		panelPrincipal.add(labelPassword, gbc_labelPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(fuenteInputs);
		passwordField.setEchoChar('*');
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 20, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panelPrincipal.add(passwordField, gbc_passwordField);
		
		// --------------------------------------------------------------------
		// 4. FILA 2: CONTENEDOR DE BOTONES (Abarca ambas columnas, centrado)
		// --------------------------------------------------------------------
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0)); // Separación limpia de 15px entre botones
		
		btnNewButton.setActionCommand(INICIO_SESION);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setEnabled(false); // Mantiene tu estado inicial deshabilitado
		panelBotones.add(btnNewButton);
		btnNewButton_1.setActionCommand(ACCESO_REGISTRO);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelBotones.add(btnNewButton_1);
		
		// Ubicamos el contenedor de botones en la cuadrícula principal
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.anchor = GridBagConstraints.CENTER;
		gbc_panelBotones.gridwidth = 2; // Fusiona la columna 0 y 1
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 2;
		panelPrincipal.add(panelBotones, gbc_panelBotones);
	}
	
	@Override
	public void arranca() {
		pack();
		setSize(420, 240); // Mismo tamaño exacto que la de registro para que no salte el tamaño de la ventana
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador){
		getPasswordField().getDocument().addDocumentListener(controlador);
		getTextUsuario().getDocument().addDocumentListener(controlador);
		getBtnInicioSesion().addActionListener(controlador);
		getBtnRegistro().addActionListener(controlador);
	}

	public JTextField getTextUsuario() {
		return textField;
	}

	public void setTextUsuario(JTextField textField) {
		this.textField = textField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnInicioSesion() {
		return btnNewButton;
	}

	public void setBtnInicioSesion(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnRegistro() {
		return btnNewButton_1;
	}

	public void setBtnRegistro(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}
}
