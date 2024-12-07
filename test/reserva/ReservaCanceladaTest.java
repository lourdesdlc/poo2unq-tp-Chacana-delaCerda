package reserva;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservaCanceladaTest {

    @Test
    void testEsCancelada() {
        ReservaCancelada reservaCancelada = new ReservaCancelada();
        assertTrue(reservaCancelada.esCancelada(), "La reserva debería estar cancelada");
    }
}
