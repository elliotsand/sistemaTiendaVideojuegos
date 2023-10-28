package proyectotienda.clases;

public class Producto {
    private static int codigoProductoCounter = 3001;
    private static int correlativoCounter = 1;
    private int codigoProducto;
    private String nombre;
    private String descripcion;
    private double precio;


    public Producto(String nombre, String descripcion, double precio) {
        this.codigoProducto = generarCodigoProducto();
        generarCorrelativo();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    private static int generarCorrelativo() {
        return correlativoCounter++;
    }

    private static int generarCodigoProducto() {
        return codigoProductoCounter++;
    }

    public static void establecerCorrelativo(int valor) {
        codigoProductoCounter = valor;
    }

    @Override
    public String toString() {
        return "Código: " + codigoProducto + "Nombre: " + nombre + ", Descripción: " + descripcion + ", Precio: " + precio;
    }
}
