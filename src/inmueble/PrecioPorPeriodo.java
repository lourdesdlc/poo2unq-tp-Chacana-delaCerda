package inmueble;

import java.time.LocalDate;

public class PrecioPorPeriodo {
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precioPorDia;
	private String nombre;

	public PrecioPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin, double precioPorDia, String nombre) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioPorDia = precioPorDia;
		this.nombre = nombre;
	}

	public boolean incluye(LocalDate fecha) {
        return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
    }

    public boolean interfiereCon(PrecioPorPeriodo periodo) {
    	return !fechaInicio.isAfter(periodo.getFechaFin()) && !fechaFin.isBefore(periodo.getFechaInicio());
        
    }
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public double getPrecioPorDia() {
		return precioPorDia;
	}

	public String getNombre() {
		return nombre;
	}
}
