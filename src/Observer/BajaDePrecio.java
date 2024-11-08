package Observer;

import java.util.Set;

import notificaciones.HomePagePublisher;
import tpgrupal.Interesado;
import tpgrupal.SitioWeb;

public class BajaDePrecio extends Interesado {

	public BajaDePrecio(SitioWeb web, Set<String> inmuebleInteresado, HomePagePublisher publicador) {
		super(web, inmuebleInteresado);
		this.publicador = publicador;
	}

	private HomePagePublisher publicador;

	@Override
	public void recibirNotificacionDe(String mensaje, String inmueble) {
		if (inmuebleInteresado.contains(inmueble)) // solo publica si es de su interes.
			publicador.publish(mensaje);
	}

}
