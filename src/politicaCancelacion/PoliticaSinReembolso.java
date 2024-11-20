package politicaCancelacion;

import reserva.Reserva;

public class PoliticaSinReembolso extends PoliticaCancelacion{
	@Override
    public double calcularPenalidad(Reserva reserva) {
		//Se paga el total de la reserva
        return reserva.getPrecioTotal();
    }
}
