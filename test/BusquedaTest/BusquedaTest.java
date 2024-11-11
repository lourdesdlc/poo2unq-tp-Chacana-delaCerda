package BusquedaTest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import filtroDeBusqueda.FiltroCompuesto;
import filtroDeBusqueda.FiltroPorCiudad;
import filtroDeBusqueda.FiltroPorFecha;
import tpgrupal.Inmueble;
import tpgrupal.SitioWeb;
import usuario.Inquilino;
import usuario.Usuario;

public class BusquedaTest {
	private Inquilino user;
	private SitioWeb web;
	private Inmueble casita;
	private LocalDate inicio;
	private LocalDate fin;

	@BeforeEach
	void setUp() {

		user = new Inquilino("Spreen", "@", "11", null);
		web = new SitioWeb();
		casita = mock(Inmueble.class);

		web.registrarUsuario(user);
		web.getInmuebles().add(casita);

		inicio = LocalDate.of(2024, 11, 15); // 15 de Noviembre de 2024
		fin = LocalDate.of(2024, 11, 20); // 20 de Noviembre de 2024

		// Definir los comportamientos de los getters del mock para que devuelvan fechas
		// que cumplen con la condición
		when(casita.getCheckIn()).thenReturn(LocalDate.of(2024, 11, 14)); // Check-in antes de fecha de salida
		when(casita.getCheckOut()).thenReturn(LocalDate.of(2024, 11, 18)); // Check-out dentro del rango de fechas
		when(casita.getCiudad()).thenReturn("Quilmes");
	}

	@Test
	public void usuarioRealizaBusquedaEfectiva() {
		FiltroCompuesto filtro = new FiltroCompuesto();

		filtro.agregarFiltro(new FiltroPorCiudad("Quilmes"));
		filtro.agregarFiltro(new FiltroPorFecha(inicio, fin));

		assertTrue(user.buscarInmueble(filtro).contains(casita));

	}

}
