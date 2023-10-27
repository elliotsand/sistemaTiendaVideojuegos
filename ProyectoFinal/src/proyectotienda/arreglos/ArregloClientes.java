package proyectotienda.arreglos;

import proyectotienda.hijas.Cliente;

import java.io.*;
import java.util.ArrayList;

public class ArregloClientes {

    private ArrayList<Cliente> clientes;

    public ArregloClientes() {
        clientes = new ArrayList<>();
        cargarCliente();
        establecerCorrelativo();
    }

    public int tamanio() {
        return clientes.size();
    }

    public void agregar(Cliente cliente) {
        clientes.add(cliente);
        grabarCliente();
        establecerCorrelativo();
    }

    public Cliente obtener(int posicion) {
        return clientes.get(posicion);
    }

    public void eliminar(Cliente cliente) {
        clientes.remove(cliente);
        grabarCliente();
        establecerCorrelativo();
    }

    public Cliente buscar(int codigo) {
        for(Cliente cliente : clientes)
            if(cliente.getCodigoCliente() == codigo)
                return cliente;

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

            bufferedReader = new BufferedReader(new FileReader("clientes.txt"));

            while((linea = bufferedReader.readLine()) != null) {
                strings = linea.split(";");
                nombres = strings[1].trim();
                apellidos = strings[2].trim();
                telefono =  strings[3].trim();
                dni = strings[4].trim();
                agregar(new Cliente(nombres, apellidos, telefono, dni));
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
        Cliente cliente;
        try {
            printWriter = new PrintWriter(new FileWriter("clientes.txt"));
            for (int i = 0; i < tamanio(); i++) {
                cliente = obtener(i);
                linea = cliente.getCodigoCliente() + ";" + cliente.getNombres() +
                        ";" + cliente.getApellidos() + ";" + cliente.getTelefono() +
                        ";" + cliente.getDni();
                printWriter.println(linea);
            }
            printWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establecerCorrelativo() {
        int maxCodigo = 1000; // Valor predeterminado si no hay clientes
        for (Cliente cliente : clientes) {
            if (cliente.getCodigoCliente() > maxCodigo) {
                maxCodigo = cliente.getCodigoCliente();
            }
        }
        Cliente.establecerCorrelativo(maxCodigo + 1);
    }

}
