package proyectotienda.arreglos;

import proyectotienda.clases.Producto;
import proyectotienda.clases.Venta;

import java.io.*;
import java.util.ArrayList;

public class ArregloVentas {

    private ArrayList<Venta> ventas;

    public ArregloVentas() {
        ventas = new ArrayList<>();
        cargarVenta();
        //establecerCorrelativo();
    }

    public int tamanio() {
        return ventas.size();
    }

    public void agregar(Venta venta) {
        ventas.add(venta);
        grabarVenta();
        //establecerCorrelativo();
    }

    public Venta obtener(int posicion) {
        return ventas.get(posicion);
    }

    public void eliminar(Venta venta) {
        ventas.remove(venta);
        grabarVenta();
        //establecerCorrelativo();
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
            int codigoVenta;
            int codigoCliente;
            int codigoVendedor;
            int codigoProducto;
            int unidades;
            double importeTotal;
            String[] strings;

            bufferedReader = new BufferedReader(new FileReader("ventas.txt"));

            while((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                codigoVenta = Integer.parseInt(strings[0].trim());
                codigoCliente = Integer.parseInt(strings[1].trim());
                codigoVendedor = Integer.parseInt(strings[2].trim());
                codigoProducto =  Integer.parseInt(strings[3].trim());
                unidades =  Integer.parseInt(strings[4].trim());
                importeTotal =  Double.parseDouble(strings[5].trim());
                agregar(new Venta(codigoVenta, codigoCliente, codigoVendedor, codigoProducto, unidades, importeTotal));
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
            printWriter = new PrintWriter(new FileWriter("ventas.txt"));
            for (int i = 0; i < tamanio(); i++) {
                venta = obtener(i);
                linea = venta.getCodigoVenta() + ";" + venta.getCodigoCliente() + ";" +
                        venta.getCodigoVendedor() + ";" + venta.getCodigoProducto() + ";" +
                        venta.getUnidades() + ";" + venta.getImporteTotal();
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
                int codigoProductoVenta = Integer.parseInt(datosVenta[3].trim());
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
                int codigoProductoVenta = Integer.parseInt(datosVenta[3].trim());
                int unidades = Integer.parseInt(datosVenta[4].trim());
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
        double importeTotalAcumulado = 0.0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoProductoVenta = Integer.parseInt(datosVenta[3].trim());
                double importeTotal = Double.parseDouble(datosVenta[5].trim());
                if (codigoProductoVenta == codigoProducto) {
                    importeTotalAcumulado += importeTotal;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importeTotalAcumulado;
    }

}
