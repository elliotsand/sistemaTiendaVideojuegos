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
	private JTextField txtbuscar;
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
		scrollPane.setBounds(20, 36, 179, 130);
		contentPane.add(scrollPane);

		txtlista = new JTextArea();
		scrollPane.setViewportView(txtlista);

		JLabel lblNewLabel = new JLabel("Lista de vendedores:");
		lblNewLabel.setBounds(20, 11, 122, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Buscar por:");
		lblNewLabel_1.setBounds(218, 14, 70, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox cbtipoBusq = new JComboBox();
		cbtipoBusq.setModel(new DefaultComboBoxModel(new String[] { "Cód. Vendedor", "Nombre", "Apellido", "DNI" }));
		cbtipoBusq.setBounds(298, 10, 111, 22);
		contentPane.add(cbtipoBusq);

		txtbuscar = new JTextField();
		txtbuscar.setBounds(218, 43, 111, 20);
		contentPane.add(txtbuscar);
		txtbuscar.setColumns(10);

		JScrollPane scrollPaneResulBusq = new JScrollPane();
		scrollPaneResulBusq.setBounds(218, 99, 179, 67);
		contentPane.add(scrollPaneResulBusq);

		JTextArea txtResulBusq = new JTextArea();
		scrollPaneResulBusq.setViewportView(txtResulBusq);

		btnbuscar = new JButton("Buscar");
		btnbuscar.setBounds(327, 41, 89, 23);
		contentPane.add(btnbuscar);

		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener la opción seleccionada en cbtipoBusq
				String opcionBusqueda = (String) cbtipoBusq.getSelectedItem();
				String textoBusqueda = txtbuscar.getText().trim();

				// Limpiar el área de resultados de búsqueda
				txtResulBusq.setText("");

				if (opcionBusqueda.equals("Cód. Vendedor")) {
					// Buscar por número de vendedor
					String resultado = null;
					for (String dato : arregloVendedores2) {
						String[] partes = dato.split(" ");
						if (partes.length >= 1 && partes[0].equals(textoBusqueda)) {
							resultado = dato;
						}
					}
					if (resultado != null) {
						txtResulBusq.append(resultado + "\n");
					}
				} else if (opcionBusqueda.equals("Nombre")) {
					// Buscar por nombre
					for (String dato : arregloVendedores2) {
						String[] partes = dato.split(" ");
						if (partes.length >= 2 && partes[1].equals(textoBusqueda)) {
							txtResulBusq.append(dato + "\n");
						}
					}
				} else if (opcionBusqueda.equals("Apellido")) {
					// Buscar por apellido
					for (String dato : arregloVendedores2) {
						String[] partes = dato.split(" ");
						if (partes.length >= 3 && partes[2].equals(textoBusqueda)) {
							txtResulBusq.append(dato + "\n");
						}
					}
				} else if (opcionBusqueda.equals("DNI")) {
					// Buscar por DNI
					for (String dato : arregloVendedores2) {
						if (dato.contains(textoBusqueda)) {
							txtResulBusq.append(dato + "\n");
						}
					}
				}
			}
		});

		JButton btnagregar = new JButton("Agregar");
		btnagregar.setBounds(209, 275, 89, 23);
		contentPane.add(btnagregar);

		btnagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});

		JButton btnmodificar = new JButton("Modificar");
		btnmodificar.setBounds(308, 275, 89, 23);
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

		JButton btnconsultar = new JButton("Consultar");
		btnconsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnconsultar.setBounds(209, 239, 89, 23);
		contentPane.add(btnconsultar);

		JLabel lblNewLabel_2 = new JLabel("Resultados de búsqueda:");
		lblNewLabel_2.setBounds(218, 74, 152, 14);
		contentPane.add(lblNewLabel_2);

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
		txtnombres.setBounds(82, 204, 99, 20);
		contentPane.add(txtnombres);
		txtnombres.setColumns(10);

		txtapellidos = new JTextField();
		txtapellidos.setBounds(82, 232, 98, 20);
		contentPane.add(txtapellidos);
		txtapellidos.setColumns(10);

		txttelf = new JTextField();
		txttelf.setBounds(82, 257, 99, 20);
		contentPane.add(txttelf);
		txttelf.setColumns(10);

		txtdni = new JTextField();
		txtdni.setBounds(82, 281, 99, 20);
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

				// Actualiza el JTextArea después de modificar el vendedor
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

	public void consultar() {
		txtlista.setText("");
		for (String dato : arregloVendedores2) {
			txtlista.append(dato + "\n");
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
