package vista;

import javax.swing.*;
import java.awt.*;


public class PanelModificar extends JPanel {
    private Vista vista;
    private JLabel etiquetaEmpleado;
    public Choice empleados;
    private JLabel etiquetaNuevoEmpleado;
    public JTextField nuevoEmpleado;
    public JButton modificar;

    public PanelModificar(Vista vista) {
        super();
        this.vista = vista;
        this.setLayout(new BorderLayout());

        JPanel pn = new JPanel(new GridLayout(1,1));
        this.add(pn, BorderLayout.CENTER);
        JLabel etiqueta = new JLabel("Modificar Empleado", SwingConstants.HORIZONTAL);
        etiqueta.setFont(new java.awt.Font("Times New Roman", 1, 20));
        etiqueta.setForeground(new java.awt.Color(68, 68, 68, 212));
        pn.add(etiqueta);

        JPanel pnDos = new JPanel(new GridLayout(5, 1));
        this.add(pnDos, BorderLayout.SOUTH);
        etiquetaEmpleado = new JLabel("Empleado");
        empleados = new Choice();
        etiquetaNuevoEmpleado = new JLabel("Introduce el nuevo nombre del empleado");
        nuevoEmpleado = new JTextField();
        modificar = new JButton("Modificar");
        pnDos.add(etiquetaEmpleado);
        pnDos.add(empleados);
        pnDos.add(etiquetaNuevoEmpleado);
        pnDos.add(nuevoEmpleado);
        pnDos.add(modificar);

        vista.add(this);
    }

    public void setListener() {
        modificar.addActionListener(this.vista.controlador);
    }
}
