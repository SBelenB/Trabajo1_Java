package trabajo2_java;

public abstract class Usuario{
    private String nombre;
    private static int contadorSocios = 0;
    private int idUsuario;
    private String numeroTelefono;
    
    public Usuario(String nombre, String numeroTelefono) {
        this.nombre = nombre;
        this.idUsuario = ++contadorSocios;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static int getContadorSocios() {
        return contadorSocios;
    }

    public static void setContadorSocios(int contadorSocios) {
        Usuario.contadorSocios = contadorSocios;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public abstract void alquilarLibro(Libro libro);
    
    public abstract void devolverLibro(Libro libro);
    
    public abstract void agregarLibro(Libro libro);
    
    public abstract void eliminarLibro(Libro libro);
    
    public abstract void buscarUsuarioPorNombre(Libro libro);
    
}