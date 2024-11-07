package usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import reserva.Reserva;
import tpgrupal.*;

public class Propietario extends Usuario {
	private Set<Inmueble> inmuebles; 
	private SitioWeb sitioWeb; 
	private int antiguedadEnElSitio;

	public Propietario(String nombreCompleto, String email, String telefono) {
		super(nombreCompleto, email, telefono);
		this.inmuebles = new HashSet<>(); 
		this.sitioWeb = new SitioWeb(); 
		this.antiguedadEnElSitio = 0; 
	}

	/** duda 
	public void agregarInmueble(Inmueble inmueble) {
		inmuebles.add(inmueble);
	}*/

		/*pasamos el inmueble por patr*/
	public void darDeAltaInmueble(Inmueble inmueble) {
		inmuebles.add(inmueble);
		sitioWeb.registrarInmueble(inmueble);
	}

	public void aceptarReserva(Reserva reserva) {
		reserva.confirmarReserva();
	}

	public void mostrarPortafolio() {
	};

	public Set<Inmueble> getInmueblesPropios() {
		return inmuebles;
	}
}
