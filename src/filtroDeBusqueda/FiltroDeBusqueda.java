package filtroDeBusqueda;

import tpgrupal.Inmueble;

public abstract class FiltroDeBusqueda {

	public abstract boolean cumple(Inmueble inmueble);

	public boolean esFiltroCiudad() {
		return false;
	}

	public boolean esFiltroFecha() {
		return false;
	}
}
