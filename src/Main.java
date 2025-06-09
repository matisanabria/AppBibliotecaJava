import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();
    public static void main(String[] args) {
        byte opcionUsuario = 0;

        //  Agregamos nuevos usuarios
        Libro nuevoLibro = new Libro("La divina comedia", "Dante Alighieri", "1");
        biblioteca.registrarLibro(nuevoLibro);
        Libro nuevoLibro2 = new Libro("Don quijote de la mancha", "Miguel Cervantes", "2");
        biblioteca.registrarLibro(nuevoLibro2);
        Usuario nuevoUsuario = new Usuario("Matias Sanabria", "1");
        biblioteca.registrarUsuario(nuevoUsuario);
        Usuario nuevoUsuario2= new Usuario("Shawn Smith", "");
        biblioteca.registrarUsuario(nuevoUsuario2);

        do {
            mostrarMenuPrincipal();
            opcionUsuario= scanner.nextByte();
            switch (opcionUsuario) {
                case 1:
                    menuRegistrarLibro();
                    break;
                case 2:
                    menuRegistrarUsuario();
                    break;
                case 3:
                    menuPrestarLibro();
                    break;
                case 4:
                    menuDevolverLibro();
                    break;
                case 5:
                    menuListarLibrosDisponibles();
                    break;
                case 6:
                    menuListarPrestamosActivos();
                    break;
                case 7:
                    menuHistorialDePrestamosUsuario();
                    break;
                case 8:
                    biblioteca.mostrarResumen();
                    break;
                default:
                    System.out.println("❌ Opción inválida.\n");
                    dormir(3000);

            }
        }while(opcionUsuario!=0);
        System.out.println("👋 Cerrando programa.");
    }
    static void mostrarMenuPrincipal(){
        System.out.println("==== Biblioteca ====\n");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Listar libros disponibles");
        System.out.println("6. Ver préstamos activos");
        System.out.println("7. Ver historial de préstamos de usuario");
        System.out.println("8. Ver resúmen general");
        System.out.println("0. Salir");
        System.out.println(" ");
        System.out.print("->");
    }
    static void menuRegistrarLibro() {
        System.out.println("📙 Registrar nuevo libro\n(Escriba \"exit\" en cualquier campo para cancelar)\n");

        String titulo = pedirDato("Ingrese título: ");
        if (titulo==null) return;
        String autor = pedirDato("Ingrese autor: ");
        if (autor==null) return;
        String isbn = pedirDato("Ingrese ISBN: ");
        if (isbn==null) return;

        Libro libro = new Libro(titulo, autor, isbn);
        biblioteca.registrarLibro(libro);
        dormir(1500);
    }
    static void menuRegistrarUsuario() {
        System.out.println("😀 Registrar nuevo usuario\n(Escriba \"exit\" para cancelar)");

        String nombre = pedirDato("Ingrese nombre: ");
        if (nombre==null) return;
        String id = pedirDato("Ingrese ID: ");
        if (id==null) return;

        Usuario usuario = new Usuario(nombre, id);
        biblioteca.registrarUsuario(usuario);
        dormir(1500);
    }
    static void menuPrestarLibro(){
        System.out.println("🔜 Prestar libro");

        String isbn = pedirDato("Ingrese ISBN de libro: ");
        if (isbn==null) return;
        String id = pedirDato("Ingrese ID de usuario: ");
        if (id==null) return;

        biblioteca.prestarLibro(isbn, id);
    }
    static void menuDevolverLibro(){
        String isbn, id;

        System.out.println("🔃 Registrar devolución\nEscribe \"exit\" para salir.");

        String isbn = pedirDato("Ingrese ISBN de libro: ");
        if (isbn==null) return;
        String id = pedirDato("Ingrese ID de usuario: ");
        if (id==null) return;

        biblioteca.devolverLibro(isbn,id);
    }
    static void menuListarLibrosDisponibles(){
        List<Libro> disponibles = biblioteca.listarLibrosDisponibles();

        if(disponibles.isEmpty()){
            System.out.println("❌ No hay libros disponibles;");
        } else{
            int i = 1;
            System.out.println("✅ Libros disponibles");
            for (Libro l : disponibles){
                System.out.println(i++ +". " + l);
            }
        }
        pausar();
    }
    static void menuListarPrestamosActivos(){
        List<Prestamo> prestamos = biblioteca.listarPrestamosActivos();
        if(prestamos.isEmpty()){
            System.out.println("❌ No hay préstamos activos");
        }else{
            int i = 1;
            System.out.println("✅ Préstamos activos");
            for(Prestamo p: prestamos){
                System.out.println(i++ + ". " + p);
            }
        }
        pausar();
    }
    static void menuHistorialDePrestamosUsuario() {
        String id;
        System.out.println("Ingrese ID de Usuario: ");
        id = scanner.nextLine();
        System.out.println(" ");

        List<Prestamo> historial = biblioteca.historialDePrestamosUsuario(id);
        if (historial.isEmpty()){
            System.out.println("❌ El usuario no tiene un historial");
        }else{
            int i = 1;
            System.out.println("✅ Historial de usuario "+id);
            for (Prestamo p : historial){
                System.out.println(i++ + ". " + p);
            }
        }
        pausar();

    }

    static void dormir(int ms){
        try {
            Thread.sleep(ms); // 1000ms = 1s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void pausar() {
        System.out.println("\n🔸 Presione Enter para continuar...");
        scanner.nextLine();
    }

    static String pedirDato(String mensaje){
        String input;

        do {
            // Imprime mensaje y recibe input.
            System.out.print(mensaje);
            input = scanner.nextLine().trim();

            // Si escribe "exit" termina la función
            if (input.equalsIgnoreCase("exit")) return null;
            // Mensaje si está vacío.
            if (input.isEmpty()) {
                System.out.println("❌ No puedes dejar vacío este campo.");
            }

        } while (input.isEmpty()); // Si está vacío volver a pedir.

        return input;
    }
}
