package filtroDeBusqueda;

import java.time.LocalDate;

import inmueble.Inmueble;

public class FiltroPorPrecioMaximo implements FiltroDeBusqueda {
    private Double precioMaximo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    public FiltroPorPrecioMaximo(Double precioMaximo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.precioMaximo = precioMaximo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    @Override
    public boolean cumple(Inmueble inmueble) {
        double precioTotal = inmueble.getPrecio(fechaInicio, fechaFin);
        return precioTotal <= precioMaximo;
    }
}
