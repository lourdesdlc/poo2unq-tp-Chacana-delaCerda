package inmueble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import observer.Notificador;
import politicaCancelacion.PoliticaCancelacion;
import ranking.Ranking;
import reserva.Reserva;
import usuario.Inquilino;
import usuario.Propietario;
import usuario.Usuario;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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

	@BeforeEach
	void setUp() {

		inmueble = new Inmueble();

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
		Set<Reserva> reservas = mock(Set.class);
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
}
