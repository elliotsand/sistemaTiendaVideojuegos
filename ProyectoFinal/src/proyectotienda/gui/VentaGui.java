package proyectotienda.gui;

import proyectotienda.arreglos.*;
import proyectotienda.clases.Factura;
import proyectotienda.clases.Producto;
import proyectotienda.clases.Venta;
import proyectotienda.hijas.Cliente;
import proyectotienda.hijas.Vendedor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentaGui extends JInternalFrame implements ActionListener {
    private JPanel contentPane;
    private JLabel lblCodigoCliente, lblCodigoVendedor, lblCodigoProducto, lblUnidades;
    public JTextField txtCodigoCliente, txtCodigoVendedor, txtCodigoProducto, txtUnidades;
    private JButton btnVender;

    ArregloClientes arregloClientes = new ArregloClientes();
    ArregloVendedores arregloVendedores = new ArregloVendedores();
    ArregloProductos arregloProductos = new ArregloProductos();
    ArregloVentas arregloVentas = new ArregloVentas();
    ArregloFacturas arregloFacturas = new ArregloFacturas();

    public VentaGui() {

        setTitle("Ventas");
        setBounds(100, 100, 450, 174);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCodigoCliente = new JLabel("Codigo cliente");
        lblCodigoCliente.setBounds(10, 36, 90, 14);
        contentPane.add(lblCodigoCliente);

        lblCodigoVendedor = new JLabel("Codigo vendedor");
        lblCodigoVendedor.setBounds(10, 61, 100, 14);
        contentPane.add(lblCodigoVendedor);

        lblCodigoProducto = new JLabel("Codigo producto");
        lblCodigoProducto.setBounds(10, 86, 100, 14);
        contentPane.add(lblCodigoProducto);

        lblUnidades = new JLabel("Unidades");
        lblUnidades.setBounds(10, 111, 90, 14);
        contentPane.add(lblUnidades);

        txtCodigoCliente = new JTextField();
        txtCodigoCliente.setBounds(120, 33, 180, 20);
        contentPane.add(txtCodigoCliente);
        txtCodigoCliente.setColumns(10);

        txtCodigoVendedor = new JTextField();
        txtCodigoVendedor.setBounds(120, 58, 180, 20);
        contentPane.add(txtCodigoVendedor);
        txtCodigoVendedor.setColumns(10);

        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(120, 83, 180, 20);
        contentPane.add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);

        txtUnidades = new JTextField();
        txtUnidades.setBounds(120, 108, 180, 20);
        contentPane.add(txtUnidades);
        txtUnidades.setColumns(10);

        btnVender = new JButton("Vender");
        btnVender.addActionListener(this);
        btnVender.setBounds(335, 7, 89, 23);
        contentPane.add(btnVender);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnVender) {
            actionPerformedVender(e);
        }
    }

    public void actionPerformedVender(ActionEvent e) {

        int codCliente = leerCodigoCliente();
        int codVendedor = leerCodigoVendedor();
        int codProductos = leerCodigoProducto();
        int unidades = leerUnidades();

        Cliente cliente = arregloClientes.buscar(codCliente);
        Vendedor vendedor = arregloVendedores.buscar(codVendedor);
        Producto producto = arregloProductos.buscar(codProductos);

        if (cliente != null){
            if (vendedor != null){
                if (producto != null){
                    mensaje("venta exitosa");
                    double precioUnitario = producto.getPrecio();
                    double importeSubtotal = precioUnitario * unidades;
                    double importeIGV = 0.18 * importeSubtotal;
                    double importeTotal = importeSubtotal + importeIGV;

                    String mensaje = "Código del cliente: " + cliente.getCodigoCliente() + "\n" +
                            "Código del vendedor: " + 2001 + "\n" +
                            "Código del producto: " + producto.getCodigoProducto() + "\n" +
                            "Descripción del producto: " + producto.getDescripcion() + "\n" +
                            "Precio unitario: " + precioUnitario + "\n" +
                            "Importe subtotal: " + importeSubtotal + "\n" +
                            "Importe del IGV: " + importeIGV + "\n" +
                            "Importe total a pagar: " + importeTotal;

                    mensaje(mensaje);
                    Venta venta = new Venta(vendedor.getCodigoVendedor(), codCliente,codVendedor,codProductos,unidades, importeTotal);
                    Factura factura = new Factura(codProductos,codVendedor, producto.getPrecio(),unidades);
                    arregloVentas.agregar(venta);
                    arregloFacturas.agregar(factura);
                    limpieza();

                }else {
                    mensaje("producto no registrado");
                }
            }else{
                mensaje("vendedor no registrado");
            }
        }else {
            mensaje("cliente no registrado");
        }

    }

    void limpieza() {
        txtCodigoCliente.setText("");
        txtCodigoVendedor.setText("");
        txtCodigoProducto.setText("");
        txtUnidades.setText("");
        txtCodigoCliente.requestFocus();
    }

    int leerCodigoCliente() {
        return Integer.parseInt(txtCodigoCliente.getText().trim());
    }

    int leerCodigoVendedor() {
        return Integer.parseInt(txtCodigoVendedor.getText().trim());
    }

    int leerCodigoProducto() {
        return Integer.parseInt(txtCodigoProducto.getText().trim());
    }

    int leerUnidades() {
        return Integer.parseInt(txtUnidades.getText().trim());
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
