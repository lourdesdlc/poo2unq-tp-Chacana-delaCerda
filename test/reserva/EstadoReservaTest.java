package reserva;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EstadoReservaTest {

    @Test
    void testConfirmarLanzaExcepcion() {

        EstadoReserva estado = new EstadoReserva() {};

        Reserva reservaMock = mock(Reserva.class); 
        Exception exception = assertThrows(RuntimeException.class, () -> estado.confirmar(reservaMock));
        assertEquals("No es posible confirmar la reserva en su estado actual.", exception.getMessage());
    }

    @Test
    void testCancelarLanzaExcepcion() {
        EstadoReserva estado = new EstadoReserva() {};

        Reserva reservaMock = mock(Reserva.class);
        Exception exception = assertThrows(RuntimeException.class, () -> estado.cancelar(reservaMock));
        assertEquals("No es posible cancelar la reserva en su estado actual.", exception.getMessage());
    }

    @Test
    void testFinalizarLanzaExcepcion() {
        EstadoReserva estado = new EstadoReserva() {};

        Reserva reservaMock = mock(Reserva.class);
        Exception exception = assertThrows(RuntimeException.class, () -> estado.finalizar(reservaMock));
        assertEquals("No es posible finalizar la reserva en su estado actual.", exception.getMessage());
    }

    @Test
    void testEsConfirmadaPorDefecto() {
        EstadoReserva estado = new EstadoReserva() {};
        assertFalse(estado.esConfirmada(), "El estado no debería estar confirmado por defecto.");
    }

    @Test
    void testEsPendientePorDefecto() {
        EstadoReserva estado = new EstadoReserva() {};
        assertFalse(estado.esPendiente(), "El estado no debería estar pendiente por defecto.");
    }

    @Test
    void testEsFinalizadaPorDefecto() {
        EstadoReserva estado = new EstadoReserva() {};
        assertFalse(estado.esFinalizada(), "El estado no debería estar finalizado por defecto.");
    }

    @Test
    void testEsCanceladaPorDefecto() {
        EstadoReserva estado = new EstadoReserva() {};
        assertFalse(estado.esCancelada(), "El estado no debería estar cancelado por defecto.");
    }
}
