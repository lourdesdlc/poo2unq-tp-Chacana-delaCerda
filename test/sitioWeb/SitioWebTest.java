package sitioWeb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtroDeBusqueda.CriterioBusqueda;
import inmueble.Inmueble;
import inmueble.Servicio;
import inmueble.TipoInmueble;
import ranking.Categoria;
import ranking.TipoRankeable;
import reserva.*;
import usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SitioWebTest {

	private SitioWeb sitioWeb;
	private Usuario usuarioMock;
	private Inmueble inmuebleMock;
	private Categoria categoriaMock;
	private Servicio servicioMock;
	private TipoInmueble tipoInmuebleMock;

	@BeforeEach
	void setUp() {
		sitioWeb = new SitioWeb();
		usuarioMock = mock(Usuario.class);
		inmuebleMock = mock(Inmueble.class);
		categoriaMock = mock(Categoria.class);
		servicioMock = mock(Servicio.class);
		tipoInmuebleMock = mock(TipoInmueble.class);
	}

	@Test
	void testRegistrarUsuarioExitosamente() {
		sitioWeb.registrarUsuario(usuarioMock);
		assertTrue(sitioWeb.getUsuarios().contains(usuarioMock));
	}

	@Test
	void testRegistrarUsuarioYaRegistrado() {
		sitioWeb.registrarUsuario(usuarioMock);
		Exception exception = assertThrows(RuntimeException.class, () -> sitioWeb.registrarUsuario(usuarioMock));
		assertEquals("El usuario ya está registrado", exception.getMessage());
	}

	@Test
	void testBuscarInmueblesConCriterio() {
		CriterioBusqueda criterioMock = mock(CriterioBusqueda.class);
		when(inmuebleMock.estaDisponibleParaLasFechas(any(), any())).thenReturn(true);
		when(usuarioMock.getInmuebles()).thenReturn(List.of(inmuebleMock));
		sitioWeb.registrarUsuario(usuarioMock);

		when(criterioMock.cumple(inmuebleMock)).thenReturn(true);
		List<Inmueble> resultados = sitioWeb.buscarInmuebles(criterioMock);
		assertTrue(resultados.contains(inmuebleMock));
	}

	@Test
	void testDarDeAltaTipoInmueble() {
		sitioWeb.darDeAltaTipoInmueble(tipoInmuebleMock);
		assertTrue(sitioWeb.getTiposDeInmueble().contains(tipoInmuebleMock));
	}

	@Test
	void testDarDeAltaCategoria() {
		sitioWeb.darDeAltaCategoria(categoriaMock);
		assertTrue(sitioWeb.getCategorias().contains(categoriaMock));
	}

	@Test
	void testDarDeAltaServicioInmueble() {
		sitioWeb.darDeAltaServicioInmueble(servicioMock);
		assertTrue(sitioWeb.getServiciosDeInmuebles().contains(servicioMock));
	}

	@Test
	void testEliminarTipoInmueble() {
		sitioWeb.darDeAltaTipoInmueble(tipoInmuebleMock);
		sitioWeb.eliminarTipoInmueble(tipoInmuebleMock);
		assertFalse(sitioWeb.getTiposDeInmueble().contains(tipoInmuebleMock));
	}

	@Test
	void testEliminarCategoria() {
		sitioWeb.darDeAltaCategoria(categoriaMock);
		sitioWeb.eliminarCategoria(categoriaMock);
		assertFalse(sitioWeb.getListaDeCategoriasPara(categoriaMock.getTipoRankeable()).contains(categoriaMock));
	}

	@Test
	void testEliminarServicioInmueble() {
		sitioWeb.darDeAltaServicioInmueble(servicioMock);
		sitioWeb.eliminarServicioInmueble(servicioMock);
		assertFalse(sitioWeb.getServiciosDeInmuebles().contains(servicioMock));
	}

	@Test
	void testTopTenInquilinosQueMasHanAlquilado() {
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		when(usuario1.cantidadDeAlquileres()).thenReturn(5);
		when(usuario2.cantidadDeAlquileres()).thenReturn(10);

		sitioWeb.registrarUsuario(usuario1);
		sitioWeb.registrarUsuario(usuario2);

		List<Usuario> topTen = sitioWeb.topTenInquilinosQueMasHanAlquilado();
		assertEquals(List.of(usuario2, usuario1), topTen);
	}

	@Test
	void testInmueblesLibres() {
		when(inmuebleMock.estaDisponibleParaLasFechas(any(), any())).thenReturn(true);
		when(usuarioMock.getInmuebles()).thenReturn(List.of(inmuebleMock));
		sitioWeb.registrarUsuario(usuarioMock);

		List<Inmueble> libres = sitioWeb.inmueblesLibres();
		assertTrue(libres.contains(inmuebleMock));
	}

	@Test
	void testTasaOcupacion() {
		when(inmuebleMock.estaDisponibleParaLasFechas(any(), any())).thenReturn(false);
		when(usuarioMock.getInmuebles()).thenReturn(List.of(inmuebleMock));
		sitioWeb.registrarUsuario(usuarioMock);

		double tasaOcupacion = sitioWeb.tasaOcupacion();
		assertEquals(0.0, tasaOcupacion, 0.01); 
	}

	@Test
	void testEsCategoriaValida() {

		Categoria categoriaMock = mock(Categoria.class);
		when(categoriaMock.getTipoRankeable()).thenReturn(TipoRankeable.PROPIETARIO);
		when(categoriaMock.getNombre()).thenReturn("Responsable");


		List<Categoria> categoriasMock = List.of(categoriaMock);


		SitioWeb sitioWebMock = spy(sitioWeb);
		doReturn(categoriasMock).when(sitioWebMock).getListaDeCategoriasPara(TipoRankeable.PROPIETARIO);


		assertTrue(sitioWebMock.esCategoriaValida(categoriaMock));

	}

	@Test
	void testEsCategoriaInvalida() {
		Categoria categoriaMock = mock(Categoria.class);
		when(categoriaMock.getTipoRankeable()).thenReturn(TipoRankeable.INQUILINO);


		SitioWeb sitioWebMock = spy(sitioWeb);


		List<Categoria> categorias = List.of();
		doReturn(categorias).when(sitioWebMock).getCategorias();


		assertFalse(sitioWebMock.esCategoriaValida(categoriaMock));
	}

	@Test
	void testDarDeAltaInmueble() {

		Usuario propietarioMock = mock(Usuario.class);
		Inmueble inmuebleMock = mock(Inmueble.class);


		SitioWeb sitioWebMock = spy(sitioWeb);


		sitioWebMock.darDeAltaInmueble(propietarioMock, inmuebleMock);

		verify(propietarioMock).agregarInmueble(inmuebleMock);
	}

	@Test
	void testEliminarInmueble() {

		Usuario usuarioMock = mock(Usuario.class);
		Inmueble inmuebleMock = mock(Inmueble.class);


		SitioWeb sitioWebMock = spy(sitioWeb);

		sitioWebMock.eliminarInmueble(usuarioMock, inmuebleMock);

		verify(usuarioMock).removerInmueble(inmuebleMock);
	}

	@Test
	void testSetCategorias() {
		SitioWeb sitioWebMock = new SitioWeb();

		List<Categoria> categorias = List.of(mock(Categoria.class), mock(Categoria.class));

		sitioWebMock.setCategorias(categorias);

		assertEquals(categorias, sitioWebMock.getCategorias());
	}

	@Test
	void testGetReservasFuturas() {
	   
	    LocalDate fechaHoy = LocalDate.now();
	    LocalDate fechaFutura = fechaHoy.plusDays(1);
	    LocalDate fechaPasada = fechaHoy.minusDays(1);

	    
	    Reserva reservaFuturaMock = mock(Reserva.class);
	    when(reservaFuturaMock.getFechaEntrada()).thenReturn(fechaFutura);

	    Reserva reservaPasadaMock = mock(Reserva.class);
	    when(reservaPasadaMock.getFechaEntrada()).thenReturn(fechaPasada);

	 
	    Usuario usuarioMock = new Usuario();
	    List<Reserva> reservasMock = List.of(reservaFuturaMock, reservaPasadaMock);
	    usuarioMock.setReservas(reservasMock); // 

	    
	    List<Reserva> reservasFuturas = usuarioMock.getReservasFuturas();

	   
	    assertEquals(1, reservasFuturas.size(), "Debe haber solo una reserva futura");
	    assertTrue(reservasFuturas.contains(reservaFuturaMock), "Debe contener la reserva futura");
	    assertFalse(reservasFuturas.contains(reservaPasadaMock), "No debe contener la reserva pasada");
	}


}
