package proyectotienda.gui;

import proyectotienda.arreglos.ArregloClientes;
import proyectotienda.arreglos.ArregloVendedores;
import proyectotienda.hijas.Cliente;
import proyectotienda.hijas.Vendedor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class VendedorGui extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtnombres;
	private JTextField txtapellidos;
	private JTextArea txtlista;
	private int contadorVendedores = 2000;
	private ArrayList<String> arregloVendedores2 = new ArrayList<>();
	private JTextField txttelf;
	private JTextField txtdni;
	private JButton btnbuscar;

	ArregloVendedores arregloVendedores = new ArregloVendedores();
	private JTextField textCodigoVendedor;

	public VendedorGui() throws IOException {
		setTitle("Mantenimiento vendedores");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 455, 363);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 36, 354, 130);
		contentPane.add(scrollPane);

		txtlista = new JTextArea();
		scrollPane.setViewportView(txtlista);

		JLabel lblNewLabel = new JLabel("Lista de vendedores:");
		lblNewLabel.setBounds(20, 11, 122, 14);
		contentPane.add(lblNewLabel);

		btnbuscar = new JButton("Buscar");
		btnbuscar.setBounds(209, 203, 89, 23);
		contentPane.add(btnbuscar);

		btnbuscar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int codigoVendedorBuscado;
		        try {
		            codigoVendedorBuscado = Integer.parseInt(textCodigoVendedor.getText().trim());
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(contentPane, "Ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        Vendedor vendedorEncontrado = arregloVendedores.buscar(codigoVendedorBuscado);

		        if (vendedorEncontrado != null) {
		            
		            textCodigoVendedor.setText(String.valueOf(vendedorEncontrado.getCodigoVendedor()));
		            txtnombres.setText(vendedorEncontrado.getNombres());
		            txtapellidos.setText(vendedorEncontrado.getApellidos());
		            txttelf.setText(vendedorEncontrado.getTelefono());
		            txtdni.setText(vendedorEncontrado.getDni());
		        } else {
		            JOptionPane.showMessageDialog(contentPane, "Vendedor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		JButton btnagregar = new JButton("Agregar");
		btnagregar.setBounds(209, 273, 89, 23);
		contentPane.add(btnagregar);

		btnagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});

		JButton btnmodificar = new JButton("Modificar");
		btnmodificar.setBounds(209, 239, 89, 23);
		contentPane.add(btnmodificar);

		btnmodificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});

		JButton btneliminar = new JButton("Eliminar");
		btneliminar.setBounds(308, 239, 89, 23);
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		contentPane.add(btneliminar);

		JLabel lblNewLabel_3 = new JLabel("Nombres:");
		lblNewLabel_3.setBounds(19, 207, 63, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Apellidos:");
		lblNewLabel_4.setBounds(19, 235, 63, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_6 = new JLabel("Telf:");
		lblNewLabel_6.setBounds(19, 260, 46, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("DNI:");
		lblNewLabel_7.setBounds(19, 284, 46, 14);
		contentPane.add(lblNewLabel_7);

		txtnombres = new JTextField();
		txtnombres.setBounds(82, 202, 99, 20);
		contentPane.add(txtnombres);
		txtnombres.setColumns(10);

		txtapellidos = new JTextField();
		txtapellidos.setBounds(83, 228, 98, 20);
		contentPane.add(txtapellidos);
		txtapellidos.setColumns(10);

		txttelf = new JTextField();
		txttelf.setBounds(82, 254, 99, 20);
		contentPane.add(txttelf);
		txttelf.setColumns(10);

		txtdni = new JTextField();
		txtdni.setBounds(82, 280, 99, 20);
		contentPane.add(txtdni);

		JLabel lblNewLabel_3_1 = new JLabel("Codigo");
		lblNewLabel_3_1.setBounds(20, 179, 63, 14);
		contentPane.add(lblNewLabel_3_1);

		textCodigoVendedor = new JTextField();
		textCodigoVendedor.setColumns(10);
		textCodigoVendedor.setBounds(82, 176, 99, 20);
		contentPane.add(textCodigoVendedor);

		cargarVendedoresDesdeArchivo("vendedores.txt");

		for (String vendedorInfo : arregloVendedores2) {
			txtlista.append(vendedorInfo + "\n");
		}
	}

	public void agregar() {
	    String nombres = txtnombres.getText();
	    String apellidos = txtapellidos.getText();
	    String telefono = txttelf.getText();
	    String dni = txtdni.getText();

	    if (nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || dni.isEmpty()) {
	        JOptionPane.showMessageDialog(contentPane, "Por favor, complete todos los campos antes de agregar.",
	                "Error", JOptionPane.ERROR_MESSAGE);
	    } else {
	        Vendedor vendedor = new Vendedor(nombres, apellidos, telefono, dni);
	        arregloVendedores.agregar(vendedor);
	        arregloVendedores.actualizar();
	        limpieza();

	        txtlista.setText("");
	        for (Vendedor v : arregloVendedores.getVendedores()) {
	            txtlista.append(v.getCodigoVendedor() + " " + v.getNombres() + " " + v.getApellidos() + " "
	                    + v.getTelefono() + " " + v.getDni() + "\n");
	        }
	    }
	}

	public void modificar() {
		int codigoVendedor = Integer.parseInt(textCodigoVendedor.getText());
		String nombres = txtnombres.getText();
		String apellidos = txtapellidos.getText();
		String telefono = txttelf.getText();
		String dni = txtdni.getText();

		if (!nombres.isEmpty() && !apellidos.isEmpty() && !telefono.isEmpty() && !dni.isEmpty()) {
			Vendedor vendedor = arregloVendedores.buscar(codigoVendedor);

			if (vendedor != null) {
				vendedor.setNombres(nombres);
				vendedor.setApellidos(apellidos);
				vendedor.setTelefono(telefono);
				vendedor.setDni(dni);
				arregloVendedores.actualizar();

				limpieza();

				txtlista.setText("");
				for (Vendedor v : arregloVendedores.getVendedores()) {
					txtlista.append(v.getCodigoVendedor() + " " + v.getNombres() + " " + v.getApellidos() + " "
							+ v.getTelefono() + " " + v.getDni() + "\n");
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "Vendedor no encontrado.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(contentPane, "Por favor, complete todos los campos antes de modificar.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarVendedoresDesdeArchivo(String archivo) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			String linea;

			while ((linea = br.readLine()) != null) {
				arregloVendedores2.add(linea);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void eliminar() {
	    int codigoVendedor = Integer.parseInt(textCodigoVendedor.getText());
	    Vendedor vendedor = arregloVendedores.buscar(codigoVendedor);

	    if (vendedor != null) {
	        arregloVendedores.eliminar(vendedor);
	        actualizarTextArea();
	        limpieza();
	    } else {
	        JOptionPane.showMessageDialog(contentPane, "Vendedor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void actualizarTextArea() {
		txtlista.setText("");
		for (Vendedor v : arregloVendedores.getVendedores()) {
			txtlista.append(v.getCodigoVendedor() + " " + v.getNombres() + " " + v.getApellidos() + " "
					+ v.getTelefono() + " " + v.getDni() + "\n");
		}
	}

	void limpieza() {
		txtnombres.setText("");
		txtapellidos.setText("");
		txttelf.setText("");
		txtdni.setText("");
	}
}
