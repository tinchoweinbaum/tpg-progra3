package Vista;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class VistaCrearNave extends JFrame implements IVista {

	private JPanel panelPrincipal;
	private final ButtonGroup buttonGroupNaves = new ButtonGroup();

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
		gbl_panel.rowHeights = new int[] {20, 30, 20, 80, 20, 30, 20}; // Estructura idéntica
		gbl_panel.columnWeights = new double[]{1.0}; // Peso 1.0 para forzar el centrado
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteOpciones = new Font("Tahoma", Font.PLAIN, 13);
		
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
		panelRadioButtons.setLayout(new GridLayout(0, 1, 0, 5)); // 5px de separación vertical
		
		JRadioButton radioExploradora = new JRadioButton("Nave Exploradora");
		radioExploradora.setFont(fuenteOpciones);
		panelRadioButtons.add(radioExploradora);
		buttonGroupNaves.add(radioExploradora);
		
		JRadioButton radioCarguera = new JRadioButton("Nave Carguera");
		radioCarguera.setFont(fuenteOpciones);
		panelRadioButtons.add(radioCarguera);
		buttonGroupNaves.add(radioCarguera);
		
		JRadioButton radioCombate = new JRadioButton("Nave de Combate");
		radioCombate.setFont(fuenteOpciones);
		panelRadioButtons.add(radioCombate);
		buttonGroupNaves.add(radioCombate);
		
		// 5. BOTÓN PRINCIPAL (Fila 5) - Configurado al CENTRO
		JButton btnCrear = new JButton("Crear Nave");
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.insets = new Insets(0, 10, 10, 10);
		gbc_btnCrear.anchor = GridBagConstraints.CENTER;
		gbc_btnCrear.gridx = 0;
		gbc_btnCrear.gridy = 5;
		panel.add(btnCrear, gbc_btnCrear);
	}
	
	@Override
	public void arranca() {
		pack();
		setSize(280, 280); // Ajustado levemente el ancho por la longitud de los textos
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VistaCrearNave vistaCrearNave = new VistaCrearNave();
		vistaCrearNave.arranca();
	}
}
