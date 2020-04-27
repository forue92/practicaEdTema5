import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = Modelo.INSTANCE;
        Vista vista = new Vista("Empleados");
        Controlador controlador = new Controlador(modelo, vista);
        controlador.iniciar();
    }
}
