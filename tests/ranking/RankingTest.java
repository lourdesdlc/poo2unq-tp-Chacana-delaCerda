package ranking;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import sitioWeb.SitioWeb;

public class RankingTest {

    private SitioWeb sitioMock;
    private Categoria categoriaMock;

    @BeforeEach
    void setUp() {
        sitioMock = mock(SitioWeb.class);
        categoriaMock = mock(Categoria.class);
    }

    @Test
    void testCrearRankingConDatosValidos() {
        Ranking ranking = new Ranking("Buen servicio", sitioMock);

        assertEquals("Buen servicio", ranking.getComentario());
        assertTrue(ranking.getPuntajePorCategoria().isEmpty());
    }

    @Test
    void testAgregarPuntajePorCategoriaConCategoriaValida() {
        when(sitioMock.esCategoriaValida(categoriaMock)).thenReturn(true);

        Ranking ranking = new Ranking("Buen servicio", sitioMock);

        PuntajePorCategoria puntaje = new PuntajePorCategoria(categoriaMock, 5);

        assertDoesNotThrow(() -> ranking.agregarPuntajePorCategoria(puntaje));
        assertEquals(1, ranking.getPuntajePorCategoria().size());
        assertEquals(puntaje, ranking.getPuntajePorCategoria().get(0));
    }

    @Test
    void testAgregarPuntajePorCategoriaConCategoriaInvalida() {
        when(sitioMock.esCategoriaValida(categoriaMock)).thenReturn(false);

        Ranking ranking = new Ranking("Buen servicio", sitioMock);

        PuntajePorCategoria puntaje = new PuntajePorCategoria(categoriaMock, 5);

        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> ranking.agregarPuntajePorCategoria(puntaje));
        assertEquals("La categoría ingresada no es válida.", exception.getMessage());
        assertTrue(ranking.getPuntajePorCategoria().isEmpty());
    }

    @Test
    void testCalculoPuntajePromedioConPuntajesValidos() {
        when(sitioMock.esCategoriaValida(any())).thenReturn(true);

        Ranking ranking = new Ranking("Buen servicio", sitioMock);

        ranking.agregarPuntajePorCategoria(new PuntajePorCategoria(categoriaMock, 4));
        ranking.agregarPuntajePorCategoria(new PuntajePorCategoria(categoriaMock, 5));

        assertEquals(4.5, ranking.getPuntajePromedio());
    }

    @Test
    void testCalculoPuntajePromedioSinPuntajesDevuelveCero() {
        Ranking ranking = new Ranking("Buen servicio", sitioMock);

        assertEquals(0.0, ranking.getPuntajePromedio());
    }

    @Test
    void testValidarPuntajeLanzaExcepcionSiEsInvalido() {
        assertThrows(RuntimeException.class, () -> new PuntajePorCategoria(categoriaMock, 0));
        assertThrows(RuntimeException.class, () -> new PuntajePorCategoria(categoriaMock, 6));
    }

    @Test
    void testValidarPuntajeAceptaValoresValidos() {
        assertDoesNotThrow(() -> new PuntajePorCategoria(categoriaMock, 1));
        assertDoesNotThrow(() -> new PuntajePorCategoria(categoriaMock, 5));
    }
}
