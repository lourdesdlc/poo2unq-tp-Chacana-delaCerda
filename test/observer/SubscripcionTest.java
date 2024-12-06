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

        // Crear una Subscripcion con la entidad mockeada
        subscripcion = new Subscripcion(mockEntidad);
    }

    @Test
    void testAgregarInteresEnInmueble() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        // Validar que el inmueble está en la lista de intereses
        subscripcion.notificarReserva("Reserva creada", mockInmuebleInteres);
        verify(mockEntidad).notificarReserva("Reserva creada", mockInmuebleInteres);
    }

    @Test
    void testEliminarInteresEnInmueble() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);
        subscripcion.eliminarInteresEnInmuble(mockInmuebleInteres);

        // Confirmar que ya no se envían notificaciones para este inmueble
        subscripcion.notificarReserva("Reserva eliminada", mockInmuebleInteres);
        verifyNoInteractions(mockEntidad);
    }

    @Test
    void testNotificarReservaInmuebleInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarReserva("Reserva confirmada", mockInmuebleInteres);

        // Verificar que se notificó correctamente
        verify(mockEntidad).notificarReserva("Reserva confirmada", mockInmuebleInteres);
    }

    @Test
    void testNotificarReservaInmuebleNoInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarReserva("Reserva confirmada", mockInmuebleNoInteres);

        // Verificar que no hubo notificación para el inmueble no interesado
        verifyNoInteractions(mockEntidad);
    }

    @Test
    void testNotificarCancelacionReservaInmuebleInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarCancelacionReserva("Reserva cancelada", mockInmuebleInteres);

        // Verificar que se notificó correctamente
        verify(mockEntidad).notificarCancelacionReserva("Reserva cancelada", mockInmuebleInteres);
    }

    @Test
    void testNotificarCancelacionReservaInmuebleNoInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarCancelacionReserva("Reserva cancelada", mockInmuebleNoInteres);

        // Verificar que no hubo notificación para el inmueble no interesado
        verifyNoInteractions(mockEntidad);
    }

    @Test
    void testNotificarBajaDePrecioInmuebleInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarBajaDePrecio("Baja de precio", mockInmuebleInteres);

        // Verificar que se notificó correctamente
        verify(mockEntidad).notificarBajaDePrecio("Baja de precio", mockInmuebleInteres);
    }

    @Test
    void testNotificarBajaDePrecioInmuebleNoInteres() {
        subscripcion.agregarInteresEnInmuble(mockInmuebleInteres);

        subscripcion.notificarBajaDePrecio("Baja de precio", mockInmuebleNoInteres);

        // Verificar que no hubo notificación para el inmueble no interesado
        verifyNoInteractions(mockEntidad);
    }
}
