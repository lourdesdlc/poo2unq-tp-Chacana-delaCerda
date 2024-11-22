package ranking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

public class GestorRankingTest {

    private Categoria categoriaMock1;
    private Categoria categoriaMock2;
    private Ranking rankingMock1;
    private Ranking rankingMock2;

    @BeforeEach
    void setUp() {
        categoriaMock1 = mock(Categoria.class);
        categoriaMock2 = mock(Categoria.class);
        rankingMock1 = mock(Ranking.class);
        rankingMock2 = mock(Ranking.class);
    }

    @Test
    void testGetPuntajePromedioConRankings() {
        when(rankingMock1.getPuntajePromedio()).thenReturn(4.5);
        when(rankingMock2.getPuntajePromedio()).thenReturn(3.0);

        List<Ranking> rankings = List.of(rankingMock1, rankingMock2);

        double promedio = GestorRanking.getPuntajePromedio(rankings);

        assertEquals(3.75, promedio, 0.01);
    }

    @Test
    void testGetPuntajePromedioSinRankingsDevuelveCero() {
        List<Ranking> rankings = new ArrayList<>();

        double promedio = GestorRanking.getPuntajePromedio(rankings);

        assertEquals(0.0, promedio);
    }

    @Test
    void testGetPuntajePromedioEnCategoriaConPuntajes() {
        PuntajePorCategoria puntaje1 = mock(PuntajePorCategoria.class);
        PuntajePorCategoria puntaje2 = mock(PuntajePorCategoria.class);

        when(puntaje1.getCategoria()).thenReturn(categoriaMock1);
        when(puntaje2.getCategoria()).thenReturn(categoriaMock1);
        when(puntaje1.getPuntaje()).thenReturn(5);
        when(puntaje2.getPuntaje()).thenReturn(3);

        when(rankingMock1.getPuntajePorCategoria()).thenReturn(List.of(puntaje1, puntaje2));

        List<Ranking> rankings = List.of(rankingMock1);

        double promedio = GestorRanking.getPuntajePromedioEnCategoria(rankings, categoriaMock1);

        assertEquals(4.0, promedio, 0.01);
    }

    @Test
    void testGetPuntajePromedioEnCategoriaSinPuntajesDevuelveCero() {
        when(rankingMock1.getPuntajePorCategoria()).thenReturn(List.of());

        List<Ranking> rankings = List.of(rankingMock1);

        double promedio = GestorRanking.getPuntajePromedioEnCategoria(rankings, categoriaMock1);

        assertEquals(0.0, promedio);
    }

    @Test
    void testGetComentariosConRankings() {
        when(rankingMock1.getComentario()).thenReturn("Buen servicio");
        when(rankingMock2.getComentario()).thenReturn("Regular atención");

        List<Ranking> rankings = List.of(rankingMock1, rankingMock2);

        List<String> comentarios = GestorRanking.getComentarios(rankings);

        assertEquals(List.of("Buen servicio", "Regular atención"), comentarios);
    }

    @Test
    void testGetComentariosConListaVaciaDevuelveListaVacia() {
        List<Ranking> rankings = new ArrayList<>();

        List<String> comentarios = GestorRanking.getComentarios(rankings);

        assertTrue(comentarios.isEmpty());
    }

    @Test
    void testGetComentariosPorRolConRankings() {
        when(rankingMock1.getTipoRankeable()).thenReturn(TipoRankeable.PROPIETARIO);
        when(rankingMock2.getTipoRankeable()).thenReturn(TipoRankeable.INQUILINO);
        when(rankingMock1.getComentario()).thenReturn("Excelente trato");

        List<Ranking> rankings = List.of(rankingMock1, rankingMock2);

        List<String> comentarios = GestorRanking.getComentariosPorRol(rankings, TipoRankeable.PROPIETARIO);

        assertEquals(List.of("Excelente trato"), comentarios);
    }

    @Test
    void testGetComentariosPorRolSinCoincidenciasDevuelveListaVacia() {
        when(rankingMock1.getTipoRankeable()).thenReturn(TipoRankeable.PROPIETARIO);

        List<Ranking> rankings = List.of(rankingMock1);

        List<String> comentarios = GestorRanking.getComentariosPorRol(rankings, TipoRankeable.INQUILINO);

        assertTrue(comentarios.isEmpty());
    }
    
    @Test
    void testGetPuntajePromedioEnCategoriaConCategoriasDiferentes() {
        PuntajePorCategoria puntaje1 = mock(PuntajePorCategoria.class);
        PuntajePorCategoria puntaje2 = mock(PuntajePorCategoria.class);

        when(puntaje1.getCategoria()).thenReturn(categoriaMock1);
        when(puntaje2.getCategoria()).thenReturn(categoriaMock2); 
        when(puntaje1.getPuntaje()).thenReturn(4);
        when(puntaje2.getPuntaje()).thenReturn(2);

        when(rankingMock1.getPuntajePorCategoria()).thenReturn(List.of(puntaje1, puntaje2));

        List<Ranking> rankings = List.of(rankingMock1);

        double promedio = GestorRanking.getPuntajePromedioEnCategoria(rankings, categoriaMock1);

        assertEquals(4.0, promedio, 0.01);
    }
    
}

