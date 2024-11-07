package filtroDeBusqueda;

import java.time.LocalDate;

import tpgrupal.Inmueble;

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
        double precioTotal = inmueble.calcularPrecioTotal(fechaInicio, fechaFin);
        return precioMinimo == null || precioTotal >= precioMinimo;
    }
}