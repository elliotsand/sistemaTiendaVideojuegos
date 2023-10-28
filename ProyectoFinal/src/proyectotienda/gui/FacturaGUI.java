package proyectotienda.gui;

import proyectotienda.arreglos.ArregloFacturas;
import proyectotienda.clases.Factura;

import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NavigationFilter.FilterBypass;
import javax.swing.text.PlainDocument;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacturaGUI extends JInternalFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCodigoFactura, lblCodigoProducto, lblCodigoVendedor, lblPrecio, lblUnidades;
    private JTextField txtCodigoFactura, txtCodigoProducto, txtCodigoVendedor, txtPrecio, txtUnidades;
    private JButton btnAdicionar, btnConsultar, btnModificar, btnEliminar;
    private JScrollPane scrollPane;
    private JTable tblTabla;
    private DefaultTableModel modelo;

    ArregloFacturas ArregloFacturas = new ArregloFacturas();

    public FacturaGUI() {
        setTitle("Mantenimiento Facturas");
        setBounds(100, 100, 732, 428);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCodigoFactura = new JLabel("Cod. factura:");
        lblCodigoFactura.setBounds(10, 11, 70, 28);
        contentPane.add(lblCodigoFactura);

        txtCodigoFactura = new JTextField();
        txtCodigoFactura.setBounds(105, 11, 70, 28);
        contentPane.add(txtCodigoFactura);
        txtCodigoFactura.setColumns(10);
        restrictToNumbers(txtCodigoFactura);

        lblCodigoProducto = new JLabel("CodigoProducto:");
        lblCodigoProducto.setBounds(10, 50, 90, 28);
        contentPane.add(lblCodigoProducto);

        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(105, 50, 70, 28);
        contentPane.add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);
        restrictToNumbers(txtCodigoProducto);

        lblCodigoVendedor = new JLabel("CodigoVendedor:");
        lblCodigoVendedor.setBounds(213, 11, 96, 28);
        contentPane.add(lblCodigoVendedor);

        txtCodigoVendedor = new JTextField();
        txtCodigoVendedor.setBounds(319, 11, 111, 28);
        contentPane.add(txtCodigoVendedor);
        txtCodigoVendedor.setColumns(10);
        restrictToNumbers(txtCodigoVendedor);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setBounds(442, 11, 80, 28);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(532, 11, 70, 28);
        contentPane.add(txtPrecio);
        txtPrecio.setColumns(10);
        restrictToNumbers(txtPrecio);

        lblUnidades = new JLabel("Unidades:");
        lblUnidades.setHorizontalAlignment(SwingConstants.RIGHT);
        lblUnidades.setBounds(185, 50, 80, 28);
        contentPane.add(lblUnidades);

        txtUnidades = new JTextField();
        txtUnidades.setBounds(319, 50, 111, 28);
        contentPane.add(txtUnidades);
        txtUnidades.setColumns(10);
        restrictToNumbers(txtUnidades);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(590, 89, 120, 23);
        contentPane.add(btnAdicionar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);
        btnConsultar.setBounds(590, 119, 120, 23);
        contentPane.add(btnConsultar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.setBounds(590, 149, 120, 23);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(590, 179, 120, 23);
        contentPane.add(btnEliminar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 89, 560, 253);
        contentPane.add(scrollPane);

        tblTabla = new JTable();  
        tblTabla.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblTabla);

        modelo = new DefaultTableModel();
        modelo.addColumn("Cód. Factura");
        modelo.addColumn("codigoProducto");
        modelo.addColumn("CodigoVendedor");
        modelo.addColumn("Precio");
        modelo.addColumn("Unidades");
        tblTabla.setModel(modelo); 

        listar();
    }
    
    public static void restrictToNumbers(JTextField textField) {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
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
        int codigoProducto = leerCodigoProducto();
        int codigoVendedor = leerCodigoVendedor();
        double precio = leerPrecio();
        int unidades = leerUnidades();

        Factura factura = new Factura(codigoProducto, codigoVendedor, precio, unidades);
        ArregloFacturas.agregar(factura);
        listar();
        limpieza();
    }

    protected void actionPerformedBtnConsultar(ActionEvent arg0) {
        int codigoFactura = leerCodigoFactura();
        Factura factura = ArregloFacturas.buscar(codigoFactura);

        txtCodigoProducto.setText(String.valueOf(factura.getCodigoProducto()));
        txtCodigoVendedor.setText(String.valueOf(factura.getCodigoVendedor()));
        txtPrecio.setText(String.valueOf(factura.getPrecio()));
    }

    protected void actionPerformedBtnModificar(ActionEvent arg0) {
        int codigoFactura = leerCodigoFactura();
        int codigoProducto = leerCodigoProducto();
        int codigoVendedor = leerCodigoVendedor();
        int unidades = leerUnidades();
        double precio = leerPrecio();

        Factura factura = ArregloFacturas.buscar(codigoFactura);

        factura.setCodigoProducto(codigoProducto);
        factura.setCodigoVendedor(codigoVendedor);
        factura.setUnidades(unidades);
        factura.setPrecio(precio);

        ArregloFacturas.actualizar();

        listar();
        limpieza();
    }

    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
        int codigoFactura = leerCodigoFactura();
        Factura factura = ArregloFacturas.buscar(codigoFactura);
        ArregloFacturas.eliminar(factura);
        limpieza();
        listar();
    }

    void limpieza() {
        txtCodigoFactura.setText("");
        txtCodigoProducto.setText("");
        txtCodigoVendedor.setText("");
        txtPrecio.setText("");
        txtCodigoFactura.requestFocus();
    }

    void listar() {
        modelo.setRowCount(0);

        for (int i = 0; i < ArregloFacturas.tamanio(); i++) {
            Factura factura = ArregloFacturas.obtener(i);
            Object[] fila = {
                    factura.getCodigoFactura(),
                    factura.getCodigoProducto(),
                    factura.getCodigoVendedor(),
                    factura.getPrecio(),
                    factura.getUnidades(),
            };
            modelo.addRow(fila);
        }
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    int leerCodigoFactura() {
        return Integer.parseInt(txtCodigoFactura.getText().trim());
    }

    int leerCodigoProducto() {
        return Integer.parseInt(txtCodigoProducto.getText().trim());
    }

    int leerUnidades() {
        return Integer.parseInt(txtUnidades.getText().trim());
    }

    int leerCodigoVendedor() {
        return Integer.parseInt(txtCodigoVendedor.getText().trim());
    }

    double leerPrecio() {
        String precioText = txtPrecio.getText().trim();
        double precio = Double.parseDouble(precioText);
        return precio;
    }

}