package proyectotienda.clases;

public class Producto {
	private static int codigoProductoCounter = 3001;
    private int codigoProducto;
    private String descripcion;
    private double precio;

 
    public Producto(int codigoProducto, String descripcion, double precio) {
    	this.codigoProducto = codigoProductoCounter++;
        this.descripcion = descripcion;
        this.precio = precio;
    }

   
    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    public double calcularPrecioConIGV() {
        
        double precioConIGV = precio * 1.18;
        return precioConIGV;
    }

    @Override
    public String toString() {
        return "Código: " + codigoProducto + ", Descripción: " + descripcion + ", Precio: " + precio;
    }
}
