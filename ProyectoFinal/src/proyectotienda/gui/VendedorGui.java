package proyectotienda.gui;

import proyectotienda.arreglos.ArregloClientes;
import proyectotienda.arreglos.ArregloVendedores;
import proyectotienda.hijas.Cliente;
import proyectotienda.hijas.Vendedor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class VendedorGui extends JInternalFrame {

    private JPanel contentPane;
    private JTextField txtbuscar;
    private JTextField txtnombres;
    private JTextField txtapellidos;
    private JTextArea txtlista;
    private int contadorVendedores = 2000;
    private ArrayList<String> arregloVendedores2 = new ArrayList<>();
    private JTextField txttelf;
    private JTextField txtdni;
    private JButton btnbuscar;
    
    ArregloVendedores arregloVendedores = new ArregloVendedores();
    
    public VendedorGui() throws IOException {
        setTitle("Mantenimiento vendedores");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Crear el JScrollPane y agregar el JTextArea a él
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 36, 160, 118);
        contentPane.add(scrollPane);

        txtlista = new JTextArea();
        scrollPane.setViewportView(txtlista);

        JLabel lblNewLabel = new JLabel("Lista de vendedores:");
        lblNewLabel.setBounds(20, 11, 122, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Buscar por:");
        lblNewLabel_1.setBounds(218, 14, 70, 14);
        contentPane.add(lblNewLabel_1);

        JComboBox cbtipoBusq = new JComboBox();
        cbtipoBusq.setModel(new DefaultComboBoxModel(new String[]{"Cód. Vendedor", "Nombre", "Apellido", "DNI"}));
        cbtipoBusq.setBounds(298, 10, 111, 22);
        contentPane.add(cbtipoBusq);

        txtbuscar = new JTextField();
        txtbuscar.setBounds(218, 43, 111, 20);
        contentPane.add(txtbuscar);
        txtbuscar.setColumns(10);

        JScrollPane scrollPaneResulBusq = new JScrollPane();
        scrollPaneResulBusq.setBounds(218, 99, 179, 67);
        contentPane.add(scrollPaneResulBusq);

        JTextArea txtResulBusq = new JTextArea();
        scrollPaneResulBusq.setViewportView(txtResulBusq);

        btnbuscar = new JButton("Buscar");
        btnbuscar.setBounds(327, 41, 89, 23);
        contentPane.add(btnbuscar);

        btnbuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener la opción seleccionada en cbtipoBusq
                String opcionBusqueda = (String) cbtipoBusq.getSelectedItem();
                String textoBusqueda = txtbuscar.getText().trim();

                // Limpiar el área de resultados de búsqueda
                txtResulBusq.setText("");

                if (opcionBusqueda.equals("Cód. Vendedor")) {
                    // Buscar por número de vendedor
                    String resultado = null;
                    for (String dato : arregloVendedores2) {
                        String[] partes = dato.split(" ");
                        if (partes.length >= 1 && partes[0].equals(textoBusqueda)) {
                            resultado = dato;
                        }
                    }
                    if (resultado != null) {
                        txtResulBusq.append(resultado + "\n");
                    }
                } else if (opcionBusqueda.equals("Nombre")) {
                    // Buscar por nombre
                    for (String dato : arregloVendedores2) {
                        String[] partes = dato.split(" ");
                        if (partes.length >= 2 && partes[1].equals(textoBusqueda)) {
                            txtResulBusq.append(dato + "\n");
                        }
                    }
                } else if (opcionBusqueda.equals("Apellido")) {
                    // Buscar por apellido
                    for (String dato : arregloVendedores2) {
                        String[] partes = dato.split(" ");
                        if (partes.length >= 3 && partes[2].equals(textoBusqueda)) {
                            txtResulBusq.append(dato + "\n");
                        }
                    }
                } else if (opcionBusqueda.equals("DNI")) {
                    // Buscar por DNI
                    for (String dato : arregloVendedores2) {
                        if (dato.contains(textoBusqueda)) {
                            txtResulBusq.append(dato + "\n");
                        }
                    }
                }
            }
        });

        JButton btnagregar = new JButton("Agregar");
        btnagregar.setBounds(209, 217, 89, 23);
        contentPane.add(btnagregar);

        btnagregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });

        JButton btnmodificar = new JButton("Modificar");
        btnmodificar.setBounds(308, 217, 89, 23);
        contentPane.add(btnmodificar);

        btnmodificar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        });

        JButton btneliminar = new JButton("Eliminar");
        btneliminar.setBounds(308, 181, 89, 23);
        contentPane.add(btneliminar);

        JButton btnconsultar = new JButton("Consultar");
        btnconsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });
        btnconsultar.setBounds(209, 181, 89, 23);
        contentPane.add(btnconsultar);

        JLabel lblNewLabel_2 = new JLabel("Resultados de búsqueda:");
        lblNewLabel_2.setBounds(218, 74, 152, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Nombres:");
        lblNewLabel_3.setBounds(20, 168, 63, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Apellidos:");
        lblNewLabel_4.setBounds(20, 196, 63, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_6 = new JLabel("Telf:");
        lblNewLabel_6.setBounds(20, 221, 46, 14);
        contentPane.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("DNI:");
        lblNewLabel_7.setBounds(20, 245, 46, 14);
        contentPane.add(lblNewLabel_7);

        txtnombres = new JTextField();
        txtnombres.setBounds(82, 165, 99, 20);
        contentPane.add(txtnombres);
        txtnombres.setColumns(10);

        txtapellidos = new JTextField();
        txtapellidos.setBounds(82, 193, 98, 20);
        contentPane.add(txtapellidos);
        txtapellidos.setColumns(10);

        txttelf = new JTextField();
        txttelf.setBounds(81, 218, 99, 20);
        contentPane.add(txttelf);
        txttelf.setColumns(10);

        txtdni = new JTextField();
        txtdni.setBounds(81, 242, 99, 20);
        contentPane.add(txtdni);

        cargarVendedoresDesdeArchivo("vendedores.txt");

        for (String vendedorInfo : arregloVendedores2) {
            txtlista.append(vendedorInfo + "\n");
        }
    }

    public void agregar() {
        String nombres = txtnombres.getText();
        String apellidos = txtapellidos.getText();
        String telefono = txttelf.getText();
        String dni = txtdni.getText();

        if (nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Por favor, complete todos los campos antes de agregar.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
			Vendedor vendedor = new Vendedor(nombres, apellidos, telefono, dni);
            arregloVendedores.agregar(vendedor);
            limpieza();
        }
    }
    public void modificar() {
    	int codigoVendedor = txtnombres.getText();
        String nombres = txtnombres.getText();
        String apellidos = txtapellidos.getText();
        String telf = txttelf.getText();
        String dni = txtdni.getText();

        if (!nombres.isEmpty() && !apellidos.isEmpty() && !telf.isEmpty() && !dni.isEmpty()) {
            contadorVendedores++;
            String vendedorInfo = contadorVendedores + " " + nombres + " " + apellidos + " - " + telf + " - " + dni;
            txtlista.append(vendedorInfo + "\n");
            arregloVendedores2.add(vendedorInfo);
            limpieza();

            guardarVendedoresEnArchivo("vendedores.txt");
        } else {
            JOptionPane.showMessageDialog(contentPane, "Por favor, complete todos los campos antes de agregar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void consultar() {
        txtlista.setText("");
        for (String dato : arregloVendedores2) {
            txtlista.append(dato + "\n");
        }
    }

    private void cargarVendedoresDesdeArchivo(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                arregloVendedores2.add(linea);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarVendedoresEnArchivo(String archivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            for (String vendedorInfo : arregloVendedores2) {
                bw.write(vendedorInfo);
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void limpieza() {
        txtnombres.setText("");
        txtapellidos.setText("");
        txttelf.setText("");
        txtdni.setText("");
    }
}



