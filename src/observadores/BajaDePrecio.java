package observadores;

import java.util.Set;

import notificaciones.HomePagePublisher;
import sitioWeb.SitioWeb;

public class BajaDePrecio extends Interesado {
	private HomePagePublisher publicador;
	
	public BajaDePrecio(SitioWeb web, Set<String> inmuebleInteresado, HomePagePublisher publicador) {
		super(web, inmuebleInteresado);
		this.publicador = publicador;
	}

	

	@Override
	public void recibirNotificacionDe(String mensaje, String inmueble) {
		if (inmuebleInteresado.contains(inmueble)) // solo publica si es de su interes.
			publicador.publish(mensaje);
	}

}
