package filtroDeBusqueda;

import inmueble.Inmueble;

public class FiltroPorCantidadHuespedes implements FiltroDeBusqueda{
	private int cantidadHuespedes;
	
    public FiltroPorCantidadHuespedes(Integer cantidadHuespedes) {
        this.cantidadHuespedes = cantidadHuespedes;
    }
    @Override
    public boolean cumple(Inmueble inmueble) {
        return inmueble.getCapacidad() >= cantidadHuespedes;
    }
    
	public int getCantidadHuespedes() {
		return cantidadHuespedes;
	}
}
