package controlador;

import modelo.*;
import vista.SociosVista;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class SociosControlador {
    private SociosVista vistaSoc = new SociosVista();
    private Datos datos = Datos.getInstance();

    public SociosControlador() {
    }

    public boolean iniciar() {
        int opcion;
        do {
            opcion = this.vistaSoc.mostrarMenu();
            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del menú de Gestión de Socios...");
                    return true;
                case 1:
                    this.agregarSocioEstandar();
                    break;
                case 2:
                    this.modificarTipoSeguroSocio();
                    break;
                case 3:
                    this.agregarSocioFederado();
                    break;
                case 4:
                    this.agregarSocioInfantil();
                    break;
                case 5:
                    this.eliminarSocio();
                    break;
                case 6:
                    this.vistaSoc.mostrarSociosPorTipo();
                    break;
                case 7:
                    this.mostrarFacturaMensualFiltroSocio();
                    break;
                default:
                    System.out.println("Opción no válida. Introduzca una opción válida.");
            }
        } while (opcion != 0);

        return false;
    }

    public void agregarSocioEstandar() {
        try {
            this.vistaSoc.mostrarMensaje("Añadiendo Socio Estándar...");
            String nombre = this.vistaSoc.solicitarNombreSocio();
            String nif = this.vistaSoc.solicitarNifSocio();
            SeguroModelo seguro = this.vistaSoc.solicitarSeguroSocio();
            SocioEstandarModelo socioEstandar = new SocioEstandarModelo(nombre, nif, seguro);
            this.datos.getSociosEst().add(socioEstandar);
            this.datos.getListaSocios().add(socioEstandar);
            this.vistaSoc.mostrarMensaje("Socio Estándar añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar el socio estándar: " + e.getMessage());
        }
    }

    public void modificarTipoSeguroSocio() {
        this.vistaSoc.mostrarMensaje("Modificando tipo de seguro de un socio estándar...");
        this.listarSociosEst();
        int numeroSocio = this.vistaSoc.solicitarNumeroSocio();
        SocioEstandarModelo socioSeleccionado = this.buscarSocioEstPorNumero(numeroSocio);
        if (socioSeleccionado != null) {
            int opcionSeguro = this.vistaSoc.solicitarNuevoTipoSeguro();
            SeguroModelo nuevoSeguro = null;
            switch (opcionSeguro) {
                case 1:
                    nuevoSeguro = this.datos.getSeguroCompleto();
                    break;
                case 2:
                    nuevoSeguro = this.datos.getSeguroBasico();
                    break;
                default:
                    this.vistaSoc.mostrarMensaje("Opción no válida. No se realizarán cambios en el tipo de seguro.");
                    return;
            }

            socioSeleccionado.setSeguro((SeguroModelo) nuevoSeguro);
            this.vistaSoc.mostrarMensaje("Tipo de seguro del socio modificado correctamente.");
        } else {
            this.vistaSoc.mostrarMensaje("El socio con el número especificado no existe.");
        }

    }

    public void listarSociosEst() {
        if (this.datos.getSociosEst().isEmpty()) {
            System.out.println("No hay socios estándar disponibles.");
        } else {
            System.out.println("Lista de Socios Estándar Disponibles:");
            Iterator var1 = this.datos.getSociosEst().iterator();

            while (var1.hasNext()) {
                SocioEstandarModelo socio = (SocioEstandarModelo) var1.next();
                PrintStream var10000 = System.out;
                int var10001 = Integer.parseInt(socio.getN_socio());
                var10000.println("Número de socio: " + var10001 + ", Nombre: " + socio.getNombre() + ", Tipo de Seguro: " + socio.getSeguro().getTipo());
            }

        }
    }

    private SocioEstandarModelo buscarSocioEstPorNumero(int numeroSocio) {
        Iterator var2 = this.datos.getSociosEst().iterator();

        SocioEstandarModelo socio;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            socio = (SocioEstandarModelo) var2.next();
        } while (socio.getN_socio() != numeroSocio);

        return socio;
    }

    public void agregarSocioFederado() {
        try {
            this.vistaSoc.mostrarMensaje("Añadiendo Socio Federado...");
            String nombre = this.vistaSoc.solicitarNombreSocio();
            String nif = this.vistaSoc.solicitarNifSocio();
            FederacionesModelo federacionSocio = this.solicitarFederacion();
            SociosFederadosModelo socioFederado = new SociosFederadosModelo(nombre, nif, federacionSocio);
            this.datos.getSociosFed().add(socioFederado);
            this.datos.getListaSocios().add(socioFederado);
            this.vistaSoc.mostrarMensaje("Socio Federado añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar el socio Federado: " + e.getMessage());
        }
    }

    public FederacionesModelo solicitarFederacion() {
        System.out.println("Seleccione la federación del socio:");
        ArrayList<FederacionesModelo> federaciones = this.datos.getFederaciones();

        int opcionFederacion;
        for (opcionFederacion = 0; opcionFederacion < federaciones.size(); ++opcionFederacion) {
            FederacionesModelo federacion = (FederacionesModelo) federaciones.get(opcionFederacion);
            System.out.println(opcionFederacion + 1 + ". " + federacion.getNombre() + " (Código: " + federacion.getCodigo() + ")");
        }

        do {
            opcionFederacion = this.vistaSoc.opcionFederacion();
            if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
                System.out.println("Seleccione una opción válida.");
            }
        } while (opcionFederacion < 1 || opcionFederacion > federaciones.size());

        return (FederacionesModelo) federaciones.get(opcionFederacion - 1);
    }

    public void agregarSocioInfantil() {
        try {
            this.vistaSoc.mostrarMensaje("Añadiendo Socio Infantil...");
            String nombre = this.vistaSoc.solicitarNombreSocio();
            int n_socioPadreMadre = this.vistaSoc.solicitarNumeroSocioPadreMadre();
            SocioEstandarModelo socioPadreMadre = this.buscarSocioEstPorNumero(n_socioPadreMadre);
            if (socioPadreMadre != null) {
                SocioInfantilModelo socioInfantil = new SocioInfantilModelo(nombre, socioPadreMadre);
                this.datos.getSociosInf().add(socioInfantil);
                this.datos.getListaSocios().add(socioInfantil);
                this.vistaSoc.mostrarMensaje("Socio Infantil añadido correctamente.");
                this.vistaSoc.mostrarSocioInfantil(socioInfantil, socioPadreMadre.getNombre());
            } else {
                this.vistaSoc.mostrarMensaje("El socio padre o madre con el número especificado no existe.");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar el socio Infantil: " + e.getMessage());
        }
    }


    public SociosModelo obtenerSocioPorCodigo(int codigo) {
        Iterator var2 = this.datos.getListaSocios().iterator();

        SociosModelo socio;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            socio = (SociosModelo)var2.next();
        } while(codigo != socio.getN_socio());

        return socio;
    }

    public void eliminarSocio() {
        ArrayList<SociosModelo> socios = this.datos.getListaSocios();
        System.out.println("Socios disponibles para eliminar:");
        if (socios.isEmpty()) {
            System.out.println("No hay socios disponibles para eliminar.");
            return;
        }
        Iterator var2 = socios.iterator();

        SociosModelo socioSeleccionado;
        while(var2.hasNext()) {
            socioSeleccionado = (SociosModelo)var2.next();
            PrintStream var10000 = System.out;
            int var10001 = Integer.parseInt(socioSeleccionado.getN_socio());
            var10000.println("Número de Socio: " + var10001 + ", Nombre: " + socioSeleccionado.getNombre());
        }

        System.out.println("Seleccione el número de socio que desea eliminar (0 para cancelar): ");
        int numeroSocio = this.vistaSoc.solicitarNumeroSocio();
        if (numeroSocio == 0) {
            System.out.println("Operación cancelada.");
        } else {
            socioSeleccionado = null;
            Iterator var4 = socios.iterator();

            while(var4.hasNext()) {
                SociosModelo socio = (SociosModelo)var4.next();
                if (socio.getN_socio() == numeroSocio) {
                    socioSeleccionado = socio;
                    break;
                }
            }

            if (socioSeleccionado != null) {
                boolean inscrito = false;
                Iterator var9 = this.datos.getInscripciones().iterator();

                while(var9.hasNext()) {
                    InscripcionesModelo inscripcion = (InscripcionesModelo)var9.next();
                    if (inscripcion.getSocio().equals(socioSeleccionado)) {
                        inscrito = true;
                        break;
                    }
                }

                if (!inscrito) {
                    socios.remove(socioSeleccionado);
                    System.out.println("Socio eliminado correctamente.");
                } else {
                    System.out.println("No se puede eliminar el socio porque está inscrito en al menos una excursión.");
                }
            } else {
                System.out.println("No se encontró ningún socio con el número especificado.");
            }

        }
    }

    public void mostrarFacturaMensualFiltroSocio() {
        ArrayList<SociosModelo> socios = this.datos.getListaSocios();
        SociosModelo socioSeleccionado = this.vistaSoc.seleccionarSocio(socios);
        if (socioSeleccionado != null) {
            this.mostrarFacturaMensualFiltroSocio(socioSeleccionado);
        } else {
            System.out.println("No se seleccionó ningún socio.");
        }

    }

    private void mostrarFacturaMensualFiltroSocio(SociosModelo socio) {
        double totalCuotaMensual = this.calcularCuotaMensual(socio);
        double totalExcursiones = this.calcularTotalExcursiones(socio);
        double totalPagar = totalCuotaMensual + totalExcursiones;
        System.out.println("Factura mensual para el socio " + socio.getNombre() + ":");
        System.out.println("Fecha: " + String.valueOf(LocalDate.now()));
        System.out.println("Cuota Mensual: " + totalCuotaMensual);
        System.out.println("Total Excursiones: " + totalExcursiones);
        if (socio instanceof SocioEstandarModelo socioEstandar) {
            totalPagar += socioEstandar.getSeguro().getPrecio();
            System.out.println("Precio del seguro: " + socioEstandar.getSeguro().getPrecio());
        }

        PrintStream var10000;
        double var10001;
        if (socio instanceof SociosFederadosModelo) {
            var10000 = System.out;
            var10001 = ((SociosFederadosModelo)socio).getDescuento_cuota();
            var10000.println("Descuento en la cuota aplicado: " + var10001 * 100.0 + "%");
            var10000 = System.out;
            var10001 = ((SociosFederadosModelo)socio).getDescuento_exc();
            var10000.println("Descuento aplicado en las excursiones: " + var10001 * 100.0 + "%");
        } else if (socio instanceof SocioInfantilModelo) {
            var10000 = System.out;
            var10001 = ((SocioInfantilModelo)socio).getDescuento_cuota();
            var10000.println("Descuento en la cuota aplicado: " + var10001 * 100.0 + "%");
        }

        System.out.println("-----------------------------");
        System.out.println("Total a Pagar: " + totalPagar);
        System.out.println("-----------------------------");
    }

    private double calcularCuotaMensual(SociosModelo socio) {
        double cuotaBaseMensual = socio.getCuotaMensual();
        double descuentoCuota = 0.0;
        if (socio instanceof SociosFederadosModelo) {
            descuentoCuota = cuotaBaseMensual * ((SociosFederadosModelo)socio).getDescuento_cuota();
        } else if (socio instanceof SocioInfantilModelo) {
            descuentoCuota = cuotaBaseMensual * ((SocioInfantilModelo)socio).getDescuento_cuota();
        }

        return cuotaBaseMensual - descuentoCuota;
    }

    private double calcularTotalExcursiones(SociosModelo socio) {
        double totalExcursiones = 0.0;
        ArrayList<InscripcionesModelo> inscripciones = this.datos.getInscripciones();
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        Iterator var7 = inscripciones.iterator();

        while(var7.hasNext()) {
            InscripcionesModelo inscripcion = (InscripcionesModelo)var7.next();
            if (inscripcion.getSocio().equals(socio)) {
                ExcursionesModelo excursion = inscripcion.getExcursion();
                LocalDate fechaExcursion = excursion.getFecha();
                int mesExcursion = fechaExcursion.getMonthValue();
                if (mesExcursion == mesActual) {
                    double precioExcursion = excursion.getPrecio();
                    if (socio instanceof SociosFederadosModelo) {
                        precioExcursion *= ((SociosFederadosModelo)socio).getDescuento_exc();
                    }

                    totalExcursiones += precioExcursion;
                }
            }
        }

        return totalExcursiones;
    }
}