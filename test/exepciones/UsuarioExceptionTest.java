package exepciones;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UsuarioExceptionTest {

    @Test
    void testUsuarioExceptionMensaje() {
        String mensaje = "Error de usuario";
        UsuarioException exception = assertThrows(UsuarioException.class, () -> {
            throw new UsuarioException(mensaje);
        });

        assertEquals(mensaje, exception.getMessage(), "El mensaje de la excepción debe coincidir");
    }
}
