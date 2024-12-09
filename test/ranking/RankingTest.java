package ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exepciones.CategoriaException;
import exepciones.PuntajeException;
import usuario.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class RankingTest {

    private List<Categoria> categoriasValidasP;
    private Categoria categoriaI;
    private Categoria categoriaP;
    private Usuario rankeador;

    @BeforeEach
    void setUp() {
    	
        rankeador = mock(Usuario.class);

        categoriaP = new Categoria("Amabilidad", TipoRankeable.PROPIETARIO);
        categoriaI = new Categoria("Puntualidad", TipoRankeable.INQUILINO);

        categoriasValidasP = new ArrayList<>();
        categoriasValidasP.add(categoriaP);
    }

    @Test
    void testCrearRankingConCategoriasValidas() {
        Ranking ranking = new Ranking("Buen servicio", rankeador, TipoRankeable.PROPIETARIO, categoriasValidasP);

        assertEquals("Buen servicio", ranking.getComentario());
        assertTrue(ranking.getPuntajePorCategoria().isEmpty());
        assertEquals(categoriasValidasP, ranking.getCategorias());
    }

    @Test
    void testAgregarPuntajePorCategoriaConCategoriaValida() {
        Ranking ranking = new Ranking("Buen servicio", rankeador, TipoRankeable.PROPIETARIO, categoriasValidasP);

        PuntajePorCategoria puntajeValido = new PuntajePorCategoria(categoriaP, 4);
        ranking.agregarPuntajePorCategoria(puntajeValido);

        assertEquals(1, ranking.getPuntajePorCategoria().size());
        assertTrue(ranking.getPuntajePorCategoria().contains(puntajeValido));
    }

    @Test
    void testAgregarPuntajePorCategoriaConCategoriaInvalida() {
        Ranking ranking = new Ranking("Buen servicio", rankeador, TipoRankeable.PROPIETARIO, categoriasValidasP);

        PuntajePorCategoria puntajeInvalido = new PuntajePorCategoria(categoriaI, 3);

        Exception exception = assertThrows(CategoriaException.class, () -> {
            ranking.agregarPuntajePorCategoria(puntajeInvalido);
        });

        assertEquals("La categoría ingresada no es válida.", exception.getMessage());
        assertTrue(ranking.getPuntajePorCategoria().isEmpty());
    }

    @Test
    void testCalcularPuntajePromedio() {
        Ranking ranking = new Ranking("Buen servicio", rankeador, TipoRankeable.PROPIETARIO, categoriasValidasP);

        PuntajePorCategoria puntaje1 = new PuntajePorCategoria(categoriaP, 5);
        PuntajePorCategoria puntaje2 = new PuntajePorCategoria(categoriaP, 3);
        ranking.agregarPuntajePorCategoria(puntaje1);
        ranking.agregarPuntajePorCategoria(puntaje2);

        assertEquals(4.0, ranking.getPuntajePromedio());
    }

    @Test
    void testCalcularPuntajePromedioSinPuntajes() {
        Ranking ranking = new Ranking("Buen servicio", rankeador, TipoRankeable.PROPIETARIO, categoriasValidasP);

        assertEquals(0.0, ranking.getPuntajePromedio());
    }

    @Test
    void testValidarPuntajeFueraDeRango() {
        Exception exception = assertThrows(PuntajeException.class, () -> {
            new PuntajePorCategoria(categoriaI, 6); 
        });

        assertEquals("El puntaje debe estar en una escala del 1 al 5.", exception.getMessage());
    }
}
