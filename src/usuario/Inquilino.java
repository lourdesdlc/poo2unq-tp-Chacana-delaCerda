package usuario;


import java.util.List;

import reserva.Reserva;


public interface Inquilino{

	public void agregarReserva(Reserva reserva);

	public void cancelarReserva(Reserva reserva);
	
	public List<Reserva> getReservas();
	
	public List<Reserva> getReservasFuturas();
	
	public List<Reserva> getReservasPorCiudad(String ciudad);
	
	public List<String> getCiudadesConReservas();
}
