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
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class Tienda extends JFrame {

    private JPanel contentPane;
    private JDesktopPane desktopPane;

    public Tienda() {
        setTitle("Video Games Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 507, 382);
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
                
                // Calcula la ubicación para centrar la ventana interna en el escritorio
                int x = (desktopPane.getWidth() - ventanadevendedor.getWidth()) / 2;
                int y = (desktopPane.getHeight() - ventanadevendedor.getHeight()) / 2;
                
                ventanadevendedor.setLocation(x, y);  // Establece la ubicación centrada
                desktopPane.add(ventanadevendedor);
                ventanadevendedor.setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_1);

        // Otros elementos de menú

        desktopPane = new JDesktopPane();
        desktopPane.setBounds(0, 21, 491, 322);
        contentPane.add(desktopPane);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tienda frame = new Tienda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
