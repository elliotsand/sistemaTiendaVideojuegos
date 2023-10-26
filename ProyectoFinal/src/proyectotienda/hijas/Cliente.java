package proyectotienda.hijas;

import proyectotienda.padre.Informacion;

public class Cliente extends Informacion {

	private int codigoCliente;
	private static int correlativo = 1001;

	public Cliente(String nombres, String apellidos, String telefono, String dni) {
		super(nombres, apellidos, telefono, dni);
		this.codigoCliente = correlativo;
		correlativo++;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public static void establecerCorrelativo(int valor) {
		correlativo = valor;
	}

}
