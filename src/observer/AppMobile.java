package observer;

import inmueble.Inmueble;
import notificaciones.PopUpWindow;

public class AppMobile implements Notificable {
	private PopUpWindow notificadorCelular;
	private String color;
	private int front;

	
	@Override
	public void notificarCancelacionReserva(String evento, Inmueble i) {
		// Bach:
		// el sitio de alquiler tiene su propio mensaje general de notifaciones
		// en este caso, la AppMobile tiene el suyo propio, y podria cambiar en un
		// futuro
		// es un detalle no mas... pero me hizo pensar

		String mensajePerzonalizado = "El/la " + i.getNombreDeTipoInmueble()
				+ " que te interesa se ha liberado! Corre a reservarlo!";

		notificadorCelular.popUp(mensajePerzonalizado, this.getColor(), this.getFront());

	}

	@Override
	public void notificarBajaDePrecio(String evento, Inmueble i) {
		// disponible para su uso en un futuro

	}

	@Override
	public void notificarReserva(String evento, Inmueble i) {
		// disponible para su uso en un futuro
	}

	public PopUpWindow getNotificadorCelular() {
		return notificadorCelular;
	}

	public void setNotificadorCelular(PopUpWindow notificadorCelular) {
		this.notificadorCelular = notificadorCelular;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getFront() {
		return front;
	}

	public void setFront(int front) {
		this.front = front;
	}

}
