package reserva;

//Patrón State para estados de Reserva
public abstract class EstadoReserva {
	private Reserva reserva;

	public abstract void confirmar();

	public abstract void cancelar();

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
