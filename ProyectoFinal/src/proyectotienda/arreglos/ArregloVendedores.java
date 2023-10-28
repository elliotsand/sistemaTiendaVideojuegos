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

    public void eliminar(Vendedor vendedor) {
        vendedores.remove(vendedor);
        grabarVendedor();
        establecerCorrelativo();
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

            bufferedReader = new BufferedReader(new FileReader("Vendedores.txt"));

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
            printWriter = new PrintWriter(new FileWriter("Vendedores.txt"));
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

}
