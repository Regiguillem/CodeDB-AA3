package modelo;

public class SociosModelo {
    private int n_socio;
    private String nombre;
    private double cuota_mensual;
    private String tipo_socio;
    private String nif;
    private FederacionesModelo federacion;
    private SeguroModelo seguro;

    // Constructor
    public SociosModelo(int n_socio, String nombre, double cuota_mensual, String tipo_socio, String nif, FederacionesModelo federacion, SeguroModelo seguro) {
        this.n_socio = n_socio;
        this.nombre = nombre;
        this.cuota_mensual = cuota_mensual;
        this.tipo_socio = tipo_socio;
        this.nif = nif;
        this.federacion = federacion;
        this.seguro = seguro;
    }

    //Getters y Setters

    public int getN_socio() {
        return n_socio;
    }

    public void setN_socio(int n_socio) {
        this.n_socio = n_socio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SeguroModelo getSeguro() {
        return seguro;
    }

    public void setSeguro(SeguroModelo seguro) {
        this.seguro = seguro;
    }

    public double getCuotaMensual() {
        return cuota_mensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuota_mensual = cuotaMensual;
    }

    public void setTipoSocio(String tipoSocio) {
    }
    public String getTipoSocio() { return tipo_socio;
    }
    public FederacionesModelo getFederacion() { return federacion;
    }
    public void setNif(String nif) {
    }

    public String getNif() {return nif;
    }
}
