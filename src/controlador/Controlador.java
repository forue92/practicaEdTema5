package controlador;

import modelo.Modelo;
import vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Controlador implements ActionListener {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.setListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object a = e.getSource();
        if (a.equals(vista.salir)) {
            modelo.cerrarConexion();
            System.exit(0);
        }
        if (a.equals(vista.home)) {
            vista.setPanelVisible(vista.phome);
        }
        if (a.equals(vista.mnuevo)) {
            vista.setPanelVisible(vista.pnuevo);
        }
        if (a.equals(vista.mconsultar)) {
            vista.setPanelVisible(vista.pconsultar);
        }
        if (a.equals(vista.meliminar)) {
            addnombreEmpleados(vista.peliminar.empleados);
            vista.setPanelVisible(vista.peliminar);
        }
        if (a.equals(vista.mmodificar)) {
            addnombreEmpleados(vista.pmodificar.empleados);
            vista.setPanelVisible(vista.pmodificar);
        }

        if (a.equals(vista.pmodificar.modificar)) {
            String nuevoEmpleado = vista.pmodificar.nuevoEmpleado.getText();
            String empleadoOrigen = vista.pmodificar.empleados.getSelectedItem();
            if (nuevoEmpleado.length() < 3 || empleadoOrigen.length() < 3)
                JOptionPane.showMessageDialog(vista.pmodificar.modificar, "ERROR. Nombre demasiado corto",
                        " ", JOptionPane.ERROR_MESSAGE);

            else if (modelo.modificaEmpleado(empleadoOrigen, nuevoEmpleado))
                JOptionPane.showMessageDialog(vista.pmodificar.modificar, "Empleado cambiado con éxito",
                        " ", JOptionPane.INFORMATION_MESSAGE);

            limpiar(vista.pmodificar.nuevoEmpleado);
            addnombreEmpleados(vista.pmodificar.empleados);
        }

        if (a.equals(vista.peliminar.eliminar)) {
            String empleado = vista.peliminar.empleados.getSelectedItem();
            int respuesta = JOptionPane.showConfirmDialog(vista.peliminar.eliminar, "¿Estas seguro de querer eliminar "
            + "a " + empleado + "?");

            if (respuesta == 0 && modelo.eliminaEmpleado(empleado))
                JOptionPane.showMessageDialog(vista.peliminar.eliminar, "Empleado eliminado con éxito");

            else if (respuesta == 0 && !modelo.eliminaEmpleado(empleado))
                JOptionPane.showMessageDialog(vista.peliminar.eliminar, "No es posible eliminar a "
                + empleado + ". Motivo:\nrestricciones en la base de datos", " ", JOptionPane.ERROR_MESSAGE);

            addnombreEmpleados(vista.peliminar.empleados);
        }

        if (a.equals(vista.pnuevo.crear)) {
            String empleado = vista.pnuevo.empleado.getText();
            if (empleado.length() < 5)
                JOptionPane.showMessageDialog(vista.pnuevo.empleado, "ERROR. Nombre demasiado corto",
                        " ", JOptionPane.ERROR_MESSAGE);

            else {
                if (modelo.nuevoEmpleado(empleado))
                    JOptionPane.showMessageDialog(vista.pnuevo.empleado, "El empleado se ha creado con éxito",
                            " ", JOptionPane.INFORMATION_MESSAGE);

                else
                    JOptionPane.showMessageDialog(vista.pnuevo.empleado, "No se ha podido crear el usuario",
                            " ", JOptionPane.ERROR_MESSAGE);

                limpiar(vista.pnuevo.empleado);
            }
        }

        if (a.equals(vista.pnuevo.limpiar))
            limpiar(vista.pnuevo.empleado);

        if (a.equals(vista.pconsultar.consultar)) {
            String tipoBusqueda = vista.pconsultar.busqueda.getSelectedItem();
            if (tipoBusqueda.equals("idEmpleado")) {
                try {
                    int idEmpleado = Integer.parseInt(vista.pconsultar.informacion.getText());
                    String salida = consultaEmpleado(modelo.consultaEmpleado(idEmpleado));
                    if (salida.length() > 1)
                        JOptionPane.showConfirmDialog(vista.pconsultar.consultar, salida, "Consulta",
                                JOptionPane.OK_CANCEL_OPTION);
                    else
                        JOptionPane.showMessageDialog(vista.pconsultar.consultar, "No existe ningún usuario"
                                + "con id = " + idEmpleado,"NOT FOUND", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception c) {
                    JOptionPane.showMessageDialog(vista.pconsultar.consultar, "Introduce un número entero por favor",
                            " ", JOptionPane.ERROR_MESSAGE);
                }

            }
            else {
                String nombreEmpleado = vista.pconsultar.informacion.getText();
                String salida = consultaEmpleado(modelo.consultaEmpleado(nombreEmpleado));
                if (salida.length() > 1)
                    JOptionPane.showConfirmDialog(vista.pconsultar.consultar, salida, "Consulta",
                            JOptionPane.OK_CANCEL_OPTION);
                else
                    JOptionPane.showMessageDialog(vista.pconsultar.consultar, "No existe ningún usuario"
                            + " con nombreEmpleado = " + nombreEmpleado,"NOT FOUND", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (a.equals(vista.pconsultar.limpiar))
            limpiar(vista.pconsultar.informacion);


    }

    public void addnombreEmpleados(Choice c) {
        List<String> empleados = new ArrayList<>();
        ResultSet rs = modelo.getEmpleados();
        c.removeAll();
        try {
            while (rs.next())
                empleados.add(rs.getString("nombreEmpleado"));
            for (String s: empleados)
                c.add(s);
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    public String consultaEmpleado(ResultSet result) {
        String salida = "";
        try {
            while (result.next()) {
                salida = salida + "ID del empleado: " + result.getString("idEmpleado") + "\n";
                salida = salida + "NOMBRE del empleado: " + result.getString("nombreEmpleado") + "\n";
            }
            return salida;
        }
        catch (Exception e) {
            System.out.println("Error");
            return null;
        }

    }

    public void limpiar(JTextField campo) {
        campo.setText("");
        campo.setForeground(Color.black);
    }
}
