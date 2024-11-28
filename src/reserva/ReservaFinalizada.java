package reserva;

public class ReservaFinalizada extends EstadoReserva { // el resto responde la clase padre

	public void finalizar(Reserva r) {
		r.cambiarEstado(new ReservaFinalizada());

	}

	public boolean esFinalizada() {
		return true;
	}

}