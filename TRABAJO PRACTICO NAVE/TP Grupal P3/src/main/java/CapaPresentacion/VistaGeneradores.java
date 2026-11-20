package CapaPresentacion;

import CapaNegocio.Controlador;

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
	public final static String PRODUCE_BATERIA = "PRODUCE_BATERIA";
	public final static String PRODUCE_NAVE = "PRODUCE_NAVE";

	private JPanel panelPrincipal;
	private JTextArea areaConsolaMini;
	private JButton btnConsumidora = new JButton("Crear Nave Consumidora");
	private JButton btnProductora = new JButton("Crear Nave Productora");


	public VistaGeneradores() {
		setTitle("GENERADORES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		panelPrincipal = new JPanel();
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelPrincipal.add(panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {240, 240};
		gbl_panel.rowHeights = new int[] {25, 40, 20, 80, 25};
		gbl_panel.columnWeights = new double[]{1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 14);
		Font fuenteBotonesAccion = new Font("Tahoma", Font.BOLD, 13);
		
		JLabel lblTitulo = new JLabel("GESTIÓN DE ENERGÍA");
		lblTitulo.setFont(fuenteTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.CENTER;
		gbc_lblTitulo.insets = new Insets(0, 15, 10, 15);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		JPanel panelBotonesCreacion = new JPanel();
		GridBagConstraints gbc_panelBotonesCreacion = new GridBagConstraints();
		gbc_panelBotonesCreacion.anchor = GridBagConstraints.CENTER;
		gbc_panelBotonesCreacion.insets = new Insets(0, 15, 10, 15);
		gbc_panelBotonesCreacion.gridx = 0;
		gbc_panelBotonesCreacion.gridy = 3;
		panel.add(panelBotonesCreacion, gbc_panelBotonesCreacion);
		panelBotonesCreacion.setLayout(new GridLayout(0, 1, 0, 12));

		btnConsumidora.setActionCommand(PRODUCE_NAVE);
		btnConsumidora.setFont(fuenteBotonesAccion);
		panelBotonesCreacion.add(btnConsumidora);
		btnProductora.setActionCommand(PRODUCE_BATERIA);
		btnProductora.setFont(fuenteBotonesAccion);
		panelBotonesCreacion.add(btnProductora);
		
		areaConsolaMini = new JTextArea();
		areaConsolaMini.setEditable(false);
		areaConsolaMini.setBackground(new Color(1, 36, 86));
		areaConsolaMini.setForeground(new Color(238, 238, 238)); 
		areaConsolaMini.setFont(new Font("Consolas", Font.PLAIN, 11)); 
		areaConsolaMini.setBorder(new EmptyBorder(10, 10, 10, 10));
		areaConsolaMini.setLineWrap(true);
		areaConsolaMini.setWrapStyleWord(true);
		
		areaConsolaMini.setText("Doors PowerShell\nFAKE-OS [Visor Universo]\n\n" +
				"[SISTEMA]: Panel activo.\n" +
				"Red: Estable al 100%");
		
		JScrollPane scrollPaneMini = new JScrollPane(areaConsolaMini);
		scrollPaneMini.setBorder(null); 
		
		GridBagConstraints gbc_scrollPaneMini = new GridBagConstraints();
		gbc_scrollPaneMini.insets = new Insets(15, 0, 15, 15);
		gbc_scrollPaneMini.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneMini.gridheight = 5;
		gbc_scrollPaneMini.gridx = 1;
		gbc_scrollPaneMini.gridy = 0;
		panel.add(scrollPaneMini, gbc_scrollPaneMini);
	}
	
	@Override
	public void arranca() {
		pack();
		setSize(540, 260);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador) {
		getBtnConsumidora().addActionListener(controlador);
		getBtnProductora().addActionListener(controlador);
	}

	public JTextArea getAreaConsolaMini() {
		return areaConsolaMini;
	}

	public JButton getBtnConsumidora() {
		return btnConsumidora;
	}

	public JButton getBtnProductora() {
		return btnProductora;
	}

}
