package filtroDeBusqueda;

import java.time.LocalDate;

import inmueble.Inmueble;

public class FiltroPorFecha implements FiltroDeBusqueda {
	private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    public FiltroPorFecha(LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }
    @Override
    public boolean cumple(Inmueble inmueble) {
        //falta implementar
    	//return inmueble.estaDisponibleEntre(fechaEntrada, fechaSalida);
    	return true;
    }
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

    
}
