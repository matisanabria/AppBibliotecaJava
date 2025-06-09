public class Usuario {
    String nombre, id;

    public Usuario(String nombre, String id){
        this.nombre = nombre; this.id = id;
    }

    ///
    /// MÉTODOS
    ///

    public String getNombre() {
        return nombre;
    }
    public String getId() {
        return id;
    }
    public String toString(){
        return "Usuario{nombre:='"+nombre+"',id='"+id+"'}";
    }
}
