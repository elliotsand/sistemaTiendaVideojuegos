package proyectotienda.arreglos;

import proyectotienda.clases.Venta;

import java.io.*;
import java.util.ArrayList;

public class ArregloVentas {

    private ArrayList<Venta> ventas;

    public ArregloVentas() {
        ventas = new ArrayList<>();
        cargarVenta();
        establecerCorrelativo();
    }

    public int tamanio() {
        return ventas.size();
    }

    public void agregar(Venta venta) {
        ventas.add(venta);
        grabarVenta();
        establecerCorrelativo();
    }

    public Venta obtener(int posicion) {
        return ventas.get(posicion);
    }

    public void eliminar(Venta venta) {
        ventas.remove(venta);
        grabarVenta();
        establecerCorrelativo();
    }

    public Venta buscar(int codigo) {
        for(Venta venta : ventas)
            if(venta.getCodigoProducto() == codigo)
                return venta;

        return null;
    }

    public void actualizar() {
        grabarVenta();
    }

    private void cargarVenta() {
        try {
            BufferedReader bufferedReader;
            String linea;
            int codigoCliente;
            int codigoVendedor;
            int codigoProducto;
            String[] strings;

            bufferedReader = new BufferedReader(new FileReader("productos.txt"));

            while((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                codigoCliente = Integer.parseInt(strings[1].trim());
                codigoVendedor = Integer.parseInt(strings[2].trim());
                codigoProducto =  Integer.parseInt(strings[3].trim());
                agregar(new Venta(codigoCliente, codigoVendedor, codigoProducto));
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void grabarVenta() {
        PrintWriter printWriter;
        String linea;
        Venta venta;
        try {
            printWriter = new PrintWriter(new FileWriter("productos.txt"));
            for (int i = 0; i < tamanio(); i++) {
                venta = obtener(i);
                linea = venta.getCodigoCliente() + ";" + venta.getCodigoVendedor() +
                        ";" + venta.getCodigoProducto();
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
        for (Venta venta : ventas) {
            if (venta.getCodigoProducto() > maxCodigo) {
                maxCodigo = venta.getCodigoProducto();
            }
        }
        Venta.establecerCorrelativo(maxCodigo + 1);
    }

    public int obtenerNumeroVentasPorProducto(int codigoProducto) {
        int numeroVentas = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoProductoVenta = Integer.parseInt(datosVenta[2].trim());
                if (codigoProductoVenta == codigoProducto) {
                    numeroVentas++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numeroVentas;
    }

    public int calcularUnidadesVendidasPorProducto(int codigoProducto) {
        int unidadesVendidas = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoProductoVenta = Integer.parseInt(datosVenta[2].trim());
                int unidades = Integer.parseInt(datosVenta[3].trim());
                if (codigoProductoVenta == codigoProducto) {
                    unidadesVendidas += unidades;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unidadesVendidas;
    }

    public double calcularImporteTotalPorProducto(int codigoProducto) {
        double importeTotal = 0.0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoProductoVenta = Integer.parseInt(datosVenta[2].trim());
                int unidades = Integer.parseInt(datosVenta[3].trim());
                double precioUnitario = obtenerPrecioPorCodigoProducto(codigoProductoVenta);
                if (codigoProductoVenta == codigoProducto) {
                    importeTotal += unidades * precioUnitario;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importeTotal;
    }

    public double obtenerPrecioPorCodigoProducto(int codigoProducto) {
        for (Venta venta : ventas) {
            if (venta.getCodigoProducto() == codigoProducto) {
                return venta.getPrecio();
            }
        }
        return 0.0;
    }
}
