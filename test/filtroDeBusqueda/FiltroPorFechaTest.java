package filtroDeBusqueda;

import inmueble.Inmueble;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FiltroPorFechaTest {

	@Test
	void testGetSetFechaEntrada() {
		LocalDate fechaEntrada = LocalDate.of(2024, 12, 10);
		FiltroPorFecha filtro = new FiltroPorFecha(fechaEntrada, LocalDate.of(2024, 12, 15));

		filtro.setFechaEntrada(fechaEntrada);

		assertEquals(fechaEntrada, filtro.getFechaEntrada(), "La fecha de entrada debe ser la misma que la asignada");
	}

	@Test
	void testGetSetFechaSalida() {
		LocalDate fechaSalida = LocalDate.of(2024, 12, 15);
		FiltroPorFecha filtro = new FiltroPorFecha(LocalDate.of(2024, 12, 10), fechaSalida);

		filtro.setFechaSalida(fechaSalida);

		assertEquals(fechaSalida, filtro.getFechaSalida(), "La fecha de salida debe ser la misma que la asignada");
	}

	@Test
	void testCumple() {
		FiltroPorFecha filtro = new FiltroPorFecha(LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15));
		Inmueble inmuebleMock = mock(Inmueble.class);

		when(inmuebleMock.estaDisponibleParaLasFechas(any(LocalDate.class), any(LocalDate.class))).thenReturn(true);

		assertTrue(filtro.cumple(inmuebleMock),
				"El filtro debe cumplirse cuando el inmueble está disponible en el rango de fechas");
	}
}
