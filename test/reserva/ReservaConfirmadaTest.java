package reserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservaConfirmadaTest {

    @Test
    void testEsConfirmada() {
        ReservaConfirmada reservaConfirmada = new ReservaConfirmada();
        assertTrue(reservaConfirmada.esConfirmada(), "La reserva debería estar confirmada");
    }

    @Test
    void testFinalizar() {
        Reserva reservaMock = mock(Reserva.class);
        ReservaConfirmada reservaConfirmada = new ReservaConfirmada();

        reservaConfirmada.finalizar(reservaMock);

        verify(reservaMock).cambiarEstado(any(ReservaFinalizada.class)); // Verifica que se cambió el estado
    }

    @Test
    void testCancelar() {
        Reserva reservaMock = mock(Reserva.class);
        ReservaConfirmada reservaConfirmada = new ReservaConfirmada();

        reservaConfirmada.cancelar(reservaMock);

        verify(reservaMock).cambiarEstado(any(ReservaCancelada.class)); // Verifica que se cambió el estado
    }
}
