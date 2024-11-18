package usuario;


import java.util.List;

import Inmueble.Inmueble;

public interface Propietario{
	
	public void agregarInmueble(Inmueble inmueble);
    public List<Inmueble> getInmuebles();
}
