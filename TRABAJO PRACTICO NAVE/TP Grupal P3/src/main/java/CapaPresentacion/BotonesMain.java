package CapaPresentacion;

import CapaNegocio.Controlador;

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
import javax.swing.border.TitledBorder;

public class BotonesMain extends JFrame implements IVista {
	public final static String RESET_GAME = "RESET_GAME";
	public final static String ACCESO_VISOR_PARALELO = "ACCESO_VISOR_PARALELO";
	public final static String ACCESO_MISIONES = "ACCESO_MISIONES";
	public final static String ACCESO_TRIPULACION = "ACCESO_TRIPULACION";
	public final static String ACCESO_BITACORA = "ACCESO_BITACORA";
	public final static String ACCESO_RECURSOS = "ACCESO_RECURSOS";

	private JPanel panelPrincipal;
	private JLabel lblCapitanMorsalo = new JLabel("Capitan Morsalo");
	private JLabel lblNombreNave = new JLabel("Nombre de la Nave");
	private JButton botonBorraCrearNave = new JButton("Start Again");
	private JButton botonVisorParalelo = new JButton("Ver Celdas de Energia");
	private JButton botonMisiones = new JButton("Ir a Misiones Disponibles");
	private JButton botonTripulacion = new JButton("Ver Tripulantes");
	private JButton botonBitacora = new JButton("Ir a Bitacora");
	private JButton botonRecursos = new JButton("Ver Recursos de la Nave");

	public BotonesMain() {
		setTitle("PANEL PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);
		setResizable(false);
		
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
		

		lblCapitanMorsalo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCapitanMorsalo = new GridBagConstraints();
		gbc_lblCapitanMorsalo.insets = new Insets(0, 0, 0, 5);
		gbc_lblCapitanMorsalo.gridx = 0;
		gbc_lblCapitanMorsalo.gridy = 0;
		panelDatos.add(lblCapitanMorsalo, gbc_lblCapitanMorsalo);
		

		lblNombreNave.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNombreNave = new GridBagConstraints();
		gbc_lblNombreNave.insets = new Insets(0, 0, 0, 5);
		gbc_lblNombreNave.gridx = 1;
		gbc_lblNombreNave.gridy = 0;
		panelDatos.add(lblNombreNave, gbc_lblNombreNave);
		
		botonBorraCrearNave.setActionCommand(RESET_GAME);
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

		botonVisorParalelo.setActionCommand(ACCESO_VISOR_PARALELO);
		panelBotones.add(botonVisorParalelo);
		botonMisiones.setActionCommand(ACCESO_MISIONES);
		panelBotones.add(botonMisiones);
		botonTripulacion.setActionCommand(ACCESO_TRIPULACION);
		panelBotones.add(botonTripulacion);
		botonBitacora.setActionCommand(ACCESO_BITACORA);
		panelBotones.add(botonBitacora);
		botonRecursos.setActionCommand(ACCESO_RECURSOS);
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

	@Override
	public void setControlador(Controlador controlador) {
		getBotonBitacora().addActionListener(controlador);
		getBotonMisiones().addActionListener(controlador);
		getBotonVisorParalelo().addActionListener(controlador);
		getBotonTripulacion().addActionListener(controlador);
		getBotonBorraCrearNave().addActionListener(controlador);
		getBotonRecursos().addActionListener(controlador);
	}

	public JLabel getLblCapitan() {
		return lblCapitanMorsalo;
	}

	public void setLblCapitan(JLabel lblCapitan) {
		this.lblCapitanMorsalo = lblCapitan;
	}

	public JLabel getLblNombreNave() {
		return lblNombreNave;
	}

	public void setLblNombreNave(JLabel lblNombreNave) {
		this.lblNombreNave = lblNombreNave;
	}

	public JButton getBotonBorraCrearNave() {
		return botonBorraCrearNave;
	}

	public JButton getBotonVisorParalelo() {
		return botonVisorParalelo;
	}

	public JButton getBotonMisiones() {
		return botonMisiones;
	}

	public JButton getBotonTripulacion() {
		return botonTripulacion;
	}

	public JButton getBotonBitacora() {
		return botonBitacora;
	}

	public JButton getBotonRecursos() {
		return botonRecursos;
	}

	public static void main(String[] args) {
		BotonesMain vistaBotonesMain = new BotonesMain();
		vistaBotonesMain.arranca();
	}
}
