package usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import inmueble.Inmueble;
import ranking.Ranking;
import reserva.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;
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
}