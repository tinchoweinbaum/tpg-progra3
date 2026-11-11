package CapaPresentacion;

import CapaNegocio.Controlador;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox; // <--- Importante para la lista desplegable
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;

public class VistaRegistro extends JFrame implements IVista {
	public final static String REGISTRO_USER = "REGISTRARUSUARIO";
	private JPanel panelPrincipal;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_1 = new JButton("Registrarse");

	public VistaRegistro() {
		setTitle("REGISTRARSE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
		setContentPane(panelPrincipal);

		// Añadimos una fila extra en rowHeights (pasó de tener 3 a 4 alturas definidas)
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {130, 200};
		gbl.rowHeights = new int[] {40, 40, 40, 50}; // <--- Fila extra de 40px de alto para el origen
		gbl.columnWeights = new double[]{0.0, 1.0};
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panelPrincipal.setLayout(gbl);

		Font fuenteLabels = new Font("Tahoma", Font.BOLD, 14);
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
		gbc_labelPassword.insets = new Insets(0, 0, 15, 15); // Reducido el inset inferior a 15 para mantener uniformidad
		gbc_labelPassword.gridx = 0;
		gbc_labelPassword.gridy = 1;
		panelPrincipal.add(labelPassword, gbc_labelPassword);

		passwordField = new JPasswordField();
		passwordField.setFont(fuenteInputs);
		passwordField.setEchoChar('*');
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 15, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panelPrincipal.add(passwordField, gbc_passwordField);

		// --------------------------------------------------------------------
		// 5. FILA 3: BOTÓN REGISTRARSE (Se desplaza a la fila index 3)
		// --------------------------------------------------------------------
		btnNewButton_1.setActionCommand(REGISTRO_USER);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.CENTER;
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3; // <--- Cambiado de 2 a 3
		panelPrincipal.add(btnNewButton_1, gbc_btnNewButton_1);
	}

	@Override
	public void arranca() {
		pack();
		setSize(420, 290);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador) {
		getPasswordField().getDocument().addDocumentListener(controlador);
		getTextUsuario().getDocument().addDocumentListener(controlador);
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

	public JButton getBtnRegistro() {
		return btnNewButton_1;
	}

	public void setBtnRegistro(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}


}
