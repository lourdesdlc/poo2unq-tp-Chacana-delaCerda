package observer;

import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;

public class Notificador implements Notificable { // Sujeto

	private final List<Notificable> suscriptores = new ArrayList<>();

	@Override
	public void notificarReserva(String mensaje, Inmueble i) {
		for (Notificable suscriptor : suscriptores) {
			suscriptor.notificarReserva(mensaje, i);
		}
	}

	@Override
	public void notificarCancelacionReserva(String mensaje, Inmueble i) {
		for (Notificable suscriptor : suscriptores) {
			suscriptor.notificarCancelacionReserva(mensaje, i);
		}
	}

	@Override
	public void notificarBajaDePrecio(String mensaje, Inmueble i) {
		for (Notificable suscriptor : suscriptores) {
			suscriptor.notificarBajaDePrecio(mensaje, i);
		}
	}

	public void suscribir(Notificable suscriptor) {
		suscriptores.add(suscriptor);
	}

	public void desuscribir(Notificable suscriptor) {
		suscriptores.remove(suscriptor);
	}

	public List<Notificable> getSuscriptores() {
		return suscriptores;
	}
}