import controlador.*;
import modelo.ExcursionesModeloDAO;
import util.DataBaseConnection; // Asegúrate de importar la clase de conexión

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        try {
            // Establecer la conexión al inicio del programa
            connection = DataBaseConnection.getConnection();
            System.out.println("Conexión con MySQL establecida.");

            // Crear la instancia de ExcursionesModeloDAO después de haber establecido la conexión
            ExcursionesModeloDAO excursionesModeloDAO = new ExcursionesModeloDAO(connection);

            int opcion;

            do {
                // Menú Principal
                System.out.println("Menú Principal");
                System.out.println("Seleccione una opción:");
                System.out.println("1. Controlador Excursiones");
                System.out.println("2. Controlador Socios");
                System.out.println("3. Controlador Inscripciones");
                System.out.println("0. Salir");

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        ExcursionesControlador controladorEx = new ExcursionesControlador(connection);
                        if (!controladorEx.iniciar()) {
                            System.out.println("Volviendo al menú principal...");
                        }
                        break;
                    case 2:
                        SociosControlador controladorSoc = new SociosControlador();
                        if (!controladorSoc.iniciar()) {
                            System.out.println("Volviendo al menú principal...");
                        }
                        break;
                    case 3:
                        InscripcionesControlador controladorInsc = new InscripcionesControlador(connection);
                        if (!controladorInsc.iniciar()) {
                            System.out.println("Volviendo al menú principal...");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar la conexión al salir del programa
                if (connection != null) {
                    DataBaseConnection.closeConnection(connection);
                    System.out.println("Conexión cerrada.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}