package CapaPresentacion;

import CapaNegocio.Controlador;
import tripulantes.Tripulante;
// import tripulantes.Tripulante; // <--- Importa tu clase Tripulante

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class VistaTripulacion extends JFrame implements IVista {

    public final static String AGREGA_TRIPULANTE = "AGREGA_TRIPULANTE";
    public final static String BORRA_TRIPULANTE = "BORRA_TRIPULANTE";

    private JPanel panelPrincipal;
    private JButton botonAgregaTripulante;
    private JButton botonElimina;
    private JScrollPane scrollPane;

    // Nuevos atributos para gestionar la lista (cambia String por Tripulante si usas objetos)
    private DefaultListModel<Tripulante> modeloLista;
    private JList<Tripulante> listaTripulantes;

    public VistaTripulacion() {
        setTitle("GESTION DE TRIPULANTES");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 400);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(panelPrincipal);

        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 1. Panel de Botones (Superior)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        botonAgregaTripulante = new JButton("Crear Nuevo Tripulante");
        botonAgregaTripulante.setActionCommand(AGREGA_TRIPULANTE);
        panelBotones.add(botonAgregaTripulante);

        botonElimina = new JButton("Despedir Tripulante");
        botonElimina.setActionCommand(BORRA_TRIPULANTE);
        panelBotones.add(botonElimina);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 15, 0);
        panelPrincipal.add(panelBotones, gbc);

        // 2. Panel del Scroll (Inferior)
        JPanel panelScroll = new JPanel();
        panelScroll.setLayout(new BorderLayout(0, 0));

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(400, 180));

        // Inicialización del Modelo y la JList dentro del ScrollPane
        modeloLista = new DefaultListModel<>();
        listaTripulantes = new JList<>(modeloLista);
        listaTripulantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(listaTripulantes); // <--- Conecta la lista con el scroll

        panelScroll.add(scrollPane, BorderLayout.CENTER);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0;
        gbc2.fill = GridBagConstraints.BOTH;
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
        getBotonAgregaTripulante().addActionListener(controlador);
        getBotonElimina().addActionListener(controlador);
    }

    public JButton getBotonAgregaTripulante() {
        return botonAgregaTripulante;
    }

    public JButton getBotonElimina() {
        return botonElimina;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultListModel<Tripulante> getModeloLista() {
        return modeloLista;
    }

    public JList<Tripulante> getListaTripulantes() {
        return listaTripulantes;
    }
}
