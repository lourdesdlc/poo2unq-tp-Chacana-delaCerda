package observer;

import inmueble.Inmueble;

public interface Notificable {
	void notificarReserva(String evento, Inmueble i);

	void notificarCancelacionReserva(String evento, Inmueble i);

	void notificarBajaDePrecio(String evento, Inmueble i);
}
