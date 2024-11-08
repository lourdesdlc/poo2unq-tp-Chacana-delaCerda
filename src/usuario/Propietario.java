package usuario;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import politicaCancelacion.PoliticaCancelacion;
import ranking.Ranking;
import reserva.Reserva;
import tpgrupal.*;

public class Propietario extends Usuario {

	private Set<Inmueble> inmuebles;
	private LocalDate antiguedadEnElSitio;

	public Propietario(String nombreCompleto, String email, String telefono) {
		super(nombreCompleto, email, telefono);
		// TODO Auto-generated constructor stub
	}

	/* pasamos el inmueble por patr */
	public void darDeAltaInmueble(Inmueble inmueble) {
		inmuebles.add(inmueble);
		sitioWeb.registrarInmuebleDe(inmueble,this);
	}

	public void aceptarReserva(Reserva reserva) {
		reserva.confirmarReserva();
	}

	public void cambiarPoliticaDeCancelacion(Inmueble i, PoliticaCancelacion p) {
		if (inmuebles.contains(i)) { // Por ahora para hacer esto, el inmueble debe estar publicado en web.
			i.cambiarPolitica(p);
		} else {
			throw new IllegalArgumentException("Este inmueble no te pertence");
		}

	}

	public void reducirPrecioDeInmueble(Inmueble i, Double monto) {
		if (inmuebles.contains(i)) { // Por ahora para hacer esto, el inmueble debe estar publicado en web.
			i.cambiarPrecio(i.precio() - monto);
			sitioWeb.notificarBajaDePrecio(i, monto);
		} else {
			throw new IllegalArgumentException("Este inmueble no te pertence");
		}

	}

	@Override
	public void mostrarHistorial() {
		/*
		 * Entre la información del dueño el sistema incorpora aquella relativa a su
		 * operatoria con el sitio: cuánto hace que es usuario, cuántas veces ha
		 * alquilado ese inmueble, cuántas veces ha alquilado inmuebles (más a allá del
		 * seleccionado en particular) y cuáles han sido
		 */
	}

	public Set<Inmueble> getInmueblesPropios() {
		return inmuebles;
	}
}
