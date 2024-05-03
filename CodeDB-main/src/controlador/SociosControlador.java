package controlador;

import java.sql.Connection;
import java.util.ArrayList;

import modelo.*;
import vista.SociosVista;

public class SociosControlador {
    private Connection connection;
    private SociosModeloDAO sociosDAO;
    private SociosVista sociosVista;

    public SociosControlador(Connection connection) {
        this.connection = connection;
        this.sociosDAO = new SociosModeloDAO(connection);
        this.sociosVista = new SociosVista();
    }

    public boolean iniciar() {
        int opcion;
        do {
            opcion = sociosVista.mostrarMenu();
            switch (opcion) {
                case 1:
                    agregarSocioEst();
                    break;
                case 2:
                    modificarTipoSeguro();
                    break;
                case 3:
                    agregarSocioFederado();
                    break;
                case 4:
                    agregarSocioInfantil();
                    break;
                case 5:
                    eliminarSocio();
                    break;
                case 6:
                    mostrarSociosFiltrados();
                    break;
                case 7:
                    mostrarFacturaMensual();
                    break;
                case 0:
                    System.out.println("Saliendo del módulo de socios.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        return true;
    }

    private void agregarSocioEst() {
        String nombre = sociosVista.solicitarNombreSocio();
        String nif = sociosVista.solicitarNifSocio();
        SeguroModelo seguro = sociosVista.solicitarSeguroSocio();
        SociosModeloDAO.agregarSocioEst(nombre, nif, seguro);
    }


    private void modificarTipoSeguro() {
        int numeroSocio = sociosVista.solicitarNumeroSocio();
        SeguroModelo nuevoSeguro = sociosVista.solicitarSeguroSocio();
        if (sociosDAO.actualizarTipoSeguro(numeroSocio, nuevoSeguro.getId())) {
            sociosVista.mostrarMensaje("Tipo de seguro actualizado correctamente.");
        } else {
            sociosVista.mostrarMensaje("Error al actualizar el tipo de seguro.");
        }
    }

    private void agregarSocioFederado() {
        String nombre = sociosVista.solicitarNombreSocio();
        String nif = sociosVista.solicitarNifSocio();
        int opcionFederacion = sociosVista.opcionFederacion();
        SociosModeloDAO.agregarSocioFederado(nombre, nif, opcionFederacion);
    }

    private void agregarSocioInfantil() {
        String nombre = sociosVista.solicitarNombreSocio();
        String nif = sociosVista.solicitarNifSocio();
        int numSocioPadreMadre = sociosVista.solicitarNumeroSocioPadreMadre();
        SociosModeloDAO.agregarSocioInfantil(nombre, nif, numSocioPadreMadre);
    }

    private void eliminarSocio() {
        int numSocio = sociosVista.solicitarNumeroSocio();
        SociosModeloDAO.eliminarSocio(numSocio);
    }

    private void mostrarSociosFiltrados() {
        ArrayList<SociosModelo> sociosFiltrados = new ArrayList<>();
        int opcion = sociosVista.mostrarMenuFiltrado();
        switch (opcion) {
            case 1:
                sociosFiltrados = SociosModeloDAO.obtenerSociosEst();
                break;
            case 2:
                sociosFiltrados = SociosModeloDAO.obtenerSociosFed();
                break;
            case 3:
                sociosFiltrados = SociosModeloDAO.obtenerSociosInf();
                break;
            case 4:
                sociosFiltrados = SociosModeloDAO.obtenerTodosLosSocios();
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        if (!sociosFiltrados.isEmpty()) {
            sociosVista.mostrarSocios();
        } else {
            System.out.println("No hay socios que mostrar.");
        }
    }
    private void mostrarFacturaMensual() {
        int numSocio = sociosVista.solicitarNumeroSocio();
        double importe = SociosModeloDAO.calcularFacturaMensual(numSocio);
        if (importe != -1) {
            sociosVista.mostrarFactura(numSocio, importe);
        } else {
            System.out.println("No se pudo calcular la factura mensual para el socio con número " + numSocio);
        }
    }

    public SociosModelo obtenerSocioPorCodigo(String codigoSocio) {
        return SociosModeloDAO.obtenerSocioPorCodigo(codigoSocio);
    }




}
