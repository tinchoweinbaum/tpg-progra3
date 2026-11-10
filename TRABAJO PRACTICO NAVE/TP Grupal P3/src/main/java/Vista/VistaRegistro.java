package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;

public class VistaRegistro extends JFrame implements IVista {

	private JPanel panelPrincipal;
	private JTextField textField;
	private JPasswordField passwordField;

	public VistaRegistro() {
		setTitle("REGISTRARSE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// 1. Configuramos el panel principal directamente con un GridBagLayout limpio
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25)); // Añadimos más margen perimetral respirable
		setContentPane(panelPrincipal);
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {130, 200}; // Ancho exacto y equilibrado de columnas (Etiqueta e Input)
		gbl.rowHeights = new int[] {40, 40, 50};  // Altura cómoda de filas y mayor espacio para la fila de los botones
		gbl.columnWeights = new double[]{0.0, 1.0}; // La columna de inputs absorbe de forma elástica el ancho sobrante
		gbl.rowWeights = new double[]{0.0, 0.0, 0.0};
		panelPrincipal.setLayout(gbl);
		
		Font fuenteLabels = new Font("Tahoma", Font.BOLD, 14); // Cambiada a negrita sutil, menor tamaño para mayor elegancia
		Font fuenteInputs = new Font("Tahoma", Font.PLAIN, 14);
		
		// --------------------------------------------------------------------
		// 2. FILA 0: USUARIO
		// --------------------------------------------------------------------
		JLabel labelName = new JLabel("USUARIO:");
		labelName.setFont(fuenteLabels);
		labelName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_labelName = new GridBagConstraints();
		gbc_labelName.anchor = GridBagConstraints.WEST; // Alineado a la izquierda
		gbc_labelName.insets = new Insets(0, 0, 15, 15); // Separación vertical inferior y horizontal derecha
		gbc_labelName.gridx = 0;
		gbc_labelName.gridy = 0;
		panelPrincipal.add(labelName, gbc_labelName);
		
		textField = new JTextField();
		textField.setFont(fuenteInputs);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL; // Se estira de forma horizontal uniformemente
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
		// 4. FILA 2: BOTÓN REGISTRARSE (Centrado horizontal ocupando las dos columnas)
		// --------------------------------------------------------------------
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.CENTER; // Forzamos el centrado matemático
		gbc_btnNewButton_1.gridwidth = 2; // Hace un span que une ambas columnas
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panelPrincipal.add(btnNewButton_1, gbc_btnNewButton_1);
	}
	
	@Override
	public void arranca() {
		pack();
		setSize(420, 240); // Redimensionado proporcional para evitar espacios vacíos gigantes
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VistaRegistro vistaRegistro = new VistaRegistro();
		vistaRegistro.arranca();
	}
}
