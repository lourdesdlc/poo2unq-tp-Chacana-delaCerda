package reserva;

//Patrón State para estados de Reserva
public abstract class EstadoReserva implements Concretable {

	// esta clase evita la repeticion de codigo.
	// Ademas de no forzar a las subclases a implementar metodos que no usan.
	// cumpliendo " I " de SOLID.

	public void confirmar(Reserva r) {
		throw new RuntimeException("No es posible confirmar la reserva en su estado actual.");
	}

	public void cancelar(Reserva r) {
		throw new RuntimeException("No es posible cancelar la reserva en su estado actual.");

	}

	public void finalizar(Reserva r) {
		throw new RuntimeException("No es posible finalizar la reserva en su estado actual.");

	}

	public boolean esConfirmada() { // de aca para abajo, son mensajes que se redefinen donde sea necesario
		return false;
	}

	public boolean esPendiente() {
		return false;
	}

	public boolean esFinalizada() {
		return false;
	}

	public boolean esCancelada() {
		return false;
	}
}
