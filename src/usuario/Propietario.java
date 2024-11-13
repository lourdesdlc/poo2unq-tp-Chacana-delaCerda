package usuario;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import Inmueble.Inmueble;
import exepciones.UsuarioException;
import politicaCancelacion.PoliticaCancelacion;
import ranking.Ranking;
import reserva.Reserva;
import tpgrupal.*;

public class Propietario extends Usuario {

	private Set<Inmueble> inmuebles;
	private Set<Reserva> reservasPropias;

	public Propietario(String nombreCompleto, String email, String telefono) {
		super(nombreCompleto, email, telefono);
		// TODO Auto-generated constructor stub
	}

	public void publicarInmuble(Inmueble inmueble) {
		inmuebles.add(inmueble);
		sitioWeb.registrarInmuebleDe(inmueble, this);

	}

	// estos cambios tambien afectan a la set de website!
	public void cambiarPoliticaDeCancelacion(Inmueble i, PoliticaCancelacion p) {
		if (esPropietarioDelInmueble(i)) { // Por ahora para hacer esto, el inmueble debe estar publicado en web.
			i.cambiarPolitica(p);
		} else {
			throw new IllegalArgumentException("Este inmueble no te pertence");
		}

	}

	public void reducirPrecioDeInmueble(Inmueble i, Double monto) {
		if (esPropietarioDelInmueble(i)) { // Por ahora para hacer esto, el inmueble debe estar publicado en web.
			i.cambiarPrecio(i.precio() - monto);
			sitioWeb.notificarBajaDePrecio(i, monto);
		} else {
			throw new IllegalArgumentException("Este inmueble no te pertence");
		}

	}

	public boolean esPropietarioDelInmueble(Inmueble i) {
		return inmuebles.contains(i);
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

	public void evaluarSolicitudDeReserva(Reserva reserva) { // L y B
		if (esPropietarioDelInmueble(reserva.inmueble())) {
			reservasPropias.add(reserva); // " el tiempo de visualizar"
		}
	}

	public void aceptarReserva(Reserva reserva) { // L
		if (this.reservasPropias.contains(reserva)) {
			
			
			sitioWeb.consolidarReserva(reserva);

		} else
			throw new RuntimeException("Esta reserva no te pertence");
	}
}
