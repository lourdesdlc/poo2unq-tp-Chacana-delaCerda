package filtroDeBusqueda;

import java.time.LocalDate;

import tpgrupal.Inmueble;

public class FiltroPorFecha extends FiltroDeBusqueda {

	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;

	public FiltroPorFecha(LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	@Override
	public boolean cumple(Inmueble inmueble) { // TESTAR
		return inmueble.estaDisponibleParaLasFechas(fechaEntrada, fechaSalida);

		/**
		 * !inmueble.getCheckIn().isAfter(fechaSalida) &&
		 * !inmueble.getCheckOut().isBefore(fechaEntrada)
		 **/
	}

	@Override
	public boolean esFiltroFecha() {
		return true;
	}

}
