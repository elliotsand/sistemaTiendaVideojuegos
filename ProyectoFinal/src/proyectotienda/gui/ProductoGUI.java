package proyectotienda.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoGUI extends JFrame {

    private JTextField codigoProductoField;
    private JTextField descripcionField;
    private JTextField precioField;
    private JButton guardarButton;

    public ProductoGUI() {
       
        setTitle("Ingreso de Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

         
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel codigoLabel = new JLabel("Código Producto:");
        codigoProductoField = new JTextField();
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionField = new JTextField();
        JLabel precioLabel = new JLabel("Precio (sin IGV):");
        precioField = new JTextField();
        guardarButton = new JButton("Guardar");

        panel.add(codigoLabel);
        panel.add(codigoProductoField);
        panel.add(descripcionLabel);
        panel.add(descripcionField);
        panel.add(precioLabel);
        panel.add(precioField);
        panel.add(new JLabel());  
        panel.add(guardarButton);

         
        getContentPane().add(panel);

        
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int codigoProducto = Integer.parseInt(codigoProductoField.getText());
                String descripcion = descripcionField.getText();
                double precio = Double.parseDouble(precioField.getText());

               
                // Producto producto = new Producto(codigoProducto, descripcion, precio);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProductoGUI productoGUI = new ProductoGUI();
                productoGUI.setVisible(true);
            }
        });
    }
}




