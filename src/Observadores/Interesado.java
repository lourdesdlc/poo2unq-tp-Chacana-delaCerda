package Observadores;

import java.util.Set;

import sitioWeb.SitioWeb;

public abstract class Interesado {

	private SitioWeb web;
	protected Set<String> inmuebleInteresado;

	public Interesado(SitioWeb web, Set<String> inmuebleInteresado) {
		this.web = web;
		this.inmuebleInteresado = inmuebleInteresado;
	}
//COMENTADO PARA QUE NO DE ERROR
/*
	public void suscribirseASitio(SitioWeb web) {
		this.web = web;
		this.web.registrarIntereado(this);
	}

	public void desubcribirseDelSitio() {
		this.web.desubscribirIntereado(this);
		this.web = null; // RARO

	}
*/
	public void agregarInteresEn(String inmueble) {
		inmuebleInteresado.add(inmueble);

	}

	public void eliminarInteresEn(String inmueble) {
		inmuebleInteresado.remove(inmueble);

	}

	public abstract void recibirNotificacionDe(String mensaje, String inmueble); // pensar en enum inmueble?

}
