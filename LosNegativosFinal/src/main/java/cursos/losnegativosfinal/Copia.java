
package cursos.losnegativosfinal;

import com.google.gson.annotations.Expose;


public class Copia {
        @Expose
  public String identificador;
         @Expose   
  public String estado;
        @Expose     
  public Libro original;
     @Expose
  public Multa multa;
     @Expose(serialize = false, deserialize = false)
  public Lector lector;
  @Expose(serialize = false, deserialize = false)
  public Prestamo prestamo;
     
     
    public Copia(String identificador, String estado, Libro original) {
        this.identificador = identificador;
        this.estado = estado;
        this.original = original;
    }
   
   
    public void prestar(){
    this.estado= "prestado";
        System.out.println("El libro se ha prestado");
    }
    public void devolver(){
    this.estado= "disponible";
        System.out.println("El libro esta disponible");
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getEstado() {
        return estado;
    }

    public Libro getOriginal() {
        return original;
    }

    public Lector getLector() {
        return lector;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setOriginal(Libro original) {
        this.original = original;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
}