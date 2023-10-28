package proyectotienda.arreglos;

import java.io.*;
import java.util.ArrayList;

import proyectotienda.clases.Factura;

public class ArregloFacturas {
    private ArrayList<Factura> facturas;

    public ArregloFacturas() {
        facturas = new ArrayList<>();
        establecerCorrelativo();
        cargarFactura();
    }

    public int tamanio() {
        return facturas.size();
    }

    public void agregar(Factura factura) {
        facturas.add(factura);
        establecerCorrelativo();
        grabarFactura();
    }

    public Factura obtener(int posicion) {
        return facturas.get(posicion);
    }

    public void eliminar(Factura factura) {
        facturas.remove(factura);
        establecerCorrelativo();
        grabarFactura();
    }

    public Factura buscar(int codigo) {
        for (Factura factura : facturas)
            if (factura.getCodigoFactura() == codigo)
                return factura;

        return null;
    }

    public void actualizar() {
        grabarFactura();
    }

    private void cargarFactura() {
        try {
            BufferedReader bufferedReader;
            String linea;
            int codigoFactura;
            int codigoProducto;
            int codigoVendedor;
            double precio;
            int unidades;
            String[] strings;

            bufferedReader = new BufferedReader(new FileReader("facturas.txt"));

            while ((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                codigoFactura = Integer.parseInt(strings[0].trim());
                codigoProducto = Integer.parseInt(strings[1].trim());
                codigoVendedor = Integer.parseInt(strings[2].trim());
                precio = Double.parseDouble(strings[3].trim());
                unidades = Integer.parseInt(strings[2].trim());

                agregar(new Factura( codigoProducto, codigoVendedor, precio, unidades));
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void grabarFactura() {
        PrintWriter printWriter;
        String linea;
        Factura factura;
        try {
            printWriter = new PrintWriter(new FileWriter("facturas.txt"));
            for (int i = 0; i < tamanio(); i++) {
                factura = obtener(i);
                linea = factura.getCodigoFactura() + ";" + factura.getCodigoProducto() +
                        ";" + factura.getCodigoVendedor() + ";" + factura.getPrecio()+ ";" + factura.getUnidades();
                printWriter.println(linea);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establecerCorrelativo() {
        int maxCodigo = 4000;
        for (Factura factura : facturas) {
            if (factura.getCodigoFactura() > maxCodigo) {
                maxCodigo = factura.getCodigoFactura();
            }
        }
        Factura.establecerCorrelativo(maxCodigo + 1);
    }

}
