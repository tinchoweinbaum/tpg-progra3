package CapaPresentacion;

import CapaNegocio.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class VistaTripulacion extends JFrame implements IVista {

    private JPanel panelPrincipal;
    private JButton botonAgregaTripulante;
    private JButton botonElimina;
    private JScrollPane scrollPane;

    public VistaTripulacion() {
        setTitle("GESTION DE TRIPULANTES");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 400); // Subí un poco la altura para dar aire
        
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(panelPrincipal);
        
        // CAMBIO: Usamos GridBagLayout para centrar y controlar los espacios
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 1. Panel de Botones (Superior)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // 0 en gap vertical
        
        botonAgregaTripulante = new JButton("Crear Nuevo Tripulante");
        panelBotones.add(botonAgregaTripulante);
        
        botonElimina = new JButton("Despedir Tripulante");
        panelBotones.add(botonElimina);
        
        // Configuración de posición para los botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // No se estira verticalmente
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 15, 0); // Margen de 15 píxeles ABAJO de los botones
        panelPrincipal.add(panelBotones, gbc);

        // 2. Panel del Scroll (Inferior)
        JPanel panelScroll = new JPanel();
        panelScroll.setLayout(new BorderLayout(0, 0));
        
        scrollPane = new JScrollPane();
        // Le asignamos un tamaño preferido mínimo para que el scroll tenga cuerpo
        scrollPane.setPreferredSize(new Dimension(400, 180)); 
        panelScroll.add(scrollPane, BorderLayout.CENTER);
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        // Configuración de posición para el scroll
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0; // Este panel sí absorbe el espacio sobrante si se estira la ventana
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.insets = new Insets(0, 0, 0, 0);
        panelPrincipal.add(panelScroll, gbc2);
    }

    @Override
    public void arranca() {
        pack();
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setControlador(Controlador controlador) {

    }

    public static void main(String[] args) {
    	VistaTripulacion vista = new VistaTripulacion();
    	vista.arranca();
    }
}
