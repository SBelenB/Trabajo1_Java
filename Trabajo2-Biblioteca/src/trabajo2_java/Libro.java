package trabajo2_java;

public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private Boolean disponible;
    private Usuario usuarioActual;

    public Libro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = true;
        this.usuarioActual = null;
    }
    
    public static final Libro LIBRO_NO_ENCONTRADO = new Libro("No Encontrado", "", "");

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }   

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean isDisponible() {
        return disponible;
    }
}