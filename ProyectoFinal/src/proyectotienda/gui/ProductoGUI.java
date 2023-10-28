package proyectotienda.gui;

import proyectotienda.arreglos.ArregloProductos;
import proyectotienda.clases.Producto
;

import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ProductoGUI extends JInternalFrame implements ActionListener {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel lblCodigoProducto, lblNombre, lblDescripcion, lblPrecio;
    private JTextField txtCodigoProducto, txtNombre, txtDescripcion, txtPrecio, txtDni;
    private JButton btnAdicionar, btnConsultar, btnModificar, btnEliminar;
    private JScrollPane scrollPane;
    private JTable tblTabla;
    private DefaultTableModel modelo;

    ArregloProductos ArregloProductos = new ArregloProductos();

    public ProductoGUI() {
        setTitle("Mantenimiento Productos");
        setBounds(100, 100, 730, 300);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCodigoProducto = new JLabel("Cod. producto:");
        lblCodigoProducto.setBounds(5, 11, 90, 28);
        contentPane.add(lblCodigoProducto);

        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(90, 11, 40, 28);
        contentPane.add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(140, 11, 90, 28);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(200, 11, 80, 28);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(290, 11, 70, 28);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(350, 11, 80, 28);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPrecio.setBounds(420, 11, 80, 28);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(510, 11, 70, 28);
        contentPane.add(txtPrecio);
        txtPrecio.setColumns(10);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        btnAdicionar.setBounds(580, 55, 120, 23);
        contentPane.add(btnAdicionar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);
        btnConsultar.setBounds(580, 85, 120, 23);
        contentPane.add(btnConsultar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.setBounds(580, 115, 120, 23);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.setBounds(580, 145, 120, 23);
        contentPane.add(btnEliminar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 55, 560, 200);
        contentPane.add(scrollPane);

        tblTabla = new JTable();
        tblTabla.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblTabla);

        modelo = new DefaultTableModel();
        modelo.addColumn("Cód. Producto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
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
        String nombre = leerNombre();
        String descripcion = leerDescripcion();
        double precio = leerPrecio();

        Producto producto = new Producto(nombre, descripcion, precio);
        ArregloProductos.agregar(producto);
        listar();
        limpieza();
    }

    protected void actionPerformedBtnConsultar(ActionEvent arg0) {
    	int codigoProducto = leerCodigoProducto();
        Producto producto = ArregloProductos.buscar(codigoProducto);

        txtNombre.setText(producto.getNombre());
        txtDescripcion.setText(producto.getDescripcion());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
    }

    protected void actionPerformedBtnModificar(ActionEvent arg0) {
        int codigoProducto = leerCodigoProducto();
        String nombre = leerNombre();
        String descripcion = leerDescripcion();
        double precio = leerPrecio();

        Producto producto = ArregloProductos.buscar(codigoProducto);

        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);

        ArregloProductos.actualizar();

        listar();
        limpieza();
    }

    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
        int codigoProducto = leerCodigoProducto();
        Producto producto = ArregloProductos.buscar(codigoProducto);
        ArregloProductos.eliminar(producto);
        limpieza();
        listar();
    }

    void limpieza() {
        txtCodigoProducto.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCodigoProducto.requestFocus();
    }

    void listar() {
        modelo.setRowCount(0);

        for (int i = 0; i < ArregloProductos.tamanio(); i++) {
            Producto producto = ArregloProductos.obtener(i);
            Object[] fila = {
                    producto.getCodigoProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
            };
            modelo.addRow(fila);
        }
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    int leerCodigoProducto() {
        return Integer.parseInt(txtCodigoProducto.getText().trim());
    }

    String leerNombre() {
        return txtNombre.getText().trim();
    }

    String leerDescripcion() {
        return txtDescripcion.getText().trim();
    }

    double leerPrecio() {
    	String precioText = txtPrecio.getText().trim();
        double precio = Double.parseDouble(precioText);
        return precio;
    }


}