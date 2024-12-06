package inmueble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import notificaciones.EmailSender;
import observer.Notificable;
import observer.Notificador;
import observer.Subscripcion;
import politicaCancelacion.PoliticaCancelacion;
import ranking.Ranking;
import reserva.Reserva;
import usuario.Inquilino;
import usuario.Propietario;
import usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InmuebleTest {

	private Inmueble inmueble;
	private Usuario propietario;
	private TipoInmueble tipoDeInmueble;
	private Servicio servicioMock;
	private FormaDePago formaDePagoMock;
	private PrecioPorPeriodo precioPorPeriodoMock;
	private PoliticaCancelacion politicaDeCancelacion;
	private Ranking rankingMock;
	private Reserva reservaMock;
	private Notificador notificador;
	private Inquilino inquilinoMock;
	private EmailSender email;

	@BeforeEach
	void setUp() {

		propietario = mock(Usuario.class);
		tipoDeInmueble = mock(TipoInmueble.class);
		servicioMock = mock(Servicio.class);
		formaDePagoMock = mock(FormaDePago.class);
		precioPorPeriodoMock = mock(PrecioPorPeriodo.class);
		politicaDeCancelacion = mock(PoliticaCancelacion.class);
		rankingMock = mock(Ranking.class);
		reservaMock = mock(Reserva.class);
		notificador = mock(Notificador.class);
		inquilinoMock = mock(Inquilino.class);
		email = mock(EmailSender.class);

		inmueble = new Inmueble(propietario, tipoDeInmueble, "Argentina", "BsAs", "calle");
		email = mock(EmailSender.class);
		inmueble.setEmailSender(email);
		inmueble.setNotificador(notificador); // Inyectar el mock en el inmueble

		when(reservaMock.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 6));
		when(reservaMock.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 10));

	}

	@Test
	void testPropietarioGetterSetter() {
		inmueble.setPropietario(propietario);
		assertEquals(propietario, inmueble.getPropietario());
	}

	@Test
	void testTipoDeInmuebleGetterSetter() {
		inmueble.setTipoDeInmueble(tipoDeInmueble);
		assertEquals(tipoDeInmueble, inmueble.getTipoDeInmueble());
	}

	@Test
	void testSuperficieGetterSetter() {
		double superficie = 75.5;
		inmueble.setSuperficie(superficie);
		assertEquals(superficie, inmueble.getSuperficie());
	}

	@Test
	void testPaisGetterSetter() {
		String pais = "Argentina";
		inmueble.setPais(pais);
		assertEquals(pais, inmueble.getPais());
	}

	@Test
	void testCiudadGetterSetter() {
		String ciudad = "Buenos Aires";
		inmueble.setCiudad(ciudad);
		assertEquals(ciudad, inmueble.getCiudad());
	}

	@Test
	void testDireccionGetterSetter() {
		String direccion = "Av. Siempre Viva 742";
		inmueble.setDireccion(direccion);
		assertEquals(direccion, inmueble.getDireccion());
	}

	@Test
	void testCapacidadGetterSetter() {
		int capacidad = 4;
		inmueble.setCapacidad(capacidad);
		assertEquals(capacidad, inmueble.getCapacidad());
	}

	@Test
	void testFotosGetterSetter() {
		List<String> fotos = new ArrayList<>();
		fotos.add("foto1.jpg");
		fotos.add("foto2.jpg");
		inmueble.setFotos(fotos);
		assertEquals(fotos, inmueble.getFotos());
	}

	@Test
	void testCheckInGetterSetter() {
		LocalTime checkIn = LocalTime.of(14, 0);
		inmueble.setCheckIn(checkIn);
		assertEquals(checkIn, inmueble.getCheckIn());
	}

	@Test
	void testCheckOutGetterSetter() {
		LocalTime checkOut = LocalTime.of(10, 0);
		inmueble.setCheckOut(checkOut);
		assertEquals(checkOut, inmueble.getCheckOut());
	}

	@Test
	void testServiciosGetterSetter() {
		List<Servicio> servicios = new ArrayList<>();
		servicios.add(servicioMock);
		inmueble.setServicios(servicios);
		assertEquals(servicios, inmueble.getServicios());
	}

	@Test
	void testFormasDePagoGetterSetter() {
		List<FormaDePago> formasDePago = new ArrayList<>();
		formasDePago.add(formaDePagoMock);
		inmueble.setFormasDePago(formasDePago);
		assertEquals(formasDePago, inmueble.getFormasDePago());
	}

	@Test
	void testPreciosPorPeriodosGetterSetter() {
		List<PrecioPorPeriodo> preciosPorPeriodos = new ArrayList<>();
		preciosPorPeriodos.add(precioPorPeriodoMock);
		inmueble.setPreciosPorPeriodos(preciosPorPeriodos);
		assertEquals(preciosPorPeriodos, inmueble.getPreciosPorPeriodos());
	}

	@Test
	void testPrecioBasePorDiaGetterSetter() {
		double precioBasePorDia = 120.0;
		inmueble.setPrecioBasePorDia(precioBasePorDia);
		assertEquals(precioBasePorDia, inmueble.getPrecioBasePorDia());
	}

	@Test
	void testPoliticaDeCancelacionGetterSetter() {
		inmueble.setPoliticaDeCancelacion(politicaDeCancelacion);
		assertEquals(politicaDeCancelacion, inmueble.getPoliticaDeCancelacion());
	}

	@Test
	void testRankingsGetterSetter() {
		List<Ranking> rankings = new ArrayList<>();
		rankings.add(rankingMock);
		inmueble.setRankings(rankings);
		assertEquals(rankings, inmueble.getRankings());
	}

	@Test
	void testReservasGetterSetter() {
		List<Reserva> reservas = mock(List.class);
		inmueble.setReservas(reservas);
		assertEquals(reservas, inmueble.getReservas());
	}

	@Test
	void testReservasEncoladasGetterSetter() {
		Queue<Reserva> reservasEncoladas = mock(Queue.class);
		inmueble.setReservasEncoladas(reservasEncoladas);
		assertEquals(reservasEncoladas, inmueble.getReservasEncoladas());
	}

	@Test
	void testNotificadorGetterSetter() {
		inmueble.setNotificador(notificador);
		assertEquals(notificador, inmueble.getNotificador());
	}

	@Test
	void testVisitantesGetterSetter() {
		List<Inquilino> visitantes = new ArrayList<>();
		visitantes.add(inquilinoMock);
		inmueble.setVisitantes(visitantes);
		assertEquals(visitantes, inmueble.getVisitantes());
	}

	@Test
	void testAgregarReserva() {
		List<Reserva> reservas = inmueble.getReservas();
		reservas.add(reservaMock);
		assertEquals(1, reservas.size());
	}

	@Test
	void testAgregarReservaEnCola() {
		Queue<Reserva> reservasEncoladas = inmueble.getReservasEncoladas();
		reservasEncoladas.add(reservaMock);
		assertEquals(1, reservasEncoladas.size());
	}

	@Test
	void testReservaCondicional() {
		Queue<Reserva> reservasEncoladas = inmueble.getReservasEncoladas();
		reservasEncoladas.add(reservaMock);
		inmueble.cancelarReserva(reservaMock);
		assertEquals(0, reservasEncoladas.size());
	}

	@Test
	void testNotificarNuevaReserva() {
		LocalDate fechaInicio = LocalDate.of(2024, 12, 10);
		LocalDate fechaFin = LocalDate.of(2024, 12, 15);

		inmueble.notificarNuevaReserva(fechaInicio, fechaFin);

		verify(notificador).notificarReserva("El inmueble " + inmueble.getTipoDeInmueble()
				+ " que te interesa, ha sido reservado desde el " + fechaInicio + " hasta el " + fechaFin + ".",
				inmueble);
	}

	@Test
	void testNotificarCancelacionDeReserva() {
		inmueble.notificarCancelacionDeReserva();

		verify(notificador).notificarCancelacionReserva("El/la " + inmueble.getTipoDeInmueble().getNombre()
				+ " que te interesa se ha liberado! Corre a reservarlo!", inmueble);
	}

	@Test
	void testNotificarBajaDePrecioDeInmueble() {

		when(tipoDeInmueble.getNombre()).thenReturn("departamento");

		inmueble.setPrecioBasePorDia(100.0); // Precio inicial

		inmueble.cambiarPrecio(50.0); // Nuevo precio más bajo

		// Verificar que el mock de Notificador haya sido invocado
		verify(notificador).notificarBajaDePrecio(
				"No te pierdas esta oferta: Un inmueble departamento a tan sólo 50.0 pesos", inmueble);
	}

	@Test
	void testRecibirSubscriptor() {
		Notificable appMobile = mock(Notificable.class);

		inmueble.recibirSubscriptor(appMobile);

		verify(notificador).suscribir(any(Subscripcion.class));
	}

	@Test
	void testEliminarSubscriptor() {
		Subscripcion subscripcion = mock(Subscripcion.class);

		inmueble.eliminarSubscriptor(subscripcion);

		verify(subscripcion).eliminarInteresEnInmuble(inmueble);
	}

}
