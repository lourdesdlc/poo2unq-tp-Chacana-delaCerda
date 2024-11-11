package filtroDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import tpgrupal.Inmueble;

public class FiltroCompuesto extends FiltroDeBusqueda {

	private List<FiltroDeBusqueda> filtros = new ArrayList<>();

	public FiltroCompuesto() {
		super();
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

		boolean tieneFiltroCiudad = filtros.stream().anyMatch(f -> f.esFiltroCiudad());
		boolean tieneFiltroFecha = filtros.stream().anyMatch(f -> f.esFiltroFecha());

		return tieneFiltroCiudad && tieneFiltroFecha;

	}

}
