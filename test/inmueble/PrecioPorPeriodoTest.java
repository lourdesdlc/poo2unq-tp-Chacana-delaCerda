package inmueble;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.time.LocalDate;


class PrecioPorPeriodoTest {

    @Test
    void testIncluye() {
        LocalDate fechaInicio = LocalDate.of(2024, 11, 1);
        LocalDate fechaFin = LocalDate.of(2024, 11, 30);
        PrecioPorPeriodo periodo = new PrecioPorPeriodo(fechaInicio, fechaFin, 100.0, "Periodo 1");

        assertTrue(periodo.incluye(LocalDate.of(2024, 11, 1)));  
        assertTrue(periodo.incluye(LocalDate.of(2024, 11, 15)));
        assertTrue(periodo.incluye(LocalDate.of(2024, 11, 30))); 
        assertFalse(periodo.incluye(LocalDate.of(2024, 10, 31))); 
        assertFalse(periodo.incluye(LocalDate.of(2024, 12, 1)));  
    }

    @Test
    void testInterfiereCon() {
        LocalDate fechaInicio1 = LocalDate.of(2024, 11, 1);
        LocalDate fechaFin1 = LocalDate.of(2024, 11, 30);
        PrecioPorPeriodo periodo1 = new PrecioPorPeriodo(fechaInicio1, fechaFin1, 100.0, "Periodo 1");

        LocalDate fechaInicio2 = LocalDate.of(2024, 11, 15);
        LocalDate fechaFin2 = LocalDate.of(2024, 12, 15);
        PrecioPorPeriodo periodo2 = new PrecioPorPeriodo(fechaInicio2, fechaFin2, 120.0, "Periodo 2");

        LocalDate fechaInicio3 = LocalDate.of(2024, 12, 1);
        LocalDate fechaFin3 = LocalDate.of(2024, 12, 31);
        PrecioPorPeriodo periodo3 = new PrecioPorPeriodo(fechaInicio3, fechaFin3, 150.0, "Periodo 3");

        assertTrue(periodo1.interfiereCon(periodo2)); 
        assertTrue(periodo2.interfiereCon(periodo1)); 

        assertFalse(periodo1.interfiereCon(periodo3));
        assertTrue(periodo2.interfiereCon(periodo3)); 
    }
}

