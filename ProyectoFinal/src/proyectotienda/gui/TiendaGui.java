package proyectotienda.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class TiendaGui extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private JMenuItem itemSalir, itemClientes, itemProductos, mntmNewMenuItem;
    private ImageIcon imagen;
    private JLabel etiqueta1;

    public TiendaGui() {
        setTitle("Video Games Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 491, 22);
        contentPane.add(menuBar);

        JMenu mnNewMenu = new JMenu("Inicio");
        menuBar.add(mnNewMenu);

        itemSalir = new JMenuItem("Salir");
        mnNewMenu.add(itemSalir);
        itemSalir.addActionListener(this);

        mntmNewMenuItem = new JMenuItem("Nosotros");
        mnNewMenu.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(this);

        JMenu mnNewMenu_1 = new JMenu("Mantenimiento");
        menuBar.add(mnNewMenu_1);

        itemClientes = new JMenuItem("Clientes");
        itemClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClienteGui clienteGui = new ClienteGui();
                int x = (desktopPane.getWidth() - clienteGui.getWidth()) / 2;
                int y = (desktopPane.getHeight() - clienteGui.getHeight()) / 2;

                desktopPane.add(clienteGui);
                clienteGui.setVisible(true);
                clienteGui.setLocation(x, y);
            }
        });
        mnNewMenu_1.add(itemClientes);

        itemProductos = new JMenuItem("Productos");
        itemProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductoGUI productoGui = new ProductoGUI();
                int x = (desktopPane.getWidth() - productoGui.getWidth()) / 2;
                int y = (desktopPane.getHeight() - productoGui.getHeight()) / 2;

                desktopPane.add(productoGui);
                productoGui.setVisible(true);
                productoGui.setLocation(x, y);
            }
        });
        mnNewMenu_1.add(itemProductos);

        JMenu mnNewMenu_2 = new JMenu("Ventas");
        menuBar.add(mnNewMenu_2);

        JMenu mnNewMenu_3 = new JMenu("Reporte");
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Vendedores");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VendedorGui vendedorGui = null;
                try {
                    vendedorGui = new VendedorGui();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                int x = (desktopPane.getWidth() - vendedorGui.getWidth()) / 2;
                int y = (desktopPane.getHeight() - vendedorGui.getHeight()) / 2;

                desktopPane.add(vendedorGui);
                vendedorGui.setVisible(true);
                vendedorGui.setLocation(x, y);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ventas");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentaGui ventaGui = new VentaGui();

                int x = (desktopPane.getWidth() - ventaGui.getWidth()) / 2;
                int y = (desktopPane.getHeight() - ventaGui.getHeight()) / 2;

                ventaGui.setLocation(x, y);
                desktopPane.add(ventaGui);
                ventaGui.setVisible(true);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Facturas");
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FacturaGUI facturaGUI = new FacturaGUI();

                int x = (desktopPane.getWidth() - facturaGUI.getWidth()) / 2;
                int y = (desktopPane.getHeight() - facturaGUI.getHeight()) / 2;

                facturaGUI.setLocation(x, y);
                desktopPane.add(facturaGUI);
                facturaGUI.setVisible(true);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_51 = new JMenuItem("Reportes");
        mntmNewMenuItem_51.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportesGui reportesGui = new ReportesGui();

                int x = (desktopPane.getWidth() - reportesGui.getWidth()) / 2;
                int y = (desktopPane.getHeight() - reportesGui.getHeight()) / 2;

                reportesGui.setLocation(x, y);
                desktopPane.add(reportesGui);
                reportesGui.setVisible(true);
            }
        });
        mnNewMenu_3.add(mntmNewMenuItem_51);

        desktopPane = new JDesktopPane();
        desktopPane.setBounds(10, 25, 760, 530);
        contentPane.add(desktopPane);

        imagen = new ImageIcon("ProyectoFinal/img/portada1.jpg");
        etiqueta1 = new JLabel();
        etiqueta1.setBounds(0, 0, 760, 530);
        etiqueta1.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta1.getWidth(), etiqueta1.getHeight(), Image.SCALE_SMOOTH)));

        desktopPane.add(etiqueta1);
        desktopPane.moveToFront(etiqueta1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemSalir) {
            actionPerformedSalir(e);
        }
        if (e.getSource() == mntmNewMenuItem) {
            actionPerformedAcercaDeTienda(e);
        }
    }

    public void actionPerformedSalir(ActionEvent e) {
        System.exit(0);
    }

    public void actionPerformedAcercaDeTienda(ActionEvent e) {
        NosotrosGui nosotrosGui = new NosotrosGui();
        nosotrosGui.setVisible(true);
    }
}
