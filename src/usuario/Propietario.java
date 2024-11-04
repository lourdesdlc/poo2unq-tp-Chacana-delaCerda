package usuario;

import java.util.ArrayList;
import java.util.List;

import reserva.Reserva;
import tpgrupal.*;
public class Propietario extends Usuario {
	    private List<Inmueble> inmuebles;
	    private SitioWeb sitioWeb;

	    public Propietario(String nombreCompleto, String email, String telefono) {
	        super(nombreCompleto, email, telefono);
	        this.inmuebles = new ArrayList<>();
	    }

	    // Agrega un inmueble al portafolio del propietario
	    public void agregarInmueble(Inmueble inmueble) {
	        inmuebles.add(inmueble);
	    }

	    //dar de alta un inmueble en el sitio(registrar un inmueble en el sitio)
	    public void darDeAltaInmueble(Inmueble inmueble) {
	    	sitioWeb.registrarInmueble(inmueble);
	    }
	    
	    
	    // Acepta una reserva y confirma la misma
	    public void aceptarReserva(Reserva reserva) {
	        reserva.confirmarReserva();
	    }

	    // Mostrar información de inmuebles y puntuación del propietario
	    public void mostrarPortafolio(){};
	    
	    //Getters y Setters
	    // Obtiene la lista de inmuebles del propietario
	    public List<Inmueble> getInmueblesPropios() {
	    	return inmuebles;
	    }
}
