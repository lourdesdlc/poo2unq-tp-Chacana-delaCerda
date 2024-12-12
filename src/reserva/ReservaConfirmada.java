package reserva;

public class ReservaConfirmada extends EstadoReserva {

	public void finalizar(Reserva r) { 
		r.cambiarEstado(new ReservaFinalizada());
	}

	public void cancelar(Reserva r) {
		r.cambiarEstado(new ReservaCancelada());
	}

	public boolean esConfirmada() {
		return true;
	}
}
