package usuario;


import java.util.List;

import inmueble.Inmueble;
import ranking.Rankeable;

public interface Propietario extends Rankeable{
	
	public void agregarInmueble(Inmueble inmueble);
    public List<Inmueble> getInmuebles();
}
