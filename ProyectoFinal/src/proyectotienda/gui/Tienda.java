package proyectotienda.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proyectotienda.hijas.Vendedor;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tienda extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private JMenuItem itemSalir, itemClientes;

    public Tienda() {
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

        JMenuItem mntmNewMenuItem = new JMenuItem("Nosotros");
        mnNewMenu.add(mntmNewMenuItem);

        JMenu mnNewMenu_1 = new JMenu("Mantenimiento");
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_2 = new JMenu("Ventas");
        menuBar.add(mnNewMenu_2);

        JMenu mnNewMenu_4 = new JMenu("Delivery");
        menuBar.add(mnNewMenu_4);

        JMenu mnNewMenu_3 = new JMenu("Reporte");
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Vendedores");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Vendedor ventanadevendedor = new Vendedor();

                int x = (desktopPane.getWidth() - ventanadevendedor.getWidth()) / 2;
                int y = (desktopPane.getHeight() - ventanadevendedor.getHeight()) / 2;

                ventanadevendedor.setLocation(x, y);
                desktopPane.add(ventanadevendedor);
                ventanadevendedor.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Clientes");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClienteGui clienteGui = new ClienteGui();
                int x = (desktopPane.getWidth() - clienteGui.getWidth()) / 2;
                int y = (desktopPane.getHeight() - clienteGui.getHeight()) / 2;

                clienteGui.setLocation(x, y);
                desktopPane.add(clienteGui);
                clienteGui.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_2);

        desktopPane = new JDesktopPane();
        desktopPane.setBounds(10, 25, 760, 530);
        contentPane.add(desktopPane);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemSalir) {
            actionPerformedSalir(e);
        }
    }

    public void actionPerformedSalir(ActionEvent e) {
        System.exit(0);
    }
}
