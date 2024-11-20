package politicaCancelacion;

import reserva.Reserva;

public class PoliticaIntermedia extends PoliticaCancelacion{
	@Override
	public double calcularPenalidad(Reserva reserva) {
		
        int diasAntesDeInicio = diasAntesDeInicio(reserva.getFechaEntrada());
        
        if (diasAntesDeInicio > 20) {
            return 0.0; 
        } else if (diasAntesDeInicio >= 10) {
            return reserva.getPrecioTotal() * 0.5; 
        } else {
            return reserva.getPrecioTotal(); 
        }
    }
}
