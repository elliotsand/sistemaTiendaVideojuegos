package proyectotienda.hijas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;

public class Vendedor extends JInternalFrame {

    private JPanel contentPane;
    private JTextField txtbuscar;
    private JTextField txtnombres;
    private JTextField txtapellidos;
    private JTextField txtcodigos;
    private JTextArea txtlista;
    private int contadorVendedores = 0;
    private ArrayList<String> datosAgregados = new ArrayList<>();

    public Vendedor() {
        setTitle("Vendedores");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtlista = new JTextArea();
        txtlista.setBounds(20, 36, 160, 130);
        contentPane.add(txtlista);

        JLabel lblNewLabel = new JLabel("Lista de vendedores:");
        lblNewLabel.setBounds(20, 11, 122, 14);
        contentPane.add(lblNewLabel);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(182, 36, -161, 130);
        contentPane.add(scrollBar);

        JLabel lblNewLabel_1 = new JLabel("Buscar por:");
        lblNewLabel_1.setBounds(218, 14, 70, 14);
        contentPane.add(lblNewLabel_1);

        JComboBox cbtipoBusq = new JComboBox();
        cbtipoBusq.setModel(new DefaultComboBoxModel(new String[]{"Nombre", "Apellido", "Código"}));
        cbtipoBusq.setBounds(298, 10, 99, 22);
        contentPane.add(cbtipoBusq);

        txtbuscar = new JTextField();
        txtbuscar.setBounds(218, 43, 105, 20);
        contentPane.add(txtbuscar);
        txtbuscar.setColumns(10);

        JTextArea txtResulBusq = new JTextArea();
        txtResulBusq.setBounds(218, 99, 179, 67);
        contentPane.add(txtResulBusq);

        JScrollBar scrollBar_1 = new JScrollBar();
        scrollBar_1.setBounds(396, 108, -178, 67);
        contentPane.add(scrollBar_1);

        JButton btnbuscar = new JButton("Buscar");
        btnbuscar.setBounds(322, 42, 75, 21);
        contentPane.add(btnbuscar);

        JButton btnagregar = new JButton("Agregar");
        btnagregar.setBounds(215, 223, 89, 23);
        contentPane.add(btnagregar);

        JButton btnmodificar = new JButton("Modificar");
        btnmodificar.setBounds(320, 223, 89, 23);
        contentPane.add(btnmodificar);

        JButton btneliminar = new JButton("Eliminar");
        btneliminar.setBounds(320, 192, 89, 23);
        contentPane.add(btneliminar);

        JLabel lblNewLabel_2 = new JLabel("Resultados de búsqueda:");
        lblNewLabel_2.setBounds(218, 74, 152, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Nombres:");
        lblNewLabel_3.setBounds(20, 180, 63, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Apellidos:");
        lblNewLabel_4.setBounds(20, 207, 63, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Código:");
        lblNewLabel_5.setBounds(20, 233, 63, 14);
        contentPane.add(lblNewLabel_5);

        txtnombres = new JTextField();
        txtnombres.setBounds(81, 177, 99, 20);
        contentPane.add(txtnombres);
        txtnombres.setColumns(10);

        txtapellidos = new JTextField();
        txtapellidos.setBounds(81, 204, 98, 20);
        contentPane.add(txtapellidos);
        txtapellidos.setColumns(10);

        txtcodigos = new JTextField();
        txtcodigos.setBounds(81, 230, 99, 20);
        contentPane.add(txtcodigos);
        txtcodigos.setColumns(10);

        JButton btnconsultar = new JButton("Consultar");
        btnconsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Limpiamos el contenido de txtlista
                txtlista.setText("");

                // Mostramos los datos almacenados
                for (String dato : datosAgregados) {
                    txtlista.append(dato + "\n");
                }
            }
        });
        btnconsultar.setBounds(215, 192, 89, 23);
        contentPane.add(btnconsultar);

        // Agregamos un ActionListener al botón "Agregar"
        btnagregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados en los campos de texto
                String nombres = txtnombres.getText();
                String apellidos = txtapellidos.getText();
                String codigo = txtcodigos.getText();

                // Verificar que los campos no estén vacíos antes de agregar
                if (!nombres.isEmpty() && !apellidos.isEmpty() && !codigo.isEmpty()) {
                    contadorVendedores++; // Incrementar el contador
                    // Formatear la información del vendedor y agregarla al JTextArea
                    String vendedorInfo = contadorVendedores + ". " + nombres + " " + apellidos + " - " + codigo;
                    txtlista.append(vendedorInfo + "\n");

                    // Agregar los datos al registro
                    datosAgregados.add(vendedorInfo);

                    // Limpiar los campos de texto después de agregar
                    txtnombres.setText("");
                    txtapellidos.setText("");
                    txtcodigos.setText("");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Por favor, complete todos los campos antes de agregar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener para el botón "Modificar"
        btnmodificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int start = txtlista.getSelectionStart();
                int end = txtlista.getSelectionEnd();
                
                try {
                    int startLine = txtlista.getLineOfOffset(start);
                    int endLine = txtlista.getLineOfOffset(end);
                    
                    if (startLine == endLine) {
                        String selectedText = txtlista.getText().substring(start, end);
                        String nuevoTexto = JOptionPane.showInputDialog(contentPane, "Modificar texto:", selectedText);
    
                        if (nuevoTexto != null) {
                            String textoModificado = txtlista.getText().substring(0, start) + nuevoTexto + txtlista.getText().substring(end);
                            txtlista.setText(textoModificado);
                            datosAgregados.set(startLine, nuevoTexto);
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Por favor, selecciona solo una línea para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // ActionListener para el botón "Eliminar"
        btneliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int start = txtlista.getSelectionStart();
                int end = txtlista.getSelectionEnd();
                
                try {
                    int startLine = txtlista.getLineOfOffset(start);
                    int endLine = txtlista.getLineOfOffset(end);
                    
                    if (startLine == endLine) {
                        String selectedText = txtlista.getText().substring(start, end);
                        String[] lines = txtlista.getText().split("\n");
                        
                        // Elimina la línea seleccionada
                        String textoModificado = "";
                        for (int i = 0; i < lines.length; i++) {
                            if (i != startLine) {
                                textoModificado += lines[i] + "\n";
                            }
                        }
                        txtlista.setText(textoModificado);
                        
                        // Actualiza el ArrayList con los datos eliminando la línea
                        datosAgregados.remove(startLine);
                        
                        // Actualiza los números de las líneas restantes
                        for (int i = startLine; i < datosAgregados.size(); i++) {
                            String dato = datosAgregados.get(i);
                            String[] parts = dato.split(" ", 2);
                            int numero = i + 1;
                            dato = numero + ". " + parts[1];
                            datosAgregados.set(i, dato);
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Por favor, selecciona solo una línea para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}



