package reserva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import inmueble.FormaDePago;
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
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;

	// extras
	private Usuario mockInquilino;
	private Inmueble mockInmueble;
	private Concretable mockEstado;
	private Reserva reservaExtra;
	private FormaDePago mockFormaDePago;
	private Usuario mockPropietario;

	@BeforeEach
	void setUp() {
		inquilinoMock = mock(Usuario.class);
		inmuebleMock = mock(Inmueble.class);
		estadoMock = mock(Concretable.class);

		reserva = new Reserva(inquilinoMock, inmuebleMock);
		reserva.setEstado(estadoMock); // Para pruebas específicas de estados

		// extras
		mockInquilino = mock(Usuario.class);
		mockInmueble = mock(Inmueble.class);
		mockEstado = mock(Concretable.class);

		fechaEntrada = LocalDate.of(2024, 12, 10);
		fechaSalida = LocalDate.of(2024, 12, 20);

		reservaExtra = new Reserva();
		reservaExtra.setInquilino(mockInquilino);
		reservaExtra.setInmueble(mockInmueble);
		reservaExtra.setFechaSalida(fechaSalida);
		reservaExtra.setEstado(mockEstado);

		mockFormaDePago = mock(FormaDePago.class);
		mockPropietario = mock(Usuario.class);
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

	void testEstadoConfirmada() {
		when(estadoMock.esConfirmada()).thenReturn(true);
		assertTrue(reserva.estaConfirmada());
	}

	@Test
	void testEstadoCancelada() {
		when(estadoMock.esCancelada()).thenReturn(true);
		assertTrue(reserva.estaCancelada());
	}

	@Test
	void testEstadoFinalizada() {
		when(estadoMock.esFinalizada()).thenReturn(true);
		assertTrue(reserva.estaFinalizada());
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

	// extras

	/*
	 * @Test void testInterfiereCon() { LocalDate nuevaFechaEntrada =
	 * LocalDate.of(2024, 12, 15); LocalDate nuevaFechaSalida = LocalDate.of(2024,
	 * 12, 25);
	 * 
	 * assertTrue(reserva.interfiereCon(nuevaFechaEntrada, nuevaFechaSalida));
	 * 
	 * nuevaFechaEntrada = LocalDate.of(2024, 12, 1); nuevaFechaSalida =
	 * LocalDate.of(2024, 12, 9);
	 * 
	 * assertFalse(reserva.interfiereCon(nuevaFechaEntrada, nuevaFechaSalida)); }
	 */

	@Test
	void testFueHechoCheckOutPara() {
		Usuario propietario = mock(Usuario.class);
		when(mockInmueble.getPropietario()).thenReturn(propietario);
		when(mockEstado.esConfirmada()).thenReturn(true);

		assertFalse(reservaExtra.fueHechoCheckOutPara(mockInquilino));

		when(mockEstado.esConfirmada()).thenReturn(false);
		assertFalse(reservaExtra.fueHechoCheckOutPara(propietario));
	}
	

	@Test
	void testEstaConfirmada() {
		when(mockEstado.esConfirmada()).thenReturn(true);
		assertTrue(reservaExtra.estaConfirmada());

		when(mockEstado.esConfirmada()).thenReturn(false);
		assertFalse(reservaExtra.estaConfirmada());
	}

	@Test
	void testEsCondicionalParaElInmueble() {
		LocalDate fechaInicio = LocalDate.of(2024, 12, 15);
		LocalDate fechaFin = LocalDate.of(2024, 12, 20);

		when(mockInmueble.estaDisponibleParaLasFechas(fechaInicio, fechaFin)).thenReturn(true);
		assertTrue(reservaExtra.esCondicionalParaElInmueble(fechaInicio, fechaFin));

		when(mockInmueble.estaDisponibleParaLasFechas(fechaInicio, fechaFin)).thenReturn(false);
		assertFalse(reservaExtra.esCondicionalParaElInmueble(fechaInicio, fechaFin));
	}

	@Test
	void testCambiarEstado() {
		reserva.cambiarEstado(mockEstado);
		// Verificar que el estado fue asignado correctamente
		assertDoesNotThrow(() -> reserva.cambiarEstado(mockEstado));
	}

	@Test
	void testSetAndGetFormaDePago() {
		reserva.setFormaDePago(mockFormaDePago);
		assertEquals(mockFormaDePago, reserva.getFormaDePago());
	}

	@Test
	void testCalcularCostoTotal() {
		assertEquals(0d, reserva.calcularCostoTotal());
	}

	@Test
	void testGetFechaEntrada() {
		fechaEntrada = LocalDate.of(2024, 12, 10);

		Reserva reserva1 = new Reserva();
		reserva1.setInmueble(mockInmueble);

		reserva1.setFechaEntrada(fechaEntrada);
		assertEquals(fechaEntrada, reserva1.getFechaEntrada());
	}

	@Test
	void testGetFechaSalida() {
		fechaSalida = LocalDate.of(2024, 12, 20);

		reserva = new Reserva();
		reserva.setInmueble(mockInmueble);

		reserva.setFechaSalida(fechaSalida);
		assertEquals(fechaSalida, reserva.getFechaSalida());
	}

	@Test
	void testGetPropietario() {
		reserva = new Reserva();
		reserva.setInmueble(mockInmueble);
		reserva.setFechaSalida(fechaSalida);
		reserva.setFechaSalida(fechaEntrada);

		when(mockInmueble.getPropietario()).thenReturn(mockPropietario);
		assertEquals(mockPropietario, reserva.getPropietario());
		verify(mockInmueble).getPropietario();
	}

	@Test
	void testInmueble() {
		reserva = new Reserva();
		reserva.setInmueble(mockInmueble);
		reserva.setFechaSalida(fechaSalida);
		reserva.setFechaSalida(fechaEntrada);
		assertEquals(mockInmueble, reserva.inmueble());
	}

}