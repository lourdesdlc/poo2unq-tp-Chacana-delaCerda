package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	public Inquilino(String nombreCompleto, String email, String telefono, LocalDate antiguedadEnElSitio) {
		super(nombreCompleto, email, telefono, antiguedadEnElSitio);
		// TODO Auto-generated constructor stub
	}

	private List<Reserva> reservas;

	public List<Inmueble> buscarInmueble(FiltroCompuesto filtro) { // se deberia pasar al usario?

		return this.sitioWeb.buscarInmuebles(filtro);
	}

	public void realizarReservaDe(Reserva reserva, String formaDePago) {

		this.sitioWeb.solicitarReservaConFormaDePago(reserva, formaDePago);
	}

}
