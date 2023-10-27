package proyectotienda.clases;


public class Factura {

	private int codigoFactura;
	private int codigoVendedor;
	private int codigoProducto;
	private int unidades;
	private double precio;

	private static int codigoFacturaCounter = 4001;

	public Factura(int codigoFactura, int codigoVendedor, int codigoProducto, int unidades, double precio) {
		this.codigoFactura = codigoFactura;
		this.codigoVendedor = codigoVendedor;
		this.codigoProducto = codigoProducto;
		this.unidades = unidades;
		this.precio = precio;
	}

	public int getCodigoFactura() {
		return codigoFactura;
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

	public double getPrecio() {
		return precio;
	}
}
