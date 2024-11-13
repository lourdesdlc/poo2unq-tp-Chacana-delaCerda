package BusquedaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Inmueble.Inmueble;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import exepciones.FiltroException;
import filtroDeBusqueda.FiltroCompuesto;
import filtroDeBusqueda.FiltroPorCiudad;
import filtroDeBusqueda.FiltroPorFecha;
import sitioWeb.SitioWeb;
import usuario.Inquilino;

public class BusquedaTest {

	private Inquilino user;
	private SitioWeb web;
	private Inmueble casita;
	private LocalDate inicio;
	private LocalDate fin;

	@BeforeEach
	void setUp() {
		// Inicializar fechas
		inicio = LocalDate.of(2024, 11, 15); // 15 de Noviembre de 2024
		fin = LocalDate.of(2024, 11, 20); // 20 de Noviembre de 2024

		// Crear usuario Inquilino
		user = new Inquilino("Spreen", "@", "11");

		// Crear un sitio web
		web = new SitioWeb();

		// Crear un mock de Inmueble
		casita = mock(Inmueble.class);

		// Registrar al usuario en el sitio web
		web.registrarUsuario(user);

		// Agregar el inmueble al sitio web
		web.getInmuebles().add(casita);

		// Configurar el comportamiento del mock para devolver valores esperados
		when(casita.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 14)); // Check-in antes de fecha de salida
		when(casita.getCheckOut()).thenReturn(LocalDate.of(2024, 11, 18)); // Check-out dentro del rango de fechas
		when(casita.getCiudad()).thenReturn("Quilmes");
	}

	@Test
	public void usuarioRealizaBusquedaEfectiva() {
		// Crear el filtro compuesto con ciudad y fechas
		FiltroCompuesto filtro = new FiltroCompuesto();
		filtro.agregarFiltro(new FiltroPorCiudad("Quilmes"));
		filtro.agregarFiltro(new FiltroPorFecha(inicio, fin));

		// Verificar que el inmueble casita esté en los resultados de la búsqueda
		assertTrue(user.buscarInmueble(filtro).contains(casita));
	}

	@Test
	public void usuarioRealizaBusquedaErronea() {
		// Crear el filtro compuesto con solo fechas
		FiltroCompuesto filtro = new FiltroCompuesto();
		filtro.agregarFiltro(new FiltroPorFecha(inicio, fin));

		// Verificar que se lance la excepción esperada
		FiltroException exception = assertThrows(FiltroException.class, () -> {
			user.buscarInmueble(filtro);
		});

		// Verificar el mensaje de la excepción si es necesario
		assertEquals("Error, Falta un filtro obligatorio", exception.getMessage());
	}
}
