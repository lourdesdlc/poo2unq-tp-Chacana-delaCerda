package filtroDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import Inmueble.Inmueble;

public class FiltroCompuesto extends FiltroDeBusqueda {

	private List<FiltroDeBusqueda> filtros = new ArrayList<>();

	public FiltroCompuesto() {
		this.filtros = new ArrayList<>();
	}

	public void agregarFiltro(FiltroDeBusqueda filtro) {
		filtros.add(filtro);
	}

	@Override
	public boolean cumple(Inmueble inmueble) {

		return filtros.stream().allMatch(filtro -> filtro.cumple(inmueble));
	}

	public boolean tieneFiltrosObligatorios() {

		boolean tieneFiltroCiudad = filtros.stream().anyMatch(f -> f.esFiltroCiudad()); // subtareas
		boolean tieneFiltroFecha = filtros.stream().anyMatch(f -> f.esFiltroFecha());

		return tieneFiltroCiudad && tieneFiltroFecha;

	}

}
