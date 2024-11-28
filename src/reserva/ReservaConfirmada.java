package reserva;

public class ReservaConfirmada extends EstadoReserva {

	public void finalizar(Reserva r) { // checkout...
		r.cambiarEstado(new ReservaFinalizada());
		// r.setEstado(new ReservaFinalizada()); rompe encapsulamiento?
	}

	public void cancelar(Reserva r) {
		r.cambiarEstado(new ReservaCancelada());
		// logica observer?
		// logica de cobro?
	}

	public boolean esConfirmada() {
		return true;
	}
}
