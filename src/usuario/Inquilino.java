package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Inmueble.Inmueble;
import filtroDeBusqueda.FiltroCompuesto;
import filtroDeBusqueda.FiltroDeBusqueda;
import filtroDeBusqueda.FiltroPorCantidadHuespedes;
import filtroDeBusqueda.FiltroPorCiudad;
import filtroDeBusqueda.FiltroPorFecha;
import filtroDeBusqueda.FiltroPorPrecioMaximo;
import filtroDeBusqueda.FiltroPorPrecioMinimo;
import ranking.Rankeable;
import ranking.Ranking;
import reserva.Reserva;
import tpgrupal.*;

public class Inquilino extends Usuario {

	public Inquilino(String nombreCompleto, String email, String telefono) {
		super(nombreCompleto, email, telefono);

	}

	public List<Inmueble> buscarInmueble(FiltroCompuesto filtro) { // se deberia pasar al usario?

		return this.sitioWeb.buscarInmuebles(filtro);
	}

	public void realizarReservaDe(Reserva reserva) {

		this.sitioWeb.solicitarReserva(reserva);
	}

	public void realizarReservaCondicional(Reserva reserva) {
		this.sitioWeb.solicitarReservaCondicional(reserva);
	}

	public void cancelarReserva(Reserva reserva) {
		this.sitioWeb.anularReserva(reserva);
	}

}
