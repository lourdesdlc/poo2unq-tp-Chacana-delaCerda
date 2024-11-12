package Observadores;

import java.util.Set;

import notificaciones.PopUpWindow;
import sitioWeb.SitioWeb;

public class Cancelacion extends Interesado {
	private PopUpWindow notificadorCelular;
	private String color;
	private int front;

	public Cancelacion(SitioWeb web, Set<String> inmuebleInteresado, PopUpWindow notificadorCelular, String color,
			int front) {
		super(web, inmuebleInteresado);
		this.notificadorCelular = notificadorCelular;
		this.color = color;
		this.front = front;
	}

	@Override
	public void recibirNotificacionDe(String mensaje, String inmueble) {
		if (inmuebleInteresado.contains(inmueble)) // solo publica si es de su interes.
			notificadorCelular.popUp(mensaje, this.getColor(), this.getFront()); // que onda esto? que le asignamos?
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
