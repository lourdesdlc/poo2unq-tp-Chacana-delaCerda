package politicaCancelacion;

import java.sql.Date;

//Patrón Strategy para Políticas de Cancelación
public interface PoliticaCancelacion {
	double calcularPenalidad(Date fechaCancelacion, Date fechaCheckIn);
}
