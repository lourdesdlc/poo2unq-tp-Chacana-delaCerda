package filtroDeBusqueda;

import inmueble.Inmueble;

public class FiltroPorCantidadHuespedes implements FiltroDeBusqueda{
	private Integer cantidadHuespedes;
	
    public FiltroPorCantidadHuespedes(Integer cantidadHuespedes) {
        this.cantidadHuespedes = cantidadHuespedes;
    }
    @Override
    public boolean cumple(Inmueble inmueble) {
        return cantidadHuespedes == null || inmueble.getCapacidad() >= cantidadHuespedes;
    }
}
