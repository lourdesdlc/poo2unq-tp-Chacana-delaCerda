package reserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import inmueble.Inmueble;
import usuario.Usuario;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {
	private Reserva reserva;
	private Usuario inquilinoMock;
	private Inmueble inmuebleMock;
	private Concretable estadoMock;

	@BeforeEach
	void setUp() {
		inquilinoMock = mock(Usuario.class);
		inmuebleMock = mock(Inmueble.class);
		estadoMock = mock(Concretable.class);

		reserva = new Reserva(inquilinoMock, inmuebleMock);
		reserva.setEstado(estadoMock); // Para pruebas específicas de estados
	}

	@Test
	void testConstructor() {
		assertEquals(inquilinoMock, reserva.getInquilino());
		assertEquals(inmuebleMock, reserva.getInmueble());
		assertNotNull(reserva.getEstado());
	}

	@Test
	void testConfirmarReserva() {
		reserva.confirmarReserva();
		verify(estadoMock).confirmar(reserva); // Verifica que delega correctamente
	}

	@Test
	void testFinalizarReserva() {
		reserva.finalizarReserva();
		verify(estadoMock).finalizar(reserva);
	}

	@Test
	void testCancelarReserva() {
		reserva.cancelarReserva();
		verify(estadoMock).cancelar(reserva);
	}

	@Test
	void testEstadoPendiente() {
		when(estadoMock.esPendiente()).thenReturn(true);
		assertTrue(reserva.estaPendiente());
	}

	@Test
	void testInterfiereCon() {
		reserva.setFechaEntrada(LocalDate.of(2024, 12, 1));
		reserva.setFechaSalida(LocalDate.of(2024, 12, 10));

		// Caso de interferencia
		assertTrue(reserva.interfiereCon(LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 15)));

		// Caso sin interferencia
		assertFalse(reserva.interfiereCon(LocalDate.of(2024, 12, 11), LocalDate.of(2024, 12, 20)));
	}

	@Test
	void testCalcularPenalidadPorCancelacion() {
		when(inmuebleMock.calcularPenalidadPorCancelacion(reserva)).thenReturn(50.0);

		double penalidad = reserva.calcularPenalidadPorCancelacion(reserva);
		assertEquals(50.0, penalidad);
		verify(inmuebleMock).calcularPenalidadPorCancelacion(reserva);
	}

	@Test
	void testGetPrecioTotal() {
		reserva.setFechaEntrada(LocalDate.of(2024, 12, 1));
		reserva.setFechaSalida(LocalDate.of(2024, 12, 10));
		when(inmuebleMock.getPrecio(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 10))).thenReturn(1000.0);

		double precioTotal = reserva.getPrecioTotal();
		assertEquals(1000.0, precioTotal);
		verify(inmuebleMock).getPrecio(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 10));
	}

	@Test
	void testMailInquilino() {
		when(inquilinoMock.getEmail()).thenReturn("inquilino@example.com");
		assertEquals("inquilino@example.com", reserva.mailInquilino());
	}

	@Test
	void testMailPropietario() {
		Usuario propietarioMock = mock(Usuario.class);
		when(inmuebleMock.getPropietario()).thenReturn(propietarioMock);
		when(propietarioMock.getEmail()).thenReturn("propietario@example.com");

		assertEquals("propietario@example.com", reserva.mailPropietario());
	}

	@Test
	void testCiudadDeReserva() {
		when(inmuebleMock.getCiudad()).thenReturn("Buenos Aires");
		assertEquals("Buenos Aires", reserva.ciudadDeReserva());
	}
}