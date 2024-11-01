package politicaCancelacion;

import java.sql.Date;

public class PoliticaSinReembolso implements PoliticaCancelacion{
	@Override
    public double calcularPenalidad(Date fechaCancelacion, Date fechaCheckIn) {
        return calcularPrecioDiario(); // Penalidad total
    }

    private double calcularPrecioDiario() {
        // Lógica para calcular el precio diario
        return 100.0;
    }
}
