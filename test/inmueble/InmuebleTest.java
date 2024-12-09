package inmueble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import notificaciones.EmailSender;
import observer.Notificable;
import observer.Notificador;
import observer.Subscripcion;
import politicaCancelacion.PoliticaCancelacion;
import ranking.Categoria;
import ranking.GestorRanking;
import ranking.Ranking;
import reserva.Reserva;
import usuario.Inquilino;
import usuario.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
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
	private Usuario inquilinoMock;
	private EmailSender email;
	private Categoria categoriaMock;
	private GestorRanking gestorRankingMock;
	
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
		inquilinoMock = mock(Usuario.class);
		email = mock(EmailSender.class);
		rankingMock = mock(Ranking.class);
		categoriaMock = mock(Categoria.class);
		inmueble = new Inmueble(propietario, tipoDeInmueble, "Argentina", "BsAs", "calle");
		email = mock(EmailSender.class);
		inmueble.setEmailSender(email);
		inmueble.setNotificador(notificador); // Inyectar el mock en el inmueble
		gestorRankingMock = mock(GestorRanking.class);
		// comportamiento predeterminado.
		when(reservaMock.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 6));
		when(reservaMock.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 10));
		when(reservaMock.mailInquilino()).thenReturn("inquilino@ejemplo.com");
		when(reservaMock.getInquilino()).thenReturn(inquilinoMock);
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
	void testAgregaryEliminarServicios() {
		inmueble.agregarServicio(servicioMock);
		
		assertEquals(1, inmueble.getServicios().size());
		assertTrue(inmueble.getServicios().contains(servicioMock));
		
		inmueble.eliminarServicio(servicioMock);
		
		assertEquals(0, inmueble.getServicios().size());
		assertFalse(inmueble.getServicios().contains(servicioMock));
	}
	
	@Test
	void testFormasDePagoGetterSetter() {
		inmueble.agregarFormaDePago(formaDePagoMock);
		
		assertEquals(1, inmueble.getFormasDePago().size());
		assertTrue(inmueble.getFormasDePago().contains(formaDePagoMock));
		
		inmueble.eliminarFormaDePago(formaDePagoMock);
		
		assertEquals(0, inmueble.getFormasDePago().size());
		assertFalse(inmueble.getFormasDePago().contains(formaDePagoMock));
	}
	
	@Test
	void testAgregarMasDe5Fotos() {
		inmueble.agregarFoto("foto1");
		inmueble.agregarFoto("foto2");
		inmueble.agregarFoto("foto3");
		inmueble.agregarFoto("foto4");
		inmueble.agregarFoto("foto5");
		RuntimeException exception = assertThrows(RuntimeException.class, () -> 
		inmueble.agregarFoto("foto5"));
		assertEquals("No se pueden agregar mas de 5 fotos.", exception.getMessage());
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
		List<Usuario> visitantes = new ArrayList<>();
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
		inmueble.setPrecioBasePorDia(100.0); 
		inmueble.cambiarPrecio(50.0);
		
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
	
	@Test
	void testRecibirSolicitudDeReserva() {

		Reserva reserva = mock(Reserva.class);
		when(reserva.estaPendiente()).thenReturn(true); 

		Usuario propietarioMock = mock(Usuario.class);
		when(propietarioMock.getEmail()).thenReturn("propietario@ejemplo.com");
		inmueble.setPropietario(propietarioMock); 

		inmueble.recibirSolicitudDeReserva(reserva);

		verify(email).enviarMail(eq("propietario@ejemplo.com"),
				eq("Nueva solicitud de reserva para uno de sus inmuebles"), eq(reserva));

		verify(propietarioMock).agregarReserva(reserva);
	}
	
	@Test
	void testAceptarReserva() {
		
		Reserva reserva = mock(Reserva.class);
		when(reserva.estaPendiente()).thenReturn(true);
		when(reserva.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 10));
		when(reserva.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 15));
		when(reserva.mailInquilino()).thenReturn("inquilino@ejemplo.com");
		when(reserva.getInquilino()).thenReturn(inquilinoMock);
	
		Inmueble inmuebleSpy = spy(inmueble);
	
		doReturn(true).when(inmuebleSpy).estaDisponibleParaLasFechas(LocalDate.of(2024, 12, 10),
				LocalDate.of(2024, 12, 15));
		inmuebleSpy.aceptarReserva(reserva);
		
		verify(reserva).confirmarReserva();
		
		assertTrue(inmuebleSpy.getReservas().contains(reserva));
	
		verify(email).enviarMail(eq("inquilino@ejemplo.com"), eq("Su reserva ha sido aceptada"), eq(reserva));
		
		verify(inquilinoMock).agregarReserva(reserva);
	}
	
	@Test
	void testAceptarReservaEncolada() {
		
		when(reservaMock.estaPendiente()).thenReturn(true);
		
		Inmueble inmuebleSpy = spy(inmueble);
		
		doReturn(false).when(inmuebleSpy).estaDisponibleParaLasFechas(any(LocalDate.class), any(LocalDate.class));
		
		Queue<Reserva> reservasEncoladasSpy = spy(new LinkedList<>());
		inmuebleSpy.setReservasEncoladas(reservasEncoladasSpy);
		
		inmuebleSpy.aceptarReserva(reservaMock);
		
		verify(reservasEncoladasSpy).add(reservaMock);
		
		verify(email).enviarMail(eq("inquilino@ejemplo.com"), eq("Su reserva ha sido encolada"), eq(reservaMock));
	}
	
	@Test
	void testCheckOutReservaConfirmada() {
		
		Reserva reserva = mock(Reserva.class);
		Usuario inquilino = mock(Usuario.class);
		when(reserva.estaConfirmada()).thenReturn(true);
		when(reserva.getInquilino()).thenReturn(inquilino);
		inmueble.checkOut(reserva);
		verify(reserva).finalizarReserva();
		assertTrue(inmueble.getVisitantes().contains(inquilino));
	}
	
	@Test
	void testCheckOutReservaNoConfirmada() {
		
		Reserva reserva = mock(Reserva.class);
		when(reserva.estaConfirmada()).thenReturn(false);
		inmueble.checkOut(reserva);
		verify(reserva, never()).finalizarReserva();
		assertTrue(inmueble.getVisitantes().isEmpty());
	}
	
	@Test
	void testFueHechoElCheckOut() {
		
		Usuario inquilino = mock(Usuario.class);
		inmueble.getVisitantes().add(inquilino);
		assertTrue(inmueble.fueHechoElCheckOut(inquilino));
	}
	
	@Test
	void testNoFueHechoElCheckOut() {
		
		Usuario inquilino = mock(Usuario.class);
		assertFalse(inmueble.fueHechoElCheckOut(inquilino));
	}
	
	@Test
	void testValidarCheckOutConExito() {
		
		Usuario inquilino = mock(Usuario.class);
		inmueble.getVisitantes().add(inquilino);
		assertDoesNotThrow(() -> inmueble.validarCheckOut(inquilino));
	}
	
	@Test
	void testValidarCheckOutLanzaExcepcion() {
		
		Usuario inquilino = mock(Usuario.class);
		RuntimeException exception = assertThrows(RuntimeException.class, () -> inmueble.validarCheckOut(inquilino));
		assertEquals("No se puede rankear antes de hacer el check-out", exception.getMessage());
	}
	
	@Test
	void testGetPrecio() {
		
		PrecioPorPeriodo periodo = mock(PrecioPorPeriodo.class);
		when(periodo.incluye(any())).thenReturn(true);
		when(periodo.getPrecioPorDia()).thenReturn(100.0);
		inmueble.getPreciosPorPeriodos().add(periodo);
		LocalDate fechaInicio = LocalDate.of(2024, 12, 1);
		LocalDate fechaFin = LocalDate.of(2024, 12, 3);
		double precioTotal = inmueble.getPrecio(fechaInicio, fechaFin);
		assertEquals(300.0, precioTotal);
	}
	
	@Test
	void testCalcularPenalidadPorCancelacion() {
		
		Reserva reserva = mock(Reserva.class);
		PoliticaCancelacion politica = mock(PoliticaCancelacion.class);
		when(politica.calcularPenalidad(reserva)).thenReturn(50.0);
		inmueble.setPoliticaDeCancelacion(politica);
		double penalidad = inmueble.calcularPenalidadPorCancelacion(reserva);
		assertEquals(50.0, penalidad);
	}
	
	@Test
	void testEstaDisponibleParaLasFechas() {
		
		Reserva reserva = mock(Reserva.class);
		when(reserva.interfiereCon(any(), any())).thenReturn(false);
		inmueble.getReservas().add(reserva);
		LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
		LocalDate fechaSalida = LocalDate.of(2024, 12, 5);
		assertTrue(inmueble.estaDisponibleParaLasFechas(fechaEntrada, fechaSalida));
	}
	
	@Test
	void testNoEstaDisponibleParaLasFechas() {
		
		Reserva reserva = mock(Reserva.class);
		when(reserva.interfiereCon(any(), any())).thenReturn(true);
		inmueble.getReservas().add(reserva);
		LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
		LocalDate fechaSalida = LocalDate.of(2024, 12, 5);
		assertFalse(inmueble.estaDisponibleParaLasFechas(fechaEntrada, fechaSalida));
	}
	
	@Test
	void testAgregarPreciosPorPeriodo() {
		
		PrecioPorPeriodo precioPorPeriodo = mock(PrecioPorPeriodo.class);
		when(precioPorPeriodo.interfiereCon(any())).thenReturn(false);
		assertDoesNotThrow(() -> inmueble.agregarPreciosPorPeriodo(precioPorPeriodo));
		assertTrue(inmueble.getPreciosPorPeriodos().contains(precioPorPeriodo));
	}
	
	@Test
	void testAgregarRanking() {
		
		Usuario inquilinoMock = mock(Usuario.class);
		Ranking rankingPrueba = mock(Ranking.class);
		
		List<Ranking> rankings = new ArrayList<>();
		
		Inmueble inmueblePrueba = mock(Inmueble.class);
		
		when(rankingPrueba.getRankeador()).thenReturn(inquilinoMock);
		doNothing().when(inmueblePrueba).validarCheckOut(inquilinoMock); // Asegurarse de que 'inmueble' sea mockeado
																			// correctamente también
		
		doAnswer(invocation -> {
			rankings.add(rankingPrueba);
			return null; 
		}).when(inmueblePrueba).agregarRanking(any(Ranking.class));
		
		inmueblePrueba.agregarRanking(rankingPrueba);
		
		assertTrue(rankings.contains(rankingPrueba)); 
	}
	
	@Test
	void testCantidadDeAlquileres() {
		
		Reserva reserva1 = mock(Reserva.class);
		Reserva reserva2 = mock(Reserva.class);
		when(reserva1.estaFinalizada()).thenReturn(true);
		when(reserva2.estaFinalizada()).thenReturn(false);
		
		inmueble.agregarReserva(reserva1);
		inmueble.agregarReserva(reserva2);
		
		assertEquals(1, inmueble.cantidadDeAlquileres());
	}
	
	@Test
	void testAgregarReservaALista() {
		Reserva reserva = mock(Reserva.class);
		
		inmueble.agregarReserva(reserva);
		
		assertTrue(inmueble.getReservas().contains(reserva));
	}
	
	@Test
	void testEliminarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		inmueble.agregarReserva(reserva);
		inmueble.eliminarReserva(reserva);
		
		assertFalse(inmueble.getReservas().contains(reserva));
	}
	
	@Test
	void testAgregarRankingValid() {
		
		Inmueble inmuebleSpy = spy(new Inmueble(propietario, tipoDeInmueble, "A", "B", "calle"));
		
		Ranking mockRanking = mock(Ranking.class);
		Usuario mockInquilino = mock(Usuario.class);
		
		when(mockRanking.getRankeador()).thenReturn(mockInquilino);
		
		doReturn(true).when(inmuebleSpy).fueHechoElCheckOut(mockInquilino);
		
		inmuebleSpy.agregarRanking(mockRanking);
		
		assertEquals(1, inmuebleSpy.getRankings().size());
		assertTrue(inmuebleSpy.getRankings().contains(mockRanking));
		
		verify(inmuebleSpy).validarCheckOut(mockInquilino);
		verify(inmuebleSpy).fueHechoElCheckOut(mockInquilino);
	}
	
	@Test
	void testAgregarRankingInvalid() {
		//
		Inmueble inmuebleSpy = spy(new Inmueble(propietario, tipoDeInmueble, "A", "B", "calle"));
		Ranking mockRanking = mock(Ranking.class);
		Usuario mockInquilino = mock(Usuario.class);
		when(mockRanking.getRankeador()).thenReturn(mockInquilino);
		doReturn(false).when(inmuebleSpy).fueHechoElCheckOut(mockInquilino);
		assertThrows(RuntimeException.class, () -> inmuebleSpy.agregarRanking(mockRanking));
		assertTrue(inmuebleSpy.getRankings().isEmpty());
		verify(inmuebleSpy).validarCheckOut(mockInquilino);
	}
	
	@Test
	void testFueHechoElCheckOutTrue() {
		Inmueble inmuebleSpy = spy(new Inmueble(propietario, tipoDeInmueble, "A", "B", "calle"));
		Usuario mockInquilino = mock(Usuario.class);
		List<Usuario> visitantes = new ArrayList<>();
		visitantes.add(mockInquilino);
		doReturn(visitantes).when(inmuebleSpy).getVisitantes();
		assertTrue(inmuebleSpy.fueHechoElCheckOut(mockInquilino));
		verify(inmuebleSpy).getVisitantes();
	}
	
	@Test
	void testFueHechoElCheckOutFalse() {
		
		Inmueble inmuebleSpy = spy(new Inmueble(propietario, tipoDeInmueble, "A", "B", "calle"));
		
		Usuario mockInquilino = mock(Usuario.class);
		List<Inquilino> visitantes = new ArrayList<>();
		doReturn(visitantes).when(inmuebleSpy).getVisitantes();
		assertFalse(inmuebleSpy.fueHechoElCheckOut(mockInquilino));
	}
	
	@Test
	void testValidarCheckOutValid() {
		Inmueble inmuebleSpy = spy(new Inmueble(propietario, tipoDeInmueble, "A", "B", "calle"));
		
		Usuario mockInquilino = mock(Usuario.class);
		doReturn(true).when(inmuebleSpy).fueHechoElCheckOut(mockInquilino);
		assertDoesNotThrow(() -> inmuebleSpy.validarCheckOut(mockInquilino));
	}
	
	@Test
	void testValidarCheckOutInvalid() {
		Inmueble inmuebleSpy = spy(new Inmueble(propietario, tipoDeInmueble, "A", "B", "calle"));
		Usuario mockInquilino = mock(Usuario.class);
		doReturn(false).when(inmuebleSpy).fueHechoElCheckOut(mockInquilino);
		assertThrows(RuntimeException.class, () -> inmuebleSpy.validarCheckOut(mockInquilino));
	}
	
	@Test
	void testGetNombreDeTipoInmueble() {
		TipoInmueble tipoInmuebleA = new TipoInmueble("Apartamento");
		Inmueble inmuebleA = new Inmueble(propietario, tipoInmuebleA, "A", "B", "calle");
		assertEquals("Apartamento", inmuebleA.getNombreDeTipoInmueble());
	}
	
	@Test
	void testGetTipo() {
		TipoInmueble tipoInmuebleA = new TipoInmueble("Apartamento");
		
		Inmueble inmuebleA = new Inmueble(propietario, tipoInmuebleA, "A", "B", "calle");
		
		TipoInmueble tipoInmueble = inmuebleA.getTipo();
		
		assertEquals(tipoInmuebleA, tipoInmueble);
	}
	
	@Test
	void testGetTipoDeCancelacion() {
		
		PoliticaCancelacion politicaCancelacionMock = mock(PoliticaCancelacion.class);
		TipoInmueble tipoInmuebleA = new TipoInmueble("Apartamento");
		
		Inmueble inmuebleA = new Inmueble(propietario, tipoInmuebleA, "A", "B", "calle");
		inmuebleA.setPoliticaDeCancelacion(politicaCancelacionMock);
		
		PoliticaCancelacion politicaCancelacion = inmuebleA.getTipoDeCancelacion();
		
		assertEquals(politicaCancelacionMock, politicaCancelacion);
	}
	
	@Test
	void testGetPrecioPorDia() {
		// Valor del precio base por día
		double precioEsperado = 100.0;
		TipoInmueble tipoInmuebleA = new TipoInmueble("Apartamento");
		// Crear el objeto SUT (Sistema Bajo Prueba)
		Inmueble inmuebleA = new Inmueble(propietario, tipoInmuebleA, "A", "B", "calle");
		inmuebleA.setPrecioBasePorDia(precioEsperado);
		// Llamar al método
		double precioPorDia = inmuebleA.getPrecioPorDia();
		// Verificar que el precio por día es el esperado
		assertEquals(precioEsperado, precioPorDia, 0.001);
	}
	
	@Test
	void testPrecioBasePorDia() {
		
		double precioEsperado = 100.0;
		TipoInmueble tipoInmuebleA = new TipoInmueble("Apartamento");
		
		Inmueble inmuebleA = new Inmueble(propietario, tipoInmuebleA, "A", "B", "calle");
		
		inmuebleA.setPrecioBasePorDia(precioEsperado);
		double precioBase = inmuebleA.precioBasePorDia();
		
		assertEquals(precioEsperado, precioBase, 0.001);
	}
	
	@Test

	void testGetComentarios() {
		Inmueble inmueble = new Inmueble();
		List<Ranking> rankings = new ArrayList<>();
		inmueble.setRankings(rankings);

		List<String> comentariosEsperados = List.of("Muy linda iluminacion", "Amplio y limpio");

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getComentarios(rankings)).thenReturn(comentariosEsperados);

			List<String> comentariosActuales = inmueble.getComentarios();

			assertEquals(comentariosEsperados, comentariosActuales);
		}
	}
}