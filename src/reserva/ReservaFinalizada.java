package reserva;

public class ReservaFinalizada extends EstadoReserva {

	public void finalizar(Reserva r) {
		r.cambiarEstado(new ReservaFinalizada());

	}

	public boolean esFinalizada() {
		return true;
	}

}