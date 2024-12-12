package observer;

import inmueble.Inmueble;
import notificaciones.HomePagePublisher;

public class PaginaExterna implements Notificable {
	private HomePagePublisher publicador;

	public PaginaExterna(HomePagePublisher publicador) {
        this.publicador = publicador;
    }
	
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
		String mensaje = "No te pierdas esta oferta: Un inmueble " + i.getNombreDeTipoInmueble() + " a tan sólo " + i.getPrecioPorDia()
				+ " pesos.";
		publicador.publish(mensaje);
	}

	public HomePagePublisher getPublicador() {
		return publicador;
	}

	public void setPublicador(HomePagePublisher publicador) {
		this.publicador = publicador;
	}

}
