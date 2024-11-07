package filtroDeBusqueda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tpgrupal.Inmueble;

//Para garantizar los filtros obligatorios
public class CriterioBusqueda {
	 private FiltroCompuesto filtroCompuesto = new FiltroCompuesto();
	
	 // Constructor con filtros obligatorios
	 public CriterioBusqueda(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida) {
	     if (ciudad == null || fechaEntrada == null || fechaSalida == null) {
	         throw new IllegalArgumentException("Ciudad y fechas son filtros obligatorios.");
	     }
	     // Añadir los filtros obligatorios
	     filtroCompuesto.agregarFiltro(new FiltroPorCiudad(ciudad));
	     filtroCompuesto.agregarFiltro(new FiltroPorFecha(fechaEntrada, fechaSalida));
	 }
	
	 public void agregarFiltroOpcional(FiltroDeBusqueda filtro) {
	     if (filtro != null) {
	         filtroCompuesto.agregarFiltro(filtro);
	     }
	 }
	
	 public boolean cumple(Inmueble inmueble) {
	     return filtroCompuesto.cumple(inmueble);
	 }
	
	 public List<Inmueble> buscar(List<Inmueble> inmuebles) {
	     return inmuebles.stream()
	             .filter(this::cumple)
	             .collect(Collectors.toList());
	 }
}
