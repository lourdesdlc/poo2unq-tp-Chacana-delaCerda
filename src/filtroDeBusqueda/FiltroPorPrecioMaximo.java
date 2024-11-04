package filtroDeBusqueda;

import java.time.LocalDate;

import tpgrupal.Inmueble;

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
        double precioTotal = inmueble.calcularPrecioTotal(fechaInicio, fechaFin);
        return precioMaximo == null || precioTotal <= precioMaximo;
    }
}
