package proyectotienda.hijas;

public class Vendedor {

    private int codigoVendedor;
    private String nombres;
    private String apellidos;
    private String telf;
    private String dni;
    private static int correlativo = 1001;

    public Vendedor(String nombres, String apellidos, String telf, String dni) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telf = telf;
        this.dni = dni;
        this.codigoVendedor = correlativo;
        correlativo++;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public static void establecerCorrelativo(int valor) {
        correlativo = valor;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telf;
    }

    public String getDni() {
        return dni;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telf) {
        this.telf = telf;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}


