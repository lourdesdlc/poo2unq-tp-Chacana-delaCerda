package politicaCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Patrón Strategy para Políticas de Cancelación
public abstract class PoliticaCancelacion {
	
	public int diasAntesDeInicio(LocalDate fechaInicio) {
		return (int) ChronoUnit.DAYS.between(LocalDate.now(), fechaInicio);
	}
	
	public abstract double calcularPenalidad(LocalDate fechaEntrada, LocalDate fechaSalida, double precioTotal);
}
