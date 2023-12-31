package proyectotienda.clases;


public class Factura {

    private static int correlativo = 4001;
    private int codigoFactura;
    private int codigoVendedor;
    private int codigoProducto;
    private int unidades;
    private double precio;

    public Factura( int codigoProducto, int codigoVendedor, double precio, int unidades) {
        this.codigoFactura = correlativo;
        this.codigoVendedor = codigoVendedor;
        this.codigoProducto = codigoProducto;
        this.unidades = unidades;
        this.precio = precio;
        correlativo++;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int codigoUnidades) {
        this.unidades = codigoUnidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static void establecerCorrelativo(int valor) {
        correlativo = valor;
    }
}
