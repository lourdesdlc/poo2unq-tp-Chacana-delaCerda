package observer;

import static org.mockito.Mockito.*;

import inmueble.Inmueble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubscripcionTest {

    private Subscripcion subscripcion;
    private Notificable mockEntidad;
    private Inmueble mockInmuebleInteres;
    private Inmueble mockInmuebleNoInteres;

    @BeforeEach
    void setUp() {
        mockEntidad = mock(Notificable.class);
        mockInmuebleInteres = mock(Inmueble.class);
        mockInmuebleNoInteres = mock(Inmueble.class);

        subscripcion = new Subscripcion(mockEntidad);
    }

    @Test
    void testAgregarInteresEnInmueble() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarReserva("Reserva creada", mockInmuebleInteres);
        verify(mockEntidad).notificarReserva("Reserva creada", mockInmuebleInteres);
    }

    @Test
    void testEliminarInteresEnInmueble() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);
        subscripcion.eliminarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarReserva("Reserva eliminada", mockInmuebleInteres);
        verifyNoInteractions(mockEntidad);
    }

    @Test
    void testNotificarReservaInmuebleInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarReserva("Reserva confirmada", mockInmuebleInteres);

        verify(mockEntidad).notificarReserva("Reserva confirmada", mockInmuebleInteres);
    }

    @Test
    void testNotificarReservaInmuebleNoInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarReserva("Reserva confirmada", mockInmuebleNoInteres);

        verifyNoInteractions(mockEntidad);
    }

    @Test
    void testNotificarCancelacionReservaInmuebleInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarCancelacionReserva("Reserva cancelada", mockInmuebleInteres);

        verify(mockEntidad).notificarCancelacionReserva("Reserva cancelada", mockInmuebleInteres);
    }

    @Test
    void testNotificarCancelacionReservaInmuebleNoInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarCancelacionReserva("Reserva cancelada", mockInmuebleNoInteres);

        verifyNoInteractions(mockEntidad);
    }

    @Test
    void testNotificarBajaDePrecioInmuebleInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarBajaDePrecio("Baja de precio", mockInmuebleInteres);

        verify(mockEntidad).notificarBajaDePrecio("Baja de precio", mockInmuebleInteres);
    }

    @Test
    void testNotificarBajaDePrecioInmuebleNoInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarBajaDePrecio("Baja de precio", mockInmuebleNoInteres);

        verifyNoInteractions(mockEntidad);
    }
}
