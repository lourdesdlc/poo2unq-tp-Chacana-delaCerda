package politicaCancelacion;

import java.time.LocalDate;

public class PoliticaIntermedia extends PoliticaCancelacion{
	@Override
	public double calcularPenalidad(LocalDate fechaInicio, LocalDate fechaSalida, double precioTotal) {
		
        int diasAntesDeInicio = diasAntesDeInicio(fechaInicio);
        
        if (diasAntesDeInicio > 20) {
            return 0.0; 
        } else if (diasAntesDeInicio >= 10) {
            return precioTotal * 0.5; 
        } else {
            return precioTotal; 
        }
    }
}
