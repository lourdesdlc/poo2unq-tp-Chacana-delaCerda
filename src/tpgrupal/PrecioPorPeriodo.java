package tpgrupal;

import java.time.LocalDate;

public class PrecioPorPeriodo {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioPorDia;

    public PrecioPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin, double precioPorDia) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioPorDia = precioPorDia;
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public double getPrecioPorDia() { return precioPorDia; }

    // Método para verificar si una fecha cae dentro de este período
    public boolean incluye(LocalDate fecha) {
        return (fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio)) &&
               (fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin));
    }
}
