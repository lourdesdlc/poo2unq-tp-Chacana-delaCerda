package usuario;


import java.util.List;

import Inmueble.Inmueble;
import ranking.Rankeable;

public interface Propietario extends Rankeable{
	
	public void agregarInmueble(Inmueble inmueble);
    public List<Inmueble> getInmuebles();
}
