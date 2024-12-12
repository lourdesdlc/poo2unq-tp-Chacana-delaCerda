package usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

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

	private Inmueble inmuebleMock;

	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;


	@BeforeEach
	void setUp() {
		usuario = new Usuario("Juan Pérez", "juan@example.com", "123456789");

		inmuebleMock = mock(Inmueble.class);


		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);

		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);

		usuario.setReservas(reservas);
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
		
		Usuario u = new Usuario();
		Reserva reservaMock = mock(Reserva.class); 

		u.agregarReserva(reservaMock); 

		
		assertEquals(1, u.getReservas().size());
		assertTrue(u.getReservas().contains(reservaMock));
	}

	@Test
	void testAgregarInmueble() {
		usuario.agregarInmueble(inmuebleMock);
		assertEquals(1, usuario.getInmuebles().size());
		assertTrue(usuario.getInmuebles().contains(inmuebleMock));

	
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

	@Test
	void testAgregarRankingValido() {
		
		Usuario uReal = spy(new Usuario());

		
		Usuario mockRankeador = mock(Usuario.class);
		Ranking mockRanking = mock(Ranking.class);

		
		when(mockRanking.getRankeador()).thenReturn(mockRankeador);

		
		doReturn(true).when(uReal).fueHechoCheckOutConPropietario(mockRankeador);

		
		uReal.agregarRanking(mockRanking);

		
		assertEquals(1, uReal.getRankings().size());
		assertTrue(uReal.getRankings().contains(mockRanking));

		
		verify(uReal).fueHechoCheckOutConPropietario(mockRankeador);
	}

	@Test
	void testAgregarRankingInvalido() {
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

		assertEquals(2, cantidad); 
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
		when(reserva1.getFechaEntrada()).thenReturn(hoy.plusDays(5)); 

		when(usuarioMock.getReservas()).thenReturn(List.of(reserva1)); 

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

		Usuario usuarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);
		when(usuarioMock.getReservas()).thenReturn(Collections.singletonList(reservaMock));
		when(reservaMock.estaFinalizada()).thenReturn(true); 


		usuarioMock.validarCheckOut(usuarioMock); 

	}

	@Test
	void testFueHechoCheckOutConPropietarioSinCheckOut() {

		Usuario propietarioMock = mock(Usuario.class);
		Usuario usuarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);

		when(reservaMock.propietarioAsigando()).thenReturn(propietarioMock);
		when(reservaMock.estaFinalizada()).thenReturn(false);
		when(usuarioMock.getReservas()).thenReturn(Collections.singletonList(reservaMock));

		boolean resultado = usuarioMock.fueHechoCheckOutConPropietario(propietarioMock);
		assertFalse(resultado); 
	}

	@Test
	void testFueHechoCheckOutConPropietario() {

		Usuario propietarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);

		when(reservaMock.fueHechoCheckOutPara(propietarioMock)).thenReturn(true);

		Usuario usuario = new Usuario();
		usuario.agregarReserva(reservaMock);

		boolean resultado = usuario.fueHechoCheckOutConPropietario(propietarioMock);

		assertTrue(resultado); 
	}

	@Test
	void testFueHechoCheckOutConInquilinoSinCheckOut() {
		Usuario inquilinoMock = mock(Usuario.class);
		Usuario usuarioMock = mock(Usuario.class);
		Reserva reservaMock = mock(Reserva.class);

		when(reservaMock.propietarioAsigando()).thenReturn(usuarioMock);
		when(reservaMock.estaFinalizada()).thenReturn(false);
		when(inquilinoMock.getReservas()).thenReturn(Collections.singletonList(reservaMock));

		boolean resultado = usuarioMock.fueHechoCheckOutConInquilino(inquilinoMock);
		assertFalse(resultado); 
	}

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

		List<String> comentariosEsperados = List.of("bueno!", "canchero!");

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getComentarios(rankings)).thenReturn(comentariosEsperados);

			List<String> comentariosActuales = usuario.getComentarios();

			assertEquals(comentariosEsperados, comentariosActuales);
		}
	}

	@Test

	void testGetPuntajePromedio() {
		Usuario usuario = new Usuario("Juan", "juan@example.com", "1234567890");
		List<Ranking> rankings = new ArrayList<>(); 
		usuario.setRankings(rankings);

		double puntajeEsperado = 4.5;

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getPuntajePromedio(rankings)).thenReturn(puntajeEsperado);

			double puntajeActual = usuario.getPuntajePromedio();

			assertEquals(puntajeEsperado, puntajeActual, 0.001);
		}
	}

	@Test
	void testGetPuntajePromedioEnCategoria() {
		Usuario usuario = new Usuario("Ana", "aana@example.com", "5555555555");
		List<Ranking> rankings = new ArrayList<>();
		usuario.setRankings(rankings);

		Categoria categoria = mock(Categoria.class);
		double puntajeEsperado = 4.2;

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getPuntajePromedioEnCategoria(rankings, categoria))
					.thenReturn(puntajeEsperado);

			double actualScore = usuario.getPuntajePromedioEnCategoria(categoria);

			assertEquals(puntajeEsperado, actualScore, 0.001);
		}
	}

	@Test
	void testGetComentariosComoPropietario() {
	    Usuario usuario = new Usuario("Juan", "juan@example.com", "1234567890");
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
		
		Usuario usuario = new Usuario("Julian", "julianA@example.com", "1234567890");
		List<Ranking> rankings = new ArrayList<>();
		usuario.setRankings(rankings);

		List<String> comentariosEsperados = List.of("Amable!", "Responde rapido");

		try (MockedStatic<GestorRanking> mockedStatic = mockStatic(GestorRanking.class)) {
			mockedStatic.when(() -> GestorRanking.getComentariosPorRol(rankings, TipoRankeable.INQUILINO))
					.thenReturn(comentariosEsperados);

			List<String> comentariosActuales = usuario.getComentariosComoInquilino();

			assertEquals(comentariosEsperados, comentariosActuales);
		}
	}

	@Test
	void testGetFechaDeCreacion() {
		LocalDate expectedDate = LocalDate.now();
		Usuario newUser = new Usuario("Mariano", "mariano@example.com", "9876543210");

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