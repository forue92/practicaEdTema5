package vista;

import javax.swing.*;
import java.awt.*;


 
public class PanelHome extends JPanel {
    private Label etiqueta;
    private JLabel etiqueta2;
    private Vista vista;
    public PanelHome(Vista vista) {
        this.vista = vista;
        this.setLayout(new BorderLayout());
        JPanel pn = new JPanel(new GridLayout(2,2));
        this.add(pn, BorderLayout.CENTER);

        etiqueta = new Label("Aplicacion patron de disenio MVC", SwingConstants.CENTER);
        etiqueta.setFont(new java.awt.Font("Times New Roman", 1, 22));
        etiqueta.setSize(300, 100);
        etiqueta.setBackground(new java.awt.Color(0, 255,255 ));
        etiqueta.setForeground(new java.awt.Color(0, 0,0 ));
        etiqueta2 = new JLabel("Fernando", SwingConstants.CENTER);
        etiqueta2.setFont(new java.awt.Font("Times New Roman", 1, 28));
        etiqueta2.setForeground(new java.awt.Color(68, 68, 68, 212));


        pn.add(etiqueta);
        pn.add(etiqueta2);
        vista.add(this);
    }
}
