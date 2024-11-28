package reserva;

public class ReservaCancelada extends EstadoReserva { // tipada con Concretable

	// bach: tiene un comportamiento o cambio de estado? creo que no

	public boolean esCancelada() {
		return true;
	}
}
