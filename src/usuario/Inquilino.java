package usuario;



import java.util.List;


import Inmueble.Inmueble;
import filtroDeBusqueda.FiltroCompuesto;

import reserva.Reserva;


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
