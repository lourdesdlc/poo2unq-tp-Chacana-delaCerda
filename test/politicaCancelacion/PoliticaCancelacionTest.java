package politicaCancelacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import reserva.Reserva;

import static org.mockito.Mockito.*;

class PoliticaCancelacionTest {

    @Test
    void testPoliticaSinReembolso() {
        PoliticaCancelacion politica = new PoliticaSinReembolso();
        Reserva reserva = mock(Reserva.class);
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.now().plusDays(10));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.now().plusDays(15));
        when(reserva.getPrecioTotal()).thenReturn(2000.0);

        double penalidad = politica.calcularPenalidad(reserva);
        assertEquals(2000.0, penalidad);
    }

    @Test
    void testPoliticaIntermediaConMasDe20Dias() {
        PoliticaCancelacion politica = new PoliticaIntermedia();
        Reserva reserva = mock(Reserva.class);
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.now().plusDays(25));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.now().plusDays(30));
        when(reserva.getPrecioTotal()).thenReturn(3000.0);

        double penalidad = politica.calcularPenalidad(reserva);
        assertEquals(0.0, penalidad);
    }

    @Test
    void testPoliticaIntermediaConEntre10Y20Dias() {
        PoliticaCancelacion politica = new PoliticaIntermedia();
        Reserva reserva = mock(Reserva.class);
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.now().plusDays(15));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.now().plusDays(20));
        when(reserva.getPrecioTotal()).thenReturn(3000.0);

        double penalidad = politica.calcularPenalidad(reserva);
        assertEquals(1500.0, penalidad);
    }

    @Test
    void testPoliticaIntermediaConMenosDe10Dias() {
        PoliticaCancelacion politica = new PoliticaIntermedia();
        Reserva reserva = mock(Reserva.class);
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.now().plusDays(5));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.now().plusDays(10));
        when(reserva.getPrecioTotal()).thenReturn(3000.0);

        double penalidad = politica.calcularPenalidad(reserva);
        assertEquals(3000.0, penalidad);
    }

    @Test
    void testPoliticaGratuitaConMasDe10Dias() {
        PoliticaCancelacion politica = new PoliticaGratuita();
        Reserva reserva = mock(Reserva.class);
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.now().plusDays(15));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.now().plusDays(20));
        when(reserva.getPrecioTotal()).thenReturn(2500.0);

        double penalidad = politica.calcularPenalidad(reserva);
        assertEquals(0.0, penalidad);
    }

    @Test
    void testPoliticaGratuitaConMenosDe10Dias() {
        PoliticaCancelacion politica = new PoliticaGratuita();
        Reserva reserva = mock(Reserva.class);
        LocalDate fechaEntrada = LocalDate.now().plusDays(5);
        LocalDate fechaSalida = LocalDate.now().plusDays(10);
        double precioTotal = 2500.0;

        when(reserva.getFechaEntrada()).thenReturn(fechaEntrada);
        when(reserva.getFechaSalida()).thenReturn(fechaSalida);
        when(reserva.getPrecioTotal()).thenReturn(precioTotal);

        int diasReservados = (int) (fechaSalida.toEpochDay() - fechaEntrada.toEpochDay());
        double penalidadEsperada = precioTotal - (2 * (precioTotal / diasReservados));

        double penalidad = politica.calcularPenalidad(reserva);
        assertEquals(penalidadEsperada, penalidad, 0.01);
    }
}
