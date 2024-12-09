package filtroDeBusqueda;

import inmueble.Inmueble;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class FiltroPorFechaTest {

	private FiltroPorFecha filtro;
    private Inmueble inmuebleMock;

    @BeforeEach
    void setUp() {
        inmuebleMock = mock(Inmueble.class); 
    }

    @Test
    void testCumpleConDisponibilidad() {
    	
    	LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
    	LocalDate fechaSalida = LocalDate.of(2024, 12, 10);
    	
    	when(inmuebleMock.estaDisponibleParaLasFechas(fechaEntrada, fechaSalida)).thenReturn(true);
    	
    	filtro = new FiltroPorFecha(fechaEntrada, fechaSalida);
    	
    	assertTrue(filtro.cumple(inmuebleMock));
    	verify(inmuebleMock).estaDisponibleParaLasFechas(fechaEntrada, fechaSalida);
    }
    
    @Test
    void testNoCumplePorFaltaDeDisponibilidad() {
    	
    	LocalDate fechaEntrada = LocalDate.of(2024, 12, 1);
    	LocalDate fechaSalida = LocalDate.of(2024, 12, 10);
    	
    	when(inmuebleMock.estaDisponibleParaLasFechas(fechaEntrada, fechaSalida)).thenReturn(false);
    	
    	filtro = new FiltroPorFecha(fechaEntrada, fechaSalida);
    	
    	assertFalse(filtro.cumple(inmuebleMock));
    	verify(inmuebleMock).estaDisponibleParaLasFechas(fechaEntrada, fechaSalida);
    }
	
    @Test
    void testCumple() {
    	FiltroPorFecha filtro = new FiltroPorFecha(LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15));
    	Inmueble inmuebleMock = mock(Inmueble.class);
    	
    	when(inmuebleMock.estaDisponibleParaLasFechas(any(LocalDate.class), any(LocalDate.class))).thenReturn(true);
    	
    	assertTrue(filtro.cumple(inmuebleMock),
    			"El filtro debe cumplirse cuando el inmueble está disponible en el rango de fechas");
    }
    
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
	
}
