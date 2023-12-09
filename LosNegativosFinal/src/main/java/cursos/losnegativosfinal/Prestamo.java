package cursos.losnegativosfinal;

import com.google.gson.annotations.Expose;
import java.time.LocalDate;
import java.util.Random;



public class Prestamo {
        @Expose
    public LocalDate fechaInicio;
        @Expose
    public LocalDate fechaFin;
        @Expose
    public Multa multa;
      @Expose
      public double monto;
    

    public Prestamo(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
  public void generarMulta() {
        LocalDate hoy = LocalDate.now();

        if (this.fechaFin != null && hoy.isAfter(this.fechaFin)) {
            // Calcular la diferencia de días
            long diferenciaDias = java.time.temporal.ChronoUnit.DAYS.between(this.fechaInicio, this.fechaFin);

            // Verificar si la diferencia de días es mayor a 7 y generar la multa
            if (diferenciaDias > 7) {
                // Calcular la multa con un valor de 2000 por día después de los 7 días
                double montoMulta = (diferenciaDias - 7) * 2000;
                this.multa = new Multa((int) diferenciaDias, null, null); // Corregir los parámetros de Multa
                System.out.println("Se ha generado una multa por retraso en la devolución del libro. Monto: " + montoMulta);
            }
        } else if (this.fechaFin == null) {
            System.out.println("Error: fechaFin es null");
        }
    }



       //recibir fecha iniical fecha final, dias, crear multa


 public LocalDate calcularFechaFin() {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        
        // Generar un número aleatorio entre 1 y 6
        Random random = new Random();
        int diasAdicionales = random.nextInt(6) + 1;

        // Establecer la fecha final como la fecha actual más días aleatorios
       LocalDate fechaFinLocal = fechaActual.plusDays(diasAdicionales);

      // Asignar la fechaFin al atributo correspondiente si es necesario
        this.fechaFin = fechaFinLocal;
  
        return fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Multa getMulta() {
        return multa;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setMulta(Multa multa) {
        this.multa = multa;
    }
    
 
    }
