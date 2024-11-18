package filtroDeBusqueda;

import java.time.LocalDate;

import Inmueble.Inmueble;

public class FiltroPorPrecioMinimo implements FiltroDeBusqueda {
    private Double precioMinimo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    public FiltroPorPrecioMinimo(Double precioMinimo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.precioMinimo = precioMinimo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    @Override
    public boolean cumple(Inmueble inmueble) {
        double precioTotal = inmueble.calcularPrecioParaRango(fechaInicio, fechaFin);
        return precioMinimo == null || precioTotal >= precioMinimo;
    }
}
