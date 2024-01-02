package trabajo2_java;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

public class Biblioteca {

    private List<Libro> libros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void mostrarMenu() {
        String[] opciones = {
            "Nuevo Socio",
            "Buscar Libro",
            "Alquilar Libro",
            "Devolver Libro",
            "Agregar Libro",
            "Eliminar Libro",
            "Salir"
        };

        while (true) {
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú de Biblioteca",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    crearSocio();
                    break;
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    alquilarLibro();
                    break;
                case 3:
                    devolverLibro();
                    break;
                case 4:
                    agregarLibro();
                    break;
                case 5:
                    eliminarLibro();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    private void crearSocio() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre: ");
        String numeroTelefono = JOptionPane.showInputDialog(null, "Ingrese el numero de telefono: ");

        Usuario nuevoUsuario = new Socio(nombre, numeroTelefono);
        usuarios.add(nuevoUsuario);

        int idUsuario = nuevoUsuario.getIdUsuario();

        JOptionPane.showMessageDialog(null, "Usuario creado: " + nombre + ". Su id es: " + idUsuario);
    }

    private void buscarLibro() {
        String[] opciones = {
            "Buscar por nombre",
            "Buscar por autor",
            "Buscar por genero",
            "Salir"
        };

        while (true) {
            int busqueda = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción",
                    "Opciones de búsqueda",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (busqueda) {
                case 0:
                    buscarLibroPorNombre(null);
                    break;
                case 1:
                    buscarLibroPorAutor();
                    break;
                case 2:
                    buscarLibroPorGenero();
                    break;
                case 3:
                    return;
            }
        }
    }

    private void alquilarLibro() {
        String nombreLibro = JOptionPane.showInputDialog(null, "Ingrese el nombre del libro a alquilar: ");
        String nombreUsuario = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario que alquilará el libro: ");

        Libro libro = buscarLibroPorNombre(nombreLibro);
        Usuario usuario = buscarUsuarioPorNombre(nombreUsuario);

        if (libro != null && usuario != null) {
            if (libro.isDisponible()) {
                libro.setDisponible(false);
                libro.setUsuarioActual(usuario);
                JOptionPane.showMessageDialog(null, "Libro alquilado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "El libro no está disponible para ser alquilado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Libro o usuario no encontrado.");
        }
    }

    private void devolverLibro() {
        String nombreLibro = JOptionPane.showInputDialog(null, "Ingrese el nombre del libro a devolver: ");
        String nombreUsuario = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario que devolverá el libro: ");

        Libro libro = buscarLibroPorNombre(nombreLibro);
        Usuario usuario = buscarUsuarioPorNombre(nombreUsuario);

        if (libro != null && usuario != null && libro.getUsuarioActual() != null && libro.getUsuarioActual().equals(usuario)) {
            libro.setDisponible(true);
            libro.setUsuarioActual(null);
            JOptionPane.showMessageDialog(null, "Libro devuelto con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado o no está registrado como alquilado por el usuario especificado.");
        }
    }

    private void agregarLibro() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro: ");
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro: ");
        String genero = JOptionPane.showInputDialog(null, "Ingrese el genero del libro: ");

        Libro nuevoLibro = new Libro(titulo, autor, genero);
        libros.add(nuevoLibro);

        JOptionPane.showMessageDialog(null, "Nuevo libro agregado.");
    }

    private void eliminarLibro() {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo del libro: ");
        String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro: ");
        String genero = JOptionPane.showInputDialog(null, "Ingrese el genero del libro: ");

        Libro libroAEliminar = new Libro(titulo, autor, genero);
        libros.remove(libroAEliminar);

        JOptionPane.showMessageDialog(null, "Libro " + titulo + " eliminado exitosamente.");
    }

    public List<Libro> buscarLibros(String criterio) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().contains(criterio)
                    || libro.getAutor().contains(criterio)
                    || libro.getGenero().contains(criterio)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    private Libro buscarLibroPorNombre(String nombreLibro) {
        if (nombreLibro == null) {
            nombreLibro = JOptionPane.showInputDialog(null, "Ingrese el nombre del libro: ");
        }

        try {
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(nombreLibro)) {
                    JOptionPane.showMessageDialog(null, "Libro encontrado: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
                    return libro;
                }
            }
            throw new NoSuchElementException("Libro no encontrado");
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        }
    }

    private Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    
    private void buscarLibroPorAutor() {
    String autorBuscado = JOptionPane.showInputDialog(null, "Ingrese el autor del libro: ");
    
    try {
        boolean encontrado = false;

        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autorBuscado)) {
                JOptionPane.showMessageDialog(null, "Libro encontrado:\n" + libro.getTitulo());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("Autor no encontrado");
        }
    } catch (NoSuchElementException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}


    private void buscarLibroPorGenero() {
    String generoBuscado = JOptionPane.showInputDialog(null, "Ingrese el genero del libro: ");
    
    try {
        boolean encontrado = false;

        for (Libro libro : libros) {
            if (libro.getGenero().equalsIgnoreCase(generoBuscado)) {
                JOptionPane.showMessageDialog(null, "Libro encontrado:\n" + libro.getTitulo());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("Genero no encontrado");
        }
    } catch (NoSuchElementException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

}
