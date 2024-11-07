package filtroDeBusqueda;

import tpgrupal.Inmueble;

public class FiltroPorCiudad implements FiltroDeBusqueda {
	private String ciudad;
	
	public FiltroPorCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean cumple(Inmueble inmueble) {
        return inmueble.getCiudad().equals(ciudad);
    }

}