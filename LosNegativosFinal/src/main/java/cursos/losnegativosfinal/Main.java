package cursos.losnegativosfinal;
import java.time.LocalDate;
import java.util.ArrayList;
import static spark.Spark.get;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class Main {
     private LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }

    public static Libro buscarLibroPorNombre(String nombre) {
    ArrayList<Libro> libros = new ArrayList<>();
    for (Libro libro : libros) {
        if (libro.nombre.equalsIgnoreCase(nombre)) {
            return libro;
        }
    }
    return null;
    
}
    public static void main(String[] args) {
       
        // Creación de instancias
        Autor autor = new Autor("Daniel", "Venezolano", "1856");
        Libro libro = new Libro("Hambre", "Panamericana", "Terror", "1802");
        Copia copia = new Copia("V564", "Disponible", libro);
        Lector lector = new Lector(1, "Nicolle", "Montaño", "Mi Casa");

        // Listas
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Lector> lectores = new ArrayList<>();
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Copia> copias = new ArrayList<>();

        // Añadir instancias a las listas
        autores.add(autor);
        libros.add(libro);
        lectores.add(lector);
        copias.add(copia);

        autor.getLibros().add(libro);
        libro.getAutores().add(autor);
        libro.getCopias().add(copia);
        lector.agregarCopia(copia);

        // Configuración de Gson
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
get("/agregarCopia/:identificador/:nombreLibro", (req, res) -> {
    // Obtener parámetros de la ruta
    String identificador = req.params(":identificador");
    String nombreLibro = req.params(":nombreLibro");

    // Buscar el libro por nombre
    Libro libroExistente = buscarLibroPorNombre(nombreLibro, libros);

    if (libroExistente != null) {
        // Crear una nueva copia y agregarla al libro
        Copia nuevaCopia = new Copia(identificador, "Disponible", libroExistente);
        libroExistente.getCopias().add(nuevaCopia);
        copias.add(nuevaCopia);

        res.type("application/json");
        return gson.toJson(nuevaCopia);
    } else {
        // Si el libro no existe, devuelve un mensaje de error
        res.status(404);
        return gson.toJson("Libro no encontrado");
    }
    
});
get("/agregarAutor/:nombre/:nacionalidad/:fechaNacimiento", (req, res) -> {
            // Obtener parámetros de la URL
            String nombre = req.params(":nombre");
            String nacionalidad = req.params(":nacionalidad");
            String fechaNacimiento = req.params(":fechaNacimiento");

              Iterator<Autor> iterator = autores.iterator();
            boolean nombreExiste = false;

            while (iterator.hasNext()) {
                Autor obj = iterator.next();

                    if (obj.getNombre().equalsIgnoreCase(nombre)) {
                        nombreExiste = true;
                        break; 
                    }
            }

            if (nombreExiste) {
                return "No se puede agregar el autor , el nombre  ya existe";
            } else {
                Autor nuevoAutor = new Autor(nombre, nacionalidad, fechaNacimiento);

            // Agregar el nuevo lector a la lista
            autores.add(nuevoAutor);

            // Devolver una respuesta
            res.type("application/json");
            return gson.toJson(nuevoAutor);
      
            }
        });
        // Endpoint para obtener la lista de libros
        get("/copias",(req, res)->{
            res.type("application/json");
            return gson.toJson(copias);
        });
        get("/libros", (req, res) -> {
            res.type("application/json");
            return gson.toJson(libros);
        });

        // Endpoint para obtener la lista de lectores
        get("/lectores", (req, res) -> {
            res.type("application/json");
            return gson.toJson(lectores);
        });

        // Endpoint para obtener la lista de autores
        get("/autores", (req, res) -> {
            res.type("application/json");
            return gson.toJson(autores);
        });

        // Endpoint para agregar lectores
        get("/agregarLectores/:numSocio/:nombre/:apellido/:direccion", (req, res) -> {
            // Obtener parámetros de la ruta
            int numSocio = Integer.parseInt(req.params("numSocio"));
            String nombre = req.params("nombre");
            String apellido = req.params("apellido");
            String direccion = req.params("direccion");
            
  Iterator<Lector> iterator = lectores.iterator();
            boolean nombreExiste = false;

            while (iterator.hasNext()) {
                Lector obj = iterator.next();

                    if (obj.getNombre().equalsIgnoreCase(nombre)) {
                        nombreExiste = true;
                        break; 
                    }
            }

            if (nombreExiste) {
                return "No se puede agregar el lector, el nombre  ya existe";
            } else {
                Lector nuevoLector = new Lector(numSocio, nombre, apellido, direccion);

            // Agregar el nuevo lector a la lista
            lectores.add(nuevoLector);

            // Devolver una respuesta
            res.type("application/json");
            return gson.toJson(nuevoLector);
      
            }
        });
    
        
        
      get("/prestar/:numSocio/:copiaId", (req, res) -> {
    res.type("application/json");

    // Obtener parámetros de la ruta
    int numSocio = Integer.parseInt(req.params(":numSocio"));
    String copiaId = req.params(":copiaId");

    // Buscar el lector por numSocio
    Lector lectorPrestamo = lectores.stream()
            .filter(lect -> lect.getNumSocio() == numSocio)
            .findFirst()
            .orElse(null);

    // Buscar la copia por copiaId
    Copia copiaPrestamo = copias.stream()
            .filter(cop -> cop.getIdentificador().equals(copiaId))
            .findFirst()
            .orElse(null);

    // Prestar la copia al lector si ambos existen
    if (lectorPrestamo != null && copiaPrestamo != null) {
        copiaPrestamo.prestar();
        lectorPrestamo.agregarCopia(copiaPrestamo);

        // Obtener la fecha actual
        LocalDate fechaInicial = LocalDate.now();

        // Iniciar el préstamo con las fechas inicial y final
        lectorPrestamo.iniciarPrestamo(fechaInicial, null);

        // Crear un objeto JSON que incluya la fecha inicial
     // Crear un objeto JSON que incluya la fecha inicial
    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("fechaInicial", lectorPrestamo.getFechaInicial().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    jsonResponse.add("lector", gson.toJsonTree(lectorPrestamo));


        return jsonResponse.toString();
    } else {
        // Devolver un mensaje de error si no se encuentran
        res.status(404);
        return gson.toJson("Lector o copia no encontrado");
    }
});

    
  
    // Método para obtener la fecha actual
  
    // Método para obtener la fecha actual
 get("/devolver/:numSocio/:copiaId", (req, res) -> {
    res.type("application/json");

    // Obtener parámetros de la ruta
    int numSocio = Integer.parseInt(req.params(":numSocio"));
    String copiaId = req.params(":copiaId");

    // Buscar el lector por numSocio
    Lector lectorDevolucion = lectores.stream()
            .filter(lect -> lect.getNumSocio() == numSocio)
            .findFirst()
            .orElse(null);

    // Acceder a la fecha inicial del lector
    LocalDate fechaInicialLector = lectorDevolucion.getFechaInicial();

    // Buscar la copia por copiaId
    LocalDate fechaFinal = LocalDate.now();
    Prestamo prestamo = new Prestamo(fechaInicialLector, fechaFinal);
    lectorDevolucion.setPrestamo(prestamo);

    Copia copiaDevolucion = copias.stream()
            .filter(cop -> cop.getIdentificador().equals(copiaId))
            .findFirst()
            .orElse(null);
    copiaDevolucion.setPrestamo(prestamo);

    // Devolver la copia al sistema si ambos existen
    copiaDevolucion.devolver();
    lectorDevolucion.quitarCopia(copiaDevolucion);

    // Crear un objeto JSON que incluya las fechas inicial y final
    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("fechaInicial", fechaInicialLector.toString());
    jsonResponse.addProperty("fechaFinal", fechaFinal.toString());
    jsonResponse.add("lector", gson.toJsonTree(lectorDevolucion));

    return jsonResponse.toString();
 });
    
get("/generarMulta/:numSocio", (req, res) -> {
    res.type("application/json");

    // Obtener parámetros de la ruta
    int numSocio = Integer.parseInt(req.params(":numSocio"));

    // Buscar el lector por número de socio
    Lector lectorT = buscarLectorPorNumSocio(numSocio, lectores);

    // Verificar si el lector existe y tiene un préstamo
    if (lectorT != null && lectorT.getPrestamo() != null) {
        // Generar la multa
        lectorT.getPrestamo().generarMulta();

        // Verificar si se generó una multa
        if (lectorT.getPrestamo().getMulta() != null) {
            return gson.toJson("Multa generada exitosamente. Monto actual de la multa: " + lectorT.getPrestamo().getMulta().getMonto());
        } else {
            return gson.toJson("No se generó multa.");
        }
    } else {
        // Devolver un mensaje de error si el lector no se encuentra o no tiene un préstamo
        res.status(404);
        return gson.toJson("Lector no encontrado o no tiene un préstamo activo");
    }
});


      
      
      
      ///////////////////////////
      get("/agregarLibro/:nombre/:editorial/:genero/:año/:nombreAutor/:nacionalidad/:añoNacimiento", (req, res) -> {
    // Obtener parámetros de la ruta
    String nombreLibro = req.params(":nombre");
    String editorial = req.params(":editorial");
    String genero = req.params(":genero");
    String año = req.params(":año");
    String nombreAutor = req.params(":nombreAutor");
    String nacionalidadAutor = req.params(":nacionalidad");
   String añoNacimientoAutor =req.params(":añoNacimiento");

    // Verificar si el autor ya existe
    Autor autorExistente = buscarAutorPorNombre(nombreAutor, autores);

    if (autorExistente != null) {
        // Si el autor ya existe, asocia el autor existente al libro 
        Libro nuevoLibro = new Libro(nombreLibro, editorial, genero, año);
        nuevoLibro.getAutores().add(autorExistente);
        libros.add(nuevoLibro);

        res.type("application/json");
        return gson.toJson(nuevoLibro);
    } else {
        // Si el autor no existe, redirige a otro endpoint para agregar al autor primero
        res.redirect("/agregarAutor/" + nombreAutor + "/" + nacionalidadAutor + "/" + añoNacimientoAutor);
        return null;
    }
});
      
      
      
      
      
    }
    public static Libro buscarLibroPorNombre(String nombre, ArrayList<Libro> listaLibros) {
    for (Libro libro : listaLibros) {
        if (libro.getNombre().equalsIgnoreCase(nombre)) {
            return libro;
        }
    }
    return null;
}
    public static Autor buscarAutorPorNombre(String nombre, ArrayList<Autor> listaAutores) {
    for (Autor autor : listaAutores) {
        if (autor.getNombre().equalsIgnoreCase(nombre)) {
            return autor;
        }
    }
    return null;
    
}   public static Copia buscarCopiaPorId(String identificador, ArrayList<Copia> listaCopias) {
    for (Copia copia : listaCopias) {
        if (copia.getIdentificador().equals(identificador)) {
            return copia;
        }
    }
    return null;
}

// Método para buscar un lector por su número de socio
// Solucionar el retorno cuando no se encuentra el lector por numSocio
public static Lector buscarLectorPorNumSocio(int numSocio, ArrayList<Lector> listaLectores) {
    for (Lector lector : listaLectores) {
        if (lector.getNumSocio() == numSocio) {
            return lector;
        }
    }
    return null; // Retornar null fuera del bucle
}

    }
    
      

