package reserva;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservaFinalizadaTest {

    @Test
    void testEsFinalizada() {
        ReservaFinalizada reservaFinalizada = new ReservaFinalizada();
        assertTrue(reservaFinalizada.esFinalizada(), "La reserva debería estar finalizada");
    }

    @Test
    void testFinalizar() {
        Reserva reservaMock = mock(Reserva.class);
        ReservaFinalizada reservaFinalizada = new ReservaFinalizada();

        reservaFinalizada.finalizar(reservaMock);

        verify(reservaMock).cambiarEstado(any(ReservaFinalizada.class));
    }
}
