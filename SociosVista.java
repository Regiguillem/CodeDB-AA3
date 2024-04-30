package vista;

import modelo.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SociosVista {
    private Scanner scanner;
    private Datos datos = Datos.getInstance();

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
        System.out.println("6. Mostrar socios filtrados:");
        System.out.println("7. Mostrar factura mensual de socio:");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion = this.scanner.nextInt();
        this.scanner.nextLine();
        return opcion;
    }

    public int solicitarNuevoTipoSeguro() {
        System.out.println("Introduzca el nuevo tipo de seguro: ");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");
        int opcion = this.scanner.nextInt();
        this.scanner.nextLine();
        return opcion;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int solicitarNumeroSocio() {
        System.out.print("Introduzca el número de socio: ");
        int codigo = this.scanner.nextInt();
        this.scanner.nextLine();
        return codigo;
    }

    public String solicitarNombreSocio() {
        System.out.print("Introduzca el nombre del socio: ");
        return this.scanner.nextLine();
    }

    public String solicitarNifSocio() {
        System.out.print("Introduzca el NIF del socio: ");
        return this.scanner.nextLine();
    }

    public SeguroModelo solicitarSeguroSocio() {
        System.out.println("Seleccione el tipo de seguro para el socio:");
        System.out.println("1. Seguro Completo");
        System.out.println("2. Seguro Básico");
        System.out.print("Elija una opción: ");
        int opcionSeguro = this.scanner.nextInt();
        this.scanner.nextLine();
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
        int opcionFed = this.scanner.nextInt();
        return opcionFed;
    }

    public int solicitarNumeroSocioPadreMadre() {
        System.out.print("Introduzca el número de socio del padre o madre: ");
        return this.scanner.nextInt();
    }

    public void mostrarSocioInfantil(SocioInfantilModelo socioInfantil, String nombrePadreMadre) {
        System.out.println("Socio Infantil añadido correctamente:");
        System.out.println("Número de socio: " + socioInfantil.getN_socio());
        System.out.println("Nombre del socio: " + socioInfantil.getNombre());
        PrintStream var10000 = System.out;
        int var10001 = Integer.parseInt(socioInfantil.getN_socioPadreMadre().getN_socio());
        var10000.println("Número de socio del padre o madre: " + var10001 + " (Nombre: " + nombrePadreMadre + ")");
        System.out.println("Descuento en cuota: " + socioInfantil.getDescuento_cuota() + "%");
    }

    public void mostrarSociosPorTipo() {
        System.out.println("Seleccione el tipo de socios que desea ver:");
        System.out.println("1. Socios Estándar");
        System.out.println("2. Socios Federados");
        System.out.println("3. Socios Infantiles");
        System.out.println("4. Mostrar todos los socios");
        System.out.println("0. Volver al menú anterior");
        int opcion = this.scanner.nextInt();
        this.scanner.nextLine();
        ArrayList<SocioEstandarModelo> listaEstandar = this.datos.getSociosEst();
        ArrayList<SociosFederadosModelo> listaFederada = this.datos.getSociosFed();
        ArrayList<SocioInfantilModelo> listaInfantil = this.datos.getSociosInf();
        switch (opcion) {
            case 0:
                System.out.println("Volviendo al menú anterior.");
                break;
            case 1:
                System.out.println("Mostrando socios estándar:");
                this.mostrarSociosEst(listaEstandar);
                break;
            case 2:
                System.out.println("Mostrando socios federados:");
                this.mostrarSociosFed(listaFederada);
                break;
            case 3:
                System.out.println("Mostrando socios infantiles:");
                this.mostrarSociosInf(listaInfantil);
                break;
            case 4:
                System.out.println("Mostrando todos los socios:");
                this.mostrarSocios();
                break;
            default:
                System.out.println("Opción no válida.");
        }

    }

    public void mostrarSociosEst(ArrayList<SocioEstandarModelo> sociosEst) {
        sociosEst = datos.getSociosEst();
        if (sociosEst.isEmpty()) {
            System.out.println("No hay socios estándar.");
        } else {
            System.out.println("Lista de Socios Estándar Disponibles:");
            Iterator var2 = sociosEst.iterator();

            while (var2.hasNext()) {
                SocioEstandarModelo socio = (SocioEstandarModelo) var2.next();
                PrintStream var10000 = System.out;
                int var10001 = Integer.parseInt(socio.getN_socio());
                var10000.println("Número de socio: " + var10001 + ", Nombre: " + socio.getNombre() + ", Tipo de Seguro: " + socio.getSeguro().getTipo());
            }

        }
    }

    public void mostrarSociosFed(ArrayList<SociosFederadosModelo> listaFederada) {
        if (listaFederada.isEmpty()) {
            System.out.println("No hay socios federados.");
        } else {
            System.out.println("Lista de socios federados:");
            Iterator var2 = listaFederada.iterator();

            while(var2.hasNext()) {
                SociosFederadosModelo socio = (SociosFederadosModelo)var2.next();
                PrintStream var10000 = System.out;
                int var10001 = Integer.parseInt(socio.getN_socio());
                var10000.println("Número de socio: " + var10001 + ", Nombre: " + socio.getNombre() + ", NIF: " + socio.getNif() + ", Código Federación: " + socio.getFederacion().getNombre());
            }
        }

    }

    public void mostrarSociosInf(ArrayList<SocioInfantilModelo> listaInfantil) {
        if (listaInfantil.isEmpty()) {
            System.out.println("No hay socios infantiles.");
        } else {
            System.out.println("Lista de socios infantiles:");
            Iterator var2 = listaInfantil.iterator();

            while(var2.hasNext()) {
                SocioInfantilModelo socio = (SocioInfantilModelo)var2.next();
                PrintStream var10000 = System.out;
                int var10001 = Integer.parseInt(socio.getN_socio());
                var10000.println("Número de socio: " + var10001 + ", Nombre: " + socio.getNombre() + ", Socio Padre/Madre: " + socio.getN_socioPadreMadre().getNombre());
            }
        }

    }

    public void mostrarSocios() {
        ArrayList<SociosModelo> listaSocios = this.datos.getListaSocios();
        if (listaSocios.isEmpty()) {
            System.out.println("No hay socios disponibles.");
        } else {
            for (Iterator var1 = this.datos.getListaSocios().iterator(); var1.hasNext(); System.out.println("------------------------------------------")) {
                SociosModelo socio = (SociosModelo) var1.next();
                System.out.println("Número de socio: " + socio.getN_socio());
                System.out.println("Nombre: " + socio.getNombre());
                if (socio instanceof SocioEstandarModelo socioEstandar) {
                    System.out.println("Tipo de socio: Estándar");
                    System.out.println("NIF: " + socioEstandar.getNif());
                    System.out.println("Seguro: " + socioEstandar.getSeguro().getTipo());
                } else if (socio instanceof SociosFederadosModelo socioFederado) {
                    System.out.println("Tipo de socio: Federado");
                    System.out.println("NIF: " + socioFederado.getNif());
                    System.out.println("Federación: " + socioFederado.getFederacion().getNombre());
                } else if (socio instanceof SocioInfantilModelo socioInfantil) {
                    System.out.println("Tipo de socio: Infantil");
                    System.out.println("Número de socio del Padre/Madre: " + socioInfantil.getN_socioPadreMadre().getN_socio());
                    System.out.println("Descuento Cuota: " + socioInfantil.getDescuento_cuota());
                }
            }

        }
    }

    public SociosModelo seleccionarSocio(ArrayList<SociosModelo> socios) {
        System.out.println("Seleccione un socio:");

        int opcion;
        for(opcion = 0; opcion < socios.size(); ++opcion) {
            System.out.println(opcion + 1 + ". " + ((SociosModelo)socios.get(opcion)).getNombre());
        }

        do {
            do {
                System.out.print("Opción: ");
                opcion = this.scanner.nextInt();
            } while(opcion < 1);
        } while(opcion > socios.size());

        return (SociosModelo)socios.get(opcion - 1);
    }
}
