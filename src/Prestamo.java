import java.time.LocalDate;

public class Prestamo {
    Usuario usuario; Libro libro; // TODO: Inicializar estas variables.
    LocalDate fechaPrestamo;
    LocalDate fechaDevolucion;

    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null; // <- Queda null hasta que se devuelva libro.
    }

    ///
    ///  MÉTODOS
    ///

    public Usuario getUsuario() {
        return usuario;
    }
    public Libro getLibro() {
        return libro;
    }
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void registrarDevolucion(){
        fechaDevolucion=LocalDate.now();
    }
    public boolean estaActivo(){
        return fechaDevolucion == null;
    }
    public String toString(){
        if (estaActivo()) {
            return "Prestamo{usuario='"+usuario+"', libro='"+libro+"', desde="+fechaPrestamo+", pendiente";
        }else{
            return "Prestamo{usuario='"+usuario+"', libro='"+libro+"', desde="+fechaPrestamo+", hasta="+fechaDevolucion+"}";
        }
    }
}
