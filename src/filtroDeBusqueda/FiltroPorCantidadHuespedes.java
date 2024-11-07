package filtroDeBusqueda;

import tpgrupal.Inmueble;

public class FiltroPorCantidadHuespedes implements FiltroDeBusqueda{
	private Integer capacidadMinima;

    public FiltroPorCantidadHuespedes(Integer capacidadMinima) {
    	//Se entiende que el usuario busca un inmueble con un minimo de capacidad de personas
        this.capacidadMinima = capacidadMinima;
    }

    @Override
    public boolean cumple(Inmueble inmueble) {
        return capacidadMinima == null || inmueble.getCapacidad() >= capacidadMinima;
    }
}
