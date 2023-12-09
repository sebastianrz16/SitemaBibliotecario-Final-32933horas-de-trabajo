# SitemaBibliotecario-Final-32933horas-de-trabajo
Hecho por Nicolle Montaño, Sebastian Ruiz, Daniel Medina.

Aqui muestro los objetos creados
Autor autor = new Autor("Daniel", "Venezolano", "1856");
        Libro libro = new Libro("Hambre", "Panamericana", "Terror", "1802");
        Copia copia = new Copia("V564", "Disponible", libro);
        Lector lector = new Lector(1, "Nicolle", "Montaño", "Mi Casa");
0--------------------------------------------------------------------------------------------0
Creamos un endpoint  para añadir una copia no existente antes
get("/agregarCopia/:identificador/:nombreLibro", (req, res) -> {
Y como ejemplo usamos 
http://localhost:4567/agregarCopia/EJEMEPLO2/AnaFrank
0---------------------------------------------------------------------------0
Igualmente creamos un endpoint para añadir un Autor
get("/agregarAutor/:nombre/:nacionalidad/:fechaNacimiento", (req, res) -> {
Y como ejemplo usamaremos
http://localhost:4567/agregarAutor/Jude/Britanica/2003
0---------------------------------------------------------------------------0
Seguimos añadiendo con los endpoints y añadiremos algun lector
get("/agregarLectores/:numSocio/:nombre/:apellido/:direccion", (req, res) -> {
Como ejemplo usaremos
localhost:4567/agregarLectores/3/Belingan/Garrancho/AvenidaPastrana
0---------------------------------------------------------------------------0
Añadiremos un libro con el siguiente endpoint 
get("/agregarLibro/:nombre/:editorial/:genero/:año/:nombreAutor/:nacionalidad/:añoNacimiento", (req, res) -> {
Como ejemplo usaremos 
localhost:4567/agregarLibro/Gaspacho/Bernabeo/Comedia/1982/Tenhack/Portugues/1802
0---------------------------------------------------------------------------0
Endpoint para ver las copias que hay 
get("/copias",(req, res)->{
Se usa así
localhost:4567/copias
0---------------------------------------------------------------------------0
Endpoint para ver los libros que hay
get("/libros",(req, res)->{
Se usa así
localhost:4567/libros
0---------------------------------------------------------------------------0
Para ver los lectores que hay
  get("/lectores", (req, res) -> {
  localhost:4567/lectores
0---------------------------------------------------------------------------0
Endpoint para ver los autores creados
 get("/autores", (req, res) -> {
 localhost:4567/autores
0---------------------------------------------------------------------------0
Endpoint para pedir prestado una copia donde se registrara la fecha en que se pidio
 get("/prestar/:numSocio/:copiaId", (req, res) -> {
 Como ejemplo usaremos la copia que ya teniamos creada anteriormente
 http://localhost:4567/prestar/1/V564
 0---------------------------------------------------------------------------0
 Endpoint para devolver la copia que pedimos, donde se registra la fecha donde se entrega(fechaFinal)
 get("/devolver/:numSocio/:copiaId", (req, res) -> {
 Seguimos usando el ejemplo anterior
 http://localhost:4567/devolver/1/V564
  0---------------------------------------------------------------------------0
  Endpoint para verificar si el lector se pasó de 7 dias de entrega de la copia que hizo, esto se hace mediante un metodo que compara la fecha Final con la fecha Incial y por cada dia despues de los 7 dias de demora se multiplica por 2000 la multa, si no a pasado el tiempo saldá que no hay multas.
  get("/generarMulta/:numSocio", (req, res) -> {
  Seguimos usando el mismo ejemplo.
   http://localhost:4567/generarMulta/1
   Usando el lector numero 1, vemos que no tiene multa ya que el programa registra el tiempo real, asi que deberiamos dejar el programa abierto 8 dias para que registre una multa.Pero con una semilla aleatoria que aumente la fecha final  podemos cambiar esto.
 



