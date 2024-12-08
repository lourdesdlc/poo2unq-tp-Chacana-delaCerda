package sitioWeb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
		assertEquals(0.0, tasaOcupacion, 0.01); // porque no hay alquileres.
	}

	@Test
	void testEsCategoriaValida() {
		// Creamos un mock de la categoria
		Categoria categoriaMock = mock(Categoria.class);
		when(categoriaMock.getTipoRankeable()).thenReturn(TipoRankeable.PROPIETARIO);
		when(categoriaMock.getNombre()).thenReturn("Responsable");

		// Creamos una lista con el mock
		List<Categoria> categoriasMock = List.of(categoriaMock);

		// Mockeamos el comportamiento de getListaDeCategoriasPara
		SitioWeb sitioWebMock = spy(sitioWeb);
		doReturn(categoriasMock).when(sitioWebMock).getListaDeCategoriasPara(TipoRankeable.PROPIETARIO);

		// Llamamos al método
		assertTrue(sitioWebMock.esCategoriaValida(categoriaMock));

	}

	@Test
	void testEsCategoriaInvalida() {
		Categoria categoriaMock = mock(Categoria.class);
		when(categoriaMock.getTipoRankeable()).thenReturn(TipoRankeable.INQUILINO);

		// Creando el SUT (SitioWeb)
		SitioWeb sitioWebMock = spy(sitioWeb);

		// Configuramos una lista vacía de categorías
		List<Categoria> categorias = List.of();
		doReturn(categorias).when(sitioWebMock).getCategorias();

		// Llamamos al método y verificamos que la categoría es inválida
		assertFalse(sitioWebMock.esCategoriaValida(categoriaMock));
	}

	@Test
	void testDarDeAltaInmueble() {
		// Creamos los mocks
		Usuario propietarioMock = mock(Usuario.class);
		Inmueble inmuebleMock = mock(Inmueble.class);

		// Creando el SUT
		SitioWeb sitioWebMock = spy(sitioWeb);

		// Llamamos al método darDeAltaInmueble
		sitioWebMock.darDeAltaInmueble(propietarioMock, inmuebleMock);

		// Verificamos que el método agregarInmueble haya sido llamado
		verify(propietarioMock).agregarInmueble(inmuebleMock);
	}

	@Test
	void testEliminarInmueble() {
		// Creamos los mocks
		Usuario usuarioMock = mock(Usuario.class);
		Inmueble inmuebleMock = mock(Inmueble.class);

		// Creando el SUT
		SitioWeb sitioWebMock = spy(sitioWeb);

		// Llamamos al método eliminarInmueble
		sitioWebMock.eliminarInmueble(usuarioMock, inmuebleMock);

		// Verificamos que el método removerInmueble haya sido llamado
		verify(usuarioMock).removerInmueble(inmuebleMock);
	}

	@Test
	void testSetCategorias() {
		// Creamos el SUT
		SitioWeb sitioWebMock = new SitioWeb();

		// Creamos la lista de categorías mockeadas
		List<Categoria> categorias = List.of(mock(Categoria.class), mock(Categoria.class));

		// Llamamos al método setCategorias
		sitioWebMock.setCategorias(categorias);

		// Verificamos que las categorías se hayan asignado correctamente
		assertEquals(categorias, sitioWebMock.getCategorias());
	}

}
