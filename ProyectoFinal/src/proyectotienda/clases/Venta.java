package proyectotienda.clases;

public class Venta {
    private int codigoCliente;
    private int codigoVendedor;
    private int codigoProducto;
    private int precio;
    private static int correlativo = 5001;

    public Venta(int codigoCliente, int codigoVendedor, int codigoProducto) {
        this.codigoCliente = correlativo;
        correlativo++;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public int getPrecio() {
        return precio;
    }

    public static void establecerCorrelativo(int valor) {
        correlativo = valor;
    }
}
