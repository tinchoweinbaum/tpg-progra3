package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VistaBitacora extends JFrame implements IVista {

	private JPanel panelPrincipal;
	private JTextArea areaConsola;

	public VistaBitacora() {
		setTitle("Bitácora de la Nave - Consola del Sistema");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiado a DISPOSE para no cerrar toda la app al salir de la bitácora
		
		// 1. Configuramos el panel principal con BorderLayout para que la consola ocupe todo el espacio elástico
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelPrincipal.setBackground(new Color(12, 12, 12)); // Fondo oscuro exterior
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		// 2. Creamos y configuramos el JTextArea estilo PowerShell
		areaConsola = new JTextArea();
		areaConsola.setEditable(false); // Evita que el usuario borre los logs de la bitácora
		areaConsola.setBackground(new Color(1, 36, 86)); // Azul característico de Windows PowerShell
		areaConsola.setForeground(new Color(238, 238, 238)); // Texto gris claro/blanco de alta legibilidad
		
		// Fuente monoespaciada ideal para consolas y código (Consolas o Monospaced como fallback)
		areaConsola.setFont(new Font("Consolas", Font.PLAIN, 14)); 
		
		// Margen interno para que el texto no esté pegado a los bordes del scroll
		areaConsola.setBorder(new EmptyBorder(8, 10, 8, 10)); 
		
		// TRUCO CLAVE para oraciones largas: Ajuste de línea inteligente
		areaConsola.setLineWrap(true);       // Activa el salto de línea automático
		areaConsola.setWrapStyleWord(true);  // Corta por palabras completas, no a mitad de una letra
		
		// Texto de prueba inicial simulando un sistema espacial
		areaConsola.setText("Windows PowerShell\n" +
				"Copyright (C) Microsoft Corporation. Todos los derechos reservados.\n\n" +
				"MORSALO-OS [Versión 1.0.24]\n" +
				"C:\\Sistemas\\Nave\\Bitacora> cargando registros de viaje...\n\n" +
				"[LOG - 10:45] Sistema de propulsión hiperespacial activado con éxito. Sin anomalías.\n" +
				"[LOG - 11:12] Alerta menor detectada en la celda de energía 4, el subsistema de autoreparación ha estabilizado los voltajes.\n" +
				"[LOG - 12:00] Entrada de bitácora de largo alcance: El Capitán Morsalo reporta que la tripulación ha entrado en estado de hibernación segura mientras cruzamos el cuadrante gamma.");

		// 3. Añadimos la consola al JScrollPane y eliminamos bordes nativos feos
		JScrollPane scrollPaneBitacoras = new JScrollPane(areaConsola);
		scrollPaneBitacoras.setBorder(null); // Limpia el borde blanco clásico de Swing alrededor del scroll
		
		// Agregamos el scroll al centro del panel principal
		panelPrincipal.add(scrollPaneBitacoras, BorderLayout.CENTER);
	}
	
	@Override
	public void arranca() {
		pack();
		// Tamaño óptimo para leer oraciones largas cómodamente (Ancho panorámico de consola)
		setSize(700, 450); 
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		VistaBitacora vista = new VistaBitacora();
		vista.arranca();
	}
}
