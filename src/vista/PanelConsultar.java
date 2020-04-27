package vista;

import javax.swing.*;
import java.awt.*;


public class PanelConsultar extends JPanel {
    private Vista vista;
    public Choice busqueda;
    public JTextField informacion;
    public JButton consultar;
    public JButton limpiar;

    public PanelConsultar(Vista vista) {
        super();
        this.vista = vista;
        this.setLayout(new BorderLayout());

        JPanel pn = new JPanel(new GridLayout(1,1));
        this.add(pn, BorderLayout.CENTER);
        JLabel etiqueta = new JLabel("Consultar Empleado", SwingConstants.HORIZONTAL);
        etiqueta.setFont(new java.awt.Font("Times New Roman", 1, 20));
        etiqueta.setForeground(new java.awt.Color(68, 68, 68, 212));
        pn.add(etiqueta);

        JPanel pnDos = new JPanel(new GridLayout(6, 1));
        this.add(pnDos, BorderLayout.SOUTH);
        JLabel tipoBusqueda = new JLabel("Selecciona el tipo de búsqueda");
        pnDos.add(tipoBusqueda);
        busqueda = new Choice();
        busqueda.add(new String("idEmpleado"));
        busqueda.add((new String("nombreEmpledo")));
        pnDos.add(busqueda);

        JLabel infoBusqueda = new JLabel("Introduce información para buscar a un empleado");
        informacion = new JTextField();
        pnDos.add(infoBusqueda);
        pnDos.add(informacion);
        consultar = new JButton("Consultar");
        limpiar = new JButton("Limpiar");
        pnDos.add(consultar);
        pnDos.add(limpiar);
        vista.add(this);
    }

    public void setListener() {
        consultar.addActionListener(this.vista.controlador);
        limpiar.addActionListener(this.vista.controlador);
    }
}