package exepciones;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FiltroExceptionTest {

    @Test
    void testFiltroExceptionMensaje() {
        String mensaje = "Error de filtro";
        FiltroException exception = assertThrows(FiltroException.class, () -> {
            throw new FiltroException(mensaje);
        });

        assertEquals(mensaje, exception.getMessage(), "El mensaje de la excepción debe coincidir");
    }
}
