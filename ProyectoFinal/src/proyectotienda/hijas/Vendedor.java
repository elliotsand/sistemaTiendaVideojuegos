package proyectotienda.hijas;

public class Vendedor {

    private String nombres;
    private String apellidos;
    private String telf;
    private String dni;

    public Vendedor(String nombres, String apellidos, String telf, String dni) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telf = telf;
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelf() {
        return telf;
    }

    public String getDni() {
        return dni;
    }
}


