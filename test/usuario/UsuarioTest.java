package usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import inmueble.Inmueble;
import ranking.Ranking;
import reserva.Reserva;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioTest {
    private Usuario usuario;
    private Reserva reservaMock;
    private Inmueble inmuebleMock;
    private Ranking rankingMock;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Juan Pérez", "juan@example.com", "123456789");
        reservaMock = mock(Reserva.class);
        inmuebleMock = mock(Inmueble.class);
        rankingMock = mock(Ranking.class);
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

    @Test
    void testGetReservasFuturas() {
        LocalDate hoy = LocalDate.now();
        when(reservaMock.getFechaEntrada()).thenReturn(hoy.plusDays(1));

        usuario.agregarReserva(reservaMock);
        List<Reserva> futuras = usuario.getReservasFuturas();

        assertEquals(1, futuras.size());
        assertTrue(futuras.contains(reservaMock));
    }

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

    @Test
    void testAgregarRankingSinCheckOutLanzaExcepcion() {
        Usuario rankeadorMock = mock(Usuario.class);
        when(rankingMock.getRankeador()).thenReturn(rankeadorMock);

        Exception exception = assertThrows(RuntimeException.class, () -> usuario.agregarRanking(rankingMock));
        assertEquals("No se puede rankear antes de hacer el check-out", exception.getMessage());
    }
}