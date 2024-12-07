package exepciones;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PuntajeExceptionTest {

    @Test
    void testPuntajeExceptionMensaje() {
        String mensaje = "Error de puntaje";
        PuntajeException exception = assertThrows(PuntajeException.class, () -> {
            throw new PuntajeException(mensaje);
        });

        assertEquals(mensaje, exception.getMessage(), "El mensaje de la excepción debe coincidir");
    }
}
