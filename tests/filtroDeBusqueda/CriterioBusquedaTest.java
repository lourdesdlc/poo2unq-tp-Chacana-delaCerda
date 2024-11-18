package filtroDeBusqueda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Inmueble.Inmueble;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiltroDeBusquedaTest {

    private Inmueble inmueble;

    @BeforeEach
    void setUp() {
        inmueble = mock(Inmueble.class);
    }

    @Test
    void testFiltroPorCantidadHuespedes_Cumple() {
        when(inmueble.getCapacidad()).thenReturn(4);
        FiltroPorCantidadHuespedes filtro = new FiltroPorCantidadHuespedes(3);
        assertTrue(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorCantidadHuespedes_NoCumple() {
        when(inmueble.getCapacidad()).thenReturn(2);
        FiltroPorCantidadHuespedes filtro = new FiltroPorCantidadHuespedes(3);
        assertFalse(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorCiudad_Cumple() {
        when(inmueble.getCiudad()).thenReturn("Buenos Aires");
        FiltroPorCiudad filtro = new FiltroPorCiudad("Buenos Aires");
        assertTrue(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorCiudad_NoCumple() {
        when(inmueble.getCiudad()).thenReturn("Rosario");
        FiltroPorCiudad filtro = new FiltroPorCiudad("Buenos Aires");
        assertFalse(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorPrecioMaximo_Cumple() {
        when(inmueble.getPrecio(any(), any())).thenReturn(500.0);
        FiltroPorPrecioMaximo filtro = new FiltroPorPrecioMaximo(600.0, LocalDate.now(), LocalDate.now().plusDays(5));
        assertTrue(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorPrecioMaximo_NoCumple() {
        when(inmueble.getPrecio(any(), any())).thenReturn(800.0);
        FiltroPorPrecioMaximo filtro = new FiltroPorPrecioMaximo(600.0, LocalDate.now(), LocalDate.now().plusDays(5));
        assertFalse(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorPrecioMinimo_Cumple() {
        when(inmueble.getPrecio(any(), any())).thenReturn(800.0);
        FiltroPorPrecioMinimo filtro = new FiltroPorPrecioMinimo(700.0, LocalDate.now(), LocalDate.now().plusDays(5));
        assertTrue(filtro.cumple(inmueble));
    }

    @Test
    void testFiltroPorPrecioMinimo_NoCumple() {
        when(inmueble.getPrecio(any(), any())).thenReturn(500.0);
        FiltroPorPrecioMinimo filtro = new FiltroPorPrecioMinimo(600.0, LocalDate.now(), LocalDate.now().plusDays(5));
        assertFalse(filtro.cumple(inmueble));
    }

    @Test
    void testCriterioBusqueda_CumpleTodosLosFiltros() {
        Inmueble inmueble = mock(Inmueble.class);
        when(inmueble.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble.getCapacidad()).thenReturn(4);

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusDays(5);
        when(inmueble.getPrecio(any(), any())).thenReturn(500.0);

        CriterioBusqueda criterio = new CriterioBusqueda("Buenos Aires", fechaInicio, fechaFin)
            .agregarFiltro(new FiltroPorCantidadHuespedes(3))
            .agregarFiltro(new FiltroPorPrecioMaximo(600.0, fechaInicio, fechaFin));

        assertTrue(criterio.cumple(inmueble));
    }


    @Test
    void testCriterioBusqueda_NoCumpleUnFiltro() {
        when(inmueble.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble.getCapacidad()).thenReturn(4);

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusDays(5);
        when(inmueble.getPrecio(any(), any())).thenReturn(700.0);

        CriterioBusqueda criterio = new CriterioBusqueda("Buenos Aires", fechaInicio, fechaFin)
            .agregarFiltro(new FiltroPorCantidadHuespedes(3))
            .agregarFiltro(new FiltroPorPrecioMaximo(600.0, fechaInicio, fechaFin));

        assertFalse(criterio.cumple(inmueble));
    }
}
