package reserva;

public class ReservaPendiente extends EstadoReserva {

	@Override
	public void confirmar() {
		this.getReserva().setEstado(new ReservaConfirmada());

	}

	@Override
	public void cancelar() {
		this.getReserva().setEstado(new ReservaCancelada());

	}

}
