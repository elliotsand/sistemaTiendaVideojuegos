package proyectotienda.clases;

public class Venta {
    private int codigoVenta;
    private int codigoCliente;
    private int codigoVendedor;
    private int codigoProducto;
    private int unidades;
    private double importeTotal;
    private static int correlativo = 5001;

    public Venta(int codigoVenta, int codigoCliente, int codigoVendedor, int codigoProducto, int unidades, double importeTotal) {
        this.codigoVenta = correlativo;
        this.codigoCliente = codigoCliente;
        this.codigoVendedor = codigoVendedor;
        this.codigoProducto = codigoProducto;
        this.unidades = unidades;
        this.importeTotal = importeTotal;
        correlativo++;
    }

    public int getCodigoVenta() {
        return codigoVenta;
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

    public int getUnidades() {
        return unidades;
    }
    public double getImporteTotal() {
        return importeTotal;
    }

    public static void establecerCorrelativo(int valor) {
        correlativo = valor;
    }


}
