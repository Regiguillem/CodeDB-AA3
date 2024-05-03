package modelo;

public class SeguroModelo {
    private int id;

    private String tipo;
    private double precio;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getId() {return id;}
}
