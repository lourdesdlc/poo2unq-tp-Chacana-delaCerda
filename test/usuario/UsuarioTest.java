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
		usuario.agregarReserva(reservaMock);
		assertEquals(1, usuario.getReservas().size());
		assertTrue(usuario.getReservas().contains(reservaMock));
	}
/* arreglar
	@Test
	void testGetReservasFuturas() {
	    // Simular fecha actual
	    LocalDate hoy = LocalDate.now();

	    // Crear mocks necesarios
	    Reserva reservaMock = mock(Reserva.class);
	    Usuario usuarioMock = spy(usuario); // Usar spy para mockear parcialmente

	    // Mockear el comportamiento de la reserva
	    when(reservaMock.getFechaEntrada()).thenReturn(hoy.plusDays(1)); // Fecha futura

	    // Agregar la reserva mockeada al usuario
	    usuarioMock.agregarReserva(reservaMock);

	    // Llamar al método y verificar
	    List<Reserva> futuras = usuarioMock.getReservasFuturas();

	    // Verificar resultados
	    assertEquals(1, futuras.size());
	    assertTrue(futuras.contains(reservaMock));
	}
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
/* arreglar
	@Test
	void testAgregarRanking() {
		Usuario rankeadorMock = mock(Usuario.class);
		when(rankingMock.getRankeador()).thenReturn(rankeadorMock);

		// Mock de una reserva finalizada para validar check-out
		when(reservaMock.propietarioAsigando()).thenReturn(rankeadorMock);
		when(reservaMock.estaFinalizada()).thenReturn(true);
		usuario.agregarReserva(reservaMock);

		usuario.agregarRanking(rankingMock);
		assertTrue(usuario.getRankings().contains(rankingMock));
	}
*/
	@Test
	void testAgregarRankingSinCheckOutLanzaExcepcion() {
		Usuario rankeadorMock = mock(Usuario.class);
		when(rankingMock.getRankeador()).thenReturn(rankeadorMock);

		Exception exception = assertThrows(RuntimeException.class, () -> usuario.agregarRanking(rankingMock));
		assertEquals("No se puede rankear antes de hacer el check-out", exception.getMessage());
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
}