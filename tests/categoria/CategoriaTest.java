package categoria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import ranking.Categoria;
import ranking.TipoRankeable;

public class CategoriaTest {

    @Test
    void testCrearCategoriaConDatosValidos() {
        Categoria categoria = new Categoria("Limpieza", TipoRankeable.INMUEBLE);

        assertEquals("Limpieza", categoria.getNombre());
        assertEquals(TipoRankeable.INMUEBLE, categoria.getTipoRankeable());
    }

    @Test
    void testGetNombreDevuelveElNombreCorrecto() {
        Categoria categoria = new Categoria("Amabilidad", TipoRankeable.PROPIETARIO);

        assertEquals("Amabilidad", categoria.getNombre());
    }

    @Test
    void testGetTipoRankeableDevuelveElTipoCorrecto() {
        Categoria categoria = new Categoria("Puntualidad", TipoRankeable.INQUILINO);

        assertEquals(TipoRankeable.INQUILINO, categoria.getTipoRankeable());
    }

    @Test
    void testPerteneceATipoRankeableDevuelveTrueCuandoCoincide() {
        Categoria categoria = new Categoria("Confiabilidad", TipoRankeable.PROPIETARIO);

        assertTrue(categoria.perteneceATipoRankeable(TipoRankeable.PROPIETARIO));
    }

    @Test
    void testPerteneceATipoRankeableDevuelveFalseCuandoNoCoincide() {
        Categoria categoria = new Categoria("Confiabilidad", TipoRankeable.PROPIETARIO);

        assertFalse(categoria.perteneceATipoRankeable(TipoRankeable.INQUILINO));
    }
}

