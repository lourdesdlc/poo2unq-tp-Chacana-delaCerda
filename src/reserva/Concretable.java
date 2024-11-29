package reserva;

public interface Concretable {
	public void confirmar(Reserva r);

	public void cancelar(Reserva r);

	public void finalizar(Reserva r);

	public boolean esConfirmada(); // de aca para abajo, son mensajes que se redefinen donde sea necesario

	public boolean esPendiente();

	public boolean esFinalizada();

	public boolean esCancelada();

}
