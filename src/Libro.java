public class Libro {
    String titulo, autor, isbn;
    boolean prestado = false;

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    ///
    /// MÉTODOS
    ///

    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getIsbn() {
        return isbn;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void prestar(){
        prestado= true;
    }
    public void devolver(){
        prestado= false;
    }
    @Override
    public String toString(){
        String disponible=(prestado ? "Prestado" : "Disponible");
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: "+ isbn+", "+ disponible;
    }
}
