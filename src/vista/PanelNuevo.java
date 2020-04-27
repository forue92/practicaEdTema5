package vista;

import javax.swing.*;
import java.awt.*;


public class PanelNuevo extends JPanel {
    private Vista vista;
    private JLabel nombre;
    public JTextField empleado;
    public JButton crear;
    public JButton limpiar;

    public PanelNuevo(Vista vista) {
        super();
        this.vista = vista;
        this.setLayout(new BorderLayout());

        JPanel pn = new JPanel(new GridLayout(1,1));
        this.add(pn, BorderLayout.CENTER);
        JLabel etiqueta = new JLabel("CREAR NUEVO EMPLEADO", SwingConstants.HORIZONTAL);
        etiqueta.setFont(new java.awt.Font("Times New Roman", 1, 18));
        etiqueta.setForeground(new java.awt.Color(68, 68, 68, 212));
        pn.add(etiqueta);

        JPanel pnDos = new JPanel(new GridLayout(4, 1));
        this.add(pnDos, BorderLayout.SOUTH);
        nombre = new JLabel("Nombre del empleado");
        empleado = new JTextField();
        crear = new JButton("Crear");
        limpiar = new JButton("Limpiar");
        pnDos.add(nombre);
        pnDos.add(empleado);
        pnDos.add(crear);
        pnDos.add(limpiar);


        vista.add(this);
    }

    public void setListener() {
        crear.addActionListener(this.vista.controlador);
        limpiar.addActionListener(this.vista.controlador);
    }
}
