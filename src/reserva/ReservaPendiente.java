package reserva;

public class ReservaPendiente extends EstadoReserva {

	public void confirmar(Reserva r) {
		r.cambiarEstado(new ReservaConfirmada());
	}

	public void cancelar(Reserva r) {
		r.cambiarEstado(new ReservaCancelada());
	}

	public boolean esPendiente() {
		return true;
	}

}
