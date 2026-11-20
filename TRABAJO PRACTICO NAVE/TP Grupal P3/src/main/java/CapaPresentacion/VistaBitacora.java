package CapaPresentacion;

import CapaNegocio.Controlador;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelPrincipal.setBackground(new Color(12, 12, 12));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		areaConsola = new JTextArea();
		areaConsola.setEditable(false);
		areaConsola.setBackground(new Color(1, 36, 86));
		areaConsola.setForeground(new Color(238, 238, 238));
		
		areaConsola.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		areaConsola.setBorder(new EmptyBorder(8, 10, 8, 10));
		
		areaConsola.setLineWrap(true);
		areaConsola.setWrapStyleWord(true);

		JScrollPane scrollPaneBitacoras = new JScrollPane(areaConsola);
		scrollPaneBitacoras.setBorder(null);
		
		panelPrincipal.add(scrollPaneBitacoras, BorderLayout.CENTER);
	}
	
	@Override
	public void arranca() {
		pack();
		setSize(800, 250);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void setControlador(Controlador controlador) {

	}

	public JTextArea getAreaConsola() {
		return areaConsola;
	}
}
