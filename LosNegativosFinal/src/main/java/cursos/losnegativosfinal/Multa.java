
package cursos.losnegativosfinal;
import com.google.gson.annotations.Expose;

public class Multa {
        @Expose
    private final int diasRetraso;
      
        @Expose
    private Lector lector;
        @Expose
    private Prestamo prestamo;
                @Expose

        private double monto;
    public Multa(int diasRetraso, Lector lector, Prestamo prestamo) {
        this.diasRetraso = diasRetraso;
       
        this.lector = lector;
        this.prestamo = prestamo;
    }

 
    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

 

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
 
}