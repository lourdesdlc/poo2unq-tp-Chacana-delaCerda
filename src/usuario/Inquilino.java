package usuario;

import java.util.ArrayList;
import java.util.List;

import reserva.Reserva;
import tpgrupal.*;

public class Inquilino extends Usuario {
	    private List<Reserva> reservas;

	    public Inquilino(String nombreCompleto, String email, String telefono) {
	        super(nombreCompleto, email, telefono);
	        this.reservas = new ArrayList<>();
	    }

	    public void agregarReserva(Reserva reserva) {
	        reservas.add(reserva);
	    }
	    
	    public List<Reserva> getReservasRealizadas() {
	        return reservas;
	    }

	    // Mostrar información de puntuación y reservas del inquilino
	    public void mostrarHistorial() {};
	    
}
