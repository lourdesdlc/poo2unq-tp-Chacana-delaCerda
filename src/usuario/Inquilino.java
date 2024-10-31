package usuario;

import java.util.ArrayList;
import java.util.List;
import tpgrupal.*;

public class Inquilino extends Usuario {
	    private List<Reserva> reservas;

	    public Inquilino(String nombreCompleto, String email, String telefono) {
	        super(nombreCompleto, email, telefono);
	        this.reservas = new ArrayList<>();
	    }

	    // Agrega una reserva completada a la lista de reservas
	    public void agregarReserva(Reserva reserva) {
	        reservas.add(reserva);
	    }

	    // Obtener la lista de reservas
	    public List<Reserva> getReservasRealizadas() {
	        return reservas;
	    }

	    // Mostrar información de puntuación y reservas del inquilino
	    public void mostrarHistorial() {};

}
