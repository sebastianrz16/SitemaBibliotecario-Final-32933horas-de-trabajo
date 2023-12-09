    package cursos.losnegativosfinal;


    import com.google.gson.annotations.Expose;
    import java.util.ArrayList;
    import java.time.LocalDate;
    public class Lector {
        // Atributos
        private int numSocio;
        @Expose

        private String nombre;
        @Expose

        private String apellido;
        @Expose 

        private String direccion;
        @Expose

        private ArrayList<Copia> copiasPrestadas; // Lista de copias prestadas al lector

    @Expose(serialize = false, deserialize = false)
        public Prestamo prestamo;
        @Expose

     private LocalDate fechaFinal;
     @Expose(serialize = false, deserialize = false)
        private LocalDate fechaInicial;







        // Constructor
        public Lector(int numSocio, String nombre, String apellido, String direccion) {
            this.numSocio = numSocio;
            this.nombre = nombre;
            this.apellido = apellido;
            this.direccion = direccion;
            this.copiasPrestadas = new ArrayList<>();

        }
     public LocalDate getFechaInicial() {
            // Cambiar la línea para retornar la fecha inicial
            return fechaInicial != null ? fechaInicial : LocalDate.now();
        }
        // Métodos
        public int getNumSocio() {
            return numSocio;
        }

        public void agregarCopia(Copia copia) {
            copiasPrestadas.add(copia);
        }
        // Getters y Setters


        public void setNumSocio(int numSocio) {
            this.numSocio = numSocio;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public ArrayList<Copia> getCopiasPrestadas() {
            return copiasPrestadas;
        }

        public void setCopiasPrestadas(ArrayList<Copia> copiasPrestadas) {
            this.copiasPrestadas = copiasPrestadas;
        }

        // Método para prestar una copia al lector
        public void prestarCopia(Copia copia) {
            copiasPrestadas.add(copia);
        }
    public void iniciarPrestamo(LocalDate fechaInicial, LocalDate fechaFinal) {
        // Verificar si la fecha inicial es nula antes de asignarla
        //Se imprime en la consola para 
        if (fechaInicial != null) {
            this.fechaInicial = fechaInicial;
            this.fechaFinal = fechaFinal; // Permitir que fechaFinal sea null
            System.out.println("Préstamo iniciado. Fecha Inicial: " + fechaInicial);
        } else {
            System.out.println("Error: La fecha inicial no puede ser nula.");
        }
    }




      public void quitarCopia(Copia copia) {
            copiasPrestadas.remove(copia);
        }

        public void setPrestamo(Prestamo prestamo) {
            this.prestamo = prestamo;
        }

        public Prestamo getPrestamo() {
            return prestamo;
        }

        public void setFechaFinal(LocalDate fechaFinal) {
            this.fechaFinal = fechaFinal;
        }

    }
