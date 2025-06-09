import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    ArrayList<Libro> libros;
    ArrayList<Usuario> usuarios;
    ArrayList<Prestamo> prestamos;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    ///
    /// MÉTODOS
    ///

    public void registrarLibro(Libro libro) {
        for (Libro l : libros){
            if(l.getIsbn().equals(libro.getIsbn())){
                System.out.println("❌ Ya existe un libro con ese ISBN.");
                return;
            }
        }
        libros.add(libro);
        System.out.println("✅ Libro agregado correctamente.");
    }
    public void registrarUsuario(Usuario usuario){
        for (Usuario u : usuarios){ // Por cada Usuario "u" en el array usuarios hacer.
            if(u.getId().equals(usuario.getId())){ // Si ID de "u" es igual al ID del que estoy introduciendo.
                System.out.println("❌ Ya existe un usuario con ese ID.");
            }
        }
        usuarios.add(usuario);
        System.out.println("✅ Usuario agregado correctamente.");
    }
    public boolean prestarLibro(String isbnLibro, String idUsuario){
        if (isbnLibro==null || idUsuario==null || isbnLibro.isEmpty() || idUsuario.isEmpty()){
            System.out.println("❌ No puedes dejar campos vacíos.");
            return false;
        }

        Libro libroEncontrado = null;
        Usuario usuarioEncontrado = null;

        // Buscar en la lista
        for (Usuario u: usuarios){
            if (u.getId().equals(idUsuario)){
                usuarioEncontrado = u;
                break;
            }
        }
        for (Libro l: libros){
            if (l.getIsbn().equals(isbnLibro)){
                libroEncontrado=l;
                break;
            }
        }

        // Verificar existencias
        if (libroEncontrado==null){
            System.out.println("❌ Libro no encontrado");
            return false;
        }
        if (usuarioEncontrado==null){
            System.out.println("❌ Usuario no encontrado");
            return false;
        }

        // Verificar disponibilidad del libro
        if (libroEncontrado.isPrestado()){
            System.out.println("❌ El libro ya está prestado.");
            return false;
        }

        // Si OK -> prestar
        libroEncontrado.prestar();
        Prestamo nuevoPrestamo = new Prestamo(usuarioEncontrado, libroEncontrado);
        prestamos.add(nuevoPrestamo);

        System.out.println("✅ Préstamo realizado a: " + usuarioEncontrado.getNombre());
        return true;
    }
    public boolean devolverLibro(String isbnLibro, String idUsuario){
        if (isbnLibro==null || idUsuario==null || isbnLibro.isEmpty() || idUsuario.isEmpty()){
            System.out.println("❌ No puedes dejar campos vacíos.");
            return false;
        }
        for (Prestamo p : prestamos){
            if(p.getLibro().getIsbn().equals(isbnLibro)){
                if(p.getUsuario().getId().equals(idUsuario)) {
                    if (p.estaActivo()) {
                        p.registrarDevolucion();
                        System.out.println("✅ Devolucion registrada correctamente.");
                        return true;
                    }
                }
                else System.out.println("❌ Usuario no encontrado");
            } else System.out.println("❌ Libro no encontrado");
        }
        System.out.println("❌ Devolución cancelada.");
        return false;
    }

    public List<Libro> listarLibrosDisponibles(){
        List<Libro> disponible = new ArrayList<>();
        for(Libro l: libros){
            if(!l.isPrestado()){
                disponible.add(l);
            }
        }
        return disponible;
    }
    public List<Prestamo> listarPrestamosActivos(){
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p: prestamos){
            if(p.estaActivo()){
                activos.add(p);
            }
        }
        return activos;
    }
    public List<Prestamo> historialDePrestamosUsuario(String idUsuario){
        List<Prestamo> historial= new ArrayList<>();
        for (Prestamo p: prestamos){
            if(p.getUsuario().getId().equals(idUsuario)){
                historial.add(p);
            }
        }
        return historial;
    }

    public void mostrarResumen(){
        int totalLibros= libros.size();
        int librosDisponibles= listarLibrosDisponibles().size();
        int usuariosRegistrados= usuarios.size();
        int prestamosActivos = listarPrestamosActivos().size();

        System.out.println("==== Resumen Biblioteca ====");
        System.out.println("Total libros registrados: " + totalLibros);
        System.out.println("Libros disponibles: " + librosDisponibles);
        System.out.println("Usuarios registrados: " + usuariosRegistrados);
        System.out.println("Prestamos activos: " + prestamosActivos);
        System.out.println("=============================");
    }

}

