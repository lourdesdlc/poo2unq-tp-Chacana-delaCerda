package politicaCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import reserva.Reserva;

//Patrón Strategy para Políticas de Cancelación
public abstract class PoliticaCancelacion {
	
	public int diasAntesDeInicio(LocalDate fechaInicio) {
		return (int) ChronoUnit.DAYS.between(LocalDate.now(), fechaInicio);
	}
	
	public abstract double calcularPenalidad(Reserva reserva);
}
