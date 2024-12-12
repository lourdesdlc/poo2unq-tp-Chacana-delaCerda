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
		paginaExterna = new PaginaExterna(mockPublisher);

		paginaExterna.setPublicador(mockPublisher);
	}

	@Test
	void testNotificarBajaDePrecio() {
		when(mockInmueble.getNombreDeTipoInmueble()).thenReturn("departamento");
		when(mockInmueble.getPrecioPorDia()).thenReturn(2500.0);


		paginaExterna.notificarBajaDePrecio("Baja de precio", mockInmueble);


		String expectedMessage = "No te pierdas esta oferta: Un inmueble departamento a tan sólo 2500.0 pesos.";
		verify(mockPublisher).publish(expectedMessage);
	}

	@Test
	void testNotificarReserva_noHaceNada() {

		paginaExterna.notificarReserva("Reserva creada", mockInmueble);


		verifyNoInteractions(mockPublisher);
	}

	@Test
	void testNotificarCancelacionReserva_noHaceNada() {

		paginaExterna.notificarCancelacionReserva("Reserva cancelada", mockInmueble);


		verifyNoInteractions(mockPublisher);
	}
}
