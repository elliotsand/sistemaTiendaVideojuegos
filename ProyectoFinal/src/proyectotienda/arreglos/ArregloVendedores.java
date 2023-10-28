package proyectotienda.arreglos;

import proyectotienda.hijas.Cliente;
import proyectotienda.hijas.Vendedor;

import java.io.*;
import java.util.ArrayList;

public class ArregloVendedores {

    private ArrayList<Vendedor> vendedores;

    public ArregloVendedores() {
        vendedores = new ArrayList<>();
        cargarVendedor();
        establecerCorrelativo();
    }

    public int tamanio() {
        return vendedores.size();
    }

    public void agregar(Vendedor vendedor) {
        vendedores.add(vendedor);
        grabarVendedor();
        //establecerCorrelativo();
    }

    public Vendedor obtener(int posicion) {
        return vendedores.get(posicion);
    }


    public Vendedor buscar(int codigo) {
        for(Vendedor vendedor : vendedores)
            if(vendedor.getCodigoVendedor() == codigo)
                return vendedor;

        return null;
    }

    public void actualizar() {
    	grabarVendedor();
    }

    private void cargarVendedor() {
        try {
            BufferedReader bufferedReader;
            String linea;
            String nombres;
            String apellidos;
            String telefono;
            String dni;
            String[] strings;

            bufferedReader = new BufferedReader(new FileReader("vendedores.txt"));

            while((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                nombres = strings[1].trim();
                apellidos = strings[2].trim();
                telefono =  strings[3].trim();
                dni = strings[4].trim();
                agregar(new Vendedor(nombres, apellidos, telefono, dni));
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void grabarVendedor() {
        PrintWriter printWriter;
        String linea;
        Vendedor vendedor;
        try {
            printWriter = new PrintWriter(new FileWriter("vendedores.txt"));
            for (int i = 0; i < tamanio(); i++) {
                vendedor = obtener(i);
                linea = vendedor.getCodigoVendedor() + ";" + vendedor.getNombres() +
                        ";" + vendedor.getApellidos() + ";" + vendedor.getTelefono() +
                        ";" + vendedor.getDni();
                printWriter.println(linea);
            }
            printWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establecerCorrelativo() {
        int maxCodigo = 1000;
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCodigoVendedor() > maxCodigo) {
                maxCodigo = vendedor.getCodigoVendedor();
            }
        }
        Cliente.establecerCorrelativo(maxCodigo + 1);
    }
    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

	public void eliminar(Vendedor vendedor) {
		vendedores.remove(vendedor);
        grabarVendedor();
        establecerCorrelativo();

	}

    public int obtenerNumeroVentasPorVendedor(int codigoVendedor) {
        int numeroVentas = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoProductoVenta = Integer.parseInt(datosVenta[2].trim());
                if (codigoProductoVenta == codigoVendedor) {
                    numeroVentas++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numeroVentas;
    }

    public int calcularUnidadesVendidasPorVendedor(int codigoVendedor) {
        int unidadesVendidas = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoVendedorVenta = Integer.parseInt(datosVenta[2].trim());
                int unidades = Integer.parseInt(datosVenta[4].trim());
                if (codigoVendedorVenta == codigoVendedor) {
                    unidadesVendidas += unidades;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unidadesVendidas;
    }

    public double calcularImporteTotalPorVendedor(int codigoVendedor) {
        double importeTotalAcumulado = 0.0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ventas.txt"));
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datosVenta = linea.split(";");
                int codigoVendedorVenta = Integer.parseInt(datosVenta[2].trim());
                double importeTotal = Double.parseDouble(datosVenta[5].trim());
                if (codigoVendedorVenta == codigoVendedor) {
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
