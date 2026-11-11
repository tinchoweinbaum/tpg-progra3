package CapaPresentacion;

import CapaNegocio.Controlador;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Toolkit;

public class VistaExcepcion extends JDialog implements IVista {

    public final static String CERRAR_ALERTA = "CERRAR_ALERTA";

    private JPanel panelPrincipal;
    private JLabel lblIcono;
    private JLabel lblMensaje;
    private JButton btnAceptar;

    public VistaExcepcion() {
        setTitle("AVISO DEL SISTEMA");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true); // Bloquea la ventana de atrás hasta que el usuario cierre la alerta
        setResizable(false);

        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(new EmptyBorder(20, 25, 20, 25));
        setContentPane(panelPrincipal);

        // 2 Columnas: Ícono nativo (izq) | Mensaje de error (der)
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] {45, 250};
        gbl.rowHeights = new int[] {60, 40};
        gbl.columnWeights = new double[]{0.0, 1.0};
        gbl.rowWeights = new double[]{1.0, 0.0};
        panelPrincipal.setLayout(gbl);

        Font fuenteMensaje = new Font("Tahoma", Font.PLAIN, 13);
        Font fuenteBoton = new Font("Tahoma", Font.BOLD, 12);

        // --------------------------------------------------------------------
        // COLUMNA 0, FILA 0: ÍCONO PREDETERMINADO DEL SISTEMA
        // --------------------------------------------------------------------
        lblIcono = new JLabel();
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblIcono = new GridBagConstraints();
        gbc_lblIcono.anchor = GridBagConstraints.CENTER;
        gbc_lblIcono.insets = new Insets(0, 0, 15, 15);
        gbc_lblIcono.gridx = 0;
        gbc_lblIcono.gridy = 0;
        panelPrincipal.add(lblIcono, gbc_lblIcono);

        // --------------------------------------------------------------------
        // COLUMNA 1, FILA 0: MENSAJE DE LA EXCEPCIÓN (Con salto de línea HTML)
        // --------------------------------------------------------------------
        lblMensaje = new JLabel();
        lblMensaje.setFont(fuenteMensaje);
        lblMensaje.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
        gbc_lblMensaje.fill = GridBagConstraints.BOTH;
        gbc_lblMensaje.insets = new Insets(0, 0, 15, 0);
        gbc_lblMensaje.gridx = 1;
        gbc_lblMensaje.gridy = 0;
        panelPrincipal.add(lblMensaje, gbc_lblMensaje);

        // --------------------------------------------------------------------
        // FILA 1: BOTÓN ACEPTAR (Centrado ocupando ambas columnas)
        // --------------------------------------------------------------------
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setActionCommand(CERRAR_ALERTA);
        btnAceptar.addActionListener(e->dispose());
        btnAceptar.setFont(fuenteBoton);


        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.gridwidth = 2;
        gbc_btnAceptar.anchor = GridBagConstraints.CENTER;
        gbc_btnAceptar.gridx = 0;
        gbc_btnAceptar.gridy = 1;
        panelPrincipal.add(btnAceptar, gbc_btnAceptar);
    }

    @Override
    public void arranca() {
        pack();
        setLocationRelativeTo(null);
        Toolkit.getDefaultToolkit().beep(); // Emite el sonido clásico de alerta de Windows
        setVisible(true);
    }

    @Override
    public void setControlador(Controlador controlador) {
        btnAceptar.addActionListener(controlador);
    }

    public JLabel getLblIcono() {
        return lblIcono;
    }

    public JLabel getLblMensaje() {
        return lblMensaje;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }
}
