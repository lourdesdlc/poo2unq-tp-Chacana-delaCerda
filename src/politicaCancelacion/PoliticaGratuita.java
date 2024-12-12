package politicaCancelacion;

import reserva.Reserva;

public class PoliticaGratuita extends PoliticaCancelacion{

	@Override
	public double calcularPenalidad(Reserva reserva) {
		
		int diasAntesDeInicio = diasAntesDeInicio(reserva.getFechaEntrada());
		
		if (diasAntesDeInicio >= 10) {
			return 0;
		} else {
			int diasReservados = (int) (reserva.getFechaSalida().toEpochDay() - reserva.getFechaEntrada().toEpochDay());
			return reserva.getPrecioTotal() - (2 * (reserva.getPrecioTotal() / diasReservados));
		}
	}
}
