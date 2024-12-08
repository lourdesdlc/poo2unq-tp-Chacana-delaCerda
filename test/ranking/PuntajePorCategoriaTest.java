package ranking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntajePorCategoriaTest {

	@Test
	void testValidarPuntajeValido() {

		Categoria c = new Categoria("Limpieza", TipoRankeable.INMUEBLE);
		PuntajePorCategoria puntajePorCategoria = new PuntajePorCategoria(c, 3);
		assertDoesNotThrow(() -> puntajePorCategoria.validarPuntaje(3));
	}

	@Test
	void testValidarPuntajeInvalidoMenor() {
		Categoria c = new Categoria("Limpieza", TipoRankeable.INMUEBLE);
		PuntajePorCategoria puntajePorCategoria = new PuntajePorCategoria(c, 3);
		Exception exception = assertThrows(RuntimeException.class, () -> puntajePorCategoria.validarPuntaje(0));
		assertEquals("El puntaje debe estar en una escala del 1 al 5.", exception.getMessage());
	}

	@Test
	void testValidarPuntajeInvalidoMayor() {
		Categoria c = new Categoria("Limpieza", TipoRankeable.INMUEBLE);
		PuntajePorCategoria puntajePorCategoria = new PuntajePorCategoria(c, 3);
		Exception exception = assertThrows(RuntimeException.class, () -> puntajePorCategoria.validarPuntaje(6));
		assertEquals("El puntaje debe estar en una escala del 1 al 5.", exception.getMessage());
	}
}
