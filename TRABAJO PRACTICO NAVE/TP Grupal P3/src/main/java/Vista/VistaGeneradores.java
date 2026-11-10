package Vista;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VistaGeneradores extends JFrame implements IVista {

	private JPanel panelPrincipal;
	private JTextArea areaConsolaMini;

	public VistaGeneradores() {
		setTitle("GENERADORES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		// 1. Panel Principal con GridLayout de 1 sola celda para expandir el panel interno
		panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel);
		
		// 2. GridBagLayout maestro configurado con DOS columnas principales
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {240, 240}; // Columna 0 (Controles) y Columna 1 (Consola lateral)
		gbl_panel.rowHeights = new int[] {25, 40, 20, 80, 25}; // Alturas proporcionales para el lado izquierdo
		gbl_panel.columnWeights = new double[]{1.0, 1.0}; // Ambas columnas se estiran equitativamente
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteBotonesAccion = new Font("Tahoma", Font.BOLD, 13);
		
		// 3. TÍTULO (Columna 0, Fila 1) - Centrado en la sección de control
		JLabel lblTitulo = new JLabel("GESTIÓN DE ENERGÍA");
		lblTitulo.setFont(fuenteTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
		gbc_lblTitulo.insets = new Insets(0, 15, 10, 15);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		// 4. PANEL DE BOTONES (Columna 0, Fila 3) - Centrado en la sección de control
		JPanel panelBotonesCreacion = new JPanel();
		GridBagConstraints gbc_panelBotonesCreacion = new GridBagConstraints();
		gbc_panelBotonesCreacion.anchor = GridBagConstraints.CENTER;
		gbc_panelBotonesCreacion.insets = new Insets(0, 15, 10, 15);
		gbc_panelBotonesCreacion.gridx = 0;
		gbc_panelBotonesCreacion.gridy = 3;
		panel.add(panelBotonesCreacion, gbc_panelBotonesCreacion);
		panelBotonesCreacion.setLayout(new GridLayout(0, 1, 0, 12)); // 12px de separación vertical
		
		JButton btnConsumidora = new JButton("Crear Nave Consumidora");
		btnConsumidora.setFont(fuenteBotonesAccion);
		panelBotonesCreacion.add(btnConsumidora);
		
		JButton btnProductora = new JButton("Crear Nave Productora");
		btnProductora.setFont(fuenteBotonesAccion);
		panelBotonesCreacion.add(btnProductora);
		
		// 5. CONSOLA POWERSHELL LATERAL (Columna 1, Fila 0 a 4)
		areaConsolaMini = new JTextArea();
		areaConsolaMini.setEditable(false);
		areaConsolaMini.setBackground(new Color(1, 36, 86)); // Azul PowerShell
		areaConsolaMini.setForeground(new Color(238, 238, 238)); 
		areaConsolaMini.setFont(new Font("Consolas", Font.PLAIN, 11)); 
		areaConsolaMini.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen interno cómodo para lectura larga
		areaConsolaMini.setLineWrap(true);
		areaConsolaMini.setWrapStyleWord(true);
		
		// Texto inicial adaptado al formato vertical de consola
		areaConsolaMini.setText("Windows PowerShell\nMORSALO-OS [Energía]\n\n" +
				"[SISTEMA]: Panel activo.\n" +
				"Esperando órdenes...\n\n" +
				"Red: Estable al 100%");
		
		JScrollPane scrollPaneMini = new JScrollPane(areaConsolaMini);
		scrollPaneMini.setBorder(null); 
		
		GridBagConstraints gbc_scrollPaneMini = new GridBagConstraints();
		gbc_scrollPaneMini.insets = new Insets(15, 0, 15, 15); // Margen externo perimetral derecho
		gbc_scrollPaneMini.fill = GridBagConstraints.BOTH; // <--- Se estira tanto a lo ancho como a lo alto
		gbc_scrollPaneMini.gridheight = 5; // <--- TRUCO CLAVE: Abarca desde la fila 0 hasta la fila 4 (todo el alto)
		gbc_scrollPaneMini.gridx = 1; // Segunda columna
		gbc_scrollPaneMini.gridy = 0; // Comienza desde arriba de todo
		panel.add(scrollPaneMini, gbc_scrollPaneMini);
	}
	
	@Override
	public void arranca() {
		pack();
		// Cambiado a tamaño panorámico: 540 de ancho para acomodar ambas secciones una al lado de la otra
		setSize(540, 260); 
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Método útil para concatenar o actualizar el historial de la consola lateral
	 */
	public void actualizarConsola(String texto) {
		areaConsolaMini.setText(texto);
	}
	
	public void añadirLog(String linea) {
		areaConsolaMini.append("\n" + linea);
	}
	
	public static void main(String[] args) {
		VistaGeneradores vista = new VistaGeneradores();
		vista.arranca();
	}
}
