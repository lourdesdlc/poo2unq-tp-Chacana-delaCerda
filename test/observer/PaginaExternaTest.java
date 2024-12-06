package observer;

import static org.mockito.Mockito.*;

import inmueble.Inmueble;
import notificaciones.HomePagePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaginaExternaTest {

	private PaginaExterna paginaExterna;
	private HomePagePublisher mockPublisher;
	private Inmueble mockInmueble;

	@BeforeEach
	void setUp() {
		mockPublisher = mock(HomePagePublisher.class);
		mockInmueble = mock(Inmueble.class);
		paginaExterna = new PaginaExterna();
		// Configuramos la dependencia inyectándola manualmente
		paginaExterna.setPublicador(mockPublisher);
	}

	@Test
	void testNotificarBajaDePrecio() {
		// Configurar el mock del inmueble
		when(mockInmueble.getNombreDeTipoInmueble()).thenReturn("departamento");
		when(mockInmueble.getPrecioPorDia()).thenReturn(2500.0);

		// Ejecutar el método
		paginaExterna.notificarBajaDePrecio("Baja de precio", mockInmueble);

		// Verificar que se publica el mensaje correcto
		String expectedMessage = "No te pierdas esta oferta: Un inmueble departamento a tan sólo 2500.0 pesos.";
		verify(mockPublisher).publish(expectedMessage);
	}

	@Test
	void testNotificarReserva_noHaceNada() {
		// Ejecutar el método
		paginaExterna.notificarReserva("Reserva creada", mockInmueble);

		// Verificar que no hay interacciones con el publicador
		verifyNoInteractions(mockPublisher);
	}

	@Test
	void testNotificarCancelacionReserva_noHaceNada() {
		// Ejecutar el método
		paginaExterna.notificarCancelacionReserva("Reserva cancelada", mockInmueble);

		// Verificar que no hay interacciones con el publicador
		verifyNoInteractions(mockPublisher);
	}
}
