package reserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservaPendienteTest {

    @Test
    void testEsPendiente() {
        ReservaPendiente reservaPendiente = new ReservaPendiente();
        assertTrue(reservaPendiente.esPendiente(), "La reserva debería estar pendiente");
    }

    @Test
    void testConfirmar() {
        Reserva reservaMock = mock(Reserva.class);
        ReservaPendiente reservaPendiente = new ReservaPendiente();

        reservaPendiente.confirmar(reservaMock);

        verify(reservaMock).cambiarEstado(any(ReservaConfirmada.class));
    }

    @Test
    void testCancelar() {
        Reserva reservaMock = mock(Reserva.class);
        ReservaPendiente reservaPendiente = new ReservaPendiente();

        reservaPendiente.cancelar(reservaMock);

        verify(reservaMock).cambiarEstado(any(ReservaCancelada.class));
    }
}
