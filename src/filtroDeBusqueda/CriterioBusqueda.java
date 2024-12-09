package filtroDeBusqueda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;

public class CriterioBusqueda {
	
	private List<FiltroDeBusqueda> filtros = new ArrayList<>();
	
    public CriterioBusqueda(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
    	
    	this.filtros.add(new FiltroPorCiudad(ciudad));
        this.filtros.add(new FiltroPorFecha(fechaEntrada, fechaSalida));
    }
    
    public CriterioBusqueda agregarFiltro(FiltroDeBusqueda filtro) {
        filtros.add(filtro);
        return this;
    }
    
    public boolean cumple(Inmueble inmueble) {
        return filtros.stream().allMatch(filtro -> filtro.cumple(inmueble));
    }
}
