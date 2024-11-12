package politicaCancelacion;

import java.time.LocalDate;

public class PoliticaSinReembolso extends PoliticaCancelacion{
	@Override
    public double calcularPenalidad(LocalDate fechaEntrada, LocalDate fechaSalida, double precioTotal) {
		//Se paga el total de la reserva
        return precioTotal;
    }
}
