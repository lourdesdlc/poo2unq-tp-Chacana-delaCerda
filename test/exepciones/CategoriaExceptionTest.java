package exepciones;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CategoriaExceptionTest {

    @Test
    void testCategoriaExceptionMensaje() {
        String mensaje = "Error de categoría";
        CategoriaException exception = assertThrows(CategoriaException.class, () -> {
            throw new CategoriaException(mensaje);
        });

        assertEquals(mensaje, exception.getMessage(), "El mensaje de la excepción debe coincidir");
    }
}
