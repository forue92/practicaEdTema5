package vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import controlador.*;

import javax.swing.*;

public class Vista extends JFrame {
    public Controlador controlador = null;
    public PanelHome phome;
    public PanelNuevo pnuevo;
    public PanelConsultar pconsultar;
    public PanelEliminar peliminar;
    public PanelModificar pmodificar;
    public MenuBar menuBar;
    public Menu mempleados;
    public Menu msalir;
    public Menu mhome;
    public MenuItem mnuevo;
    public MenuItem mconsultar;
    public MenuItem meliminar;
    public MenuItem mmodificar;
    public MenuItem salir;
    public MenuItem home;

    public Vista(String nombre) {
        super(nombre);

        Container contenedor = this.getContentPane();
        contenedor.setLayout(new FlowLayout());
        menuBar = new MenuBar();
        mhome = new Menu("Home");
        mempleados = new Menu("Empleados");
        msalir = new Menu("Salir");
        mnuevo = new MenuItem("Nuevo");
        mconsultar = new MenuItem("Consultar");
        meliminar = new MenuItem("Eliminar");
        mmodificar = new MenuItem("Modificar");
        salir = new MenuItem("salir");
        home = new MenuItem("home");

        mempleados.add(mnuevo);
        mempleados.add(mconsultar);
        mempleados.add(meliminar);
        mempleados.add(mmodificar);
        msalir.add(salir);
        mhome.add(home);
        menuBar.add(mhome);
        menuBar.add(mempleados);
        menuBar.add(msalir);

        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        setMenuBar(menuBar);
        setVisible(true);
        setSize(400,400);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        phome = new PanelHome(this);
        pconsultar = new PanelConsultar(this);
        peliminar = new PanelEliminar(this);
        pnuevo = new PanelNuevo(this);
        pmodificar = new PanelModificar(this);
        contenedor.add(phome);
        contenedor.add(pconsultar);
        contenedor.add(peliminar);
        contenedor.add(pnuevo);
        contenedor.add(pmodificar);

        phome.setVisible(true);
        pconsultar.setVisible(false);
        peliminar.setVisible(false);
        pnuevo.setVisible(false);
        pmodificar.setVisible(false);

    }

    public void setListener(Controlador controlador) {
        this.controlador = controlador;
        salir.addActionListener(controlador);
        home.addActionListener(controlador);
        mnuevo.addActionListener(controlador);
        mconsultar.addActionListener(controlador);
        meliminar.addActionListener(controlador);
        mmodificar.addActionListener(controlador);

        pmodificar.setListener();
        peliminar.setListener();
        pnuevo.setListener();
        pconsultar.setListener();
    }

    public void setPanelVisible(JPanel panel) {
        phome.setVisible(false);
        pnuevo.setVisible(false);
        pconsultar.setVisible(false);
        peliminar.setVisible(false);
        pmodificar.setVisible(false);

        panel.setVisible(true);
    }
}
