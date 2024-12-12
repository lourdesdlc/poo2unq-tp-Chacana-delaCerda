package observer;

import inmueble.Inmueble;
import notificaciones.PopUpWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AppMobileTest {

	private AppMobile appMobile;

	@Mock
	private PopUpWindow mockPopUpWindow;

	@Mock
	private Inmueble mockInmueble;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		appMobile = new AppMobile();
		appMobile.setNotificadorCelular(mockPopUpWindow);
		appMobile.setColor("rojo");
		appMobile.setFront(14);
	}

	@Test
	void testNotificarCancelacionReserva() {
		when(mockInmueble.getNombreDeTipoInmueble()).thenReturn("departamento");

		appMobile.notificarCancelacionReserva("Cancelación", mockInmueble);

		String mensajeEsperado = "El/la departamento que te interesa se ha liberado! Corre a reservarlo!";
		verify(mockPopUpWindow).popUp(mensajeEsperado, "rojo", 14);
	}

	@Test
	void testNotificadorCelularEsInvocado() {
		when(mockInmueble.getNombreDeTipoInmueble()).thenReturn("casa");

		appMobile.notificarCancelacionReserva("Cancelación", mockInmueble);

		verify(mockPopUpWindow, times(1)).popUp(anyString(), eq("rojo"), eq(14));
	}
	
	@Test
	void testGetNotificadorCelularDevuelveElNotificadorConfigurado() {

	    PopUpWindow mockPopUpWindow = mock(PopUpWindow.class);
	    appMobile.setNotificadorCelular(mockPopUpWindow);

	    assertEquals(mockPopUpWindow, appMobile.getNotificadorCelular(), 
	                 "El getter debería devolver el mismo objeto que se configuró.");
	}

}
