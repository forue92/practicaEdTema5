package vista;

import javax.swing.*;
import java.awt.*;


public class PanelEliminar extends JPanel {
    private Vista vista;
    private JLabel etiquetaEmpleado;
    public Choice empleados;
    public JButton eliminar;

    public PanelEliminar(Vista vista) {
        this.vista = vista;
        this.setLayout(new BorderLayout());

        JPanel pn = new JPanel(new GridLayout(1,1));
        this.add(pn, BorderLayout.CENTER);
        JLabel etiqueta = new JLabel("Eliminar Empleado", SwingConstants.HORIZONTAL);
        etiqueta.setFont(new java.awt.Font("Times New Roman", 1, 20));
        etiqueta.setForeground(new java.awt.Color(68, 68, 68, 212));
        pn.add(etiqueta);

        JPanel pnDos = new JPanel(new GridLayout(3, 1));
        this.add(pnDos, BorderLayout.SOUTH);
        etiquetaEmpleado = new JLabel("Empleado");
        empleados = new Choice();
        eliminar = new JButton("Eliminar");
        pnDos.add(etiquetaEmpleado);
        pnDos.add(empleados);
        pnDos.add(eliminar);

        vista.add(this);
    }

    public void setListener() {
        eliminar.addActionListener(this.vista.controlador);
    }
}
