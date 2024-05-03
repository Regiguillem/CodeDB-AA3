package controlador;

import modelo.*;
import vista.InscripcionesVista;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import controlador.*;

public class InscripcionesControlador {

    private InscripcionesVista vistaInsc;
    private InscripcionesModeloDAO inscripcionesModeloDAO;

    public InscripcionesControlador(Connection connection) {
        this.vistaInsc = new InscripcionesVista(connection);
        this.inscripcionesModeloDAO = new InscripcionesModeloDAO(connection);
    }

    public boolean iniciar() {
        int opcion;
        do {
            opcion = vistaInsc.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarInscripcion();
                    break;
                case 2:
                    mostrarInscripcion();
                    break;
                case 3:
                    eliminarInscripcion();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    return true;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 4);
        return false;
    }


    public void agregarInscripcion() {
        InscripcionesModelo inscripcion = vistaInsc.DatosInscripcion();
        //datos.getExcursiones().add(excursion); // Utilizamos el Singleton Datos para agregar la excursión
        try {
            inscripcionesModeloDAO.agregarInscripcion(inscripcion); // Llamar al método agregarExcursion() de ExcursionesModeloDAO
            vistaInsc.mostrarMensaje("Inscripción añadida correctamente.");
        } catch (SQLException e) {
            vistaInsc.mostrarMensaje("Error al añadir la inscripción: " + e.getMessage());
        }
    }

    public void mostrarInscripcion() {
        try {
            SociosModelo socioSeleccionado = vistaInsc.listarSocios();
            if (socioSeleccionado != null) {
                ArrayList<InscripcionesModelo> inscripcionesSocio = inscripcionesModeloDAO.obtenerInscripcionesPorSocio(socioSeleccionado);
                if (!inscripcionesSocio.isEmpty()) {
                    System.out.println("Inscripciones de " + socioSeleccionado.getNombre() + ":");
                    for (InscripcionesModelo inscripcion : inscripcionesSocio) {
                        System.out.println("Número de Inscripción: " + inscripcion.getN_inscripcion());
                        System.out.println("Fecha de Inscripción: " + inscripcion.getFechaInscripcion());
                        System.out.println("Excursión: " + inscripcion.getExcursion().getDescripcion());
                        System.out.println("Fecha de la excursión: " + inscripcion.getExcursion().getFecha());
                        System.out.println("--------------------");
                    }
                } else {
                    System.out.println("El socio seleccionado no tiene inscripciones.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las inscripciones: " + e.getMessage());
        }
    }

    public void eliminarInscripcion() {
        InscripcionesModelo inscripcion = vistaInsc.BorrarInscripcion();
        //datos.getExcursiones().add(excursion); // Utilizamos el Singleton Datos para agregar la excursión
        try {
            inscripcionesModeloDAO.eliminarInscripcion(inscripcion); // Llamar al método agregarExcursion() de ExcursionesModeloDAO
            vistaInsc.mostrarMensaje("Inscripción eliminada correctamente.");
        } catch (SQLException e) {
            vistaInsc.mostrarMensaje("Error al elliminar la inscripción: " + e.getMessage());
        }
    }

}
