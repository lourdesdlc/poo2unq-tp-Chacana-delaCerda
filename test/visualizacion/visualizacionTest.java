package visualizacion;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inmueble.Inmueble;
import ranking.Categoria;
import sitioWeb.SitioWeb;
import usuario.Usuario;
import java.util.List;

class VisualizacionTest {
	
	private Inmueble inmueble1Mock;
	private Usuario propietarioMock;
	private SitioWeb web;
	@BeforeEach
	void setup() {
		// SUT
		web = new SitioWeb();
		inmueble1Mock = mock(Inmueble.class);
		propietarioMock = mock(Usuario.class);
		when(inmueble1Mock.getComentarios()).thenReturn(List.of("Amplio", "Moderno", "Cómodo"));
		when(inmueble1Mock.getPuntajePromedio()).thenReturn(4.7);
		when(inmueble1Mock.getPuntajePromedioEnCategoria(any(Categoria.class))).thenReturn(4.5);
		when(inmueble1Mock.getPropietario()).thenReturn(propietarioMock);
		when(propietarioMock.getPuntajePromedio()).thenReturn(4.9);
		when(propietarioMock.getAntiguedad()).thenReturn(5);
		when(propietarioMock.cantidadDeAlquileres()).thenReturn(20);
		when(inmueble1Mock.cantidadDeAlquileres()).thenReturn(12);
		Inmueble casaA = mock(Inmueble.class);
		Inmueble departamentoB = mock(Inmueble.class);
		
		
		when(casaA.toString()).thenReturn("Casa A");
		when(departamentoB.toString()).thenReturn("Departamento B");
		when(propietarioMock.inmueblesAlquilados()).thenReturn(List.of(casaA, departamentoB));
	}
	@Test
	void testMostrarDatosDe() {
		
		String resultado = web.mostrarDatosDe(inmueble1Mock);
		
		System.out.println("Datos del Inmueble:");
		System.out.println(resultado);
		
		String esperado = """
				Comentarios del inmueble:
				- Amplio
				- Moderno
				- Cómodo
				Puntaje promedio: 4.7
				Puntaje promedio en categoría: 4.5
				""";
		assertEquals(esperado, resultado);
	}
	@Test
	void testMostrarDatosDelPropietarioDe() {
		
		String resultado = web.mostrarDatosDelPropietarioDe(inmueble1Mock);
		
		System.out.println("Datos del Propietario:");
		System.out.println(resultado);
		
		String esperado = """
				Propietario del inmueble:
				Puntaje promedio del propietario: 4.9
				Antigüedad del propietario: 5 años
				Cantidad de alquileres realizados por el propietario: 20
				Cantidad de alquileres de este inmueble: 12
				Inmuebles alquilados por el propietario:
				- Casa A
				- Departamento B
				""";
		assertEquals(esperado, resultado);
	}
}