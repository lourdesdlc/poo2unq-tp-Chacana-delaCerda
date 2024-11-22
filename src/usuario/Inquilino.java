package usuario;


import java.util.List;

import ranking.Rankeable;
import reserva.Reserva;


public interface Inquilino extends Rankeable{

	public void agregarReserva(Reserva reserva);
	
	public List<Reserva> getReservas();
	
	public List<Reserva> getReservasFuturas();
	
	public List<Reserva> getReservasPorCiudad(String ciudad);
	
	public List<String> getCiudadesConReservas();
}
