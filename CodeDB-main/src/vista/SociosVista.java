package vista;

import modelo.*;

import java.util.Scanner;

public class SociosVista {
    private Scanner scanner;

    public SociosVista() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("Gestión de Socios");
        System.out.println("1. Añadir Socio Estándar");
        System.out.println("2. Modificar tipo de seguro de un socio estándar");
        System.out.println("3. Añadir Socio Federado");
        System.out.println("4. Añadir Socio Infantil");
        System.out.println("5. Eliminar socio");
        System.out.println("6. Mostrar socios filtrados");
        System.out.println("7. Mostrar factura mensual de socio");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public int solicitarNuevoTipoSeguro() {
        System.out.println("Introduzca el nuevo tipo de seguro: ");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int solicitarNumeroSocio() {
        System.out.print("Introduzca el número de socio: ");
        return scanner.nextInt();
    }

    public String solicitarNombreSocio() {
        System.out.print("Introduzca el nombre del socio: ");
        return scanner.nextLine();
    }

    public String solicitarNifSocio() {
        System.out.print("Introduzca el NIF del socio: ");
        return scanner.nextLine();
    }

    public SeguroModelo solicitarSeguroSocio() {
        System.out.println("Seleccione el tipo de seguro para el socio:");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");

        int opcionSeguro = scanner.nextInt();
        switch (opcionSeguro) {
            case 1:
                return new SeguroCompletoModelo();
            case 2:
                return new SeguroBasicoModelo();
            default:
                System.out.println("Opción no válida. Seleccionando Seguro Básico por defecto.");
                return new SeguroBasicoModelo();
        }
    }

    public int opcionFederacion() {
        System.out.println("Seleccione una opción válida: ");
        return scanner.nextInt();
    }

    public int solicitarNumeroSocioPadreMadre() {
        System.out.print("Introduzca el número de socio del padre o madre: ");
        return scanner.nextInt();
    }

    public void mostrarSocioInfantil(SocioInfantilModelo socioInfantil, String nombrePadreMadre) {
        System.out.println("Socio Infantil añadido correctamente:");
        System.out.println("Número de socio: " + socioInfantil.getN_socio());
        System.out.println("Nombre del socio: " + socioInfantil.getNombre());
        System.out.println("Número de socio del padre o madre: " + socioInfantil.getN_socioPadreMadre().getN_socio() + " (Nombre: " + nombrePadreMadre + ")");
        System.out.println("Descuento en cuota: " + socioInfantil.getDescuento_cuota() + "%");
    }

    public int mostrarMenuFiltrado() {
        System.out.println("Seleccione el tipo de socios que desea ver:");
        System.out.println("1. Socios Estándar");
        System.out.println("2. Socios Federados");
        System.out.println("3. Socios Infantiles");
        System.out.println("4. Mostrar todos los socios");
        System.out.println("0. Volver al menú anterior");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Mostrando socios estándar:");
                break;
            case 2:
                System.out.println("Mostrando socios federados:");
                break;
            case 3:
                System.out.println("Mostrando socios infantiles:");
                break;
            case 4:
                System.out.println("Mostrando todos los socios:");
                break;
            case 0:
                System.out.println("Volviendo al menú anterior.");
                break;
            default:
                System.out.println("Opción no válida.");
        }return opcion;
    }



    public void mostrarSocios() {
        System.out.println("Lista de socios:");
        for (SociosModelo socio : socios) {// acabar de mirar como hacer esto ya que no existen los socios como tal
            System.out.println("Número de socio: " + socio.getN_socio());
            System.out.println("Nombre: " + socio.getNombre());
            if (socio instanceof SocioEstandarModelo) {
                SocioEstandarModelo socioEstandar = (SocioEstandarModelo) socio;
                System.out.println("Tipo de socio: Estándar");
                System.out.println("NIF: " + socioEstandar.getNif());
                System.out.println("Seguro: " + socioEstandar.getSeguro().getTipo());
            } else if (socio instanceof SociosFederadosModelo) {
                SociosFederadosModelo socioFederado = (SociosFederadosModelo) socio;
                System.out.println("Tipo de socio: Federado");
                System.out.println("NIF: " + socioFederado.getNif());
                System.out.println("Federación: " + socioFederado.getFederacion().getNombre());
            } else if (socio instanceof SocioInfantilModelo) {
                SocioInfantilModelo socioInfantil = (SocioInfantilModelo) socio;
                System.out.println("Tipo de socio: Infantil");
                System.out.println("Número de socio del Padre/Madre: " + socioInfantil.getN_socioPadreMadre().getN_socio());
                System.out.println("Descuento Cuota: " + socioInfantil.getDescuento_cuota());
            }
            System.out.println("------------------------------------------");
        }
    }


    public void mostrarFactura(int numSocio, double importe) {
        System.out.println("Factura mensual para el socio " + numSocio + ":");
        System.out.println("Importe: $" + importe);
    }
    public String solicitarFederacion() {
        System.out.print("Ingrese el código de la federación: ");
        return scanner.nextLine();
    }
    public void mostrarFacturaMensual(FacturaModelo factura) {
        if (factura != null) {
            System.out.println("Factura Mensual:");
            System.out.println("Fecha: " + factura.getFecha());
            System.out.println("Importe: " + factura.getImporte());

        } else {
            System.out.println("No se encontró una factura para el socio y las fechas especificadas.");
        }
    }
}
