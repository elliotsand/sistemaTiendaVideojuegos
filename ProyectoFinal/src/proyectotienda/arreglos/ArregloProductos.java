package proyectotienda.arreglos;

import proyectotienda.clases.Producto;
import proyectotienda.hijas.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ArregloProductos {

    private ArrayList<Producto> productos;

    public ArregloProductos() {
        productos = new ArrayList<>();
        cargarCliente();
        establecerCorrelativo();
    }

    public int tamanio() {
        return productos.size();
    }

    public void agregar(Producto producto) {
        productos.add(producto);
        grabarCliente();
        establecerCorrelativo();
    }

    public Producto obtener(int posicion) {
        return productos.get(posicion);
    }

    public void eliminar(Producto producto) {
        productos.remove(producto);
        grabarCliente();
        establecerCorrelativo();
    }

    public Producto buscar(int codigo) {
        for(Producto producto : productos)
           // if(producto.getCodigoCliente() == codigo)
                return producto;

        return null;
    }

    public void actualizar() {
        grabarCliente();
    }

    private void cargarCliente() {
        try {
            BufferedReader bufferedReader;
            String linea;
            String nombres;
            String apellidos;
            String telefono;
            String dni;
            String[] strings;

            bufferedReader = new BufferedReader(new FileReader("Clientes.txt"));

            while((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                nombres = strings[1].trim();
                apellidos = strings[2].trim();
                telefono =  strings[3].trim();
                dni = strings[4].trim();
                //agregar(new Producto(nombres, apellidos, telefono, dni));
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void grabarCliente() {
        PrintWriter printWriter;
        String linea;
        Producto producto;
        try {
            printWriter = new PrintWriter(new FileWriter("Clientes.txt"));
            for (int i = 0; i < tamanio(); i++) {
                producto = obtener(i);
                /*linea = producto.getCodigoCliente() + ";" + producto.getNombres() +
                        ";" + producto.getApellidos() + ";" + producto.getTelefono() +
                        ";" + producto.getDni();
                printWriter.println(linea);*/
            }
            printWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establecerCorrelativo() {
        int maxCodigo = 1000; // Valor predeterminado si no hay clientes
        for (Producto producto : productos) {
            //if (producto.getCodigoCliente() > maxCodigo) {
            //    maxCodigo = producto.getCodigoCliente();
            //}
        }
        //Producto.establecerCorrelativo(maxCodigo + 1);
    }

}
