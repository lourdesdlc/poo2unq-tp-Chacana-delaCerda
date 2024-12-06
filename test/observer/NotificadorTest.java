package observer;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inmueble.Inmueble;

class NotificadorTest {

    private Notificador notificador;
    private Notificable mockSuscriptor1;
    private Notificable mockSuscriptor2;
    private Inmueble mockInmueble;

    @BeforeEach
    void setUp() {
        notificador = new Notificador();
        mockSuscriptor1 = mock(Notificable.class);
        mockSuscriptor2 = mock(Notificable.class);
        mockInmueble = mock(Inmueble.class);
    }

    @Test
    void testSuscribir() {
        notificador.suscribir(mockSuscriptor1);
        notificador.suscribir(mockSuscriptor2);

        List<Notificable> suscriptores = notificador.getSuscriptores();

        // Validar que los suscriptores están en la lista
        assert(suscriptores.contains(mockSuscriptor1));
        assert(suscriptores.contains(mockSuscriptor2));
    }

    @Test
    void testDesuscribir() {
        notificador.suscribir(mockSuscriptor1);
        notificador.desuscribir(mockSuscriptor1);

        List<Notificable> suscriptores = notificador.getSuscriptores();

        // Validar que el suscriptor fue removido
        assert(!suscriptores.contains(mockSuscriptor1));
    }

    @Test
    void testNotificarReserva() {
        notificador.suscribir(mockSuscriptor1);
        notificador.suscribir(mockSuscriptor2);

        String mensaje = "Reserva confirmada";

        notificador.notificarReserva(mensaje, mockInmueble);

        // Verificar que ambos suscriptores fueron notificados
        verify(mockSuscriptor1).notificarReserva(mensaje, mockInmueble);
        verify(mockSuscriptor2).notificarReserva(mensaje, mockInmueble);
    }

    @Test
    void testNotificarCancelacionReserva() {
        notificador.suscribir(mockSuscriptor1);

        String mensaje = "Reserva cancelada";

        notificador.notificarCancelacionReserva(mensaje, mockInmueble);

        // Verificar que el suscriptor fue notificado
        verify(mockSuscriptor1).notificarCancelacionReserva(mensaje, mockInmueble);
    }

    @Test
    void testNotificarBajaDePrecio() {
        notificador.suscribir(mockSuscriptor1);

        String mensaje = "El precio ha bajado";

        notificador.notificarBajaDePrecio(mensaje, mockInmueble);

        // Verificar que el suscriptor fue notificado
        verify(mockSuscriptor1).notificarBajaDePrecio(mensaje, mockInmueble);
    }

    @Test
    void testSinSuscriptoresNoLanzaNotificaciones() {
        String mensaje = "Sin suscriptores";

        notificador.notificarReserva(mensaje, mockInmueble);

        // No debería haber interacciones con suscriptores
        verifyNoInteractions(mockSuscriptor1);
    }
}
