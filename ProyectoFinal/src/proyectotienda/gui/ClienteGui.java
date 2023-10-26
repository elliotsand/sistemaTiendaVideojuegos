package proyectotienda.gui;

import proyectotienda.arreglos.ArregloClientes;
import proyectotienda.hijas.Cliente;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class ClienteGui extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel lblCodigoCliente;
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblTelefono;
    private JLabel lblDni;
    private JLabel lblCilindrada;
    private JLabel lblTraccion;
    private JTextField txtCodigoCliente;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtDni;
    private JTextField txtCilindrada;
    private JTextField txtTraccion;
    private JButton btnAdicionar;
    private JButton btnConsultar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JScrollPane scrollPane;
    private JTable tblTabla;
    private DefaultTableModel modelo;
    private JComboBox<String> tipoVehiculoComboBox;

    ArregloClientes arregloClientes = new ArregloClientes();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClienteGui frame = new ClienteGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClienteGui() {
        setTitle("Mantenimiento cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 730, 420);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCodigoCliente = new JLabel("Cod. Cliente:");
        lblCodigoCliente.setBounds(10, 11, 70, 28);
        contentPane.add(lblCodigoCliente);

        txtCodigoCliente = new JTextField();
        txtCodigoCliente.setBounds(85, 11, 40, 28);
        contentPane.add(txtCodigoCliente);
        txtCodigoCliente.setColumns(10);

        lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(140, 11, 90, 28);
        contentPane.add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(200, 11, 80, 28);
        contentPane.add(txtNombres);
        txtNombres.setColumns(10);

        lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(290, 11, 70, 28);
        contentPane.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(350, 11, 80, 28);
        contentPane.add(txtApellidos);
        txtApellidos.setColumns(10);

        lblTelefono = new JLabel("Telefono:");
        lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTelefono.setBounds(420, 11, 80, 28);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(510, 11, 70, 28);
        contentPane.add(txtTelefono);
        txtTelefono.setColumns(10);

        lblDni = new JLabel("Dni:");
        lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDni.setBounds(565, 11, 50, 28);
        contentPane.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(630, 11, 70, 28);
        contentPane.add(txtDni);
        txtDni.setColumns(10);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(580, 90, 120, 23);
        contentPane.add(btnAdicionar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);
        btnConsultar.setBounds(580, 120, 120, 23);
        contentPane.add(btnConsultar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.setBounds(580, 150, 120, 23);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(580, 180, 120, 23);
        contentPane.add(btnEliminar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 560, 274);
        contentPane.add(scrollPane);

        tblTabla = new JTable();
        tblTabla.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblTabla);

        modelo = new DefaultTableModel();
        modelo.addColumn("Cód. Cliente");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Telefono");
        modelo.addColumn("Dni");
        tblTabla.setModel(modelo);

        listar();
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == btnEliminar) {
            actionPerformedBtnEliminar(arg0);
        }
        if (arg0.getSource() == btnModificar) {
            actionPerformedBtnModificar(arg0);
        }
        if (arg0.getSource() == btnConsultar) {
            actionPerformedBtnConsultar(arg0);
        }
        if (arg0.getSource() == btnAdicionar) {
            actionPerformedBtnAdicionar(arg0);
        }
    }

    protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
        /*int codigoCliente = leerCodigoCliente();
        boolean codigoExistente = false;

        for (int i = 0; i < arregloClientes.tamanio(); i++) {
            if (arregloClientes.obtener(i).getCodigoCliente() == codigoCliente) {
                codigoExistente = true;
                break;
            }
        }
        if (codigoExistente) {
            mensaje("El código ya existe. No se puede adicionar el cliente.");
        } else {
            String nombres = leerNombres();
            String apellidos = leerApellidos();
            String telefono = leerTelefono();
            String dni = leerDni();

            Cliente cliente = new Cliente(nombres, apellidos, telefono, dni);
            arregloClientes.agregar(cliente);
            listar();
            limpieza();
        }*/
        String nombres = leerNombres();
        String apellidos = leerApellidos();
        String telefono = leerTelefono();
        String dni = leerDni();

        Cliente cliente = new Cliente(nombres, apellidos, telefono, dni);
        arregloClientes.agregar(cliente);
        listar();
        limpieza();
    }

    protected void actionPerformedBtnConsultar(ActionEvent arg0) {
        int codigoCliente = leerCodigoCliente();
        Cliente cliente = arregloClientes.buscar(codigoCliente);

        txtNombres.setText(cliente.getNombres());
        txtApellidos.setText(cliente.getApellidos());
        txtTelefono.setText(cliente.getTelefono());
        txtDni.setText(cliente.getDni());
    }

    protected void actionPerformedBtnModificar(ActionEvent arg0) {
        int codigoCliente = leerCodigoCliente();
        String nombres = leerNombres();
        String apellidos = leerApellidos();
        String telefono = leerTelefono();
        String dni = leerDni();

        Cliente cliente = arregloClientes.buscar(codigoCliente);

        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setTelefono(telefono);
        cliente.setDni(dni);

        arregloClientes.actualizar();

        listar();
        limpieza();
    }

    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
        int codigoCliente = leerCodigoCliente();
        Cliente cliente = arregloClientes.buscar(codigoCliente);
        arregloClientes.eliminar(cliente);
        limpieza();
        listar();
    }

    void limpieza() {
        txtCodigoCliente.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtDni.setText("");
        txtCodigoCliente.requestFocus();
    }

    void listar() {
        modelo.setRowCount(0);

        for (int i = 0; i < arregloClientes.tamanio(); i++) {
            Cliente cliente = arregloClientes.obtener(i);
            Object[] fila = {
                    cliente.getCodigoCliente(),
                    cliente.getNombres(),
                    cliente.getApellidos(),
                    cliente.getTelefono(),
                    cliente.getDni()
            };
            modelo.addRow(fila);
        }
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    int leerCodigoCliente() {
        return Integer.parseInt(txtCodigoCliente.getText().trim());
    }

    String leerNombres() {
        return txtNombres.getText().trim();
    }

    String leerApellidos() {
        return txtApellidos.getText().trim();
    }

    String leerTelefono() {
        return txtTelefono.getText().trim();
    }

    String leerDni() {
        return txtDni.getText().trim();
    }


}