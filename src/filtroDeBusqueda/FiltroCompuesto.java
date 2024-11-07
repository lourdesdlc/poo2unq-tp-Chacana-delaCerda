package filtroDeBusqueda;

import java.util.ArrayList;
import java.util.List;

import tpgrupal.Inmueble;

class FiltroCompuesto implements FiltroDeBusqueda {
    private List<FiltroDeBusqueda> filtros = new ArrayList<>();

    public void agregarFiltro(FiltroDeBusqueda filtro) {
        filtros.add(filtro);
    }

    @Override
    public boolean cumple(Inmueble inmueble) {
        return filtros.stream().allMatch(filtro -> filtro.cumple(inmueble));
    }
}
