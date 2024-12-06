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
		// Configurar el mock del inmueble
		when(mockInmueble.getNombreDeTipoInmueble()).thenReturn("departamento");

		// Ejecutar el método
		appMobile.notificarCancelacionReserva("Cancelación", mockInmueble);

		// Verificar que se llama al popUp con el mensaje correcto
		String expectedMessage = "El/la departamento que te interesa se ha liberado! Corre a reservarlo!";
		verify(mockPopUpWindow).popUp(expectedMessage, "rojo", 14);
	}

	@Test
	void testNotificadorCelularEsInvocado() {
		// Configurar el mock del inmueble
		when(mockInmueble.getNombreDeTipoInmueble()).thenReturn("casa");

		// Ejecutar el método
		appMobile.notificarCancelacionReserva("Cancelación", mockInmueble);

		// Verificar que el método popUp fue invocado al menos una vez
		verify(mockPopUpWindow, times(1)).popUp(anyString(), eq("rojo"), eq(14));
	}
	
	@Test
	void testGetNotificadorCelularDevuelveElNotificadorConfigurado() {
	    // Configurar el objeto bajo prueba
	    PopUpWindow mockPopUpWindow = mock(PopUpWindow.class);
	    appMobile.setNotificadorCelular(mockPopUpWindow);

	    // Verificar que el getter devuelve el objeto configurado
	    assertEquals(mockPopUpWindow, appMobile.getNotificadorCelular(), 
	                 "El getter debería devolver el mismo objeto que se configuró.");
	}

}
