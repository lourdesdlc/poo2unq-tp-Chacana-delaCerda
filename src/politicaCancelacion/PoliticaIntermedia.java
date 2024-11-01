package politicaCancelacion;

import java.sql.Date;

public class PoliticaIntermedia implements PoliticaCancelacion{
	@Override
    public double calcularPenalidad(Date fechaCancelacion, Date fechaCheckIn) {
        long diasDiferencia = (fechaCheckIn.getTime() - fechaCancelacion.getTime()) / (1000 * 60 * 60 * 24);
        if (diasDiferencia >= 20) {
            return 0.0;
        } else if (diasDiferencia >= 10) {
            return 0.5 * calcularPrecioDiario(); // 50% de penalidad
        } else {
            return calcularPrecioDiario(); // Penalidad total
        }
    }

    private double calcularPrecioDiario() {
        // Lógica para calcular el precio diario
        return 100.0;
    }
}
