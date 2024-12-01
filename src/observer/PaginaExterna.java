package observer;

import java.util.List;
import java.util.Set;

import inmueble.Inmueble;
import notificaciones.HomePagePublisher;
import sitioWeb.SitioWeb;

public class PaginaExterna implements Notificable {
	private HomePagePublisher publicador;

	@Override
	public void notificarReserva(String evento, Inmueble i) {
		// disponible para su uso en un futuro

	}

	@Override
	public void notificarCancelacionReserva(String evento, Inmueble i) {
		// disponible para su uso en un futuro

	}

	@Override
	public void notificarBajaDePrecio(String evento, Inmueble i) {
		// Bach:
		// el sitio de alquiler tiene su propio mensaje general de notifaciones
		// en este caso, la AppMobile tiene el suyo propio, y podria cambiar en un
		// futuro
		// es un detalle no mas... pero me hizo pensar

		String mensaje = "No te pierdas esta oferta: Un inmueble " + i.getTipo() + " a tan sólo " + i.getPrecioPorDia()
				+ " pesos.";
		publicador.publish(mensaje);
	}

}
