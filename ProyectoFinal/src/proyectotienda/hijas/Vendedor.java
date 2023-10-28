package proyectotienda.hijas;

import proyectotienda.padre.Informacion;

public class Vendedor extends Informacion {

    private int codigoVendedor;
    private static int correlativo = 2001;

    public Vendedor(String nombres, String apellidos, String telefono, String dni) {
        super(nombres, apellidos, telefono, dni);
        this.codigoVendedor = correlativo;
        correlativo++;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }
    public static void establecerCorrelativo(int valor) {
        correlativo = valor;
    }

}


