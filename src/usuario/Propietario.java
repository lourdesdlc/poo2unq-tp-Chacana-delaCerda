package usuario;


import java.util.HashSet;
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

	@Override
	public void mostrarHistorial() {
		/*Entre la información del dueño el sistema
		 incorpora aquella relativa a su operatoria con el sitio: cuánto hace que es usuario,
		 cuántas veces ha alquilado ese inmueble, cuántas veces ha alquilado inmuebles
		 (más a allá del seleccionado en particular) y cuáles han sido
		 */
	}
	
	public Set<Inmueble> getInmueblesPropios() {
    	return inmuebles;
    }
}
