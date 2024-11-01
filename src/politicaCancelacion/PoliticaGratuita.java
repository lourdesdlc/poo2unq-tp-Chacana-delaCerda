package politicaCancelacion;

import java.sql.Date;

public class PoliticaGratuita implements PoliticaCancelacion{
	@Override
    public double calcularPenalidad(Date fechaCancelacion, Date fechaCheckIn) {
        long diasDiferencia = (fechaCheckIn.getTime() - fechaCancelacion.getTime()) / (1000 * 60 * 60 * 24);
        if (diasDiferencia >= 10) {
            return 0.0; // Cancelación gratuita
        } else {
            return 2.0 * calcularPrecioDiario(); // Penalidad de dos días
        }
    }

    private double calcularPrecioDiario() {
        // Lógica para calcular el precio diario
        return 100.0;
    }
}
