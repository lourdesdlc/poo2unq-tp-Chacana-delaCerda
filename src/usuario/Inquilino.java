package usuario;

import java.util.ArrayList;
import java.util.List;

import ranking.Rankeable;
import ranking.Ranking;
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
	    
}
