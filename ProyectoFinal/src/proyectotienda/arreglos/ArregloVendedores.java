package proyectotienda.arreglos;

import proyectotienda.hijas.Cliente;
import proyectotienda.hijas.Vendedor;

import java.io.*;
import java.util.ArrayList;

public class ArregloVendedores {

    private ArrayList<Vendedor> vendedores;

    public ArregloVendedores() {
        vendedores = new ArrayList<>();
        cargarCliente();
        establecerCorrelativo();
    }

    public int tamanio() {
        return vendedores.size();
    }

    public void agregar(Vendedor vendedor) {
        vendedores.add(vendedor);
        grabarCliente();
        establecerCorrelativo();
    }

    public Vendedor obtener(int posicion) {
        return vendedores.get(posicion);
    }

    public void eliminar(Vendedor vendedor) {
        vendedores.remove(vendedor);
        grabarCliente();
        establecerCorrelativo();
    }

    public Vendedor buscar(int codigo) {
        for(Vendedor vendedor : vendedores)
            //if(vendedor.getCodigoCliente() == codigo)
                return vendedor;

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
                //agregar(new Vendedor(nombres, apellidos, telefono, dni));
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
        Vendedor vendedor;
        try {
            printWriter = new PrintWriter(new FileWriter("Clientes.txt"));
            for (int i = 0; i < tamanio(); i++) {
                vendedor = obtener(i);
                /*linea = vendedor.getCodigoCliente() + ";" + vendedor.getNombres() +
                        ";" + vendedor.getApellidos() + ";" + vendedor.getTelefono() +
                        ";" + vendedor.getDni();
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
        for (Vendedor vendedor : vendedores) {
            //if (vendedor.getCodigoCliente() > maxCodigo) {
           //     maxCodigo = vendedor.getCodigoCliente();
           // }
        }
        //Vendedor.establecerCorrelativo(maxCodigo + 1);
    }

}
