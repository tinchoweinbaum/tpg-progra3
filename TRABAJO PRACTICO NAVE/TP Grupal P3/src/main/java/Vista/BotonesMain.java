package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class BotonesMain extends JFrame implements IVista {

	private JPanel panelPrincipal;

	public BotonesMain() {
		setTitle("PANEL PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		
		// 1. Cambiamos el Layout de panelPrincipal a GridBagLayout para permitir spans de filas
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[] {350, 250}; // Ancho proporcional estimado de columna 1 y 2
		gbl_panelPrincipal.rowHeights = new int[] {60, 200};   // Alto estimado para fila 1 (Datos) y fila 2 (Botones)
		gbl_panelPrincipal.columnWeights = new double[]{1.0, 1.0}; // Permite que ambas columnas se estiren uniformemente
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 1.0};    // La fila de botones y matriz se expande verticalmente
		panelPrincipal.setLayout(gbl_panelPrincipal);
		
		// ----------------------------------------------------
		// PANEL DATOS: Fila 0, Columna 0
		// ----------------------------------------------------
		JPanel panelDatos = new JPanel();
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] {0, 0, 0};
		gbl_panelDatos.rowHeights = new int[] {0};
		gbl_panelDatos.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_panelDatos.rowWeights = new double[]{1.0};
		panelDatos.setLayout(gbl_panelDatos);
		
		JLabel lblCapitanMorsalo = new JLabel("Capitan Morsalo");
		lblCapitanMorsalo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCapitanMorsalo = new GridBagConstraints();
		gbc_lblCapitanMorsalo.insets = new Insets(0, 0, 0, 5);
		gbc_lblCapitanMorsalo.gridx = 0;
		gbc_lblCapitanMorsalo.gridy = 0;
		panelDatos.add(lblCapitanMorsalo, gbc_lblCapitanMorsalo);
		
		JLabel lblNombreNave = new JLabel("Nombre de la Nave");
		lblNombreNave.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNombreNave = new GridBagConstraints();
		gbc_lblNombreNave.insets = new Insets(0, 0, 0, 5);
		gbc_lblNombreNave.gridx = 1;
		gbc_lblNombreNave.gridy = 0;
		panelDatos.add(lblNombreNave, gbc_lblNombreNave);
		
		JButton botonBorraCrearNave = new JButton("Start Again");
		GridBagConstraints gbc_botonBorraCrearNave = new GridBagConstraints();
		gbc_botonBorraCrearNave.gridx = 2;
		gbc_botonBorraCrearNave.gridy = 0;
		panelDatos.add(botonBorraCrearNave, gbc_botonBorraCrearNave);
		
		// UBICACIÓN DE PANEL DATOS EN EL PANEL PRINCIPAL
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.fill = GridBagConstraints.BOTH; // Rellena todo el espacio asignado
		gbc_panelDatos.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatos.gridx = 0; // Primera Columna
		gbc_panelDatos.gridy = 0; // Primera Fila
		panelPrincipal.add(panelDatos, gbc_panelDatos);
		
		// ----------------------------------------------------
		// PANEL BOTONES: Fila 1, Columna 0
		// ----------------------------------------------------
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3, 2, 5, 5)); // 5px de espacio interno entre botones
		
		JButton botonVisorParalelo = new JButton("Ver Celdas de Energia");
		panelBotones.add(botonVisorParalelo);
		
		JButton botonMisiones = new JButton("Ir a Misiones Disponibles");
		panelBotones.add(botonMisiones);
		
		JButton botonTripulacion = new JButton("Ver Tripulantes");
		panelBotones.add(botonTripulacion);
		
		JButton botonBitacora = new JButton("Ir a Bitacora");
		panelBotones.add(botonBitacora);
		
		JButton botonRecursos = new JButton("Ver Recursos de la Nave");
		panelBotones.add(botonRecursos);
		
		// UBICACIÓN DE PANEL BOTONES EN EL PANEL PRINCIPAL
		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.insets = new Insets(0, 0, 0, 5);
		gbc_panelBotones.gridx = 0; // Primera Columna
		gbc_panelBotones.gridy = 1; // Segunda Fila
		panelPrincipal.add(panelBotones, gbc_panelBotones);
		
		// ----------------------------------------------------
		// PANEL MATRIZ: Ocupa Fila 0 y Fila 1 de la Columna 1
		// ----------------------------------------------------
		JPanel panelMatriz = new JPanel();
		// Le añadimos un borde visual temporal para que puedas ver perfectamente su espacio en la previsualización
		panelMatriz.setBorder(new TitledBorder(null, "Mapa del Universo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		// UBICACIÓN DE PANEL MATRIZ EN EL PANEL PRINCIPAL
		GridBagConstraints gbc_panelMatriz = new GridBagConstraints();
		gbc_panelMatriz.fill = GridBagConstraints.BOTH;
		gbc_panelMatriz.gridheight = 2; // <--- TRUCO CLAVE: Le indicamos que abarque 2 filas de alto
		gbc_panelMatriz.gridx = 1;       // Segunda Columna
		gbc_panelMatriz.gridy = 0;       // Comienza desde la primera Fila (Fila 0)
		panelPrincipal.add(panelMatriz, gbc_panelMatriz);
	}
	
	@Override
	public void arranca() {
		pack();
		setSize(700, 350); // Tamaño inicial cómodo para ver la distribución horizontal y vertical
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		BotonesMain vistaBotonesMain = new BotonesMain();
		vistaBotonesMain.arranca();
	}
}
