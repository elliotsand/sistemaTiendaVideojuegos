package proyectotienda.arreglos;

import proyectotienda.clases.Producto;
import proyectotienda.hijas.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ArregloProductos {

    private ArrayList<Producto> productos;

    public ArregloProductos() {
        productos = new ArrayList<>();
        cargarProducto();
        establecerCorrelativo();
    }

    public int tamanio() {
        return productos.size();
    }

    public void agregar(Producto producto) {
        productos.add(producto);
        grabarProducto();
        establecerCorrelativo();
    }

    public Producto obtener(int posicion) {
        return productos.get(posicion);
    }

    public void eliminar(Producto producto) {
        productos.remove(producto);
        grabarProducto();
        establecerCorrelativo();
    }

    public Producto buscar(int codigo) {
        for(Producto producto : productos)
            if(producto.getCodigoProducto() == codigo)
                return producto;

        return null;
    }

    public void actualizar() {
    	grabarProducto();
    }

    private void cargarProducto() {
        try {
            BufferedReader bufferedReader;
            String linea;
            String nombre;
            String descripcion;
            Double precio;
            String[] strings;

            bufferedReader = new BufferedReader(new FileReader("productos.txt"));

            while((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                nombre = strings[1].trim();
                descripcion = strings[2].trim();
                precio =  Double.parseDouble(strings[3].trim());
                agregar(new Producto(nombre, descripcion, precio));
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void grabarProducto() {
        PrintWriter printWriter;
        String linea;
        Producto producto;
        try {
            printWriter = new PrintWriter(new FileWriter("productos.txt"));
            for (int i = 0; i < tamanio(); i++) {
                producto = obtener(i);
                linea = producto.getCodigoProducto() + ";" + producto.getNombre() +
                        ";" + producto.getDescripcion() + ";" + producto.getPrecio();
                printWriter.println(linea);
            }
            printWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establecerCorrelativo() {
        int maxCodigo = 3000;
        for (Producto producto : productos) {
            if (producto.getCodigoProducto() > maxCodigo) {
                maxCodigo = producto.getCodigoProducto();
            }
        }
        Producto.establecerCorrelativo(maxCodigo + 1);
    }
    
    public double calcularPrecioPromedio() {
        double suma = 0.0;
        for (Producto producto : productos) {
            suma += producto.getPrecio();
        }
        if (productos.size() > 0) {
            return suma / productos.size();
        }
        return 0.0; 
    }

    public double encontrarPrecioMasAlto() {
        double precioMasAlto = Double.MIN_VALUE;
        for (Producto producto : productos) {
            if (producto.getPrecio() > precioMasAlto) {
                precioMasAlto = producto.getPrecio();
            }
        }
        return precioMasAlto;
    }

    public double encontrarPrecioMasBajo() {
        double precioMasBajo = Double.MAX_VALUE;
        for (Producto producto : productos) {
            if (producto.getPrecio() < precioMasBajo) {
                precioMasBajo = producto.getPrecio();
            }
        }
        return precioMasBajo;
    }

}
