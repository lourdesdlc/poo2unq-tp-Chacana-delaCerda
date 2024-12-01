package observadores;

import java.util.HashSet;
import java.util.Set;

import inmueble.Inmueble;
import notificaciones.Notificable;

public class Subscripcion implements Notificable {
	private Notificable entidad; // sitio , appMobile, cualquier entidad que implemente notifiacable.
	private Set<Inmueble> inmueblesDeInteres;

	public Subscripcion(Notificable entidad) {
		this.entidad = entidad;
		this.inmueblesDeInteres = new HashSet<>();
	}

	// las notificaciones solo van a llegar, si la entidad tiene interes en el
	// inmueble
	@Override
	public void notificarReserva(String evento, Inmueble i) {
		if (inmueblesDeInteres.contains(i)) {
			entidad.notificarReserva(evento, i);
		}
	}

	@Override
	public void notificarCancelacionReserva(String evento, Inmueble i) {
		if (inmueblesDeInteres.contains(i)) {
			entidad.notificarCancelacionReserva(evento, i);
		}

	}

	@Override
	public void notificarBajaDePrecio(String evento, Inmueble i) {
		if (inmueblesDeInteres.contains(i)) {
			entidad.notificarBajaDePrecio(evento, i);
		}

	}

	public void agregarInteresEnInmuble(Inmueble i) {
		inmueblesDeInteres.add(i);
	}

	public void eliminarInteresEnInmuble(Inmueble i) {
		inmueblesDeInteres.remove(i);
	}

}
