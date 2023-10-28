package proyectotienda.gui;

import proyectotienda.arreglos.ArregloProductos;
import proyectotienda.arreglos.ArregloVendedores;
import proyectotienda.arreglos.ArregloVentas;
import proyectotienda.clases.Producto;
import proyectotienda.clases.Venta;
import proyectotienda.hijas.Vendedor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;

public class ReportesGui extends JInternalFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel lblGenerarReportes;
    private JButton btnCerrar;
    public JComboBox cmbGenerarReportes;
    private JScrollPane scrollPane;
    private JTextArea txtAreaResultado;

    ArregloVendedores arregloVendedores = new ArregloVendedores();
    ArregloProductos arregloProductos = new ArregloProductos();
    ArregloVentas arregloVentas = new ArregloVentas();

    public ReportesGui() {
        setTitle("Generar Reportes");
        setBounds(100, 100, 490, 300);
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblGenerarReportes = new JLabel("Generar Reportes");
        lblGenerarReportes.setBounds(10, 15, 108, 14);
        contentPane.add(lblGenerarReportes);

        cmbGenerarReportes = new JComboBox();
        cmbGenerarReportes.setFont(new Font("Tahoma", Font.PLAIN, 10));
        cmbGenerarReportes.addActionListener(this);
        cmbGenerarReportes.setModel(new DefaultComboBoxModel(new String[]{"Reporte general por productos", "Reporte general por vendedores", "Reporte por vendedor", "Reporte por producto", "Reporte de precios"}));
        cmbGenerarReportes.setBounds(128, 11, 237, 22);
        contentPane.add(cmbGenerarReportes);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 454, 210);
        contentPane.add(scrollPane);

        txtAreaResultado = new JTextArea();
        txtAreaResultado.setText("");
        scrollPane.setViewportView(txtAreaResultado);
        txtAreaResultado.setEditable(false);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbGenerarReportes) {
            actionPerformedGenerar(e);
        }

    }

    public void actionPerformedGenerar(ActionEvent e) {
        txtAreaResultado.setText("");

        int indicegenerar, contadorsupera = 0, contadorigual = 0, contadormenor = 0;

        indicegenerar = cmbGenerarReportes.getSelectedIndex();

        if (indicegenerar == 0) {
            generarReporteGeneralPorProductos();
        }

        if (indicegenerar == 1) {

        }

        if (indicegenerar == 2) {

        }

        if (indicegenerar == 3) {
        	
        } 
        if (indicegenerar == 4) {
        	generarReportePrecios();
        }
    }

    public void generarReporteGeneralPorProductos() {
        txtAreaResultado.setText("REPORTE GENERAL POR PRODUCTOS\n");

        for (int i = 0; i < arregloProductos.tamanio(); i++) {
            Producto producto = arregloProductos.obtener(i);
            int codigoProducto = producto.getCodigoProducto();
            String descripcion = producto.getDescripcion();
            int numeroVentas = arregloVentas.obtenerNumeroVentasPorProducto(codigoProducto);
            int unidadesVendidas = arregloVentas.calcularUnidadesVendidasPorProducto(codigoProducto);
            double importeTotal = arregloVentas.calcularImporteTotalPorProducto(codigoProducto);

            txtAreaResultado.append("Código del producto: " + codigoProducto + "\n");
            txtAreaResultado.append("Descripción del producto: " + descripcion + "\n");
            txtAreaResultado.append("Número de ventas efectuadas: " + numeroVentas + "\n");
            txtAreaResultado.append("Unidades vendidas acumuladas: " + unidadesVendidas + "\n");
            txtAreaResultado.append("Importe total acumulado: S/." + importeTotal + "\n\n");
        }
    }


    public void generarReporteGeneralPorVendedores() {
        txtAreaResultado.setText("REPORTE GENERAL POR VENDEDORES\n");

        for (int i = 0; i < arregloVendedores.tamanio(); i++) {
            /*int codigoVendedor = arregloVendedores.getCodigoVendedor();
            int numeroVentas = arregloVendedores.obtenerNumeroVentasPorVendedor(codigoVendedor);
            int unidadesVendidas = arregloVendedores.calcularUnidadesVendidasPorVendedor(codigoVendedor);
            double importeTotal = arregloVendedores.calcularImporteTotalPorVendedor(codigoVendedor);

            txtAreaResultado.append("Código del vendedor: " + codigoVendedor + "\n");
            txtAreaResultado.append("Número de ventas efectuadas: " + numeroVentas + "\n");
            txtAreaResultado.append("Unidades vendidas acumuladas: " + unidadesVendidas + "\n");
            txtAreaResultado.append("Importe total acumulado: S/." + importeTotal + "\n\n");*/
        }
    }


    public void generarReportePorVendedor(int codigoVendedor) {
        txtAreaResultado.setText("REPORTE POR VENDEDOR\n");

        for (int i = 0; i < arregloVendedores.tamanio(); i++) {
            /*int codigoVenta = venta.getCodigoVenta();
            int codigoProducto = venta.getCodigoProducto();
            int unidadesVendidas = venta.getUnidadesVendidas();
            double precioUnitario = obtenerPrecioPorCodigoProducto(codigoProducto);

            txtAreaResultado.append("Código de venta: " + codigoVenta + "\n");
            txtAreaResultado.append("Código de producto: " + codigoProducto + "\n");
            txtAreaResultado.append("Unidades vendidas: " + unidadesVendidas + "\n");
            txtAreaResultado.append("Precio unitario: S/." + precioUnitario + "\n\n");*/
        }
    }

    public void generarReportePorProducto(int codigoProducto) {
        txtAreaResultado.setText("REPORTE POR PRODUCTO\n");

       /* for (Venta venta : arregloVentas.getVentasPorProducto(codigoProducto)) {
            int codigoVenta = venta.getCodigoVenta();
            int codigoVendedor = venta.getCodigoVendedor();
            int unidadesVendidas = venta.getUnidadesVendidas();
            double precioUnitario = obtenerPrecioPorCodigoProducto(codigoProducto);

            txtAreaResultado.append("Código de venta: " + codigoVenta + "\n");
            txtAreaResultado.append("Código de vendedor: " + codigoVendedor + "\n");
            txtAreaResultado.append("Unidades vendidas: " + unidadesVendidas + "\n");
            txtAreaResultado.append("Precio unitario: S/." + precioUnitario + "\n\n");*/
    }



    public void generarReportePrecios() {
        double precioPromedio = arregloProductos.calcularPrecioPromedio();
        double precioMasAlto = arregloProductos.encontrarPrecioMasAlto();
        double precioMasBajo = arregloProductos.encontrarPrecioMasBajo();

        txtAreaResultado.append("Reporte de Precios:");
        txtAreaResultado.append("Precio Promedio: " + precioPromedio+ "\n");
        txtAreaResultado.append("Precio Más Alto: " + precioMasAlto+ "\n");
        txtAreaResultado.append("Precio Más Bajo: " + precioMasBajo+ "\n");
    }


}
