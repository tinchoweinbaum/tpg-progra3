package CapaPresentacion;

import CapaNegocio.Controlador;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class BotonesMain extends JFrame implements IVista {
	public final static String RESET_GAME = "RESET_GAME";
	public final static String ACCESO_VISOR_PARALELO = "ACCESO_VISOR_PARALELO";
	public final static String ACCESO_MISIONES = "ACCESO_MISIONES";
	public final static String ACCESO_TRIPULACION = "ACCESO_TRIPULACION";
	public final static String ACCESO_BITACORA = "ACCESO_BITACORA";
	public final static String ACCESO_RECURSOS = "ACCESO_RECURSOS";

	private JPanel panelPrincipal;
	private JPanel panelDatos; // <--- Declarado como atributo para acceder desde los métodos de refresco
	private JLabel lblCapitanMorsalo = new JLabel();
	private JLabel lblNombreNave = new JLabel();
	private JButton botonBorraCrearNave = new JButton("Start Again");
	private JButton botonVisorParalelo = new JButton("Ver Celdas de Energia");
	private JButton botonMisiones = new JButton("Ir a Misiones Disponibles");
	private JButton botonTripulacion = new JButton("Ver Tripulantes");
	private JButton botonBitacora = new JButton("Ver Bitacora");
	private JButton botonRecursos = new JButton("Ver Recursos de la Nave");

	public BotonesMain() {
		setTitle("PANEL PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);

		// 1. Layout maestro del panel principal
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[] {370, 250}; // Ajustado para dar más margen a la sección izquierda
		gbl_panelPrincipal.rowHeights = new int[] {60, 220};
		gbl_panelPrincipal.columnWeights = new double[]{1.0, 1.0};
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 1.0};
		panelPrincipal.setLayout(gbl_panelPrincipal);

		// --------------------------------------------------------------------
		// PANEL DATOS: Fila 0, Columna 0 (Rediseñado para dar prioridad a la Nave)
		// --------------------------------------------------------------------
		panelDatos = new JPanel();
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] {110, 150, 90}; // Más espacio asignado por defecto a la columna del medio (Nave)
		gbl_panelDatos.rowHeights = new int[] {0};
		gbl_panelDatos.columnWeights = new double[]{0.5, 1.5, 0.0}; // El nombre de la nave recibe triple prioridad de estiramiento (1.5)
		gbl_panelDatos.rowWeights = new double[]{1.0};
		panelDatos.setLayout(gbl_panelDatos);

		lblCapitanMorsalo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapitanMorsalo.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblCapitanMorsalo = new GridBagConstraints();
		gbc_lblCapitanMorsalo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCapitanMorsalo.insets = new Insets(0, 5, 0, 5);
		gbc_lblCapitanMorsalo.gridx = 0;
		gbc_lblCapitanMorsalo.gridy = 0;
		panelDatos.add(lblCapitanMorsalo, gbc_lblCapitanMorsalo);

		lblNombreNave.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreNave.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNombreNave = new GridBagConstraints();
		gbc_lblNombreNave.fill = GridBagConstraints.HORIZONTAL; // <--- Forzado HORIZONTAL para expandirse con el texto largo
		gbc_lblNombreNave.insets = new Insets(0, 5, 0, 5);
		gbc_lblNombreNave.gridx = 1; // Columna central
		gbc_lblNombreNave.gridy = 0;
		panelDatos.add(lblNombreNave, gbc_lblNombreNave);

		botonBorraCrearNave.setActionCommand(RESET_GAME);
		botonBorraCrearNave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_botonBorraCrearNave = new GridBagConstraints();
		gbc_botonBorraCrearNave.anchor = GridBagConstraints.CENTER;
		gbc_botonBorraCrearNave.insets = new Insets(0, 5, 0, 5);
		gbc_botonBorraCrearNave.gridx = 2;
		gbc_botonBorraCrearNave.gridy = 0;
		panelDatos.add(botonBorraCrearNave, gbc_botonBorraCrearNave);

		// Agregar panelDatos al panel principal
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.insets = new Insets(0, 0, 8, 8);
		gbc_panelDatos.gridx = 0;
		gbc_panelDatos.gridy = 0;
		panelPrincipal.add(panelDatos, gbc_panelDatos);

		// --------------------------------------------------------------------
		// PANEL BOTONES: Fila 1, Columna 0
		// --------------------------------------------------------------------
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3, 2, 6, 6));

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

		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.fill = GridBagConstraints.BOTH;
		gbc_panelBotones.insets = new Insets(0, 0, 0, 8);
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 1;
		panelPrincipal.add(panelBotones, gbc_panelBotones);

		// --------------------------------------------------------------------
		// PANEL MATRIZ: Lado Derecho
		// --------------------------------------------------------------------
		JPanel panelMatriz = new JPanel();
		panelMatriz.setBorder(new TitledBorder(null, "Mapa del Universo", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		GridBagConstraints gbc_panelMatriz = new GridBagConstraints();
		gbc_panelMatriz.fill = GridBagConstraints.BOTH;
		gbc_panelMatriz.gridheight = 2;
		gbc_panelMatriz.gridx = 1;
		gbc_panelMatriz.gridy = 0;
		panelPrincipal.add(panelMatriz, gbc_panelMatriz);
	}

	@Override
	public void arranca() {
		pack();
		setSize(720, 360);
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

	// --------------------------------------------------------------------
	// GETTERS COMPONENTES
	// --------------------------------------------------------------------
	public JLabel getLblCapitan() {
		return lblCapitanMorsalo;
	}

	public JLabel getLblNombreNave() {
		return lblNombreNave;
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
}
