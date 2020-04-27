package modelo;

import java.sql.*;

public class Modelo {
    public static final Modelo INSTANCE = new Modelo();
    private Connection conexion = null;
    private static final String MySQL_DB_DRIVER = "com.mysql.jdbc.Driver";
    private final static String MySQL_DB_URL = "jdbc:mysql://localhost:3306/empresa";
    private final static String MySQL_DB_USUARIO = "java";
    private final static String MySQL_DB_PASSWORD = "java";

    private Modelo() {
        try {
            Class.forName(MySQL_DB_DRIVER);
            this.conexion = DriverManager.getConnection(MySQL_DB_URL + "?&useSSL=false", MySQL_DB_USUARIO, MySQL_DB_PASSWORD);
            if (conexion != null) {
                System.out.println("Conectado a la base de datos");
            }
        }
        catch (SQLException ex) {
            System.out.println("ERROR:La dirección no es válida o el usuario y clave");
        }
        catch (ClassNotFoundException c) {
            System.out.println("Error en el driver de conexión");
        }
    }

    public void cerrarConexion() {
        try {
            this.conexion.close();
        }
        catch (SQLException ex) {
            System.out.println("Error cerrando la conexión");
        }
    }

    public ResultSet consultaEmpleado(int idEmpleado) {
        String consulta = "select idEmpleado, nombreEmpleado from empleados " +
                "where idEmpleado = (?);";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, idEmpleado);
            ResultSet resultado = ps.executeQuery();
            return resultado;
        }
        catch (SQLException sq) {
            System.out.println("Error al realizar la consulta a empleado");
            sq.printStackTrace();
        }
        return null;
    }

    public ResultSet consultaEmpleado(String nombreEmpleado) {
        if (nombreEmpleado == null)
            return null;
        String consulta = "select idEmpleado, nombreEmpleado from empleados " +
                "where nombreEmpleado = (?);";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreEmpleado);
            ResultSet resultado = ps.executeQuery();
            return resultado;
        }
        catch (SQLException sq) {
            System.out.println("Error al realizar la consulta a empleado");
            sq.printStackTrace();
        }
        return null;
    }

    public ResultSet getEmpleados () {
        String consulta = "select idEmpleado, nombreEmpleado from empleados;";
        if (conexion == null)
            System.out.println("Conexion = null");
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ResultSet resultado = ps.executeQuery();
            return resultado;
        }
        catch (SQLException sq) {
            System.out.println("Error al realizar la consulta sobre todos los empleados");
            sq.printStackTrace();
        }
        return null;
    }

    public boolean eliminaEmpleado (String nombreEmpleado) {
        if (nombreEmpleado == null)
            return false;
        String consulta = " delete from empleados where nombreEmpleado = (?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreEmpleado);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException sq) {
            System.out.println("Error al eliminar un empleado");
            return false;
        }
    }

    public boolean nuevoEmpleado(String empleado) {
        if (empleado == null)
            return false;
        String consulta = "insert into empleados (nombreEmpleado) values (?);";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, empleado);
            ps.executeUpdate();
            System.out.println("Empleado insertado con éxito");
            return true;
        }
        catch (Exception e) {
            System.out.println("No ha sido posible insertar un nuevo empleado");
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificaEmpleado(String nombreOrigen, String nombreDestino) {
        if (nombreDestino == null || nombreOrigen == null)
            return false;
        String consulta = "update empleados set nombreEmpleado = (?) where nombreEmpleado = (?) ;";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreDestino);
            ps.setString(2, nombreOrigen);
            ps.executeUpdate();
            System.out.println("Empleado modificado con éxito");
            return true;
        }
        catch (Exception e) {
            System.out.println("No ha sido posible modificar un nuevo empleado");
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificaEmpleado(int idOrigen, String nombreDestino) {
        if (nombreDestino == null)
            return false;
        String consulta = "update empleados set nombreEmpleado = (?) where idEmpleado = (?) ;";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setString(1, nombreDestino);
            ps.setInt(2, idOrigen);
            ps.executeUpdate();
            System.out.println("Empleado modificado con éxito");
            return true;
        }
        catch (Exception e) {
            System.out.println("No ha sido posible modificar un nuevo empleado");
            e.printStackTrace();
        }
        return false;
    }
}
