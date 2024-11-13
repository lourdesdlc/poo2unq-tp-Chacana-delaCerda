package politicaCancelacion;

import java.time.LocalDate;

public class PoliticaGratuita extends PoliticaCancelacion{
	@Override
	public double calcularPenalidad(LocalDate fechaInicio, LocalDate fechaFinal, double precioTotal) {
		
		int diasAntesDeInicio = diasAntesDeInicio(fechaInicio);
		
		if (diasAntesDeInicio <= 0) {
			//REVISAR ESTA OPCION
			return 0;
		}
		if (diasAntesDeInicio >= 10) {
			return 0;
		} else {
			int diasReservados = (int) (fechaFinal.toEpochDay() - fechaInicio.toEpochDay());
			return precioTotal - (2 * (precioTotal / diasReservados));
		}
	}
}
