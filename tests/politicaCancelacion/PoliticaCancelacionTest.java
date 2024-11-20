package politicaCancelacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PoliticaCancelacionTest {

    @Test
    void testPoliticaSinReembolso() {
        PoliticaCancelacion politica = new PoliticaSinReembolso();
        LocalDate fechaEntrada = LocalDate.now().plusDays(10);
        LocalDate fechaSalida = LocalDate.now().plusDays(15);
        double precioTotal = 2000.0;

        double penalidad = politica.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
        assertEquals(2000.0, penalidad);
    }

    @Test
    void testPoliticaIntermediaConMasDe20Dias() {
        PoliticaCancelacion politica = new PoliticaIntermedia();
        LocalDate fechaEntrada = LocalDate.now().plusDays(25);
        LocalDate fechaSalida = LocalDate.now().plusDays(30);
        double precioTotal = 3000.0;

        double penalidad = politica.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
        assertEquals(0.0, penalidad);
    }

    @Test
    void testPoliticaIntermediaConEntre10Y20Dias() {
        PoliticaCancelacion politica = new PoliticaIntermedia();
        LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        LocalDate fechaSalida = LocalDate.now().plusDays(20);
        double precioTotal = 3000.0;

        double penalidad = politica.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
        assertEquals(1500.0, penalidad);
    }

    @Test
    void testPoliticaIntermediaConMenosDe10Dias() {
        PoliticaCancelacion politica = new PoliticaIntermedia();
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        LocalDate fechaSalida = LocalDate.now().plusDays(10);
        double precioTotal = 3000.0;

        double penalidad = politica.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
        assertEquals(3000.0, penalidad);
    }

    @Test
    void testPoliticaGratuitaConMasDe10Dias() {
        PoliticaCancelacion politica = new PoliticaGratuita();
        LocalDate fechaEntrada = LocalDate.now().plusDays(15);
        LocalDate fechaSalida = LocalDate.now().plusDays(20);
        double precioTotal = 2500.0;

        double penalidad = politica.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
        assertEquals(0.0, penalidad);
    }

    @Test
    void testPoliticaGratuitaConMenosDe10Dias() {
        PoliticaCancelacion politica = new PoliticaGratuita();
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        LocalDate fechaSalida = LocalDate.now().plusDays(10);
        double precioTotal = 2500.0;

        int diasReservados = (int) (fechaSalida.toEpochDay() - fechaEntrada.toEpochDay());
        double penalidadEsperada = precioTotal - (2 * (precioTotal / diasReservados));

        double penalidad = politica.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
        assertEquals(penalidadEsperada, penalidad, 0.01);
    }
}