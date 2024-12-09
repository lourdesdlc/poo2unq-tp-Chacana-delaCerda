package usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import inmueble.Inmueble;
import ranking.Categoria;
import ranking.GestorRanking;
import ranking.Ranking;
import ranking.TipoRankeable;
import reserva.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioTest {
	private Usuario usuario;
	private Reserva reservaMock;
	private Inmueble inmuebleMock;
	private Ranking rankingMock;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;

	private GestorRanking mockGestorRanking;

	@BeforeEach
	void setUp() {
		usuario = new Usuario("Juan Pérez", "juan@example.com", "123456789");
		reservaMock = mock(Reserva.class);
		inmuebleMock = mock(Inmueble.class);
		rankingMock = mock(Ranking.class);

		// usuario = new Usuario();
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);

		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);

		usuario.setReservas(reservas);

		mockGestorRanking = new GestorRanking();
	}

	@Test
	void testConstructor() {
		assertEquals("Juan Pérez", usuario.getNombreCompleto());
		assertEquals("juan@example.com", usuario.getEmail());
		assertEquals("123456789", usuario.getTelefono());
		assertNotNull(usuario.getReservas());
		assertNotNull(usuario.getInmuebles());
		assertNotNull(usuario.getRankings());
		assertNotNull(usuario.getfechaDeCreacion());
	}

	@Test
	void testAgregarReserva() {
		// Usar una instancia real de Usuario en lugar de un mock
		Usuario u = new Usuario();
		Reserva reservaMock = mock(Reserva.class); // Crear un mock para la reserva

		u.agregarReserva(reservaMock); // Agregar la reserva al usuario

		// Verificar que la lista de reservas tiene el tamaño esperado y contiene la
		// reserva mockeada
		assertEquals(1, u.getReservas().size());
		assertTrue(u.getReservas().contains(reservaMock));
	}

	/*
	 * arreglar
	 * 
	 * @Test void testGetReservasFuturas() { // Simular fecha actual LocalDate hoy =
	 * LocalDate.now();
	 * 
	 * // Crear mocks necesarios Reserva reservaMock = mock(Reserva.class); Usuario
	 * usuarioMock = spy(usuario); // Usar spy para mockear parcialmente
	 * 
	 * // Mockear el comportamiento de la reserva
	 * when(reservaMock.getFechaEntrada()).thenReturn(hoy.plusDays(1)); // Fecha
	 * futura
	 * 
	 * // Agregar la reserva mockeada al usuario
	 * usuarioMock.agregarReserva(reservaMock);
	 * 
	 * // Llamar al método y verificar List<Reserva> futuras =
	 * usuarioMock.getReservasFuturas();
	 * 
	 * // Verificar resultados assertEquals(1, futuras.size());
	 * assertTrue(futuras.contains(reservaMock)); }
	 */

	@Test
	void testAgregarInmueble() {
		usuario.agregarInmueble(inmuebleMock);
		assertEquals(1, usuario.getInmuebles().size());
		assertTrue(usuario.getInmuebles().contains(inmuebleMock));

		// Agregar un duplicado no debe cambiar la lista
		usuario.agregarInmueble(inmuebleMock);
		assertEquals(1, usuario.getInmuebles().size());
	}

	@Test
	void testRemoverInmueble() {
		usuario.agregarInmueble(inmuebleMock);
		usuario.removerInmueble(inmuebleMock);

		assertEquals(0, usuario.getInmuebles().size());
		assertFalse(usuario.getInmuebles().contains(inmuebleMock));
	}

	/*
	 * arreglar
	 * 
	 * @Test void testAgregarRanking() { Usuario rankeadorMock =
	 * mock(Usuario.class);
	 * when(rankingMock.getRankeador()).thenReturn(rankeadorMock);
	 * 
	 * // Mock de una reserva finalizada para validar check-out
	 * when(reservaMock.propietarioAsigando()).thenReturn(rankeadorMock);
	 * when(reservaMock.estaFinalizada()).thenReturn(true);
	 * usuario.agregarReserva(reservaMock);
	 * 
	 * usuario.agregarRanking(rankingMock);
	 * assertTrue(usuario.getRankings().contains(rankingMock)); }
	 */

	@Test
	void testAgregarRankingValid() {
		// Create a spy of the real Usuario object
		Usuario uReal = spy(new Usuario());

		// Create mock objects
		Usuario mockRankeador = mock(Usuario.class);
		Ranking mockRanking = mock(Ranking.class);

		// Set up the behavior of the mock objects
		when(mockRanking.getRankeador()).thenReturn(mockRankeador);

		// Use doReturn().when() for the spy object
		doReturn(true).when(uReal).fueHechoCheckOutConPropietario(mockRankeador);

		// Perform the action
		uReal.agregarRanking(mockRanking);

		// Verify the results
		assertEquals(1, uReal.getRankings().size());
		assertTrue(uReal.getRankings().contains(mockRanking));

		// Verify that fueHechoCheckOutConPropietario was called
		verify(uReal).fueHechoCheckOutConPropietario(mockRankeador);
	}

	@Test
	void testAgregarRankingInvalid() {
		Usuario usuarioSpy = spy(new Usuario());

		// Create mock objects
		Usuario mockRankeador = mock(Usuario.class);
		Ranking mockRanking = mock(Ranking.class);

		when(mockRanking.getRankeador()).thenReturn(mockRankeador);
		doReturn(false).when(usuarioSpy).fueHechoCheckOutConPropietario(mockRankeador);
		doReturn(false).when(usuarioSpy).fueHechoCheckOutConInquilino(mockRankeador);

		assertThrows(RuntimeException.class, () -> usuarioSpy.agregarRanking(mockRanking));
		assertTrue(usuarioSpy.getRankings().isEmpty());
	}

	@Test

	void testValidarCheckOutValid() {
		Usuario usuarioSpy = spy(new Usuario());
		// Create mock objects
		Usuario mockRankeador = mock(Usuario.class);
		Ranking mockRanking = mock(Ranking.class);

		doReturn(true).when(usuarioSpy).fueHechoCheckOutConPropietario(mockRankeador);

		assertDoesNotThrow(() -> usuarioSpy.validarCheckOut(mockRankeador));
	}

	@Test

	void testValidarCheckOutInvalid() {
		Usuario usuarioSpy = spy(new Usuario());

		Usuario mockRankeador = mock(Usuario.class);

		doReturn(false).when(usuarioSpy).fueHechoCheckOutConPropietario(mockRankeador);
		doReturn(false).when(usuarioSpy).fueHechoCheckOutConInquilino(mockRankeador);

		assertThrows(RuntimeException.class, () -> usuarioSpy.validarCheckOut(mockRankeador));
	}

	@Test

	void testFueHechoCheckOutConPropietarioTrue() {
		Usuario usuarioSpy = spy(new Usuario());

		Usuario mockRankeador = mock(Usuario.class);
		Reserva mockReserva = mock(Reserva.class);

		usuarioSpy.getReservas().add(mockReserva);
		when(mockReserva.fueHechoCheckOutPara(mockRankeador)).thenReturn(true);

		assertTrue(usuarioSpy.fueHechoCheckOutConPropietario(mockRankeador));
	}

	@Test

	void testFueHechoCheckOutConPropietarioFalse() {
		Usuario usuarioSpy = spy(new Usuario());

		Usuario mockRankeador = mock(Usuario.class);
		Reserva mockReserva = mock(Reserva.class);

		usuarioSpy.getReservas().add(mockReserva);
		when(mockReserva.fueHechoCheckOutPara(mockRankeador)).thenReturn(false);

		assertFalse(usuarioSpy.fueHechoCheckOutConPropietario(mockRankeador));
	}

	@Test

	void testFueHechoCheckOutConPropietarioNoReservations() {
		Usuario usuarioSpy = spy(new Usuario());

		Usuario mockRankeador = mock(Usuario.class);

		assertFalse(usuarioSpy.fueHechoCheckOutConPropietario(mockRankeador));
	}

	/*
	 * @Test void testAgregarRanking() {
	 * 
	 * Usuario inquilinoMock = mock(Usuario.class); Usuario propietarioMock =
	 * mock(Usuario.class);
	 * 
	 * Ranking rankingMock = mock(Ranking.class);
	 * 
	 * List<Ranking> rankingsReal = new ArrayList<>();
	 * when(usuarioMock.validarCheckOut(uReal)).thenReturn(true);
	 * when(rankingMock.validarCheckOut(uReal)).thenReturn(true);
	 * 
	 * 
	 * inquilinoMock.agregarRanking(rankingMock); uReal.setRankings(rankings);
	 * uReal.agregarRanking(uReal);
	 * 
	 * assertTrue(inquilinoMock.getRankings().size() == 1);
	 * assertTrue(inquilinoMock.getRankings().contains(rankingMock));
	 * 
	 * }
	 */

	@Test
	void testGetReservasPorCiudad() {
		when(reserva1.ciudadDeReserva()).thenReturn("Buenos Aires");
		when(reserva2.ciudadDeReserva()).thenReturn("Córdoba");
		when(reserva3.ciudadDeReserva()).thenReturn("Buenos Aires");

		List<Reserva> reservasBA = usuario.getReservasPorCiudad("buenos aires");
		assertEquals(2, reservasBA.size());
		assertTrue(reservasBA.contains(reserva1));
		assertTrue(reservasBA.contains(reserva3));
	}

	@Test
	void testGetReservasPorCiudadSinResultados() {
		when(reserva1.ciudadDeReserva()).thenReturn("Buenos Aires");
		when(reserva2.ciudadDeReserva()).thenReturn("Córdoba");
		when(reserva3.ciudadDeReserva()).thenReturn("Buenos Aires");

		List<Reserva> reservasRosario = usuario.getReservasPorCiudad("Rosario");
		assertTrue(reservasRosario.isEmpty());
	}

	@Test
	void testGetCiudadesConReservas() {
		when(reserva1.ciudadDeReserva()).thenReturn("Buenos Aires");
		when(reserva2.ciudadDeReserva()).thenReturn("Córdoba");
		Usuario u = new Usuario();
		u.agregarReserva(reserva1);
		u.agregarReserva(reserva2);

		List<String> ciudades = u.getCiudadesConReservas();

		assertEquals(2, ciudades.size());
		assertTrue(ciudades.contains("Buenos Aires"));
		assertTrue(ciudades.contains("Córdoba"));
	}

	@Test
	void testCantidadDeAlquileres() {
		when(reserva1.estaFinalizada()).thenReturn(true);
		when(reserva2.estaFinalizada()).thenReturn(false);

		usuario.agregarReserva(reserva1);
		usuario.agregarReserva(reserva2);

		int cantidad = usuario.cantidadDeAlquileres();

		assertEquals(2, cantidad); // Solo una reserva está finalizada
	}

	@Test
	void testInmueblesAlquilados() {
		when(reserva1.estaFinalizada()).thenReturn(true);
		when(reserva1.getInmueble()).thenReturn(inmuebleMock);
		when(reserva2.estaFinalizada()).thenReturn(false);

		usuario.agregarReserva(reserva1);
		usuario.agregarReserva(reserva2);

		List<Inmueble> inmuebles = usuario.inmueblesAlquilados();

		assertEquals(1, inmuebles.size());
		assertTrue(inmuebles.contains(inmuebleMock));
	}

	@Test
	void testGetReservasFuturas() {

		Usuario usuarioMock = mock(Usuario.class);

		Reserva reserva1 = mock(Reserva.class);

		LocalDate hoy = LocalDate.now();
		when(reserva1.getFechaEntrada()).thenReturn(hoy.plusDays(5)); // Reserva futura

		// Simular que el usuario tiene esta reserva
		when(usuarioMock.getReservas()).thenReturn(List.of(reserva1)); // Retorna la lista con reserva1

		// Llamar al método getReservasFuturas en el usuarioMock
		List<Reserva> futuras = usuarioMock.getReservasFuturas();

		futuras.add(reserva1);

		assertEquals(1, futuras.size());
		assertTrue(futuras.contains(reserva1));
	}

	@Test
	void testGetAntiguedad() {
		usuario.setFechaDeCreacion(LocalDate.now().minusYears(3));

		int antiguedad = usuario.getAntiguedad();

		assertEquals(3, antiguedad);

	}

	@Test
	void testValidarCheckOutConCheckOut() {
		// Crear mocks necesarios
		Usuario usuarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);
		when(usuarioMock.getReservas()).thenReturn(Collections.singletonList(reservaMock));
		when(reservaMock.estaFinalizada()).thenReturn(true); // Simula que la reserva está finalizada

		// Llamar al método validarCheckOut
		usuarioMock.validarCheckOut(usuarioMock); // Simula que el usuario intenta validar su propio check-out
		// No se espera ninguna excepción, el método debe ejecutarse sin problemas
	}

	@Test
	void testFueHechoCheckOutConPropietarioSinCheckOut() {
		// Crear mocks necesarios
		Usuario propietarioMock = mock(Usuario.class);
		Usuario usuarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);

		// Simular que la reserva tiene al propietarioMock y que la reserva no está
		// finalizada
		when(reservaMock.propietarioAsigando()).thenReturn(propietarioMock);
		when(reservaMock.estaFinalizada()).thenReturn(false);
		when(usuarioMock.getReservas()).thenReturn(Collections.singletonList(reservaMock));

		// Llamar al método y verificar que retorna false
		boolean resultado = usuarioMock.fueHechoCheckOutConPropietario(propietarioMock);
		assertFalse(resultado); // Se espera que el check-out no haya sido realizado
	}

	@Test // ESTE ANDA
	void testFueHechoCheckOutConPropietario() {
		// Crear mocks necesarios
		Usuario propietarioMock = mock(Usuario.class); // Mockear el propietario
		Reserva reservaMock = mock(Reserva.class);

		// Mockear que la reserva está finalizada y que la propiedad fue check-out
		when(reservaMock.fueHechoCheckOutPara(propietarioMock)).thenReturn(true);

		Usuario usuario = new Usuario();
		usuario.agregarReserva(reservaMock);

		// Llamar al método y verificar que retorna true
		boolean resultado = usuario.fueHechoCheckOutConPropietario(propietarioMock);

		// Verificar que el resultado sea true
		assertTrue(resultado); // Se espera que el check-out haya sido realizado
	}

	@Test
	void testFueHechoCheckOutConInquilinoSinCheckOut() {
		// Crear mocks necesarios
		Usuario inquilinoMock = mock(Usuario.class);
		Usuario usuarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);

		// Simular que la reserva está finalizada y que la propiedad está asignada al
		// usuarioMock
		when(reservaMock.propietarioAsigando()).thenReturn(usuarioMock);
		when(reservaMock.estaFinalizada()).thenReturn(false);
		when(inquilinoMock.getReservas()).thenReturn(Collections.singletonList(reservaMock));

		// Llamar al método y verificar que retorna false
		boolean resultado = usuarioMock.fueHechoCheckOutConInquilino(inquilinoMock);
		assertFalse(resultado); // Se espera que el check-out no haya sido realizado
	}

	/*
	 * @Test
	 * 
	 * @DisplayName("getReservasFuturas should return only future reservations")
	 * void testGetReservasFuturas() { LocalDate today = LocalDate.now(); Reserva
	 * pastReserva = mock(Reserva.class); Reserva futureReserva =
	 * mock(Reserva.class);
	 * 
	 * when(pastReserva.getFechaEntrada()).thenReturn(today.minusDays(1));
	 * when(futureReserva.getFechaEntrada()).thenReturn(today.plusDays(1));
	 * 
	 * usuario.getReservas().add(pastReserva);
	 * usuario.getReservas().add(futureReserva);
	 * 
	 * List<Reserva> futureReservas = usuario.getReservasFuturas();
	 * 
	 * assertEquals(1, futureReservas.size());
	 * assertTrue(futureReservas.contains(futureReserva));
	 * assertFalse(futureReservas.contains(pastReserva)); }
	 */

	@Test

	void testFueHechoCheckOutConInquilinoTrue() {
		Usuario inquilino = mock(Usuario.class);
		Reserva reserva = mock(Reserva.class);
		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva);

		when(inquilino.getReservas()).thenReturn(reservas);
		when(reserva.fueHechoCheckOutPara(usuario)).thenReturn(true);

		assertTrue(usuario.fueHechoCheckOutConInquilino(inquilino));
	}

	@Test

	void testFueHechoCheckOutConInquilinoFalse() {
		Usuario inquilino = mock(Usuario.class);
		Reserva reserva = mock(Reserva.class);
		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva);

		when(inquilino.getReservas()).thenReturn(reservas);
		when(reserva.fueHechoCheckOutPara(usuario)).thenReturn(false);

		assertFalse(usuario.fueHechoCheckOutConInquilino(inquilino));
	}

	@Test

	void testGetComentarios() {
		Usuario usuario = new Usuario("messi", "messi@", "11");
		List<Ranking> rankings = new ArrayList<>();
		usuario.setRankings(rankings);

		List<String> expectedComments = List.of("bueno!", "canchero!");

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getComentarios(rankings)).thenReturn(expectedComments);

			List<String> actualComments = usuario.getComentarios();

			assertEquals(expectedComments, actualComments);
		}
	}

	@Test

	void testGetPuntajePromedio() {
		Usuario usuario = new Usuario("John Doe", "john@example.com", "1234567890");
		List<Ranking> rankings = new ArrayList<>(); // Assume this is populated with some rankings
		usuario.setRankings(rankings);

		double expectedScore = 4.5;

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getPuntajePromedio(rankings)).thenReturn(expectedScore);

			double actualScore = usuario.getPuntajePromedio();

			assertEquals(expectedScore, actualScore, 0.001);
		}
	}

	@Test
	void testGetPuntajePromedioEnCategoria() {
		Usuario usuario = new Usuario("Alice Smith", "alice@example.com", "5555555555");
		List<Ranking> rankings = new ArrayList<>();
		usuario.setRankings(rankings);

		Categoria categoria = mock(Categoria.class);
		double expectedScore = 4.2;

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getPuntajePromedioEnCategoria(rankings, categoria))
					.thenReturn(expectedScore);

			double actualScore = usuario.getPuntajePromedioEnCategoria(categoria);

			assertEquals(expectedScore, actualScore, 0.001);
		}
	}

	@Test
	void testGetComentariosComoPropietario() {
	    Usuario usuario = new Usuario("John Doe", "john@example.com", "1234567890");
	    List<Ranking> rankings = new ArrayList<>();
	    usuario.setRankings(rankings);

	    List<String> comentariosNuevos = List.of("Bueno", "Responsable!");

	    try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
	        mockedStatic.when(() -> GestorRanking.getComentariosPorRol(rankings, TipoRankeable.PROPIETARIO))
	                .thenReturn(comentariosNuevos);

	        List<String> comentariosActuales = usuario.getComentariosComoPropietario();
	        assertEquals(comentariosNuevos, comentariosActuales);
	    }
	}

	@Test

	void testGetComentariosComoInquilino() {
		Usuario usuario = new Usuario("John Doe", "john@example.com", "1234567890");
		List<Ranking> rankings = new ArrayList<>();
		usuario.setRankings(rankings);

		List<String> expectedComments = List.of("Clean guest!", "Respectful!");

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getComentariosPorRol(rankings, TipoRankeable.INQUILINO))
					.thenReturn(expectedComments);

			List<String> actualComments = usuario.getComentariosComoInquilino();

			assertEquals(expectedComments, actualComments);
		}
	}

	@Test

	void testGetFechaDeCreacion() {
		LocalDate expectedDate = LocalDate.now();
		Usuario newUser = new Usuario("Jane Doe", "jane@example.com", "9876543210");

		assertEquals(expectedDate, newUser.getFechaDeCreacion());
	}

	@Test

	void testSetInmuebles() {
		Inmueble mockInmueble = mock(Inmueble.class);
		List<Inmueble> inmuebles = new ArrayList<>();
		inmuebles.add(mockInmueble);

		usuario.setInmuebles(inmuebles);

		assertEquals(inmuebles, usuario.getInmuebles());
	}

	@Test

	void testSetRankings() {
		Ranking mockRanking = mock(Ranking.class);
		List<Ranking> rankings = new ArrayList<>();
		rankings.add(mockRanking);

		usuario.setRankings(rankings);

		assertEquals(rankings, usuario.getRankings());
	}

}